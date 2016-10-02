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
	An action of the command pattern : Change the AEC attribute's group
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.aec.AECcontroller;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.ICommand;
import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.PanelConfiguration.ModelPanelStructure;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;

public class ChangeAttributeGroup implements ICommand{

	private ModelPanelStructure modelPanelStruct;
	@Override
	public void execute() {
		this.modelPanelStruct = DialogStore.getInstance().getModelPanelStructure();
		try {
			// Manage attribute name
			this.manageStructNameAndSize();
			
			// Save, sort the attribute and finally save
			DialogStore.getInstance().getMainFrame().saveCalibration();
			AECcontroller.sortThisAttribute(DataStore.getInstance().getAECattributeKey());
			DialogStore.getInstance().getMainFrame().saveCalibration();
			
			// Analyze memory space
			AECcontroller.analyseMemorySpace();
		} catch (AECexception e) {
			DialogStore.getInstance().displayErrorAECcalibrationSize();
		}
	}
	
	/**
	 * process called to manage the name and size attribute
	 */
	private void manageStructNameAndSize(){
		String attrName = modelPanelStruct.inputAttributeName.getText();
		String beginString = "u"+(8*DataStore.getInstance().getAECstructureList().get(DataStore.getInstance().getAECattributeKey()).getAttributeSize());
		Double size = Math.pow(2, modelPanelStruct.comboBoxType.getSelectedIndex());
		String newBeginString = "u"+(8*size.intValue());
		
		if(attrName.substring(0, beginString.length()).equals(beginString)){
			modelPanelStruct.inputAttributeName.setText(modelPanelStruct.inputAttributeName.getText().substring(beginString.length()));
			DataStore.getInstance().getAECstructureList().get(DataStore.getInstance().getAECattributeKey()).setAttributeSize(size.intValue());
			modelPanelStruct.inputAttributeName.setText(newBeginString+modelPanelStruct.inputAttributeName.getText());
		}else{
			System.out.println(newBeginString+modelPanelStruct.inputAttributeName.getText());
			boolean response = DialogStore.getInstance().displayChangeAECattributeStructureName(modelPanelStruct.inputAttributeName.getText(),newBeginString);
			if(response){
				modelPanelStruct.inputAttributeName.setText(newBeginString+modelPanelStruct.inputAttributeName.getText());
			}
		}
	}

}
