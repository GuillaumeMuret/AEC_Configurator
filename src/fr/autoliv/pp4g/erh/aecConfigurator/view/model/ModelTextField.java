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
	Class model of the text field where the user can enter some text
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;

public class ModelTextField extends JTextField implements NativeKeyListener, FocusListener{

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The first color
	 */
	private Color color1;
	
	/**
	 * The second color
	 */
	private Color color2;
	
	/**
	 * Main constructor of all the input field
	 * @param text
	 * @param dim
	 * @param font
	 */
	public ModelTextField(String text,Dimension dim,Font font,boolean editable){
		super();
		this.setEditable(editable);
		this.setPreferredSize(dim);
		this.setFont(font);
		if(isEditable()){
			color1 = ColorStore.getInstance().getColorCenterPan();
			color2 = ColorStore.getInstance().getColorCenterPanGradient();
		}else{
			color1 = ColorStore.getInstance().getColorCenterPan();
			color2 = ColorStore.getInstance().getColorCenterPanGradient().darker().darker();
		}
		this.setForeground(ColorStore.getInstance().getColorInputForeground());
		Border line = BorderFactory.createLineBorder(ColorStore.getInstance().getColorInputBorder());
		Border empty = new EmptyBorder(0,10,0,0);
		this.setBorder(new CompoundBorder(line,empty));
		this.setCaretColor(ColorStore.getInstance().getColorInputForeground());
		this.setText(text);
		this.addFocusListener(this);
		GlobalScreen.addNativeKeyListener(this);
		this.requestFocus(true);
		this.selectAll();
	}
	
	/**
	 * Setter of the color
	 * @param color1
	 * @param color2
	 */
	public void setColor(Color color1, Color color2){
		this.color1=color1;
		this.color2=color2;
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();
		//GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
		GradientPaint gp = new GradientPaint(0, 0, color1, 5*h, 5*h, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
		setOpaque(false);
		super.paintComponent(g);
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		if(ControlStore.getInstance().getCtrlKeyPressed()){
			if(e.getKeyCode()==NativeKeyEvent.VC_DELETE){
				this.select(this.getText().length(), this.getText().length());
			}
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		this.selectAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
		this.select(0, 0);
	}
}