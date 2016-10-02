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
	Button of the arrow down
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.button;

import org.jnativehook.keyboard.NativeKeyEvent;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantIcon;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantString;

public class ButtonArrowDown extends ModelButton{

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The main frame where all the component are displayed
	 */
	private MainFrame mainFrame;
	
	/**
	 * Constructor of the button to take the component down in the list
	 * @param mainFrame
	 */
	public ButtonArrowDown(MainFrame mainFrame){
		super(ConstantIcon.IC_ARROW_DOWN,NativeKeyEvent.VC_F,true,ModelButton.BUTTON_ICON,ConstantString.TOOL_TIP_BUTTON_ARROW_DOWN);
		this.mainFrame=mainFrame;
	}
	
	/**
	 * Process called to manage the arrow down event
	 */
	@Override
	synchronized public void buttonEvent(){
		if(this.mainFrame.getFrameState()==MainFrame.STATE_EDIT_VALUE){
			ControlStore.getInstance().getExecutor().executeCommand(IDCommand.CHANGE_AEC_POSITION_DOWN);
		}
		if(this.mainFrame.getFrameState()==MainFrame.STATE_CONFIGURATION){
			ControlStore.getInstance().getExecutor().executeCommand(IDCommand.CHANGE_AEC_ATTRIBUTE_POSITION_DOWN);
		}
	}		
}
