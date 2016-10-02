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
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;

public class ChooseThemePanel extends JPanel {
	
	private static final String BRIGHT =	"Bright Theme";
	private static final String DARK = 		"Dark Theme";
	
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the ListComponentPanel listener
	 * @param mainFrame
	 * @param listLabel
	 */
	public ChooseThemePanel(){
		super();
		this.setPreferredSize(new Dimension(1,40));
		this.setOpaque(false);
		this.setLayout(new GridLayout(1,2));
		this.setPanelChooseTheme();
		this.setBorder(BorderFactory.createLineBorder(ColorStore.getInstance().getColorInputBorder()));
	}
	
	/**
	 * Process called to set the panel choose theme
	 */
	private void setPanelChooseTheme(){
		JPanel panelBrightTheme = new JPanel();
		panelBrightTheme.setLayout(new BorderLayout());
		panelBrightTheme.setOpaque(false);
		panelBrightTheme.add(new LabelChooseTheme(BRIGHT),BorderLayout.CENTER);
		
		JPanel panelDarkTheme = new JPanel();
		panelDarkTheme.setLayout(new BorderLayout());
		panelDarkTheme.setOpaque(false);
		panelDarkTheme.add(new LabelChooseTheme(DARK),BorderLayout.CENTER);
		
		this.add(panelBrightTheme);
		this.add(panelDarkTheme);

	}
	
	private class LabelChooseTheme extends JLabel implements MouseListener{
		
		/**
		 * The serial version UID
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * the theme
		 */
		private String theme;
		
		/**
		 * Constructor of the label choose theme
		 * @param theme
		 */
		public LabelChooseTheme(String theme){
			super(theme,JLabel.CENTER);
			this.theme=theme;
			this.setOpaque(true);
			if(theme == BRIGHT){
				this.setForeground(Color.black);
				this.setBackground(Color.white);
			}
			if(theme == DARK){
				this.setForeground(Color.WHITE);
				this.setBackground(Color.BLACK);
			}
			this.addMouseListener(this);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(this.theme==BRIGHT){
				ControlStore.getInstance().getExecutor().executeCommand(IDCommand.CHANGE_COLOR_BRIGHT);
			}
			if(this.theme==DARK){
				ControlStore.getInstance().getExecutor().executeCommand(IDCommand.CHANGE_COLOR_DARK);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
