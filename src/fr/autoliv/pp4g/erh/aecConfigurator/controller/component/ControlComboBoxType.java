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
	Class of the combo box of the type. There are just 3 different type in this application.
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.PanelConfiguration;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantString;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;

public class ControlComboBoxType extends JComboBox<String> implements ActionListener, MouseListener{
	
	/**
	 * The default UID version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The selected index 
	 */
	private int theSelectedIndex;
	
	/**
	 * The attribute key
	 */
	private int numAttributeKey;
	
	/**
	 * The model structure panel
	 */
	private PanelConfiguration.ModelPanelStructure modelPanelStruct;
	
	/**
	 * Main Constructor of the ModelComboBoxType
	 * @param comboString
	 */
	public ControlComboBoxType(PanelConfiguration.ModelPanelStructure modelPanelStruct){
		super(ConstantString.TYPE_STRING);
		this.modelPanelStruct=modelPanelStruct;
		this.theSelectedIndex=-1;
		this.setOpaque(false);
		this.setFont(ConstantFont.FONT_STRUCT_ATTRIBUTE);
		this.getComponent(0).setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setTheRenderer();
		this.addActionListener(this);
		this.addMouseListener(this);
	}
	
	/**
	 * Second Constructor of the ModelComboBoxType
	 * @param comboString
	 */
	public ControlComboBoxType(){
		super(ConstantString.TYPE_STRING);
		this.setOpaque(false);
		this.setFont(ConstantFont.FONT_STRUCT_ATTRIBUTE);
		this.getComponent(0).setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setTheRenderer();
	}
	
	/**
	 * Process called to set the renderer
	 */
	private void setTheRenderer(){
		this.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;
	
			@Override
		    public void paint(Graphics g) {
		        setBackground(ColorStore.getInstance().getColorInputBackground());
		        setForeground(ColorStore.getInstance().getColorInputForeground());
		        Border line = BorderFactory.createLineBorder(ColorStore.getInstance().getColorInputBorder());
				Border empty = new EmptyBorder(0,10,0,0);
				setBorder(new CompoundBorder(line,empty));
		        super.paint(g);
		    }
		});
	}
	
	/**
	 * Process called to set the num of the attribute key associate with this combo box
	 * @param numAttributeKey
	 */
	public void setNumAttributeKey(int numAttributeKey){
		this.numAttributeKey=numAttributeKey;
	}
	
	@Override
	public void setSelectedIndex(int theSelectedIndex){
		super.setSelectedIndex(theSelectedIndex);
		this.theSelectedIndex=theSelectedIndex;
	}
	
	/**
	 * Process called when the user choose the comboBoxType
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		if(this.theSelectedIndex!=-1){
			if(this.theSelectedIndex!=this.getSelectedIndex()){
				ControlStore.getInstance().getExecutor().executeCommand(IDCommand.CHANGE_ATTRIBUTE_GROUP);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		DataStore.getInstance().setNewAttributeSelected(this.numAttributeKey);
		DialogStore.getInstance().setModelPanelStructure(this.modelPanelStruct);
		ControlStore.getInstance().getExecutor().executeCommand(IDCommand.SELECT_ATTRIBUTE);
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
		
	}
}
