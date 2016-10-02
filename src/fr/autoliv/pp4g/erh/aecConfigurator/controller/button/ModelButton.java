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
	Class model of all the button in the package. This class implement the mouse listener when
	the mouse enter and click on the panel. The native key listener is use to listen the key with
	with the panel.
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.button;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;

public class ModelButton extends JPanel implements MouseListener, NativeKeyListener{
		
	/**
	 * The different button type
	 */
	public static final int BUTTON_FLOATING = 0;
	public static final int BUTTON_ICON     = 1;
	
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The key code used
	 */
	private int keyCodeUsed;
	
	/**
	 * The type of button (depends on the different type define above
	 */
	private int buttonType;
	
	/**
	 * Boolean to know if the user have to tap left ctrl to do the action
	 */
	private boolean ctrlNeed;
	
	/**
	 * The tool tip text to display under the mouse cursor
	 */
	private String toolTipText;
	
	/**
	 * Constructor of the setting button
	 * @param iconAddr
	 * @param keyCodeUsed
	 * @param ctrlNeed
	 * @param buttonType
	 * @param toolTipText
	 */
	public ModelButton(String iconAddr,int keyCodeUsed,boolean ctrlNeed,int buttonType,String toolTipText){
		super();
		this.toolTipText=toolTipText;
		this.ctrlNeed = ctrlNeed;
		this.keyCodeUsed=keyCodeUsed;
		ControlStore.getInstance().setCtrlKeyPressed(false);
		this.buttonType = buttonType;
		this.setLayout(new BorderLayout());
        this.setOpaque(false);
        
        if(this.buttonType==BUTTON_ICON){
	        this.setMaximumSize(ConstantDimension.PANEL_ICON_DIMENSION);
	        this.setMinimumSize(ConstantDimension.PANEL_ICON_DIMENSION);
        }
        if(this.buttonType==BUTTON_FLOATING){
        	this.setMaximumSize(ConstantDimension.PANEL_FLOATING_BUTTON);
	        this.setMinimumSize(ConstantDimension.PANEL_FLOATING_BUTTON);
        }
        
		this.addMouseListener(this);
		GlobalScreen.addNativeKeyListener(this);
		
		this.setImageIcon(iconAddr);
		
        this.repaint();
	}
	
	/**
	 * Process called to set the Image icon
	 */
	public void setImageIcon(String iconAddr){
    	URL url = getClass().getResource(iconAddr);
    	Image image;
    	Image scaleImage;
		try {
			image = ImageIO.read(url);
			scaleImage = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			if(this.buttonType==BUTTON_ICON){
				scaleImage = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			}
			if(this.buttonType==BUTTON_FLOATING){
				scaleImage = image.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
			}
			ImageIcon icon = new ImageIcon();
			icon.setImage(scaleImage);
			JLabel label = new JLabel(icon,JLabel.CENTER);
			this.add(label,BorderLayout.NORTH);
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	/**
	 * Process called when the user want to set the NVP calibration setting
	 */
	synchronized public void buttonEvent(){
		
	}
	
	/**
	 * Process called when the button is pressed
	 */
	private void buttonPressed(){
		this.setOpaque(true);
		this.setBackground(ColorStore.getInstance().getColorCenterPan().darker());
	}
	
	/**
	 * Process called when the user released the button
	 */
	public void buttonReleased(){
		this.setBackground(ColorStore.getInstance().getColorCenterPan());
		this.setOpaque(false);
	}
	
	/**
	 * Process called when the user click on the mouse
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		this.buttonEvent();
	}

	/**
	 * Process called when the user press on the mouse
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		this.buttonPressed();
	}

	/**
	 * Process called when the user released the mouse (called after a press)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		this.buttonReleased();
	}

	/**
	 * Process called when the mouse enter the panel zone
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		this.setOpaque(true);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setToolTipText(toolTipText);
		this.setBackground(ColorStore.getInstance().getColorButtonMouseEntered());
	}

	/**
	 * Process called when the mouse exit the panel zone
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		this.setBackground(ColorStore.getInstance().getColorCenterPan());
		this.setOpaque(false);
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		if(e.getKeyCode()==this.keyCodeUsed){
			if(ctrlNeed){
				if(ControlStore.getInstance().getCtrlKeyPressed()){
					this.buttonPressed();
					this.buttonEvent();
					this.buttonReleased();
				}
			}else{
				this.buttonPressed();
				this.buttonEvent();
				this.buttonReleased();
			}
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
	}
}
