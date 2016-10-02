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
	Button use to refresh the screen
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.button;

import org.jnativehook.keyboard.NativeKeyEvent;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantIcon;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantString;

public class ButtonRefresh extends ModelButton{
		
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the button to take the component up in the list
	 * @param mainFrame
	 */
	public ButtonRefresh(MainFrame mainFrame){
		super(ConstantIcon.IC_REFRESH,NativeKeyEvent.VC_F5,false,ModelButton.BUTTON_ICON,ConstantString.TOOL_TIP_BUTTON_REFRESH);
	}
	
	/**
	 * Process called when the user want to get the precedent argument
	 */
	@Override
	synchronized public void buttonEvent(){
		ControlStore.getInstance().getExecutor().executeCommand(IDCommand.REFRESH);
	}
}
