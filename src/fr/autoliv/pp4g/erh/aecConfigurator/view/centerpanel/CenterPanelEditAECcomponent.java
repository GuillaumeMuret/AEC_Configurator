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
	Class of the Panel edition where the edition screen is displayed and the 
	save is made
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.aec.AECcontroller;
import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECstructure;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.PanelEditAECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelPanel;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelTextField;

public class CenterPanelEditAECcomponent extends ModelPanel{

	/**
	 * the serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The setting panel
	 */
	private PanelEditAECcomponent panelEditAECcomponent;
	
	/**
	 * The main frame : where all the component are displayed
	 */
	private MainFrame mainFrame;
	
	/**
	 * Constructor of the setting panel
	 * @param mainFrame
	 */
	public CenterPanelEditAECcomponent(MainFrame mainFrame){
		super(ConstantDimension.PANEL_CENTER,true,new BorderLayout(),ModelPanel.PANEL_CENTER);
		this.mainFrame = mainFrame;
		this.panelEditAECcomponent = new PanelEditAECcomponent(this.mainFrame);
		this.add(this.panelEditAECcomponent);
	}
	
	/**
	 * Process called to set the AEC title
	 * @param tField
	 * @param aec
	 * @return
	 */
	private boolean setAECtitle(ModelTextField tField,AECcomponent aec){
		int currentAECkey = DataStore.getInstance().getAECkey();
		if(AECcontroller.analyseTextFieldVariableText(tField)){
			return true;
		}else{
			aec.setComponentTitle(tField.getText());
			// Change the label name list
			DataStore.getInstance().getAEClabelList().get(currentAECkey).setText(tField.getText());
			DialogStore.getInstance().getMainFrame().centerPanelEditAECvalue.getPanelAEClist().remakeLabelList();
			return false;
		}
	}
	
	/**
	 * Process called to set the AEC attribute description
	 * @param tField
	 * @param aec
	 * @return
	 */
	private boolean setAECattributeDescription(ModelTextField tField,ModelTextField descr,AECcomponent aec){
		int currentParamKey = DataStore.getInstance().getAECattributeKey();
		
		aec.setParamDescriptionDescription(currentParamKey,descr.getText());
		if(AECcontroller.analyseTextFieldText(tField)){
			return true;
		}
		aec.setParamDescription(currentParamKey,tField.getText());
		return false;
	}
	
	/**
	 * Process called to set the attribute value
	 * @param tField
	 * @param aec
	 * @return
	 */
	private boolean setAECattributeValue(ModelTextField tField,ModelTextField descr,AECcomponent aec,int offset){
		int currentParamKey = DataStore.getInstance().getAECattributeKey();
		aec.setParamValueDescription(currentParamKey,descr.getText());

		if(offset==0){
			AECstructure aecStruct = DataStore.getInstance().getAECstructureList().get(currentParamKey);
	
			if(AECcontroller.analyseTextFieldPositiveNumber(tField,aecStruct.getAttributeSize()*2)){
				return true;
			}else{
				aec.setParamValue(currentParamKey,Integer.valueOf(tField.getText()));
				return false;
			}
		}else{
			aec.setParamValue(currentParamKey,this.panelEditAECcomponent.getComboBoxGroup().getSelectedIndex());
			return false;
		}
	}
	
	/**
	 * Process called to set the aec attribute unit
	 * @param tField
	 * @param aec
	 * @return
	 */
	private boolean setAECattributeUnit(ModelTextField tField,ModelTextField descr,AECcomponent aec){
		int currentParamKey = DataStore.getInstance().getAECattributeKey();
		aec.setParamUnitDescription(currentParamKey,descr.getText());
		if(AECcontroller.analyseTextFieldText(tField)){
			return true;
		}else{
			aec.setParamUnit(currentParamKey,tField.getText());
			return false;
		}
	}
	
	/**
	 * 
	 * @param tField
	 * @param descr
	 * @param aec
	 * @return
	 */
	private boolean setAECname(ModelTextField tField,ModelTextField descr,AECcomponent aec){
		int currentParamKey = DataStore.getInstance().getAECattributeKey();

		aec.setParamNameDescription(currentParamKey,descr.getText());
		if(AECcontroller.analyseTextFieldText(tField)){
			return true;
		}else{
			aec.setParamName(currentParamKey,tField.getText());
			return false;
		}
	}
	
	/**
	 * Process called to set the AEC scaling factor
	 * @param currentParam
	 * @param tField
	 * @param descr
	 * @param aec
	 * @return
	 */
	private boolean setAECscalingFactor(int currentParam,ModelTextField tField,ModelTextField descr,AECcomponent aec){
		aec.setParamScalingFactorDescription(currentParam,descr.getText());
		if(AECcontroller.analyseTextFieldDecimalNumber(tField)){
			return true;
		}else{
			aec.setParamScalingFactor(currentParam,Integer.valueOf(tField.getText()));
			return false;
		}
	}
	
	/**
	 * Process called to set the AEC scaling offset
	 * @param currentParam
	 * @param tField
	 * @param descr
	 * @param aec
	 * @return
	 */
	private boolean setAECscalingOffset(int currentParam,ModelTextField tField,ModelTextField descr,AECcomponent aec){
		aec.setParamScalingOffsetDescription(currentParam,descr.getText());
		if(AECcontroller.analyseTextFieldDecimalNumber(tField)){
			return true;
		}else{
			aec.setParamScalingOffset(currentParam,Integer.valueOf(tField.getText()));
			return false;
		}
	}
	
	/**
	 * Process called to set the interpreted value of the current attribute
	 * @param error
	 * @param currentParam
	 * @param tField
	 * @param descr
	 * @param aec
	 */
	private void setAECinterpretedValue(boolean error,int currentParam,ModelTextField tField,ModelTextField descr,AECcomponent aec){
		aec.setParamInterpretedValueDescription(currentParam,descr.getText());
		if(error){
			tField.setText("ERROR");
		}else{
			int value = aec.getAECattributes().get(currentParam).getAttributeValue();
			int scalingFactor = aec.getAECattributes().get(0).getAttributeScalingFactor();
			int scalingOffset = aec.getAECattributes().get(0).getAttributeScalingOffset();
			aec.setParamInterpretedValue(currentParam,value*scalingFactor+scalingOffset);	
		}
	}
	
	/**
	 * Process called to save the configuration of the parameter
	 * @throws AECexception
	 */
	public void saveEditionAEC() throws AECexception{
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();

		ArrayList<ModelTextField> listInputInfo = this.panelEditAECcomponent.getListInputInfo();
		ArrayList<ModelTextField> listInputParam = this.panelEditAECcomponent.getListInputParam();
		ArrayList<ModelTextField> listInputDescr = this.panelEditAECcomponent.getListInputDescription();
		
		int currentAECkey = DataStore.getInstance().getAECkey();
		int currentParamKey = DataStore.getInstance().getAECattributeKey();

		AECcomponent aec = aecList.get(currentAECkey);
		
		boolean error = false;
		
		// offset used if the attribute is a group
		int offset = 0;
		if(aec.getAECattributes().get(currentParamKey).getAttributeName().toUpperCase().equals("GROUP")){
			offset++;
		}
		
		// set AEC title
		error |= this.setAECtitle(listInputInfo.get(0),aec);
		
		// set AEC attribute description
		error |= this.setAECattributeDescription(listInputParam.get(1),listInputDescr.get(1),aec);
		
		// set AEC value
		if(!listInputParam.get(2).getText().contains("Padding byte value"))
			error |= this.setAECattributeValue(listInputParam.get(2),listInputDescr.get(2),aec,offset);

		// set AEC unit
		error |= this.setAECattributeUnit(listInputParam.get(3-offset),listInputDescr.get(3),aec);
		
		// For all of this AEC's attribute
		for(int i=0;i<aecList.size();i++){
			AECcomponent anAEC = aecList.get(i);
			
			// set AEC name
			error|=this.setAECname(listInputParam.get(0),listInputDescr.get(0),anAEC);

			// For all of the AEC's attribute
			for(int j=0;j<aecList.get(i).getAECattributes().size();j++){
				
				// set AEC scaling factor
				error|=this.setAECscalingFactor(j,listInputParam.get(4-offset),listInputDescr.get(4),anAEC);
				
				// set AEC scaling offset
				error|=this.setAECscalingOffset(j,listInputParam.get(5-offset),listInputDescr.get(5),anAEC);
				
				// set AEC interpreted value
				this.setAECinterpretedValue(error,j,listInputParam.get(6-offset),listInputDescr.get(6),anAEC);
			}
		}
		if(error){
			throw new AECexception("Bonjour", AECexception.TEXT_FIELD_EXCEPTION);
		}
	}
	
	/**
	 * Process called when the user want to set more precisely the NVP component
	 */
	public void setPanelEditAEC(){
		this.panelEditAECcomponent.setPanelEditAEC();
	}
	
	/**
	 * Getter of the panel edit AEC component
	 * @return
	 */
	public PanelEditAECcomponent getPanelEditAEC(){
		return this.panelEditAECcomponent;
	}
}
