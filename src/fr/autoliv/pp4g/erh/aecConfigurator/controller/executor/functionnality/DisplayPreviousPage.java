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
	An action of the command pattern : Display the previous page
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.ICommand;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.FileStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;

public class DisplayPreviousPage implements ICommand{

	@Override
	public void execute() {
		if(FileStore.getInstance().getCurrentPage()>1){
			MainFrame mainFrame = DialogStore.getInstance().getMainFrame();
			FileStore.getInstance().setCurrentPage(FileStore.getInstance().getCurrentPage()-1);
			mainFrame.actionBarPanel.updatActionBarTitle();
			mainFrame.centerPanelShowDifference.displayPanelShowDifference();
		}
	}

}
