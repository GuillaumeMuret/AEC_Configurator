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
	An action of the command pattern : Select the next AEC
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality;

import java.util.ArrayList;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.aec.AECcontroller;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.ICommand;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.LabelAEC;

public class SelectNextAEC implements ICommand{

	@Override
	public void execute() {
		ArrayList<LabelAEC> labelAEClist=DataStore.getInstance().getAEClabelList();
		int currentAECkey = DataStore.getInstance().getAECkey();
		if(currentAECkey<labelAEClist.size()-1){
			AECcontroller.AECselected(currentAECkey+1);
		}
	}

}
