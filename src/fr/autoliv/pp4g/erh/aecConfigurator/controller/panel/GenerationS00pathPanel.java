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

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;

public class GenerationS00pathPanel extends JPanel implements MouseListener {
	
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the ListComponentPanel listener
	 * @param mainFrame
	 * @param listLabel
	 */
	public GenerationS00pathPanel(){
		super();
		this.setPreferredSize(new Dimension(1,40));
		this.setOpaque(false);
		this.setBackground(ColorStore.getInstance().getColorGenPathMouseEnter());
		this.setLayout(new BorderLayout());
		this.addMouseListener(this);
	}
	
	/**
	 * Process called when the user click on the component
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Process called when the user press on the mouse
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		ControlStore.getInstance().getExecutor().executeCommand(IDCommand.DISPLAY_GEN_PATH_S00);
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
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setOpaque(true);
		this.repaint();
	}

	/**
	 * Process called when the mouse exit the panel zone
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.setOpaque(false);
		this.repaint();
	}	

}
