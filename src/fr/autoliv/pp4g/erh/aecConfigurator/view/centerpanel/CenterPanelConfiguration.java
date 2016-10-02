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
	Class of the Panel configuration where the configuration screen is displayed and the 
	save is made
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.aec.AECcontroller;
import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECstructure;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.PanelConfiguration;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelPanel;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelTextField;

public class CenterPanelConfiguration extends ModelPanel{

	/**
	 * the serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The setting panel
	 */
	private PanelConfiguration panelConfiguration;
	
	/**
	 * The main Frame
	 */
	private MainFrame mainFrame;
	
	/**
	 * Constructor of the generation configuration panel
	 * @param mainFrame
	 */
	public CenterPanelConfiguration(MainFrame mainFrame){
		super(ConstantDimension.PANEL_CENTER,true,new BorderLayout(),ModelPanel.PANEL_CENTER);
		this.mainFrame=mainFrame;
		this.displayConfigurationPanel();
	}
	
	/**
	 * Process called to add the setting panel where the generation configuration is set
	 */
	public void displayConfigurationPanel(){
		this.panelConfiguration = new PanelConfiguration(this.mainFrame);
		this.add(this.panelConfiguration,BorderLayout.CENTER);
	}
	
	/**
	 * 
	 */
	public void refreshConfigurationPanel(){
		this.remove(this.panelConfiguration);
		this.panelConfiguration = new PanelConfiguration(this.mainFrame);
		this.add(this.panelConfiguration,BorderLayout.CENTER);
	}
	
	/**
	 * Process called to set the AEC settings value
	 */
	public void saveConfiguration() throws AECexception{
		ArrayList<ModelTextField> listInput = this.panelConfiguration.getListInput();
		boolean error=false;
		error|=this.saveAttributeCalibrationMaxSize(listInput.get(0));
		error|=this.saveAttributeSoftwareAliasInRam(listInput.get(1));
		error|=this.saveAttributePaddingByteValue(	listInput.get(2));
		error|=this.saveAttributeFirstMemoryValue(	listInput.get(3));
		error|=this.saveAttributeStructureName(		listInput.get(4));
		error|=this.saveStructureInformation();
		if(error){
			throw new AECexception("text or number exception", AECexception.TEXT_FIELD_EXCEPTION);
		}
		this.setPanelConfiguration();
	}
	
	/**
	 * process called to save the calibration max size
	 */
	private boolean saveAttributeCalibrationMaxSize(ModelTextField tField) throws AECexception{
		if(AECcontroller.analyseTextFieldPositiveNumber(tField, 999999999)){
			return true;
		}else{
			DataStore.getInstance().setAECcalibrationMaxSize(Integer.parseInt(tField.getText()));
			return false;
		}
	}
	
	/**
	 * process called to display the configuration panel
	 */
	private boolean saveAttributeSoftwareAliasInRam(ModelTextField tField){
		if(AECcontroller.analyseTextFieldVariableText(tField)){
			return true;
		}else{
			DataStore.getInstance().setSoftwareAliasInRam(tField.getText());
			return false;
		}
	}
	
	/**
	 * process called to display the configuration panel
	 */
	private boolean saveAttributePaddingByteValue(ModelTextField tField){
		try{
			if(tField.getText().length()>0&&tField.getText().length()<=2){
				DataStore.getInstance().setPaddingByteValue(Integer.toHexString(Integer.parseInt(tField.getText(),16)));
				tField.setColor(ColorStore.getInstance().getColorInputBackground(), ColorStore.getInstance().getColorInputBackgroundGradient());
				return false;
			}
		}catch(Exception e){}
		tField.setColor(ColorStore.getInstance().getColorError(), ColorStore.getInstance().getColorError());
		tField.repaint();
		return true;
	}
	
	/**
	 * process called to display the configuration panel
	 */
	private boolean saveAttributeFirstMemoryValue(ModelTextField tField){
		try{
			DataStore.getInstance().setFirstMemoryValue(Integer.parseInt(tField.getText(),16));
			tField.setColor(ColorStore.getInstance().getColorInputBackground(), ColorStore.getInstance().getColorInputBackgroundGradient());
			return false;
		}catch(Exception e){}
		tField.setColor(ColorStore.getInstance().getColorError(), ColorStore.getInstance().getColorError());
		tField.repaint();
		return true;
	}
	
	/**
	 * process called to display the configuration panel
	 */
	private boolean saveAttributeStructureName(ModelTextField tField){
		if(AECcontroller.analyseTextFieldVariableText(tField)){
			return true;
		}else{
			DataStore.getInstance().setAECstructureName(tField.getText());
			return false;
		}
	}
	
	/**
	 * process called to display the configuration panel
	 */
	private boolean saveStructureInformation(){
		ArrayList<AECstructure> structList = DataStore.getInstance().getAECstructureList();
		ArrayList<PanelConfiguration.ModelPanelStructure> listPanelStruct = this.panelConfiguration.getListStructurePanel();
		boolean error = false;
		for(int i=0;i<structList.size();i++){
			Double size = Math.pow(2, listPanelStruct.get(i).comboBoxType.getSelectedIndex());
			structList.get(i).setAttributeSize(size.intValue());
			structList.get(i).setAttributeType((String)listPanelStruct.get(i).comboBoxType.getSelectedItem());
			
			if(AECcontroller.analyseTextFieldVariableText(listPanelStruct.get(i).inputAttributeName)){
				error=true;
			}else{
				error|=false;
				if(!structList.get(i).getAttributeName().equals(listPanelStruct.get(i).inputAttributeName.getText())){
					structList.get(i).setAttributeName(AECcontroller.analyseAECattributeStructureName(structList.get(i), listPanelStruct.get(i).inputAttributeName.getText()));
				}
			}
			if(AECcontroller.analyseTextFieldText(listPanelStruct.get(i).inputAttributeDescr)){
				error=true;
			}else{
				structList.get(i).setAttributeDescription(listPanelStruct.get(i).inputAttributeDescr.getText());
			}
		}
		return error;
	}
	
	/**
	 * process called to display the configuration panel
	 */
	public void setPanelConfiguration(){
		this.panelConfiguration.setStructurePanel();
		this.panelConfiguration.setPanelConfiguration();
	}
	
	/**
	 * Getter of the setting panel
	 * @return
	 */
	public PanelConfiguration getPanelConfiguration() {
		return panelConfiguration;
	}	
}
