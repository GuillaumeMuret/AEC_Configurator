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
	Panel where the information of Edition of an AEC is displayed
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonNext;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonPrecedent;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonSwipeLeft;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonSwipeRight;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelComboBoxGroup;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelTextField;

public class PanelEditAECcomponent extends JPanel{

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The number of line
	 */
	public static final int NB_LINE = 11;
	
	/**
	 * The info panel
	 */
	private JPanel infoPanel;
	
	/**
	 * The param panel
	 */
	private JPanel paramPanel;
	
	/**
	 * The component info panel
	 */
	private JPanel componentInfoPanel;
	
	/**
	 * The component info input panel
	 */
	private JPanel componentInfoInputPanel;
	
	/**
	 * The parameter panel name
	 */
	private JPanel componentParamPanel;
	
	/**
	 * The parameter input panel name
	 */
	private JPanel componentParamInputPanel;
	
	/**
	 * The floating button panel
	 */
	private JPanel floatingButtonPanel;
	
	/**
	 * The input list info : where the parameters main info are set
	 */
	private ArrayList<ModelTextField> listInputInfo;
	
	/**
	 * the input list : where just the parameters are set
	 */
	private ArrayList<ModelTextField> listInputParam;
	
	/**
	 * the input list : where the descriptions are set
	 */
	private ArrayList<ModelTextField> listInputDescr;
	
	/**
	 * the combo box of the group
	 */
	private ModelComboBoxGroup comboBoxGroup;
	
	/**
	 * The param num of the nvp component
	 */
	private JLabel paramNum;
	
	/**
	 * The button swipe left
	 */
	private ButtonSwipeLeft buttonSwipeLeft;
	
	/**
	 * The button precedent
	 */
	private ButtonPrecedent buttonPrecedent;
	
	/**
	 * The button next
	 */
	private ButtonNext buttonNext;
	
	/**
	 * The button swipe right
	 */
	private ButtonSwipeRight buttonSwipeRight;
	
	/**
	 * The icon panel
	 */
	private JPanel iconPanel;
	
	/**
	 * Constructor of the InfoPanel
	 * @param mainFrame
	 */
	public PanelEditAECcomponent(MainFrame mainFrame){
		super();
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.listInputInfo=new ArrayList<ModelTextField>();
		this.listInputParam=new ArrayList<ModelTextField>();
		this.listInputDescr=new ArrayList<ModelTextField>();
		
		// create info panel
		this.infoPanel=new JPanel();
		this.infoPanel.setOpaque(false);
		this.infoPanel.setLayout(new BorderLayout());
		this.infoPanel.setPreferredSize(ConstantDimension.PANEL_INFO);
		
		// create parameter panel
		this.paramPanel=new JPanel();
		this.paramPanel.setLayout(new BorderLayout());
		this.paramPanel.setOpaque(false);
				
		// create button attribute button
		this.buttonSwipeLeft=new ButtonSwipeLeft(mainFrame);
		this.buttonPrecedent=new ButtonPrecedent(mainFrame);
		this.buttonNext=new ButtonNext(mainFrame);
		this.buttonSwipeRight=new ButtonSwipeRight(mainFrame);
				
		// create the floating button panel
		this.floatingButtonPanel = new JPanel();
        this.floatingButtonPanel.setLayout(new BoxLayout(this.floatingButtonPanel,BoxLayout.LINE_AXIS));
        this.floatingButtonPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.floatingButtonPanel.setPreferredSize(ConstantDimension.PANEL_FLOATING_BUTTON);
		this.floatingButtonPanel.setOpaque(false);
		this.setFloatingButtonPanel();
		
		// create component info panel
		this.componentInfoPanel = new JPanel();
		this.componentInfoPanel.setLayout(new GridLayout(2,1));
		this.componentInfoPanel.setOpaque(false);
		
		// create component info input panel
		this.componentInfoInputPanel = new JPanel();
		this.componentInfoInputPanel.setLayout(new GridLayout(2,1));
		this.componentInfoInputPanel.setOpaque(false);
		
		// create icon panel
		this.iconPanel = new JPanel();
		this.iconPanel.setLayout(new BoxLayout(this.iconPanel,BoxLayout.LINE_AXIS));
		this.iconPanel.setOpaque(false);
		this.iconPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		// create component param panel
		this.componentParamPanel = new JPanel();
		this.componentParamPanel.setLayout(new GridLayout(NB_LINE,1));
		this.componentParamPanel.setOpaque(false);
		
		// create component info input panel
		this.componentParamInputPanel = new JPanel();
		GridLayout lay = new GridLayout(NB_LINE,2);
		lay.setHgap(5);
		this.componentParamInputPanel.setLayout(lay);
		this.componentParamInputPanel.setOpaque(false);
		
		DataStore.getInstance().setAECattributeKey(0);
	}
	
	/**
	 * Setter of the floating Button Panel
	 */
	public void setFloatingButtonPanel(){
		this.floatingButtonPanel.add(ControlStore.getInstance().getButtonSave());
		this.add(floatingButtonPanel,BorderLayout.SOUTH);
	}
	
	/**
	 * Remove the parameter panel
	 */
	public void removeParams(){
		this.componentParamPanel.removeAll();
		this.componentParamInputPanel.removeAll();
		this.listInputParam =new ArrayList<ModelTextField>();
		this.listInputDescr =new ArrayList<ModelTextField>();
	}
	
	/**
	 * Remove the information panel
	 */
	public void removeInformation(){
		this.componentInfoPanel.removeAll();
		this.componentInfoInputPanel.removeAll();
	}
	
	/**
	 * Process called to create the component info 
	 */
	public void setAECinfo(){
		this.listInputInfo = new ArrayList<ModelTextField>();
		this.iconPanel.removeAll();
		
		int key = DataStore.getInstance().getAECkey();
		if(key==-1)
			key++;
		AECcomponent aec = DataStore.getInstance().getAEClist().get(key);
		this.addComponentInfo("Title  ",aec.getAECname());
		
		this.paramNum = new JLabel("Attribute n° "+Integer.valueOf(DataStore.getInstance().getAECattributeKey()+1));
		this.paramNum.setFont(ConstantFont.FONT_INPUT_AEC_INFO);
		this.paramNum.setForeground(ColorStore.getInstance().getColorSelectedAEC());
		this.paramNum.setPreferredSize(ConstantDimension.PANEL_ATTRIBUTE_AND_ICON);
		
		this.iconPanel.add(this.buttonSwipeLeft);
		this.iconPanel.add(this.buttonPrecedent);
		this.iconPanel.add(this.paramNum);
		this.iconPanel.add(this.buttonNext);
		this.iconPanel.add(this.buttonSwipeRight);
		
		this.infoPanel.add(this.iconPanel,BorderLayout.SOUTH);
		this.infoPanel.revalidate();
	}
	
	/**
	 * Process called to create the component parameters displayed on the panel
	 */
	public void setAECattribute(){
		int currentEditParam = DataStore.getInstance().getAECattributeKey();
		this.paramNum.setText("Attribute n° "+Integer.valueOf(currentEditParam+1));
		this.removeParams();
		
		int key = DataStore.getInstance().getAECkey();
		AECcomponent aec = DataStore.getInstance().getAEClist().get(key);
		
		// Param 0
		this.addComponentParam("Name  ",
			aec.getAECattributes().get(currentEditParam).getAttributeName(),
			aec.getAECattributes().get(currentEditParam).getAttributeNameDescription(),
			true
		);
		// Param 1
		this.addComponentParam("Descritpion  ", 
			aec.getAECattributes().get(currentEditParam).getAttributeDescription(),
			aec.getAECattributes().get(currentEditParam).getAttributeDescriptionDescription(),
			true
		);
		// Param 2 -> Value
		if(aec.getAECattributes().get(currentEditParam).getAttributeName().toUpperCase().equals("GROUP")){
			// If it's a group
			this.addComponentParamComboBox("Group ",
				aec.getAECattributes().get(currentEditParam).getAttributeValue(),
				aec.getAECattributes().get(currentEditParam).getAttributeValueDescription()
			);
		}else if(aec.getAECattributes().get(currentEditParam).getAttributeName().toUpperCase().contains("RESERVED BYTE")){
			// If it's not a group
			this.addComponentParam("Value : ",
				DataStore.getInstance().getPaddingByteValue().toUpperCase()+" (Padding byte value)",
				aec.getAECattributes().get(currentEditParam).getAttributeValueDescription(),
				false
			);
		}else{
			// If it's not a group and not reserved
			this.addComponentParam("Value (dec) ",
				String.valueOf(aec.getAECattributes().get(currentEditParam).getAttributeValue()),
				aec.getAECattributes().get(currentEditParam).getAttributeValueDescription(),
				true
			);
		}
		// Param 3
		this.addComponentParam("Unit  ", 
			String.valueOf(aec.getAECattributes().get(currentEditParam).getAttributeUnit()),
			aec.getAECattributes().get(currentEditParam).getAttributeUnitDescription(),
			true
		);
		// Param 4
		this.addComponentParam("Scaling factor  ",
			String.valueOf(aec.getAECattributes().get(currentEditParam).getAttributeScalingFactor()),
			aec.getAECattributes().get(currentEditParam).getAttributeScalingFactorDescription(),
			true
		);
		// Param 5
		this.addComponentParam("Scaling offset  ", 
			String.valueOf(aec.getAECattributes().get(currentEditParam).getAttributeScalingOffset()),
			aec.getAECattributes().get(currentEditParam).getAttributeScalingOffsetDescription(),
			true
		);
		// Param 6
		this.addComponentParam("Interpreted value  ", 
			String.valueOf(aec.getAECattributes().get(currentEditParam).getAttributeInterpretedValue()),
			aec.getAECattributes().get(currentEditParam).getAttributeInterpretedValueDescription(),
			false
		);
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Add a new parameter to the edition panel
	 * @param paramName
	 */
	public void addComponentParam(String paramName,String param,String description,boolean enable){
		JLabel labelParamName = new JLabel(paramName);
		labelParamName.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelParamName.setForeground(ColorStore.getInstance().getColorFontInfo());
		ModelTextField inputParamAttribute = new ModelTextField(
			param, 
			ConstantDimension.PANEL_INPUT_ATTRIBUTE_VALUE,
			ConstantFont.FONT_VALUE_ATTRIBUTE,
			enable
		);
		this.listInputParam.add(inputParamAttribute);
		
		if(description==null){
			description = new String("/");
		}
		
		ModelTextField inputDescAttribute = new ModelTextField(
			description, 
			ConstantDimension.PANEL_INPUT_ATTRIBUTE_VALUE,
			ConstantFont.FONT_VALUE_ATTRIBUTE,
			true
		);
		this.listInputDescr.add(inputDescAttribute);
		
		this.componentParamPanel.add(labelParamName);
		this.componentParamInputPanel.add(inputParamAttribute);
		this.componentParamInputPanel.add(inputDescAttribute);
    	
		this.paramPanel.add(componentParamPanel,BorderLayout.WEST);
		this.paramPanel.add(componentParamInputPanel,BorderLayout.CENTER);
		
		this.add(paramPanel,BorderLayout.CENTER);
	}
	
	/**
	 * Add a new parameter to the edition panel
	 * @param paramName
	 */
	public void addComponentParamComboBox(String paramName,int param,String description){
		JLabel labelParamName = new JLabel(paramName);
		labelParamName.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelParamName.setForeground(ColorStore.getInstance().getColorFontInfo());
		this.comboBoxGroup = new ModelComboBoxGroup();
		this.comboBoxGroup.setSelectedIndex(param);
		
		if(description==null){
			description = new String("/");
		}
		
		ModelTextField inputDescAttribute = new ModelTextField(
			description, 
			ConstantDimension.PANEL_INPUT_ATTRIBUTE_VALUE,
			ConstantFont.FONT_VALUE_ATTRIBUTE,
			true
		);
		this.listInputDescr.add(inputDescAttribute);
		
		this.componentParamPanel.add(labelParamName);
		this.componentParamInputPanel.add(comboBoxGroup);
		this.componentParamInputPanel.add(inputDescAttribute);
    	
		this.paramPanel.add(componentParamPanel,BorderLayout.WEST);
		this.paramPanel.add(componentParamInputPanel,BorderLayout.CENTER);
		
		this.add(paramPanel,BorderLayout.CENTER);
	}
	
	
	
	/**
	 * Add a new parameter to the edition panel
	 * @param theParam
	 */
	public void addComponentInfo(String theParam,String value){
		JLabel labelParamName = new JLabel(theParam);
		labelParamName.setFont(ConstantFont.FONT_INPUT_AEC_INFO);
		labelParamName.setForeground(ColorStore.getInstance().getColorFontInfo());
		
		ModelTextField inputAttribute = new ModelTextField(
			value, 
			ConstantDimension.PANEL_INPUT_AEC_INFO,
			ConstantFont.FONT_INPUT_AEC_INFO,
			true
		);
		
		this.listInputInfo.add(inputAttribute);
    	
		this.componentInfoPanel.add(labelParamName);
		this.componentInfoInputPanel.add(inputAttribute);
    	
		this.infoPanel.add(componentInfoPanel,BorderLayout.WEST);
		this.infoPanel.add(componentInfoInputPanel,BorderLayout.CENTER);
		
		this.add(infoPanel,BorderLayout.NORTH);
	}
	
	/**
	 * Getter of the input info list
	 * @return the input info list
	 */
	public ArrayList<ModelTextField> getListInputInfo(){
		return this.listInputInfo;
	}
	
	/**
	 * Getter of the input parameter list
	 * @return the input parameter list
	 */
	public ArrayList<ModelTextField> getListInputParam(){
		return this.listInputParam;
	}
	
	/**
	 * Getter of the input description list
	 * @return the input parameter list
	 */
	public ArrayList<ModelTextField> getListInputDescription(){
		return this.listInputDescr;
	}
	
	/**
	 * Getter of the combo box of the group
	 * @return
	 */
	public ModelComboBoxGroup getComboBoxGroup(){
		return this.comboBoxGroup;
	}
	
	/**
	 * process called to initialize the NVP component edition
	 */
	public void setPanelEditAEC(){	
		this.removeParams();
		this.removeInformation();
		
		this.infoPanel.removeAll();
		this.paramPanel.removeAll();

		this.setAECinfo();
		this.setAECattribute();
		this.setFloatingButtonPanel();
	}
}
