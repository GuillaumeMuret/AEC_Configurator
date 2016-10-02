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
	Class of the Panel configuration where the different information of the screen
	are displayed
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.aec.AECcontroller;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.component.ControlComboBoxType;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.panel.ChooseThemePanel;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.panel.GenerationARXMLpathPanel;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.panel.GenerationHpathPanel;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.panel.GenerationS00pathPanel;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.panel.ManageAECgroupPanel;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECstructure;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelLabel;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelPanel;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelTextField;

public class PanelConfiguration extends JPanel {

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The number of line
	 */
	public static final int NB_LINE_PARAM = 10;
	
	/**
	 * The parameter panel
	 */
	private JPanel paramPanel;
	
	/**
	 * The structure Panel
	 */
	private JPanel structurePanel;
	
	/**
	 * The parameter panel name
	 */
	private JPanel paramNamePanel;
	
	/**
	 * The parameter input panel name
	 */
	private JPanel paramInputPanel;
	
	/**
	 * The panel where the user choose the theme
	 */
	private ChooseThemePanel chooseThemePanel;
	
	/**
	 * The generation path panel
	 */
	private GenerationHpathPanel generationHpathPanel;
	
	/**
	 * The generation path panel
	 */
	private GenerationARXMLpathPanel generationARXMLpathPanel;
	
	/**
	 * The generation path panel
	 */
	private GenerationS00pathPanel generationS00pathPanel;
	
	/**
	 * The panel to manage the AEC group
	 */
	private ManageAECgroupPanel manageAECgroupPanel;
	
	/**
	 * The AEC calibration size panel
	 */
	private JPanel aecCalibrationSizePanel;
	
	/**
	 * The floating button panel
	 */
	private JPanel floatingButtonPanel;
	
	/**
	 * the aec claibration size label
	 */
	private ModelLabel aecCalibrationSizeLabel;
	
	/**
	 * The list of the structure panel
	 */
	private ArrayList<ModelPanelStructure> listStructurePanel;
	
	/**
	 * The input list : where the parameters are set
	 */
	private ArrayList<ModelTextField> listInputParam;
	
	/**
	 * Constructor of the InfoPanel
	 */
	public PanelConfiguration(MainFrame mainFrame){
		super();
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.listInputParam=new ArrayList<ModelTextField>();
		
		this.paramPanel=new JPanel();
		this.paramPanel.setLayout(new BorderLayout());
		this.paramPanel.setOpaque(false);
		this.paramPanel.setPreferredSize(new Dimension(0,200));
		
		this.chooseThemePanel = new ChooseThemePanel();
		
		this.generationHpathPanel = new GenerationHpathPanel();
		this.generationHpathPanel.setBorder(BorderFactory.createLineBorder(ColorStore.getInstance().getColorInputBorder()));
		this.setGenerationCHpathPanel();
		
		this.generationARXMLpathPanel = new GenerationARXMLpathPanel();
		this.generationARXMLpathPanel.setBorder(BorderFactory.createLineBorder(ColorStore.getInstance().getColorInputBorder()));
		this.setGenerationARXMLpathPanel();
		
		this.generationS00pathPanel = new GenerationS00pathPanel();
		this.generationS00pathPanel.setBorder(BorderFactory.createLineBorder(ColorStore.getInstance().getColorInputBorder()));
		this.setGenerationS00pathPanel();
		
		this.manageAECgroupPanel = new ManageAECgroupPanel();
		
		this.aecCalibrationSizePanel = new ModelPanel(new Dimension(260,40),false,new BorderLayout(),ModelPanel.OTHER);
		this.aecCalibrationSizePanel.setLayout(new BoxLayout(this.aecCalibrationSizePanel,BoxLayout.LINE_AXIS));
		this.aecCalibrationSizePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setAECcalibrationSizePanel();
		
		this.paramNamePanel = new JPanel();
		this.paramNamePanel.setLayout(new GridLayout(NB_LINE_PARAM,1));
		this.paramNamePanel.setOpaque(false);
		
		this.paramInputPanel = new JPanel();
		this.paramInputPanel.setLayout(new GridLayout(NB_LINE_PARAM,1));
		this.paramInputPanel.setOpaque(false);
		
		this.structurePanel=new JPanel();
		this.structurePanel.setLayout(new BoxLayout(this.structurePanel,BoxLayout.PAGE_AXIS));
		this.structurePanel.setPreferredSize(getMaximumSize());
		this.structurePanel.setOpaque(false);
		
		this.floatingButtonPanel = new ModelPanel(ConstantDimension.PANEL_FLOATING_BUTTON,false,new FlowLayout(FlowLayout.RIGHT),ModelPanel.OTHER);
        this.floatingButtonPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.setFloatingButtonPanel();
		
		this.createParams();
	}
	
	/**
	 * Process called to set the folder where the files need to be generated
	 */
	public void setGenerationCHpathPanel(){
		this.generationHpathPanel.removeAll();
		
		JLabel generatePathLabel = new JLabel("  "+DataStore.getInstance().getPathGenerationCHfolder());
		generatePathLabel.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		generatePathLabel.setForeground(ColorStore.getInstance().getColorInputForeground());
		
		this.generationHpathPanel.add(generatePathLabel,BorderLayout.CENTER);
		
		JLabel labelBrowse = new JLabel("...");
		labelBrowse.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelBrowse.setForeground(ColorStore.getInstance().getColorInputForeground());
		labelBrowse.setBorder(BorderFactory.createLineBorder(ColorStore.getInstance().getColorInputBorder()));
		
		this.generationHpathPanel.add(labelBrowse,BorderLayout.EAST);
		
		this.generationHpathPanel.revalidate();
	}
	
	/**
	 * Process called to set the folder where the files need to be generated
	 */
	public void setGenerationARXMLpathPanel(){
		this.generationARXMLpathPanel.removeAll();
		
		JLabel generatePathLabel;
		generatePathLabel = new JLabel("  "+DataStore.getInstance().getPathGenerationARXMLfolder());
		generatePathLabel.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		generatePathLabel.setForeground(ColorStore.getInstance().getColorInputForeground());
		
		this.generationARXMLpathPanel.add(generatePathLabel,BorderLayout.CENTER);
		
		JLabel labelBrowse = new JLabel("...");
		labelBrowse.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelBrowse.setForeground(ColorStore.getInstance().getColorInputForeground());
		labelBrowse.setBorder(BorderFactory.createLineBorder(ColorStore.getInstance().getColorInputBorder()));
		
		this.generationARXMLpathPanel.add(labelBrowse,BorderLayout.EAST);
		
		this.generationARXMLpathPanel.revalidate();
	}
	
	/**
	 * Process called to set the folder where the files need to be generated
	 */
	public void setGenerationS00pathPanel(){
		this.generationS00pathPanel.removeAll();
		
		JLabel generatePathLabel;
		generatePathLabel = new JLabel("  "+DataStore.getInstance().getPathGenerationS00folder());
		generatePathLabel.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		generatePathLabel.setForeground(ColorStore.getInstance().getColorInputForeground());
		
		this.generationS00pathPanel.add(generatePathLabel,BorderLayout.CENTER);
		
		JLabel labelBrowse = new JLabel("...");
		labelBrowse.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelBrowse.setForeground(ColorStore.getInstance().getColorInputForeground());
		labelBrowse.setBorder(BorderFactory.createLineBorder(ColorStore.getInstance().getColorInputBorder()));
		
		this.generationS00pathPanel.add(labelBrowse,BorderLayout.EAST);
		
		this.generationS00pathPanel.revalidate();
	}
	
	/**
	 * Process called to set the structure displayed on this panel
	 */
	public void setAECcalibrationSizePanel(){
		this.aecCalibrationSizePanel.removeAll();
		this.aecCalibrationSizeLabel = new ModelLabel("",
			ConstantFont.FONT_VALUE_ATTRIBUTE,
			ColorStore.getInstance().getColorInputForeground(),
			JLabel.LEFT
		);
		setAECcalibrationSizeLabel();
		
		ModelTextField inputAttribute = new ModelTextField(
			DataStore.getInstance().getAECcalibrationMaxSize().toString(), 
			ConstantDimension.PANEL_INPUT_ATTRIBUTE_VALUE,
			ConstantFont.FONT_VALUE_ATTRIBUTE,
			true
		);
		
		this.listInputParam.add(inputAttribute);
		
		JLabel aecCalibrationSizeUnit = new ModelLabel(
			" bytes max",
			ConstantFont.FONT_VALUE_ATTRIBUTE,
			ColorStore.getInstance().getColorInputForeground(),
			JLabel.LEFT
		);
		
		this.aecCalibrationSizePanel.add(aecCalibrationSizeLabel);//,BorderLayout.WEST);
		this.aecCalibrationSizePanel.add(inputAttribute);//,BorderLayout.CENTER);
		this.aecCalibrationSizePanel.add(aecCalibrationSizeUnit);//,BorderLayout.EAST);
		
		this.aecCalibrationSizePanel.revalidate();
	}
	
	/**
	 * Process called to set the calibration size label
	 */
	public void setAECcalibrationSizeLabel(){
		AECcontroller.calculAECcalibrationSize();
		this.aecCalibrationSizeLabel.setText("  "+DataStore.getInstance().getAECcalibrationSize().toString()+" bytes / ");
		this.aecCalibrationSizePanel.revalidate();
	}
	
	/**
	 * Getter of the AEC calibration Size panel
	 */
	public JPanel getAECcalibrationSizePanel(){
		return this.aecCalibrationSizePanel;
	}
	
	/**
	 * Process called to create the parameters that are displayed on this panel
	 */
	private void createParams(){
		this.addChooseThemePanel();
		this.addGenerationCHpathPanel();
		this.addGenerationARXMLpathPanel();
		this.addGenerationS00pathPanel();
		this.addManageAECgroupPanel();
		this.addParams("Software alias in ram : ", DataStore.getInstance().getSoftwareAliasInRam());
		this.addParams("Padding byte value (hex) : ", DataStore.getInstance().getPaddingByteValue().toUpperCase());
		this.addParams("First memory value (hex) : ", Integer.toHexString(DataStore.getInstance().getFirstMemoryValue()).toUpperCase());
		this.addStructureSizePanel();
		this.addParams("Structure name : ", DataStore.getInstance().getAECstructureName());
		
		this.setStructurePanel();
	}
	
	/**
	 * Process called to refresh the generation path panel and the structure size panel
	 */
	public void setPanelConfiguration(){
		this.setAECcalibrationSizeLabel();
		this.setFloatingButtonPanel();
	}
	
	/**
	 * Process called to set the structure panel
	 */
	public void setStructurePanel(){
		ArrayList<AECstructure> aecStruct = DataStore.getInstance().getAECstructureList();

		this.structurePanel.removeAll();
		JLabel structTitleLabel = new JLabel("Structure Configuration : ",JLabel.LEFT);
		structTitleLabel.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		structTitleLabel.setForeground(ColorStore.getInstance().getColorAECnameList());
		
		JPanel titlePan = new JPanel();
		titlePan.setLayout(new BorderLayout());
		titlePan.add(structTitleLabel,BorderLayout.WEST);
		titlePan.setOpaque(false);
		this.structurePanel.add(titlePan);
		
		this.listStructurePanel = new ArrayList<ModelPanelStructure>();
		boolean isSelected;
		this.structurePanel.add(new ModelPanelStructure());
		for(int i=0;i<aecStruct.size();i++){
			if(i==DataStore.getInstance().getAECattributeKey()){
				isSelected=true;
			}else{
				isSelected=false;
			}
			ModelPanelStructure panelStruct = new ModelPanelStructure(
				String.valueOf(i+1), 
				i, 
				aecStruct.get(i).getAttributeName(), 
				aecStruct.get(i).getAttributeDescription(),
				isSelected
			);
			listStructurePanel.add(panelStruct);
			if(	panelStruct.selected){
				panelStruct.setSelectedLine(i);
			}
			this.structurePanel.add(panelStruct);
		}
		
		this.add(structurePanel,BorderLayout.CENTER);
	}
	
	/**
	 * Process called to add the generation path panel to the north panel
	 */
	private void addChooseThemePanel(){
		JLabel labelParamName = new JLabel("Choose the window theme : ");
		labelParamName.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelParamName.setForeground(ColorStore.getInstance().getColorFontInfo());
		this.paramNamePanel.add(labelParamName);
		this.paramInputPanel.add(this.chooseThemePanel);
		this.paramPanel.add(paramNamePanel,BorderLayout.WEST);
		this.paramPanel.add(paramInputPanel,BorderLayout.CENTER);
	}
	
	/**
	 * Process called to add the manage AEC group panel 
	 */
	private void addManageAECgroupPanel(){
		JLabel labelParamName = new JLabel("Manage the AEC group : ");
		labelParamName.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelParamName.setForeground(ColorStore.getInstance().getColorFontInfo());
		this.paramNamePanel.add(labelParamName);
		this.paramInputPanel.add(this.manageAECgroupPanel);
		this.paramPanel.add(paramNamePanel,BorderLayout.WEST);
		this.paramPanel.add(paramInputPanel,BorderLayout.CENTER);
	}
	
	/**
	 * Process called to add the generation path panel to the north panel
	 */
	private void addGenerationCHpathPanel(){
		JLabel labelParamName = new JLabel("Generation .h folder Path : ");
		labelParamName.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelParamName.setForeground(ColorStore.getInstance().getColorFontInfo());
		this.paramNamePanel.add(labelParamName);
		this.paramInputPanel.add(this.generationHpathPanel);
		this.paramPanel.add(paramNamePanel,BorderLayout.WEST);
		this.paramPanel.add(paramInputPanel,BorderLayout.CENTER);
	}
	
	/**
	 * Process called to add the generation path panel to the north panel
	 */
	private void addGenerationARXMLpathPanel(){
		JLabel labelParamName = new JLabel("Generation ARXML folder Path : ");
		labelParamName.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelParamName.setForeground(ColorStore.getInstance().getColorFontInfo());
		this.paramNamePanel.add(labelParamName);
		this.paramInputPanel.add(this.generationARXMLpathPanel);
		this.paramPanel.add(paramNamePanel,BorderLayout.WEST);
		this.paramPanel.add(paramInputPanel,BorderLayout.CENTER);
	}
	
	/**
	 * Process called to add the generation path panel to the north panel
	 */
	private void addGenerationS00pathPanel(){
		JLabel labelParamName = new JLabel("Generation .S00 folder Path : ");
		labelParamName.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelParamName.setForeground(ColorStore.getInstance().getColorFontInfo());
		this.paramNamePanel.add(labelParamName);
		this.paramInputPanel.add(this.generationS00pathPanel);
		this.paramPanel.add(paramNamePanel,BorderLayout.WEST);
		this.paramPanel.add(paramInputPanel,BorderLayout.CENTER);
	}
	
	/**
	 * Process called to add the generation path panel to the north panel
	 */
	private void addStructureSizePanel(){
		JLabel labelParamName = new JLabel("AEC calibration Size : ");
		labelParamName.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelParamName.setForeground(ColorStore.getInstance().getColorFontInfo());
		
		JPanel panelStructureSize = new JPanel();
		panelStructureSize.setLayout(new BorderLayout());
		panelStructureSize.add(this.aecCalibrationSizePanel,BorderLayout.WEST);
		panelStructureSize.setOpaque(false);
		panelStructureSize.setBorder(BorderFactory.createLineBorder(ColorStore.getInstance().getColorInputBorder()));
		
		this.paramNamePanel.add(labelParamName);
		this.paramInputPanel.add(panelStructureSize);
		this.paramPanel.add(paramNamePanel,BorderLayout.WEST);
		this.paramPanel.add(paramInputPanel,BorderLayout.CENTER);
	}
	
	/**
	 * Remove the parameter panel
	 */
	public void removeParams(){
		this.paramNamePanel.removeAll();
		this.paramInputPanel.removeAll();
		this.listInputParam =new ArrayList<ModelTextField>();
	}
	
	/**
	 * Add a new parameter to the info panel
	 * @param theParam
	 */
	public void addParams(String theParam,String value){
		JLabel labelParamName = new JLabel(theParam);
		labelParamName.setFont(ConstantFont.FONT_VALUE_ATTRIBUTE);
		labelParamName.setForeground(ColorStore.getInstance().getColorFontInfo());

		ModelTextField inputAttribute = new ModelTextField(
			value, 
			ConstantDimension.PANEL_INPUT_ATTRIBUTE_VALUE,
			ConstantFont.FONT_VALUE_ATTRIBUTE,
			true
		);
		this.listInputParam.add(inputAttribute);
    	
		this.paramNamePanel.add(labelParamName);
		this.paramInputPanel.add(inputAttribute);
    	
		this.paramPanel.add(paramNamePanel,BorderLayout.WEST);
		this.paramPanel.add(paramInputPanel,BorderLayout.CENTER);
		
		this.add(paramPanel,BorderLayout.NORTH);
	}
	
	/**
	 * Setter of the floating Button Panel
	 */
	public void setFloatingButtonPanel(){
		this.floatingButtonPanel.add(ControlStore.getInstance().getButtonSave());
		this.add(this.floatingButtonPanel,BorderLayout.SOUTH);
	}
	
	/**
	 * Process called to set the structure panel
	 */
	public void setStructurePanelPosition(int pos){
		int attributeSelected=DataStore.getInstance().getAECattributeKey();
		for(int i=0;i<listStructurePanel.size();i++){
			this.structurePanel.remove(listStructurePanel.get(i));
		}
		listStructurePanel.get(attributeSelected).setSelectedLine(attributeSelected);
		
		ModelPanelStructure tmp = listStructurePanel.get(attributeSelected);
		listStructurePanel.set(attributeSelected, listStructurePanel.get(attributeSelected+pos));
		listStructurePanel.set(attributeSelected+pos, tmp);
		
		for(int i=0;i<listStructurePanel.size();i++){
			listStructurePanel.get(i).labelNum.setText("Attribute "+(i+1));
			listStructurePanel.get(i).comboBoxType.setNumAttributeKey(i);
			this.structurePanel.add(listStructurePanel.get(i));
		}
		this.structurePanel.revalidate();
	}
	
	/**
	 * Getter of the input list
	 * @return the input list
	 */
	public ArrayList<ModelTextField> getListInput(){
		return this.listInputParam;
	}
	
	/**
	 * Getter of the structure panel list
	 * @return
	 */
	public ArrayList<ModelPanelStructure> getListStructurePanel() {
		return listStructurePanel;
	}
	
	/**
	 * Nested class of the attribute panel
	 */
	public class ModelPanelStructure extends JPanel implements MouseListener{
		/**
		 * The default version UID
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * The structure panel num
		 */
		private JPanel structureNumPanel;
		
		/**
		 * The structure panel type
		 */
		private JPanel structureTypePanel;
		
		/**
		 * The structure panel name
		 */
		private JPanel structureNamePanel;
		
		/**
		 * The structure panel description
		 */
		private JPanel structureDescriptionPanel;
		
		/**
		 * The boolean to know if this attribute is selected
		 */
		private boolean selected;
		
		/**
		 * The label num of the attribute
		 */
		public JLabel labelNum;
		
		/**
		 * The combo box for this attribute
		 */
		public ControlComboBoxType comboBoxType;
		
		/**
		 * The model text field of the attribute name
		 */
		public ModelTextField inputAttributeName;
		
		/**
		 * The model text field of the attribute description
		 */
		public ModelTextField inputAttributeDescr;
		
		/**
		 * Main constructor of the model panel structure
		 * @param num
		 * @param type
		 * @param name
		 * @param description
		 */
		public ModelPanelStructure(String num, int currentComponent, String name, String description,boolean isSelected){
			super();
			this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
	        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			this.setOpaque(false);
			this.selected=isSelected;
			if(isSelected){
				this.setBackground(Color.black);
				this.setOpaque(true);
			}
			this.setStructurePanels();
			this.addStructNum(num);
			this.addStructType(currentComponent);
			this.addStructName(name);
			this.addStructDescription(description);
			this.addMouseListener(this);
		}
		
		/**
		 * second constructor for the title panel
		 */
		public ModelPanelStructure(){
			super();
			this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
	        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			this.setOpaque(false);
			this.setStructurePanels();
			this.addTitlesStructPanel();
		}
		
		/**
		 * Process called to set the structure panel
		 */
		public void setStructurePanels(){
			this.structureNumPanel=new JPanel();
			this.structureNumPanel.setLayout(new BorderLayout());
			this.structureNumPanel.setOpaque(false);
			
			this.structureTypePanel=new JPanel();
			this.structureTypePanel.setLayout(new BorderLayout());
			this.structureTypePanel.setOpaque(false);
			
			this.structureNamePanel=new JPanel();
			this.structureNamePanel.setLayout(new BorderLayout());
			this.structureNamePanel.setOpaque(false);
			
			this.structureDescriptionPanel=new JPanel();
			this.structureDescriptionPanel.setLayout(new BorderLayout());
			this.structureDescriptionPanel.setOpaque(false);
			
			this.structureNumPanel.setPreferredSize(new Dimension(100,getSize().height));
			this.structureTypePanel.setPreferredSize(new Dimension(100,getSize().height));
			this.structureNamePanel.setPreferredSize(new Dimension(300,getSize().height));
			this.structureDescriptionPanel.setPreferredSize(new Dimension(800,getSize().height));
		}
		
		/**
		 * Process called to add a combo box to the panel configuration
		 */
		public void addStructType(int currentKeyAttribute){
			this.comboBoxType=new ControlComboBoxType(this);
			this.comboBoxType.setNumAttributeKey(currentKeyAttribute);
			String type = DataStore.getInstance().getAECstructureList().
				get(currentKeyAttribute).getAttributeType();
			if(type.equals("uint8")){
				this.comboBoxType.setSelectedIndex(0);
			}			
			if(type.equals("uint16")){
				this.comboBoxType.setSelectedIndex(1);
			}
			if(type.equals("uint32")){
				this.comboBoxType.setSelectedIndex(2);
			}
			
			this.structureTypePanel.add(comboBoxType,BorderLayout.CENTER);
			this.add(this.structureTypePanel);
		}
		
		/**
		 * Process called to add the title struct panel
		 */
		public void addTitlesStructPanel(){
			this.structureNumPanel.add(
				new ModelLabel("Number",ConstantFont.FONT_LABEL_AEC,ColorStore.getInstance().getColorInitialAEC(),JLabel.CENTER),
				BorderLayout.CENTER
			);
			this.structureTypePanel.add(
				new ModelLabel("Type",ConstantFont.FONT_LABEL_AEC,ColorStore.getInstance().getColorInitialAEC(),JLabel.CENTER),
				BorderLayout.CENTER
			);
			this.structureNamePanel.add(
				new ModelLabel("Name",ConstantFont.FONT_LABEL_AEC,ColorStore.getInstance().getColorInitialAEC(),JLabel.CENTER),
				BorderLayout.CENTER
			);
			this.structureDescriptionPanel.add(
				new ModelLabel("Description",ConstantFont.FONT_LABEL_AEC,ColorStore.getInstance().getColorInitialAEC(),JLabel.CENTER),
				BorderLayout.CENTER
			);
			this.add(structureNumPanel);
			this.add(structureTypePanel);
			this.add(structureNamePanel);
			this.add(structureDescriptionPanel);
		}
		
		/**
		 * Process called to add the attribute num to the structure panel
		 */
		private void addStructNum(String num){
			this.labelNum = new ModelLabel(
				"Attribute "+num,
				ConstantFont.FONT_STRUCT_ATTRIBUTE,
				ColorStore.getInstance().getColorInputForeground(),
				JLabel.CENTER
			);
			labelNum.setPreferredSize(ConstantDimension.PANEL_INPUT_ATTRIBUTE_VALUE);
			this.structureNumPanel.add(labelNum,BorderLayout.CENTER);
			this.add(this.structureNumPanel);
		}
		
		/**
		 * Process called to add the struct name to the structure panel
		 */
		private void addStructName(String structureName){
			this.inputAttributeName = new ModelTextField(
				structureName, 
				ConstantDimension.PANEL_INPUT_ATTRIBUTE_VALUE,
				ConstantFont.FONT_STRUCT_ATTRIBUTE,
				true
			);
			this.inputAttributeName.addMouseListener(this);
			this.structureNamePanel.add(inputAttributeName,BorderLayout.CENTER);
			this.add(this.structureNamePanel);
		}
		
		/**
		 * Process called to add a new parameter to the structure panel
		 */
		private void addStructDescription(String structureDescripiton){
			this.inputAttributeDescr = new ModelTextField(
				structureDescripiton, 
				ConstantDimension.PANEL_INPUT_ATTRIBUTE_VALUE,
				ConstantFont.FONT_STRUCT_ATTRIBUTE,
				true
			);
			this.inputAttributeDescr.addMouseListener(this);
			this.structureDescriptionPanel.add(inputAttributeDescr,BorderLayout.CENTER);
			this.add(this.structureDescriptionPanel);
		}
		
		/**
		 * Process called to set the color of the selected line
		 */
		public void setSelectedLine(int i){
			listStructurePanel.get(i).setOpaque(true);
			listStructurePanel.get(i).setBackground(ColorStore.getInstance().getColorSelectedStructLine());
			listStructurePanel.get(i).inputAttributeName.setColor(ColorStore.getInstance().getColorSelectedStructLine(), ColorStore.getInstance().getColorSelectedStructLine());
			listStructurePanel.get(i).inputAttributeDescr.setColor(ColorStore.getInstance().getColorSelectedStructLine(), ColorStore.getInstance().getColorSelectedStructLine());
		}
		
		/**
		 * Process called to set the initial panels structure color and opacity
		 */
		public void setInitialStruct(){
			for(int i=0;i<listStructurePanel.size();i++){
				listStructurePanel.get(i).selected=false;
				listStructurePanel.get(i).setBackground(ColorStore.getInstance().getColorCenterPan());
				listStructurePanel.get(i).inputAttributeName.setColor(ColorStore.getInstance().getColorInputBackground(),ColorStore.getInstance().getColorInputBackgroundGradient());
				listStructurePanel.get(i).inputAttributeDescr.setColor(ColorStore.getInstance().getColorInputBackground(),ColorStore.getInstance().getColorInputBackgroundGradient());
				listStructurePanel.get(i).setOpaque(false);
				listStructurePanel.get(i).revalidate();
			}
		}
		

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			this.setInitialStruct();
			this.selected=true;
			for(int i=0;i<listStructurePanel.size();i++){
				if(listStructurePanel.get(i).selected){
					DataStore.getInstance().setAECattributeKey(i);
					this.setSelectedLine(i);
				}
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
