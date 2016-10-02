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
	An action of the command pattern : Select the attribute
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.ICommand;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.PanelConfiguration.ModelPanelStructure;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;

public class SelectAttribute implements ICommand{

	@Override
	public void execute() {
		ModelPanelStructure modelPanelStruct = DialogStore.getInstance().getModelPanelStructure();
		modelPanelStruct.setInitialStruct();
		DataStore.getInstance().setAECattributeKey(DataStore.getInstance().getNewAttributeSelected());
		modelPanelStruct.setSelectedLine(DataStore.getInstance().getNewAttributeSelected());
		modelPanelStruct.repaint();
	}

}
