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
	Class of the combo box group. When the user choose a group of AEC
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.model;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;

public class ModelComboBoxGroup extends JComboBox<String>{
	
	/**
	 * The default UID version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Main Constructor of the ModelComboBoxType
	 * @param comboString
	 */
	public ModelComboBoxGroup(){
		super(DataStore.getInstance().getAECgroupStringList());
		this.setOpaque(false);
		this.setFont(ConstantFont.FONT_STRUCT_ATTRIBUTE);
		this.setPreferredSize(new Dimension(200,0));
		this.getComponent(0).setBackground(ColorStore.getInstance().getColorCenterPan());
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
}