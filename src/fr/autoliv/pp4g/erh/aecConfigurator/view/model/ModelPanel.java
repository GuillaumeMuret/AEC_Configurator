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
	Class model of the panels
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;

public class ModelPanel extends JPanel{

	public static final int PANEL_CENTER = 	1;
	public static final int OTHER = 		2;
	
	private boolean opaque;
	
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public ModelPanel(Dimension preferredSize,boolean opaque,LayoutManager layout,int typePanel){
		super();
		this.opaque=opaque;
		this.setLayout(layout);
		this.setPreferredSize(preferredSize);
		this.setOpaque(opaque);
		if(typePanel==PANEL_CENTER){
			this.setBorder(new EmptyBorder(
				ConstantDimension.PADDING_PANEL_CENTER, 
				ConstantDimension.PADDING_PANEL_CENTER, 
				ConstantDimension.PADDING_PANEL_CENTER, 
				ConstantDimension.PADDING_PANEL_CENTER
			));
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.opaque){
			Graphics2D g2d = (Graphics2D) g;
			Color color1 = ColorStore.getInstance().getColorCenterPan();
			Color color2 = ColorStore.getInstance().getColorCenterPanGradient();
			int w = getWidth();
			int h = getHeight();
			//GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
			GradientPaint gp = new GradientPaint(0, 0, color1, h, h, color2);
			g2d.setPaint(gp);
			g2d.fillRect(0, 0, w, h);
		}
	}
}
