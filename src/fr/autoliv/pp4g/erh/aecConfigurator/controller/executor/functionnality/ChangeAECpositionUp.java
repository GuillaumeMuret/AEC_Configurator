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
	An action of the command pattern : change the AEC position with the previous
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.ICommand;
import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;

public class ChangeAECpositionUp implements ICommand{

	@Override
	public void execute() {
		try{
			MainFrame mainFrame = DialogStore.getInstance().getMainFrame();
			mainFrame.saveCalibration();
			int aecKey = DataStore.getInstance().getAECkey();
			if(aecKey>0){
				mainFrame.centerPanelEditAECvalue.changeAECposition(-1);
			}
			mainFrame.saveCalibration();
		}catch(AECexception aecException){
			if(aecException.getExceptionCause()==AECexception.AEC_CALIBRATION_SIZE){
				DialogStore.getInstance().displayErrorAECcalibrationSize();
				DialogStore.getInstance().getMainFrame().changePanel(MainFrame.STATE_CONFIGURATION);
			}
			if(aecException.getExceptionCause()==AECexception.AEC_ATTRIBUTE_SIZE){
				DialogStore.getInstance().displayErrorAECattributeSize();
			}
			if(aecException.getExceptionCause()==AECexception.TEXT_FIELD_EXCEPTION){
				DialogStore.getInstance().displayErrorTextFieldDialog();
			}
		}
	}

}
