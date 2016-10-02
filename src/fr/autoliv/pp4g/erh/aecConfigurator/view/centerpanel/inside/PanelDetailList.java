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
	Class where the detail list is displayed
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECattribute;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelComboBoxGroup;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelLabel;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelTextField;

public class PanelDetailList extends JPanel {

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The floating button panel
	 */
	private JPanel floatingButtonPanel;
	
	/**
	 * the panel of the AEC list
	 */
	private JPanel panelAEClist;
	
	/**
	 * the scroll panel of the panel AEC list
	 */
	private JScrollPane scrollPanePanelAEClist;
	
	/**
	 * The input list : where the parameters are set
	 */
	private ArrayList<ModelTextField> listAttributeInput;
	
	/**
	 * The input list : where the parameters are set
	 */
	private ArrayList<ModelTextField> listAttributeNameInput;
	
	/**
	 * combo box group model
	 */
	private ArrayList<ModelComboBoxGroup> listComboGroup;
	
	/**
	 * Constructor of the InfoPanel
	 */
	public PanelDetailList(MainFrame mainFrame){
		super();
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.listAttributeInput=new ArrayList<ModelTextField>();
		this.listAttributeNameInput=new ArrayList<ModelTextField>();
		
		this.floatingButtonPanel = new JPanel();
        this.floatingButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.floatingButtonPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.floatingButtonPanel.setOpaque(false);
        this.floatingButtonPanel.setPreferredSize(new Dimension(100,80));
		this.setFloatingButtonPanel();
		
		// Init the panel aec list
		this.panelAEClist = new JPanel();
		this.panelAEClist.setOpaque(false);
		this.displayPanelAEClist();
		
		// Init the scroll pane
	    this.scrollPanePanelAEClist = new JScrollPane(this.panelAEClist);
	    this.scrollPanePanelAEClist.setOpaque(false);
	    this.scrollPanePanelAEClist.getViewport().setOpaque(false);
	    this.scrollPanePanelAEClist.setBorder(BorderFactory.createEmptyBorder());
	    this.scrollPanePanelAEClist.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    this.scrollPanePanelAEClist.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    this.scrollPanePanelAEClist.getVerticalScrollBar().setUnitIncrement(5);
	    this.scrollPanePanelAEClist.getVerticalScrollBar().setValue(0);
	    this.add(scrollPanePanelAEClist);
	}
	
	/**
	 * Setter of the floating Button Panel
	 */
	public void setFloatingButtonPanel(){
		this.floatingButtonPanel.add(ControlStore.getInstance().getButtonSave());
		this.add(floatingButtonPanel,BorderLayout.SOUTH);
	}
	
	/**
	 * Process called to display the panel which display a detailed list
	 */
	public void displayPanelAEClist(){
		panelAEClist.removeAll();
		this.listComboGroup=new ArrayList<ModelComboBoxGroup>();
		
		this.listAttributeInput.removeAll(listAttributeInput);
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		this.panelAEClist.setLayout(new GridLayout(aecList.size()+1,1));
		for(int i = -1;i<aecList.size();i++){
			JPanel panelAECnameAndAttribute = new JPanel();
			panelAECnameAndAttribute.setOpaque(false);
			panelAECnameAndAttribute.setLayout(new BorderLayout());
			panelAECnameAndAttribute.setBorder(BorderFactory.createLineBorder(ColorStore.getInstance().getColorInputBorder()));

			JPanel panelAECname = new JPanel();
			panelAECname.setOpaque(false);
			panelAECname.setLayout(new BorderLayout());
			panelAECname.setPreferredSize(ConstantDimension.PANEL_AEC_NAME_LIST);
			
			AECcomponent aec = aecList.get(0);
			ArrayList<AECattribute> aecAttrList = aec.getAECattributes();
			JPanel panelAECattribute = new JPanel();
			panelAECattribute.setOpaque(false);
			GridLayout layout = new GridLayout(1,aecAttrList.size());
			panelAECattribute.setLayout(layout);
			
			this.addParamList(i,panelAECname,panelAECattribute,panelAECnameAndAttribute);
		}
	}
	
	/**
	 * Process called to add the param list
	 * @param i
	 * @param panelAECname
	 * @param panelAECattribute
	 * @param panelAECnameAndAttribute
	 */
	public void addParamList(int i,JPanel panelAECname,JPanel panelAECattribute,JPanel panelAECnameAndAttribute){
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		AECcomponent aec = aecList.get(0);
		JLabel labelAECname;
		ArrayList<AECattribute> aecAttrList;
		// Add the name of the attribute
		if(i<0){
			aec = aecList.get(0);
			labelAECname = new ModelLabel("",ConstantFont.FONT_LABEL_AEC,ColorStore.getInstance().getColorInitialAEC(),JLabel.LEFT);
			aecAttrList = aec.getAECattributes();
			for(int j=0;j<aecAttrList.size();j++){
				panelAECattribute.add(this.getTitleParamList(aecAttrList.get(j)));
			}
		// Add the value of all the attribute
		}else{
			aec = aecList.get(i);
			labelAECname = new ModelLabel(aec.getAECname(),ConstantFont.FONT_LABEL_AEC,ColorStore.getInstance().getColorInitialAEC(),JLabel.LEFT);
			labelAECname.setOpaque(true);
			labelAECname.setBackground(ColorStore.getInstance().getColorSelectedStructLine());
			labelAECname.setForeground(ColorStore.getInstance().getColorCenterPan());
			aecAttrList = aec.getAECattributes();
			for(int j=0;j<aecAttrList.size();j++){
				if(aecAttrList.get(j).getAttributeName().toUpperCase().equals("GROUP")){
					panelAECattribute.add(this.getAttributeComboGroup(j,aecAttrList.get(j)));
				}else{
					if(aecAttrList.get(j).getAttributeName().toUpperCase().contains("RESERVED BYTE")){
						panelAECattribute.add(this.getAttributeReservedParamList(j));
					}else{
						panelAECattribute.add(this.getAttributeParamList(j,aecAttrList.get(j)));
					}
				}
			}
		}
		panelAECname.add(labelAECname,BorderLayout.NORTH);
		panelAECnameAndAttribute.add(panelAECname,BorderLayout.WEST);
		panelAECnameAndAttribute.add(panelAECattribute,BorderLayout.CENTER);
		this.panelAEClist.add(panelAECnameAndAttribute);
	}
	
	/**
	 * Process called to get the title input param
	 * @param aecAttr
	 * @return
	 */
	private ModelTextField getTitleParamList(AECattribute aecAttr){
		ModelTextField textField = new ModelTextField(
			aecAttr.getAttributeName(),
			new Dimension(50,20),
			ConstantFont.FONT_LABEL_AEC,
			true
		);
		textField.setForeground(ColorStore.getInstance().getColorCenterPan());
		textField.setColor(ColorStore.getInstance().getColorSelectedStructLine(), ColorStore.getInstance().getColorSelectedStructLine());
		this.listAttributeNameInput.add(textField);
		return textField;
	}
	
	/**
	 * Process called to have the attribute input param
	 * @param aecAttr
	 * @return
	 */
	private ModelTextField getAttributeParamList(int j,AECattribute aecAttr){
		ModelTextField textField = new ModelTextField(
			aecAttr.getAttributeValue().toString(),
			new Dimension(50,20),
			ConstantFont.FONT_LABEL_AEC,
			true
		);
		if(Integer.toHexString(aecAttr.getAttributeValue()).length()>(DataStore.getInstance().getAECstructureList().get(j).getAttributeSize()*2))
			textField.setColor(ColorStore.getInstance().getColorError(), ColorStore.getInstance().getColorError());
		this.listAttributeInput.add(textField);
		return textField;
	}
	
	/**
	 * Process called to have the attribute input param
	 * @param aecAttr
	 * @return
	 */
	private ModelTextField getAttributeReservedParamList(int j){
		ModelTextField textField = new ModelTextField(
			DataStore.getInstance().getPaddingByteValue().toUpperCase()+" (Padding byte value)",
			new Dimension(50,20),
			ConstantFont.FONT_LABEL_AEC,
			false
		);
		this.listAttributeInput.add(textField);
		return textField;
	}
	
	/**
	 * Process called to have the attribute input param
	 * @param aecAttr
	 * @return
	 */
	private ModelComboBoxGroup getAttributeComboGroup(int j,AECattribute aecAttr){
		ModelComboBoxGroup comboGroup = new ModelComboBoxGroup();
		try{
			comboGroup.setSelectedIndex(aecAttr.getAttributeValue());
		}catch(Exception e){
			comboGroup.setSelectedIndex(0);
		}
		this.listComboGroup.add(comboGroup);
		return comboGroup;
	}
	
	/**
	 * Getter of the input attribute list
	 * @return the input list
	 */
	public ArrayList<ModelTextField> getListAttributeValueInput(){
		return this.listAttributeInput;
	}
	
	/**
	 * Getter of the input attribute name list
	 * @return the input attribute name list
	 */
	public ArrayList<ModelTextField> getListAttributeNameInput(){
		return this.listAttributeNameInput;
	}
	
	/**
	 * Getter of the combo box group
	 * @return
	 */
	public ArrayList<ModelComboBoxGroup> getListComboGroup(){
		return this.listComboGroup;
	}
}
