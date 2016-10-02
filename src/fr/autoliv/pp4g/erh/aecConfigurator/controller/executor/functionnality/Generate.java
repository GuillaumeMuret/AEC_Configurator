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
	An action of the command pattern : Generate the files
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality;

import java.awt.Cursor;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.aec.AECcontroller;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.ICommand;
import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.convertor.ConvertOldFiles;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.Generatefiles;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;

public class Generate implements ICommand{

	@Override
	public void execute() {
		MainFrame mainFrame = DialogStore.getInstance().getMainFrame();
		try{
	    	mainFrame.saveCalibration();
	    	AECcontroller.analyseMemorySpace();
	    	AECcontroller.analyseAttributeSpace();
	    	AECcontroller.analyseAECdoubleName();
	    	AECcontroller.analyseAECattributeDoubleName();
	    	AECcontroller.analyseAECreservedOrder();
	    	AECcontroller.analyseAECreservedByteOrder();
	    	
			mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			new ConvertOldFiles();
    		new Generatefiles();
    		mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    	DialogStore.getInstance().displaySuccessGeneration();
		}catch(AECexception aecException){
			mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			if(aecException.getExceptionCause()==AECexception.AEC_CALIBRATION_SIZE)
				DialogStore.getInstance().displayErrorAECcalibrationSize();
			if(aecException.getExceptionCause()==AECexception.AEC_ATTRIBUTE_SIZE){
				DialogStore.getInstance().displayErrorAECattributeSize();
				DialogStore.getInstance().getMainFrame().changePanel(MainFrame.STATE_DETAIL_LIST);
			}
			if(aecException.getExceptionCause()==AECexception.DOUBLE_AEC_NAME){
				DialogStore.getInstance().displayErrorAECdoubleName();
				DialogStore.getInstance().getMainFrame().changePanel(MainFrame.STATE_EDIT_VALUE);
			}
			if(aecException.getExceptionCause()==AECexception.DOUBLE_AEC_ATTRIBUTE_NAME){
				DialogStore.getInstance().displayErrorAECdoubleAttributeName();
				DialogStore.getInstance().getMainFrame().changePanel(MainFrame.STATE_CONFIGURATION);
				try {AECcontroller.analyseAECattributeDoubleName();} catch (AECexception e) {}
			}
			if(aecException.getExceptionCause()==AECexception.AEC_RESERVED_NOT_IN_THE_BOTTOM){
				DialogStore.getInstance().displayErrorAECreservedNotInTheBottom();
				DialogStore.getInstance().getMainFrame().changePanel(MainFrame.STATE_EDIT_VALUE);
			}
			if(aecException.getExceptionCause()==AECexception.AEC_RESERVED_ATTR_NOT_IN_THE_BOTTOM){
				DialogStore.getInstance().displayErrorAECreservedByteNotInTheBottom();
				DialogStore.getInstance().getMainFrame().changePanel(MainFrame.STATE_CONFIGURATION);
			}
			if(aecException.getExceptionCause()==AECexception.FOLDER_CH_NOT_FOUND){
				int returnCode = DialogStore.getInstance().displayGenerationCHfolderPathDialog();
				DialogStore.getInstance().getMainFrame().changePanel(DialogStore.getInstance().getMainFrame().getFrameState());
				if(returnCode==0){
					execute();
				}
			}
			if(aecException.getExceptionCause()==AECexception.FOLDER_S00_NOT_FOUND){
				int returnCode = DialogStore.getInstance().displayGenerationS00folderPathDialog();
				DialogStore.getInstance().getMainFrame().changePanel(DialogStore.getInstance().getMainFrame().getFrameState());
				if(returnCode==0){
					execute();
				}
			}
			if(aecException.getExceptionCause()==AECexception.FOLDER_ARXML_NOT_FOUND){
				int returnCode = DialogStore.getInstance().displayGenerationARXMLfolderPathDialog();
				DialogStore.getInstance().getMainFrame().changePanel(DialogStore.getInstance().getMainFrame().getFrameState());
				if(returnCode==0){
					execute();
				}
			}
			if(aecException.getExceptionCause()==AECexception.TEXT_FIELD_EXCEPTION){
				DialogStore.getInstance().displayErrorTextFieldDialog();
			}
		}
	}

}
