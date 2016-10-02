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
	An action of the command pattern : Select the next attribute
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.ICommand;
import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;

public class SelectNextAttribute implements ICommand{

	@Override
	public void execute() {

		try{
			MainFrame mainFrame = DialogStore.getInstance().getMainFrame();
			mainFrame.saveCalibration();
			
			int numberOfParam;
			
			int nvpComponentKey = DataStore.getInstance().getAECkey();
			numberOfParam = DataStore.getInstance().getAEClist().get(nvpComponentKey).getAECattributes().size();
			
			if(numberOfParam > DataStore.getInstance().getAECattributeKey()+1){
				// increment the number of the current edition param
				DataStore.getInstance().setAECattributeKey(DataStore.getInstance().getAECattributeKey()+1);
				mainFrame.centerPanelEditAECcomponent.getPanelEditAEC().setAECattribute();
				mainFrame.centerPanelEditAECcomponent.revalidate();
			}
		}catch(AECexception aecException){
			if(aecException.getExceptionCause()==AECexception.AEC_CALIBRATION_SIZE){
				DialogStore.getInstance().displayErrorAECcalibrationSize();
			}
			if(aecException.getExceptionCause()==AECexception.TEXT_FIELD_EXCEPTION){
				DialogStore.getInstance().displayErrorTextFieldDialog();
			}
		}
	}

}
