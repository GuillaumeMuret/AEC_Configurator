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
	Button use to escape the current screen
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.button;

import org.jnativehook.keyboard.NativeKeyEvent;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantIcon;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantString;

public class ButtonEscape extends ModelButton{
		
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the setting button
	 * @param mainFrame
	 */
	public ButtonEscape(MainFrame mainFrame){
		super(ConstantIcon.IC_ESCAPE,NativeKeyEvent.VC_ESCAPE,false,ModelButton.BUTTON_ICON,ConstantString.TOOL_TIP_BUTTON_ESCAPE);
	}
	
	/**
	 * Process called when the user want to escape the current screen
	 */
	@Override
	synchronized public void buttonEvent(){
		ControlStore.getInstance().getExecutor().executeCommand(IDCommand.ESCAPE);
	}	
}
