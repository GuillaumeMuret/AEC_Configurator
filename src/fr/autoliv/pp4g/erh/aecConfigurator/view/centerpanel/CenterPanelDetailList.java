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
	Class of the Panel detail list where the detail list screen is displayed and the 
	save is made
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.aec.AECcontroller;
import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECattribute;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.PanelDetailList;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelPanel;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelTextField;

public class CenterPanelDetailList extends ModelPanel{

	/**
	 * the serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The setting panel
	 */
	private PanelDetailList panelDetailList;
	
	/**
	 * The main Frame
	 */
	private MainFrame mainFrame;
	
	/**
	 * Constructor of the generation configuration panel
	 * @param mainFrame
	 */
	public CenterPanelDetailList(MainFrame mainFrame){
		super(ConstantDimension.PANEL_CENTER,true,new BorderLayout(),ModelPanel.PANEL_CENTER);
		this.mainFrame=mainFrame;
		this.setPreferredSize(new Dimension(100,1500));
		
		this.createPanelList();
	}
	
	/**
	 * Process called to add the setting panel where the generation configuration is set
	 */
	public void createPanelList(){
		if(this.panelDetailList!=null)
			this.remove(this.panelDetailList);
		this.panelDetailList = new PanelDetailList(this.mainFrame);
		this.add(this.panelDetailList,BorderLayout.CENTER);
		this.revalidate();
	}
	
	/**
	 * Process called to set the AEC value
	 */
	public void saveConfiguration() throws AECexception{
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		ArrayList<ModelTextField> listAttributeName = this.panelDetailList.getListAttributeNameInput();
		ArrayList<ModelTextField> listAttributeValue= this.panelDetailList.getListAttributeValueInput();
		
		boolean error = false;
		int offset=0;
		for(int j=0;j<aecList.size();j++){
			ArrayList<AECattribute> aecAttr = aecList.get(j).getAECattributes();
			for(int k=0;k<aecAttr.size();k++){
				// Manage name
				error=error|this.manageNameTextField(listAttributeName.get(k),aecAttr.get(k));
				
				if(aecAttr.get(k).getAttributeName().toUpperCase().equals("GROUP")){
					// Group value
					aecAttr.get(k).setParamValue(this.panelDetailList.getListComboGroup().get(j).getSelectedIndex());
					offset++;
				}else{
					// Other values
					int dataSize = DataStore.getInstance().getAECstructureList().get(k).getAttributeSize();
					if(!listAttributeValue.get(j*aecAttr.size()+k-offset).getText().contains("Padding byte value")){
						if(AECcontroller.analyseTextFieldPositiveNumber(listAttributeValue.get(j*aecAttr.size()+k-offset),dataSize*2)){
							error=true;
						}else{
							aecAttr.get(k).setParamValue(Integer.valueOf(listAttributeValue.get(j*aecAttr.size()+k-offset).getText()));
						}
					}
				}
			}
		}
		if(error){
			throw new AECexception("ERROR !!!!", AECexception.TEXT_FIELD_EXCEPTION);
		}
	}
	
	/**
	 * Process called to manage the text field name
	 * @param tField
	 * @param anAECattr
	 * @return
	 */
	private boolean manageNameTextField(ModelTextField tField,AECattribute anAECattr){
		if(AECcontroller.analyseTextFieldText(tField)){
			return true;
		}else{
			tField.setColor(ColorStore.getInstance().getColorSelectedStructLine(), ColorStore.getInstance().getColorSelectedStructLine());
			anAECattr.setParamName(tField.getText());
			return false;
		}
	}
	
	/**
	 * process called to display the configuration panel
	 */
	public void displayPanelList(){
		this.createPanelList();
		this.panelDetailList.setFloatingButtonPanel();
	}
	
	/**
	 * Getter of the setting panel
	 * @return
	 */
	public PanelDetailList getPanelList() {
		return panelDetailList;
	}	
}
