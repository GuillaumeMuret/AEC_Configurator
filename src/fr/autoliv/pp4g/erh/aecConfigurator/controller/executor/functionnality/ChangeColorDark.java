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
	An action of the command pattern : Change the theme color in dark
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.aec.AECcontroller;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.ICommand;
import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;

public class ChangeColorDark implements ICommand{

	@Override
	public void execute() {
		try{
			DialogStore.getInstance().getMainFrame().saveCalibration();
			ColorStore.getInstance().setColorTheme(ColorStore.COLOR_THEME_DARK);
			DialogStore.getInstance().getMainFrame().repaintAll();
			AECcontroller.refreshScreenCTRL_Z();
		} catch (AECexception aecException) {
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
