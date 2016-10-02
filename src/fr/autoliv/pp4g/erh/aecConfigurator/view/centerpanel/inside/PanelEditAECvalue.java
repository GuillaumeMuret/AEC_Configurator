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
	Panel of the first screen (panel in the right) where the AEC value are editable
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECattribute;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelComboBoxGroup;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelLabel;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelTextField;

public class PanelEditAECvalue extends JPanel{
	
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The number of line of the attribute
	 */
	public static final int NB_LINE = 15;
	
	/**
	 * The attribute panel where all the 
	 */
	private JPanel panelAttribute;
	
	/**
	 * The parameter panel name
	 */
	private JPanel panelAttributeName;
	
	/**
	 * The parameter input panel name
	 */
	private JPanel panelAttributeInput;
	
	/**
	 * The parameter unit panel
	 */
	private JPanel panelAttributeUnit;
	
	/**
	 * The floating button panel
	 */
	private JPanel floatingButtonPanel;
	
	/**
	 * The input list : where the parameters are set
	 */
	private ArrayList<ModelTextField> listInput;
	
	/**
	 * the AEC name
	 */
	private JLabel aecName;
	
	/**
	 * the combo box of the group
	 */
	private ModelComboBoxGroup comboGroup;
	
	/**
	 * Constructor of the InfoPanel
	 * @param mainFrame
	 */
	public PanelEditAECvalue(){
		super();
		this.setLayout(new BorderLayout());
		setOpaque(false);
		this.listInput=new ArrayList<ModelTextField>();
		
		this.panelAttribute=new JPanel();
		this.panelAttribute.setOpaque(false);
		this.panelAttribute.setLayout(new BoxLayout(this.panelAttribute,BoxLayout.LINE_AXIS));
		this.panelAttribute.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		this.floatingButtonPanel = new JPanel();
		this.setFloatingButtonPanel();
		
		this.aecName = new JLabel("",SwingConstants.CENTER);
		this.aecName.setPreferredSize(new Dimension(100,100));
		this.aecName.setFont(ConstantFont.FONT_LABEL_AEC);
		this.aecName.setForeground(ColorStore.getInstance().getColorSelectedAEC());
		this.add(this.aecName,BorderLayout.NORTH);
		
		this.panelAttributeName = new JPanel();
		this.panelAttributeName.setOpaque(false);
		this.panelAttributeName.setLayout(new GridLayout(NB_LINE,1));
		this.panelAttributeName.setPreferredSize(ConstantDimension.PANEL_ATTRIBUTE_VALUE_NAME);
		
		this.panelAttributeInput = new JPanel();
		this.panelAttributeInput.setLayout(new GridLayout(NB_LINE,1));
		this.panelAttributeInput.setPreferredSize(ConstantDimension.PANEL_INPUT_ATTRIBUTE_VALUE);
		this.panelAttributeInput.setOpaque(false);
		
		this.panelAttributeUnit = new JPanel();
		this.panelAttributeUnit.setLayout(new GridLayout(NB_LINE,1));
		this.panelAttributeUnit.setPreferredSize(ConstantDimension.PANEL_ATTRIBUTE_VALUE_UNIT);
		
		this.panelAttributeUnit.setOpaque(false);		
	}
	
	/**
	 * Setter of the floating Button Panel
	 */
	public void setFloatingButtonPanel(){
		this.floatingButtonPanel.removeAll();
        this.floatingButtonPanel.setLayout(new BoxLayout(this.floatingButtonPanel,BoxLayout.LINE_AXIS));
        this.floatingButtonPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.floatingButtonPanel.setOpaque(false);
		this.floatingButtonPanel.setPreferredSize(new Dimension(100,80));
		
		this.floatingButtonPanel.add(ControlStore.getInstance().getButtonSave());
		this.floatingButtonPanel.add(ControlStore.getInstance().getButtonEdit());
		
		this.add(floatingButtonPanel,BorderLayout.SOUTH);
	}
	
	/**
	 * process called to change the AEC Name
	 */
	public void setAECtitle(String aecName){
		this.aecName.setForeground(ColorStore.getInstance().getColorSelectedAEC());
		this.aecName.setText(aecName);
	}
	
	/**
	 * Remove the parameter panel
	 */
	public void removeParams(){
		this.panelAttributeName.removeAll();
		this.panelAttributeInput.removeAll();
		this.panelAttributeUnit.removeAll();
		this.listInput =new ArrayList<ModelTextField>();
	}
	
	/**
	 * Add a new parameter to the info panel
	 * @param aecAttribute
	 */
	public void addAttribute(AECattribute aecAttribute){
		JLabel labelAttributeName = new JLabel(aecAttribute.getAttributeName()+" (dec) : ");
		labelAttributeName.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelAttributeName.setForeground(ColorStore.getInstance().getColorFontInfo());
		
		ModelTextField inputAttribute = new ModelTextField(
			aecAttribute.getAttributeValue().toString(), 
			ConstantDimension.PANEL_INPUT_ATTRIBUTE_VALUE,
			ConstantFont.FONT_VALUE_ATTRIBUTE,
			true
		);
		inputAttribute.setMinimumSize(new Dimension(50,0));
		
		this.listInput.add(inputAttribute);
		
		JLabel labelAttributeUnit = new ModelLabel(
			" "+aecAttribute.getAttributeUnit(),
			ConstantFont.FONT_VALUE_ATTRIBUTE,
			ColorStore.getInstance().getColorFontInfo(),
			JLabel.LEFT
		);
		
		labelAttributeUnit.setMinimumSize(new Dimension(50,0));
		labelAttributeUnit.setMaximumSize(new Dimension(50,100));
    	
		this.panelAttributeName.add(labelAttributeName);
		this.panelAttributeInput.add(inputAttribute);
		this.panelAttributeUnit.add(labelAttributeUnit);
    	
		this.panelAttribute.add(panelAttributeUnit);
		this.panelAttribute.add(panelAttributeInput);
		this.panelAttribute.add(panelAttributeName);
		
		this.add(panelAttribute,BorderLayout.CENTER);
	}
	
	/**
	 * Add a new parameter to the info panel
	 * @param aecAttribute
	 */
	public void addReservedAttribute(AECattribute aecAttribute){
		JLabel labelAttributeName = new JLabel(aecAttribute.getAttributeName()+" : ");
		labelAttributeName.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelAttributeName.setForeground(ColorStore.getInstance().getColorFontInfo());
		
		ModelTextField inputAttribute = new ModelTextField(
			DataStore.getInstance().getPaddingByteValue().toUpperCase()+" (Padding byte value)", 
			ConstantDimension.PANEL_INPUT_ATTRIBUTE_VALUE,
			ConstantFont.FONT_VALUE_ATTRIBUTE,
			false
		);
		inputAttribute.setMinimumSize(new Dimension(50,0));
		
		this.listInput.add(inputAttribute);
		
		JLabel labelAttributeUnit = new ModelLabel(
			" "+aecAttribute.getAttributeUnit(),
			ConstantFont.FONT_VALUE_ATTRIBUTE,
			ColorStore.getInstance().getColorFontInfo(),
			JLabel.LEFT
		);
		
		labelAttributeUnit.setMinimumSize(new Dimension(50,0));
		labelAttributeUnit.setMaximumSize(new Dimension(50,100));
    	
		this.panelAttributeName.add(labelAttributeName);
		this.panelAttributeInput.add(inputAttribute);
		this.panelAttributeUnit.add(labelAttributeUnit);
    	
		this.panelAttribute.add(panelAttributeUnit);
		this.panelAttribute.add(panelAttributeInput);
		this.panelAttribute.add(panelAttributeName);
		
		this.add(panelAttribute,BorderLayout.CENTER);
	}
	
	/**
	 * Process called to add the group of this attribute
	 */
	public void addGroupAttribute(AECattribute aecAttribute){
		JLabel labelAttributeName = new JLabel(aecAttribute.getAttributeName()+" : ");
		labelAttributeName.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelAttributeName.setForeground(ColorStore.getInstance().getColorFontInfo());
		
		this.comboGroup = new ModelComboBoxGroup();
		
		// surround with try catch since if the user change the group, we cannot assure the bound
		try{
			this.comboGroup.setSelectedIndex(aecAttribute.getAttributeValue());
		}catch(Exception e){
			this.comboGroup.setSelectedIndex(0);
		}
		
		JLabel labelAttributeUnit = new JLabel(" "+aecAttribute.getAttributeUnit());
		labelAttributeUnit.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelAttributeUnit.setForeground(ColorStore.getInstance().getColorFontInfo());
    	
		this.panelAttributeName.add(labelAttributeName);
		this.panelAttributeInput.add(comboGroup);
		this.panelAttributeUnit.add(labelAttributeUnit);
    	
		this.panelAttribute.add(panelAttributeUnit);
		this.panelAttribute.add(panelAttributeInput);
		this.panelAttribute.add(panelAttributeName);
		
		this.add(panelAttribute,BorderLayout.CENTER);
	}
	
	/**
	 * Getter of the combo box group
	 * @return
	 */
	public ModelComboBoxGroup getComboBoxGroup(){
		return this.comboGroup;
	}
	
	/**
	 * Getter of the input list
	 * @return the input list
	 */
	public ArrayList<ModelTextField> getListInput(){
		return this.listInput;
	}
}
