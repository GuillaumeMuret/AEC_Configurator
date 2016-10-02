/******************************************************************************

AUTOLIV ELECTRONIC document.

-------------------------------------

Copyright Autoliv Inc. All rights reserved.

*******************************************************************************
JAVA-File project AEC_Configurator
******************************************************************************/
/* PRQA S 0288 ++ */
/*
 * Explanation:
 *    see @details
 */
/*
$Revision: 1.0 $
$ProjectName: ?? $
*/
/* PRQA S 0288 -- */
/*!****************************************************************************

@details
	This class has the process which analyze the memory, the text field, and execute 
	some action like add an AEC, delete an AEC, sort the attribute by size etc.
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.aec;

import java.util.ArrayList;

import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECattribute;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECstructure;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.LabelAEC;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.PanelConfiguration.ModelPanelStructure;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelTextField;

public class AECcontroller {
	
	/**
	 * Process called to delete a parameter of a AEC component
	 */
	public static void deleteAECattribute(){			
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		ArrayList<AECstructure> structList = DataStore.getInstance().getAECstructureList();

		int currentAttribute = DataStore.getInstance().getAECattributeKey();

		if(DialogStore.getInstance().displayDeleteAttributeConfirmation()){
			structList.remove(currentAttribute);
			
			for(int j = 0;j<aecList.size();j++){
				AECcomponent aec=aecList.get(j);
				for(int i=currentAttribute;i<aec.getAECattributes().size()-1;i++){
					aec.getAECattributes().set(i,aec.getAECattributes().get(i+1));
				}
				aec.getAECattributes().remove(aec.getAECattributes().size()-1);
			}			
		}
		// Change the list of AEC component
		DataStore.getInstance().setAEClist(aecList);
		if(currentAttribute==aecList.get(0).getAECattributes().size()){
			DataStore.getInstance().setAECattributeKey(currentAttribute-1);
		}
	}
	
	/**
	 * Process called to add a new parameter to the AEC component parameter list
	 */
	public static void addAECattribute() throws AECexception{
		AECstructure newAECstructure = DialogStore.getInstance().displayAddStructureParameter();
		if(newAECstructure!=null){
			AECattribute newAECattribute = DialogStore.getInstance().displayDefineAllComponentDefaultValue(newAECstructure.getAttributeSize());
			if(newAECattribute!=null){
				ArrayList<AECstructure> list = DataStore.getInstance().getAECstructureList();
				list.add(0,newAECstructure);
				DialogStore.getInstance().displaySuccessCreationAttribute();
				addTheNewAttribute(newAECattribute);
			}else{
				throw new AECexception("nothing to do",AECexception.NOTHING_TO_DO);
			}
		}else{
			throw new AECexception("nothing to do",AECexception.NOTHING_TO_DO);
		}
	}
	
	/**
	 * process called to add a new parameter for all the AEC component
	 * @param newAECattr
	 */
	private static void addTheNewAttribute(AECattribute newAECattr){
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		
		newAECattr.setAttributeNameDescription("/");
		newAECattr.setAttributeDescriptionDescription("/");
		newAECattr.setAttributeUnitDescription("/");
		newAECattr.setAttributeValueDescription("/");
		
		newAECattr.setAttributeScalingFactor(aecList.get(0).getAECattributes().get(0).getAttributeScalingFactor());
		newAECattr.setAttributeScalingFactorDescription(aecList.get(0).getAECattributes().get(0).getAttributeScalingFactorDescription());
		newAECattr.setAttributeScalingOffset(aecList.get(0).getAECattributes().get(0).getAttributeScalingOffset());
		newAECattr.setAttributeScalingOffsetDescription(aecList.get(0).getAECattributes().get(0).getAttributeScalingOffsetDescription());
		newAECattr.setAttributeInterpretedValue(
			newAECattr.getAttributeValue()*newAECattr.getAttributeScalingFactor()+newAECattr.getAttributeScalingOffset()
		);
		newAECattr.setAttributeInterpretedValueDescription(aecList.get(0).getAECattributes().get(0).getAttributeInterpretedValueDescription());
		
		for(int j=0;j<aecList.size();j++){
			AECattribute aecAttr = new AECattribute(newAECattr);
			AECcomponent aecComponent = aecList.get(j);
			aecComponent.getAECattributes().add(0,aecAttr);
		}
		DataStore.getInstance().setAECattributeKey(0);
		sortAttributeBySize();
	}
	
	/**
	 * Process called to sort the attribute by decreasing allocation size
	 */
	public static void sortAttributeBySize(){
		ArrayList<AECstructure> structList = DataStore.getInstance().getAECstructureList();
		int attrKey = DataStore.getInstance().getAECattributeKey();
		for(attrKey=0;attrKey<structList.size()-1;attrKey++){
			if(structList.get(attrKey).getAttributeSize()<structList.get(attrKey+1).getAttributeSize()){
				changeAECattributePosition(+1,true);
			}
		}
		refreshScreen();
	}
	
	/**
	 * Process called to change the AEC attribute position
	 * @param pos
	 * @param forceToChange
	 */
    public static void changeAECattributePosition(int pos,boolean forceToChange){
    	ArrayList<AECstructure> structList = DataStore.getInstance().getAECstructureList();
    	int currentEditAttr = DataStore.getInstance().getAECattributeKey();
    	if((pos==-1&&currentEditAttr>0)||(pos==1&&currentEditAttr<structList.size()-1)){
    		if(forceToChange){
	    		switchAECattributePosition(pos);
    		}else{
    			if(structList.get(currentEditAttr).getAttributeSize()==structList.get(currentEditAttr+pos).getAttributeSize()){
    				switchAECattributePosition(pos);
    			}
    		}
    	}
    	refreshScreen();
    }
    
    /**
     * Process called to switch the AEC attribute position
     * @param pos
     */
    private static void switchAECattributePosition(int pos){
    	ArrayList<AECstructure> structList = DataStore.getInstance().getAECstructureList();
    	ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
    	int currentEditAttr = DataStore.getInstance().getAECattributeKey();
		AECstructure tmp1 = structList.get(currentEditAttr);
		structList.set(currentEditAttr, structList.get(currentEditAttr+pos));
		structList.set(currentEditAttr+pos, tmp1);
		
		for(int i=0;i<aecList.size();i++){
			ArrayList<AECattribute> paramList = aecList.get(i).getAECattributes();
			AECattribute tmp2 = paramList.get(currentEditAttr);
			paramList.set(currentEditAttr, paramList.get(currentEditAttr+pos));
			paramList.set(currentEditAttr+pos, tmp2);
		}
		DialogStore.getInstance().getMainFrame().centerPanelConfiguration.getPanelConfiguration().setStructurePanelPosition(pos);
		DataStore.getInstance().setAECattributeKey(currentEditAttr+pos);
    }
    
    /**
     * Process called to sort the attribute at the current attribute position
     * @param numOfTheAttribute
     * @param theSelectedIndex
     */
    public static void sortThisAttribute(int numOfTheAttribute){
    	ArrayList<AECstructure> structList = DataStore.getInstance().getAECstructureList();
    	DataStore.getInstance().setAECattributeKey(numOfTheAttribute);
		for(int i=0;i<structList.size();i++){
			changeAECattributePosition(-1,true);
		}
		sortAttributeBySize();
    }
    
    /**
     * Process called to analyse the memory space
     */
    public static void analyseMemorySpace() throws AECexception{
    	calculAECcalibrationSize();
    	if(DataStore.getInstance().getAECcalibrationMaxSize()<DataStore.getInstance().getAECcalibrationSize()){
    		DialogStore.getInstance().getMainFrame().centerPanelConfiguration.getPanelConfiguration().getAECcalibrationSizePanel().setOpaque(true);
    		DialogStore.getInstance().getMainFrame().centerPanelConfiguration.getPanelConfiguration().getAECcalibrationSizePanel().setBackground(ColorStore.getInstance().getColorError());
    		DialogStore.getInstance().getMainFrame().centerPanelConfiguration.getPanelConfiguration().getAECcalibrationSizePanel().repaint();
    		throw new AECexception("size over", AECexception.AEC_CALIBRATION_SIZE);
		}else{
			DialogStore.getInstance().getMainFrame().centerPanelConfiguration.getPanelConfiguration().getAECcalibrationSizePanel().setOpaque(false);
			DialogStore.getInstance().getMainFrame().centerPanelConfiguration.getPanelConfiguration().getAECcalibrationSizePanel().repaint();
		}
    }
    
    /**
     * Process called to analyse the memory space if an AEC is added
     */
    public static void analyseMemorySpaceAddingAEC() throws AECexception{
		ArrayList<AECstructure> aecStruct = DataStore.getInstance().getAECstructureList();
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		int dataSize = 0;
		// Calcul data size
		for(int i=0;i<aecStruct.size();i++){
			dataSize+=aecStruct.get(i).getAttributeSize();
		}
		Integer aecCalibrationSize = dataSize*(aecList.size()+1);
		if(aecCalibrationSize>DataStore.getInstance().getAECcalibrationMaxSize()){
			throw new AECexception("size over", AECexception.ADDING_AEC_CALIBRATION_SIZE);
		}
    }
    
    /**
     * Process called to calculate the current AEC calibration size
     */
    public static void calculAECcalibrationSize(){
		ArrayList<AECstructure> aecStruct = DataStore.getInstance().getAECstructureList();
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		int dataSize = 0;
		for(int i=0;i<aecStruct.size();i++){
			dataSize+=aecStruct.get(i).getAttributeSize();
		}
		DataStore.getInstance().setAECcalibrationSize(dataSize*aecList.size());
    }
    
    /**
     * Process called to analyse the attribute space
     */
    public static void analyseAttributeSpace() throws AECexception{
    	ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
    	ArrayList<AECstructure> structList = DataStore.getInstance().getAECstructureList();
    	for(int i=0;i<aecList.size();i++){
    		AECcomponent aec = aecList.get(i);
    		for(int j=0;j<aec.getAECattributes().size();j++){
    			AECattribute aecAttr = aec.getAECattributes().get(j);
    			AECstructure aecStruct = structList.get(j);
    			if(Integer.toHexString(aecAttr.getAttributeValue()).length()>aecStruct.getAttributeSize()*2){
    				throw new AECexception("", AECexception.AEC_ATTRIBUTE_SIZE);
    			}
    		}
    	}
    }
    
    /**
     * Process called to analyze if the AEC list has 2 AEC names equal
     */
    public static void analyseAECdoubleName() throws AECexception{
    	ArrayList<LabelAEC> aecLabelList = DataStore.getInstance().getAEClabelList();
    	for(int i=0;i<aecLabelList.size();i++){
    		for(int j=0;j<aecLabelList.size();j++){
    			if(i!=j){
    				if(aecLabelList.get(i).getText().equals(aecLabelList.get(j).getText())){
    					AECselected(i);
    					aecLabelList.get(i).setForeground(ColorStore.getInstance().getColorError());
    					aecLabelList.get(j).setForeground(ColorStore.getInstance().getColorError());
    					DataStore.getInstance().setAECkey(i);
    					throw new AECexception("double name", AECexception.DOUBLE_AEC_NAME);
    				}
    			}
    		}
    	}
    }
    
    /**
     * Process called to analyze if the AEC list has 2 AEC names equal
     */
    public static void analyseAECattributeDoubleName() throws AECexception{
    	ArrayList<ModelPanelStructure> listPanelStruct = 
    		DialogStore.getInstance().getMainFrame().centerPanelConfiguration.getPanelConfiguration().getListStructurePanel();
    	for(int i=0;i<listPanelStruct.size();i++){
    		for(int j=0;j<listPanelStruct.size();j++){
    			if(i!=j){
    				if(listPanelStruct.get(i).inputAttributeName.getText().equals(listPanelStruct.get(j).inputAttributeName.getText())){
    					listPanelStruct.get(i).inputAttributeName.setColor(ColorStore.getInstance().getColorError(),ColorStore.getInstance().getColorError());
    					listPanelStruct.get(j).inputAttributeName.setColor(ColorStore.getInstance().getColorError(),ColorStore.getInstance().getColorError());
    					DataStore.getInstance().setAECkey(i);
    					throw new AECexception("double name", AECexception.DOUBLE_AEC_ATTRIBUTE_NAME);
    				}
    			}
    		}
    	}
    }
    
    /**
     * Process called to analyze the AEC structure list
     */
    public static void analyseAECreservedByteOrder() throws AECexception{
    	boolean haveToBeReserved=false;
    	boolean error=false;
    	ArrayList<AECstructure> structList = DataStore.getInstance().getAECstructureList();
    	for(int i=0;i<structList.size();i++){
    		if(structList.get(i).getAttributeName().length()>=9){
	    		if(structList.get(i).getAttributeName().substring(0, 9).toUpperCase().equals("U8RESERVE")){
	    			haveToBeReserved=true;
	    		}
    		}
    		if(haveToBeReserved){
    			if(structList.get(i).getAttributeName().length()>=9){
	    			if(!structList.get(i).getAttributeName().substring(0, 9).toUpperCase().equals("U8RESERVE")){
						DataStore.getInstance().setAECattributeKey(i);
	    				error=true;
	    			}
    			}else{
    				DataStore.getInstance().setAECattributeKey(i);
    				error = true;
    			}
    		}
    	}
    	if(error){
    		throw new AECexception("reserved not in the right order", AECexception.AEC_RESERVED_ATTR_NOT_IN_THE_BOTTOM);
    	}
    }
    
    /**
     * Process called to analyze the AEC reserved order
     * @throws AECexception
     */
    public static void analyseAECreservedOrder() throws AECexception{
    	ArrayList<LabelAEC> aecLabelList = DataStore.getInstance().getAEClabelList();
    	boolean haveToBeReserved=false;
    	boolean error = false;
    	for(int i=0;i<aecLabelList.size();i++){
    		if(aecLabelList.get(i).getText().length()>=9){
	    		if(aecLabelList.get(i).getText().substring(0, 9).toUpperCase().equals("RESERVED_")){
	    			haveToBeReserved=true;
	    		}
    		}
    		if(haveToBeReserved){
    			if(aecLabelList.get(i).getText().length()>=9){
	    			if(!aecLabelList.get(i).getText().substring(0, 9).toUpperCase().equals("RESERVED_")){
						DataStore.getInstance().setAECkey(i);
						aecLabelList.get(i).setForeground(ColorStore.getInstance().getColorError());
	    				error=true;
	    			}
    			}else{
    				DataStore.getInstance().setAECkey(i);
					aecLabelList.get(i).setForeground(ColorStore.getInstance().getColorError());
    				error = true;
    			}
    		}
    	}
    	if(error){
    		throw new AECexception("reserved not in the right order", AECexception.AEC_RESERVED_NOT_IN_THE_BOTTOM);
    	}
    }
    
    /**
	 * Process called when the user select the AEC
	 */
	synchronized public static void AECselected(int currentKey){
		MainFrame mainFrame = DialogStore.getInstance().getMainFrame();
		try{
			if(	mainFrame.getFrameState()==MainFrame.STATE_EDIT_AEC||
				mainFrame.getFrameState()==MainFrame.STATE_NEW_AEC ||
				mainFrame.getFrameState()==MainFrame.STATE_EDIT_VALUE
			){
				// save calibration
				mainFrame.saveCalibration();
				
				// change the AEC key
				DataStore.getInstance().setAECkey(currentKey);
				DataStore.getInstance().setInitialLabel();

				if(	mainFrame.getFrameState()==MainFrame.STATE_EDIT_AEC||
					mainFrame.getFrameState()==MainFrame.STATE_NEW_AEC
				){
					mainFrame.changePanel(MainFrame.STATE_EDIT_AEC);
				}else{
					mainFrame.changePanel(MainFrame.STATE_EDIT_VALUE);
				}
				DataStore.getInstance().getAEClabelList().get(currentKey).setForeground(ColorStore.getInstance().getColorSelectedAEC());
			}
		}catch(AECexception aecException){
			if(aecException.getExceptionCause()==AECexception.AEC_CALIBRATION_SIZE){
				DialogStore.getInstance().displayErrorAECcalibrationSize();
			}
			if(aecException.getExceptionCause()==AECexception.TEXT_FIELD_EXCEPTION){
				DialogStore.getInstance().displayErrorTextFieldDialog();
			}
		}
	}
	
	/**
	 * Process called to analyse the text field when it should be a text
	 * @param tField
	 * @return
	 */
	public static boolean analyseTextFieldText(ModelTextField tField){
		if(tField.getText().length()==0){
			tField.setColor(ColorStore.getInstance().getColorError(), ColorStore.getInstance().getColorError());
			return true;
		}else{
			tField.setColor(ColorStore.getInstance().getColorInputBackground(), ColorStore.getInstance().getColorInputBackgroundGradient());
			return false;
		}
	}
	
	/**
	 * 
	 * @param tField
	 * @return
	 */
	public static boolean analyseAECgroupValue(ModelTextField tField,int size){
		try{
			if(Integer.parseInt(tField.getText(),16)>=0){
				if(tField.getText().length()>0){
					if(tField.getText().length()<=size){
						tField.setColor(ColorStore.getInstance().getColorInputBackground(), ColorStore.getInstance().getColorInputBackgroundGradient());
						return false;
					}
				}
			}
		}catch(Exception e){}
		tField.setColor(ColorStore.getInstance().getColorError(), ColorStore.getInstance().getColorError());
		tField.repaint();
		return true;
	}
	
	/**
	 * Process called to analyse the text field when it should be a variable text
	 * @param tField
	 * @return
	 */
	public static boolean analyseTextFieldVariableText(ModelTextField tField){
		boolean error = false;
		if(tField.getText().length()==0){
			error=true;
		}else{
			error|=!(tField.getText().substring(0,1).matches("\\w")&&!tField.getText().substring(0,1).matches("\\d"));
			error|=tField.getText().contains(" ");
			error|=!(tField.getText().substring(1).matches("\\w*")||tField.getText().substring(1).matches("\\d*"));
		}
		if(error){
			tField.setColor(ColorStore.getInstance().getColorError(), ColorStore.getInstance().getColorError());
		}else{
			tField.setColor(ColorStore.getInstance().getColorInputBackground(), ColorStore.getInstance().getColorInputBackgroundGradient());
		}
		return error;
	}
	
	/**
	 * Process called to analyse the text field when it should be a number
	 * @param tField
	 * @return
	 */
	public static boolean analyseTextFieldDecimalNumber(ModelTextField tField){
		if(tField.getText().length()==0){
			tField.setColor(ColorStore.getInstance().getColorError(),ColorStore.getInstance().getColorError());
			return true;
		}
		if(tField.getText().matches("[\\-]?[0-9]*||[\\+]?[0-9]*||[[0-9]*]")){
			tField.setColor(ColorStore.getInstance().getColorInputBackground(), ColorStore.getInstance().getColorInputBackgroundGradient());
			tField.repaint();
			return false;
		}else{
			tField.setColor(ColorStore.getInstance().getColorError(), ColorStore.getInstance().getColorError());
			tField.repaint();
			return true;
		}
	}
	
	/**
	 * Process called to analyse the text field when it should be a positive number
	 * @param tField
	 * @return
	 */
	public static boolean analyseTextFieldPositiveNumber(ModelTextField tField,int size){
		try{
			if(tField.getText().length()>0){
				if(tField.getText().matches("[0-9]*")){
					if(String.valueOf(Integer.toHexString(Integer.valueOf(tField.getText()))).length()<=size){
						tField.setColor(ColorStore.getInstance().getColorInputBackground(), ColorStore.getInstance().getColorInputBackgroundGradient());
						return false;
					}
				}
			}
		}catch(Exception e){
			
		}
		tField.setColor(ColorStore.getInstance().getColorError(), ColorStore.getInstance().getColorError());
		tField.repaint();
		return true;
	}
	
	/**
	 * Process called to analyze the attribute structure name
	 */
	public static String analyseAECattributeStructureName(AECstructure aecStruct,String tField){
		String beginString = "u"+(8*aecStruct.getAttributeSize());
		if(tField.length()>beginString.length()){
			if(tField.substring(0, beginString.length()).equals(beginString)){
				return tField;
			}
		}
		boolean response = DialogStore.getInstance().displayChangeAECattributeStructureName(tField,beginString);
		if(response){
			return beginString+tField;
		}
		return tField;
	}
	
    /**
     * Process called to refresh the screen
     */
    public static void refreshScreen(){
		if(DialogStore.getInstance().getMainFrame().getFrameState()==MainFrame.STATE_CONFIGURATION){
			DialogStore.getInstance().getMainFrame().centerPanelEditAECcomponent.setPanelEditAEC();
			DialogStore.getInstance().getMainFrame().centerPanelConfiguration.setPanelConfiguration();
		}else{
			DialogStore.getInstance().getMainFrame().centerPanelConfiguration.setPanelConfiguration();
			DialogStore.getInstance().getMainFrame().centerPanelEditAECcomponent.setPanelEditAEC();
		}
    }
    
    /**
     * Process called to refresh the screen : get the saved values
     */
    public static void refreshScreenCTRL_Z(){
    	MainFrame theFrame = DialogStore.getInstance().getMainFrame();
    	switch(theFrame.getFrameState()){
			case MainFrame.STATE_CONFIGURATION:
				theFrame.centerPanelConfiguration.refreshConfigurationPanel();
				theFrame.revalidate();
				break;
				
			case MainFrame.STATE_EDIT_VALUE:
				theFrame.centerPanelEditAECvalue.displayPanelEditValue();
				break;
				
			case MainFrame.STATE_EDIT_AEC:
				theFrame.centerPanelEditAECcomponent.setPanelEditAEC();
				break;
				
			case MainFrame.STATE_NEW_AEC:
				theFrame.centerPanelEditAECcomponent.setPanelEditAEC();
				break;
				
			case MainFrame.STATE_DETAIL_LIST:
				theFrame.centerPanelDetailList.displayPanelList();
				break;
		}
    }
}
