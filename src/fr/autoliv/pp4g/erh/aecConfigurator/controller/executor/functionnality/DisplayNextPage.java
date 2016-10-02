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
	An action of the command pattern : display the next page
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.ICommand;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.FileStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;

public class DisplayNextPage implements ICommand{

	@Override
	public void execute() {
		if(FileStore.getInstance().getCurrentPage()<FileStore.FILE_NUMBER){
			MainFrame mainFrame = DialogStore.getInstance().getMainFrame();
			FileStore.getInstance().setCurrentPage(FileStore.getInstance().getCurrentPage()+1);
			mainFrame.actionBarPanel.updatActionBarTitle();
			mainFrame.centerPanelShowDifference.displayPanelShowDifference();
		}
	}

}
