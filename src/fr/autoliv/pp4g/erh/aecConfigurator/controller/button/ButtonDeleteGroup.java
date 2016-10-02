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
	Button use to add a group
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.button;

import org.jnativehook.keyboard.NativeKeyEvent;

import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantIcon;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantString;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;

public class ButtonDeleteGroup extends ModelButton {
		
	/**
	 * The serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The main frame
	 */
	private MainFrame mainFrame;
	
	/**
	 * Constructor of the button to add a component
	 * @param mainFrame
	 */
	public ButtonDeleteGroup(MainFrame mainFrame){
		super(ConstantIcon.IC_DELETE_GROUP,NativeKeyEvent.VC_DELETE,true,ModelButton.BUTTON_ICON,ConstantString.TOOL_TIP_BUTTON_DELETE_GROUP);
		this.mainFrame=mainFrame;
	}
	
	/**
	 * Process called when the user want to add a new parameter or a new component
	 */
	@Override
	public void buttonEvent(){
		if(this.mainFrame.getFrameState()==MainFrame.STATE_MANAGE_GROUP){
			DialogStore.getInstance().getPanelDialogSetAECgroup().deleteAECgroup();
		}
	}
}
