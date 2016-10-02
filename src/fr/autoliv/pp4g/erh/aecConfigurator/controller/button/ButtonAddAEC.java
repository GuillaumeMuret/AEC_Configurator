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
	Button use to add an AEC
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.button;

import org.jnativehook.keyboard.NativeKeyEvent;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantIcon;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantString;

public class ButtonAddAEC extends ModelButton {
		
	/**
	 * The serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The main frame where all the component are displayed
	 */
	private MainFrame mainFrame;
	
	/**
	 * Constructor of the button to add a component
	 * @param mainFrame
	 */
	public ButtonAddAEC(MainFrame mainFrame){
		super(ConstantIcon.IC_ADD_AEC,NativeKeyEvent.VC_N,true,ModelButton.BUTTON_ICON,ConstantString.TOOL_TIP_BUTTON_ADD_AEC);
		this.mainFrame=mainFrame;
	}
	
	/**
	 * Process called when the user want to add a new AEC
	 */
	@Override
	synchronized public void buttonEvent(){
		if	(	mainFrame.getFrameState()==MainFrame.STATE_EDIT_VALUE ||
				mainFrame.getFrameState()==MainFrame.STATE_EDIT_AEC ||
				mainFrame.getFrameState()==MainFrame.STATE_NEW_AEC ||
				mainFrame.getFrameState()==MainFrame.STATE_DETAIL_LIST 
		){
			ControlStore.getInstance().getExecutor().executeCommand(IDCommand.ADD_AEC);
		}
	}
}
