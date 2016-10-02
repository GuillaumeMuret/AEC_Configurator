/******************************************************************************

AUTOLIV ELECTRONIC document.

-------------------------------------

Copyright Autoliv Inc. All rights reserved.

*******************************************************************************
JAVA-File Template Version: 
******************************************************************************/
/* PRQA S 0288 ++ */
/*
 * Explanation:
 *    Disabled for MKS keywords
 */
/*
$Revision: 0.1 $
$ProjectName: e:/MKSProjects/ALE Core Software/ALE Process Standards/ProductDevelopment/Template/Template.pj $
*/
/* PRQA S 0288 -- */
/*!****************************************************************************

@details
   This module control and display the panel where the nvp component list will be displayed.
   A click in this panel will display the initial panel and save the NVP configuration.
   This panel is displayed when the user want to select a component.
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;

public class PanelAEClistControl implements MouseListener,NativeKeyListener{
	
	/**
	 * The main frame where all the component are displayed
	 */
	private MainFrame mainFrame;
	
	/**
	 * Constructor of the ListComponentPanel listener
	 * @param mainFrame
	 * @param listLabel
	 */
	public PanelAEClistControl(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		GlobalScreen.addNativeKeyListener(this);
	}
	
	/**
	 * Process called when the user click on the component
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		ControlStore.getInstance().getExecutor().executeCommand(IDCommand.ESCAPE);
	}

	/**
	 * Process called when the user press on the mouse
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Process called when the user released the mouse (called after a press)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Process called when the mouse enter the panel zone
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Process called when the mouse exit the panel zone
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	synchronized public void nativeKeyPressed(NativeKeyEvent e) {
		if(e.getKeyCode()==NativeKeyEvent.VC_CONTROL_L){
			ControlStore.getInstance().setCtrlKeyPressed(true);
		}
		if(e.getKeyCode()==NativeKeyEvent.VC_CONTROL_R){
			ControlStore.getInstance().setCtrlKeyPressed(true);
		}
		if(ControlStore.getInstance().getCtrlKeyPressed()){
			if(e.getKeyCode()==NativeKeyEvent.VC_ENTER||e.getKeyCode()==NativeKeyEvent.VC_DOWN){
				if(this.mainFrame.getFrameState()!=MainFrame.STATE_SHOW_DIFFERENCE&&this.mainFrame.getFrameState()!=MainFrame.STATE_MANAGE_GROUP){
					ControlStore.getInstance().getExecutor().executeCommand(IDCommand.SELECT_NEXT_AEC);
				}
			}
			if(e.getKeyCode()==NativeKeyEvent.VC_UP){
				if(this.mainFrame.getFrameState()!=MainFrame.STATE_SHOW_DIFFERENCE&&this.mainFrame.getFrameState()!=MainFrame.STATE_MANAGE_GROUP){
					ControlStore.getInstance().getExecutor().executeCommand(IDCommand.SELECT_PREVIOUS_AEC);
				}
			}
			if(e.getKeyCode()==NativeKeyEvent.VC_Z){
				ControlStore.getInstance().getExecutor().executeCommand(IDCommand.REFRESH);
			}
			if(e.getKeyCode()==NativeKeyEvent.VC_W){
				ControlStore.getInstance().getExecutor().executeCommand(IDCommand.REFRESH);
			}
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		if(e.getKeyCode()==NativeKeyEvent.VC_CONTROL_L)
			ControlStore.getInstance().setCtrlKeyPressed(false);
		
		if(e.getKeyCode()==NativeKeyEvent.VC_CONTROL_R)
			ControlStore.getInstance().setCtrlKeyPressed(false);
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		
	}	

}
