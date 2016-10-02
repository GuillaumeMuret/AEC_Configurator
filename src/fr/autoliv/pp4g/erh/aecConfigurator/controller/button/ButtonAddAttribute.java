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
	Button use to add an attribute
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.button;

import org.jnativehook.keyboard.NativeKeyEvent;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantIcon;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantString;

public class ButtonAddAttribute extends ModelButton {
		
	/**
	 * The serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the button to add a component
	 * @param mainFrame
	 */
	public ButtonAddAttribute(MainFrame mainFrame){
		super(ConstantIcon.IC_ADD_ATTRIBUTE,NativeKeyEvent.VC_SPACE,true,ModelButton.BUTTON_ICON,ConstantString.TOOL_TIP_BUTTON_ADD_ATTRIBUTE);
	}
	
	/**
	 * Process called when the user want to add a new parameter or a new component
	 */
	@Override
	public void buttonEvent(){
		ControlStore.getInstance().getExecutor().executeCommand(IDCommand.ADD_ATTRIBUTE);
	}
}
