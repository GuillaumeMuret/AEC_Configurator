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
	Button use to switch the AEC attribute with the previous
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.button;

import org.jnativehook.keyboard.NativeKeyEvent;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantIcon;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantString;

public class ButtonSwipeLeft extends ModelButton{
		
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The main frame where all the component are displayed
	 */
	private MainFrame mainFrame;
	
	/**
	 * Constructor of the setting button
	 * @param mainFrame
	 */
	public ButtonSwipeLeft(MainFrame mainFrame){
		super(ConstantIcon.IC_SWIPE_LEFT,NativeKeyEvent.VC_I,true,ModelButton.BUTTON_ICON,ConstantString.TOOL_TIP_BUTTON_SWIPE_LEFT);
		this.mainFrame = mainFrame;
	}
	
	/**
	 * Process called when the user want to change the AEC attribute order (up in the typedef)
	 */
	@Override
	synchronized public void buttonEvent(){
		int state = this.mainFrame.getFrameState();
		if(state == MainFrame.STATE_EDIT_AEC || state == MainFrame.STATE_NEW_AEC){
			ControlStore.getInstance().getExecutor().executeCommand(IDCommand.CHANGE_AEC_ATTRIBUTE_POSITION_UP);
		}			
	}
}
