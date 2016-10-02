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
	Button of the arrow up
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.button;

import org.jnativehook.keyboard.NativeKeyEvent;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantIcon;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantString;

public class ButtonArrowUp extends ModelButton{
		
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The main frame where all the component are displayed
	 */
	private MainFrame mainFrame;
	
	/**
	 * Constructor of the button to take the component up in the list
	 * @param mainFrame
	 */
	public ButtonArrowUp(MainFrame mainFrame){
		super(ConstantIcon.IC_ARROW_UP,NativeKeyEvent.VC_R,true,ModelButton.BUTTON_ICON,ConstantString.TOOL_TIP_BUTTON_ARROW_UP);
		this.mainFrame=mainFrame;
	}
	
	/**
	 * Process called when the user want to take a component up in the list
	 */
	@Override
	synchronized public void buttonEvent(){
		if(this.mainFrame.getFrameState()==MainFrame.STATE_EDIT_VALUE){
			ControlStore.getInstance().getExecutor().executeCommand(IDCommand.CHANGE_AEC_POSITION_UP);
		}
		if(this.mainFrame.getFrameState()==MainFrame.STATE_CONFIGURATION){
			ControlStore.getInstance().getExecutor().executeCommand(IDCommand.CHANGE_AEC_ATTRIBUTE_POSITION_UP);
		}
	}			
}
