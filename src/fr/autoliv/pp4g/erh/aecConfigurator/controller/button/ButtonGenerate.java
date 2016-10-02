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
	Button use to generate the files
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.button;

import org.jnativehook.keyboard.NativeKeyEvent;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantIcon;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantString;

public class ButtonGenerate extends ModelButton{
		
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The main frame where all the component are displayed
	 */
	private MainFrame mainFrame;
	
	/**
	 * Constructor of the generate button
	 * @param mainFrame
	 */
	public ButtonGenerate(MainFrame mainFrame){
		super(ConstantIcon.IC_GENERATE,NativeKeyEvent.VC_G,true,ModelButton.BUTTON_ICON,ConstantString.TOOL_TIP_BUTTON_GENERATE);
		this.mainFrame=mainFrame;
	}
	
	/**
	 * Process called when the user want to generate the files
	 */
	@Override
	synchronized public void buttonEvent(){
		if(this.mainFrame.getFrameState()!=MainFrame.STATE_SHOW_DIFFERENCE){
			ControlStore.getInstance().getExecutor().executeCommand(IDCommand.GENERATE);
		}
	}	
}
