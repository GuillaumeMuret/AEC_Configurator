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
	Button use to save the calibration
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.button;

import org.jnativehook.keyboard.NativeKeyEvent;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantIcon;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantString;

public class ButtonSave extends ModelButton {
		
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the save button
	 */
	public ButtonSave(MainFrame mainFrame){
		super(ConstantIcon.IC_SAVE,NativeKeyEvent.VC_S,true,ModelButton.BUTTON_FLOATING,ConstantString.TOOL_TIP_BUTTON_SAVE);
	}	
	
	/**
	 * Process called when the user want to save the AEC configuration
	 */
	@Override
	synchronized public void buttonEvent(){
		ControlStore.getInstance().getExecutor().executeCommand(IDCommand.SAVE);
	}
}
