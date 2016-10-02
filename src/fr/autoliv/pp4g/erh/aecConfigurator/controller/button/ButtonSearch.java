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
	Button use to search an AEC
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.button;

import org.jnativehook.keyboard.NativeKeyEvent;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantIcon;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantString;

public class ButtonSearch extends ModelButton {
		
	/**
	 * The serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The main Frame
	 */
	private MainFrame mainFrame;
	
	/**
	 * Constructor of the button to add a component
	 * @param mainFrame
	 */
	public ButtonSearch(MainFrame mainFrame){
		super(ConstantIcon.IC_SEARCH,NativeKeyEvent.VC_H,true,ModelButton.BUTTON_ICON,ConstantString.TOOL_TIP_BUTTON_SEARCH);
		this.mainFrame = mainFrame;
	}
	
	/**
	 * Process called when the user want to add a new parameter or a new component
	 */
	@Override
	synchronized public void buttonEvent(){
		int state = this.mainFrame.getFrameState();
		if(	state == MainFrame.STATE_EDIT_AEC ||
			state == MainFrame.STATE_EDIT_VALUE || 
			state == MainFrame.STATE_NEW_AEC
		){
			ControlStore.getInstance().getExecutor().executeCommand(IDCommand.SEARCH_AEC);
		}
	}
}
