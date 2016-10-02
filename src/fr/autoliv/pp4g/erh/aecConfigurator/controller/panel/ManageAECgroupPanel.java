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
	
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.panel;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;

public class ManageAECgroupPanel extends JPanel implements MouseListener {
	
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the ListComponentPanel listener
	 * @param mainFrame
	 * @param listLabel
	 */
	public ManageAECgroupPanel(){
		super();
		this.setPreferredSize(new Dimension(1,40));
		this.setOpaque(false);
		this.setBackground(ColorStore.getInstance().getColorGenPathMouseEnter().darker().darker());
		this.setLayout(new BorderLayout());
		Border line = BorderFactory.createLineBorder(ColorStore.getInstance().getColorInputBorder());
		Border empty = new EmptyBorder(0,10,0,0);
		this.setBorder(new CompoundBorder(line,empty));
		this.add(this.labelManageAECgroup(),BorderLayout.WEST);
		this.addMouseListener(this);
	}
	
	/**
	 * Process called to get the label of the panel "manage AEC group"
	 * @return
	 */
	private JLabel labelManageAECgroup(){
		JLabel labelManageAECgroup = new JLabel("Manage the AEC group");
		labelManageAECgroup.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelManageAECgroup.setForeground(ColorStore.getInstance().getColorInputForeground());
		return labelManageAECgroup;
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
		ControlStore.getInstance().getExecutor().executeCommand(IDCommand.DISPLAY_MANAGE_AEC_GROUP);
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
