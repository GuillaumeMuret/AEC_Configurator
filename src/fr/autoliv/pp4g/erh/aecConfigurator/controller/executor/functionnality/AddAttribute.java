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
	An action of the command pattern : Add a new attribute
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.aec.AECcontroller;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.ICommand;
import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;

public class AddAttribute implements ICommand{

	@Override
	public void execute() {
		MainFrame mainFrame = DialogStore.getInstance().getMainFrame();
		try{
			mainFrame.saveCalibration();
			switch(mainFrame.getFrameState()){
				case MainFrame.STATE_EDIT_VALUE:
					AECcontroller.addAECattribute();
					mainFrame.centerPanelEditAECcomponent.getPanelEditAEC().setAECattribute();
					mainFrame.centerPanelConfiguration.getPanelConfiguration().setStructurePanel();
					mainFrame.changePanel(MainFrame.STATE_DETAIL_LIST);
					mainFrame.saveCalibration();
					break;
				
				case MainFrame.STATE_DETAIL_LIST:
					AECcontroller.addAECattribute();
					mainFrame.centerPanelEditAECcomponent.getPanelEditAEC().setAECattribute();
					mainFrame.centerPanelConfiguration.getPanelConfiguration().setStructurePanel();
					mainFrame.changePanel(MainFrame.STATE_DETAIL_LIST);
					mainFrame.saveCalibration();
					break;
					
				case MainFrame.STATE_EDIT_AEC:
					AECcontroller.addAECattribute();
					mainFrame.centerPanelEditAECcomponent.getPanelEditAEC().setAECattribute();
					mainFrame.centerPanelConfiguration.getPanelConfiguration().setStructurePanel();
					mainFrame.saveCalibration();
					break;
					
				case MainFrame.STATE_NEW_AEC:
					AECcontroller.addAECattribute();
					mainFrame.centerPanelEditAECcomponent.getPanelEditAEC().setAECattribute();
					mainFrame.centerPanelConfiguration.getPanelConfiguration().setStructurePanel();
					mainFrame.saveCalibration();
					break;
					
				case MainFrame.STATE_CONFIGURATION:
					AECcontroller.addAECattribute();
					mainFrame.centerPanelConfiguration.getPanelConfiguration().setStructurePanel();
					mainFrame.saveCalibration();
					break;
			}
			AECcontroller.analyseMemorySpace();
		}catch(AECexception aecException){
			if(aecException.getExceptionCause()==AECexception.AEC_CALIBRATION_SIZE){
				DialogStore.getInstance().displayErrorAECcalibrationSize();
				DialogStore.getInstance().getMainFrame().changePanel(MainFrame.STATE_CONFIGURATION);
			}
			if(aecException.getExceptionCause()==AECexception.AEC_ATTRIBUTE_SIZE){
				DialogStore.getInstance().displayErrorAECattributeSize();
				DialogStore.getInstance().getMainFrame().changePanel(MainFrame.STATE_DETAIL_LIST);
			}
			if(aecException.getExceptionCause()==AECexception.ADDING_AEC_CALIBRATION_SIZE){
				DialogStore.getInstance().displayErrorAddingAECattributeSize();
			}
			if(aecException.getExceptionCause()==AECexception.TEXT_FIELD_EXCEPTION){
				DialogStore.getInstance().displayErrorTextFieldDialog();
			}
		}
	}

}
