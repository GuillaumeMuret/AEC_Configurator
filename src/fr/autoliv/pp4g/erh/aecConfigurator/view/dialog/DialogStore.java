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
	Class where the dialog are displayed
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.aec.AECcontroller;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.component.ControlComboBoxType;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECattribute;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECstructure;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.FileStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.FileToGenerate;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.PanelConfiguration.ModelPanelStructure;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantIcon;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelTextField;

public class DialogStore {
	
	/**
     * Singleton management
     */
    private static DialogStore instance;    
    
    /**
     * Getter of the instance DataStore
     * @return the instance DataStore
     */
    public static DialogStore getInstance () {
        if (instance == null)
            instance = new DialogStore();
        return instance;
    }
    
    ////// Generation C source path //////
    /**
     * The main frame used in all the application
     */
    private MainFrame mainFrame;
    
    /**
     * Setter of the generation folder path
     * @param generationFolderPath
     */
    public void setMainFrame(MainFrame mainFrame){
        this.mainFrame=mainFrame;
    }

    /**
     * Getter of the generation folder path
     * @return
     */
    public MainFrame getMainFrame(){
        return mainFrame;
    }
    
    // The model panel structure
    /**
     * The panel selected
     */
    private ModelPanelStructure modelPanelStructure;
    
    /**
     * Setter of the model panel structure
     * @param generationFolderPath
     */
    public void setModelPanelStructure(ModelPanelStructure modelPanelStructure){
        this.modelPanelStructure=modelPanelStructure;
    }

    /**
     * Getter of the model panel structure
     * @return
     */
    public ModelPanelStructure getModelPanelStructure(){
        return modelPanelStructure;
    }
    
    ////// the different dialog //////
	/**
	 * The dialog of the end of the generation
	 */
	public void displaySuccessGeneration(){
		String[] options = new String[2];
		options[0] = new String("Ok");
		options[1] = new String("Show differences");
		int result = JOptionPane.showOptionDialog(
			this.mainFrame, 
			"Generation success in the folder : \n"
			+ "   *.c and *.h in "+DataStore.getInstance().getPathGenerationCHfolder()+"\n"
			+ "   *.S00 in "+DataStore.getInstance().getPathGenerationS00folder()+"\n"
			+ "   *.arxml in "+DataStore.getInstance().getPathGenerationARXMLfolder()+"\n",
			"Generation Success !",
			0,
			JOptionPane.INFORMATION_MESSAGE,
			getIcon(ConstantIcon.IC_GENERATE),
			options,
			null
		);
		if(result==JOptionPane.YES_OPTION){
			
		}else{
			FileStore.getInstance().setCurrentPage(FileStore.ERH_CFG_PUBLIC_H);
			DialogStore.getInstance().getMainFrame().changePanel(MainFrame.STATE_SHOW_DIFFERENCE);
		}
	}
	
	/**
	 * display error when there is no structure parameter
	 */
	public void displayErrorLastAECattribute(){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"You cannot delete all the attribute !!",
			"Attribute error",
			JOptionPane.ERROR_MESSAGE,
			getIcon(ConstantIcon.IC_DELETE_ATTR)
		);
	}
	
	/**
	 * Process called when the JSON file is not found
	 */
	public void displayErrorJsonFileNotFound(){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"The File "+FileToGenerate.JSON_AEC_DATA+" not found...",
			"JSON file not found error",
			JOptionPane.ERROR_MESSAGE,
			getIcon(ConstantIcon.IC_DELETE_ATTR)
		);
	}
	
	/**
	 * Process called when the JSON file has got an error
	 */
	public void displayErrorJsonFileData(){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"The File "+FileToGenerate.JSON_AEC_DATA+" has got error...",
			"JSON file error",
			JOptionPane.ERROR_MESSAGE,
			getIcon(ConstantIcon.IC_DELETE_ATTR)
		);
	}
	
	/**
	 * Process called to display a dialog that inform the user to change the number input
	 */
	public void displayErrorTextFieldDialog(){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"The input text is not right...\n"
			+ "\tReason : \n"
			+ "\t\t - Maybe the size of the value\n"
			+ "\t\t - Maybe a string instead of integer\n"
			+ "\t\t - Maybe you have not fill the input text\n"
			+ "\t\t - Maybe the first character used\n",
			"Input Text Error",
			JOptionPane.ERROR_MESSAGE,
			getIcon(ConstantIcon.IC_DELETE_ATTR)
		);
	}
	
	/**
	 * Process called when the field is blank
	 */
	public void displayErrorBlankField(){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"You need to fill all the text field !",
			"Blank field error",
			JOptionPane.ERROR_MESSAGE,
			getIcon(ConstantIcon.IC_DELETE_ATTR)
		);
	}
	
	/**
	 * Process called when the file path is not correct !
	 */
	public void displayErrorFolder(){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"You need to choose a file in the same root !",
			"File path error",
			JOptionPane.ERROR_MESSAGE,
			getIcon(ConstantIcon.IC_DELETE_ATTR)
		);
	}
	
	/**
	 * Process called when the memory allocated for the calibration is over !
	 */
	public void displayErrorAECcalibrationSize(){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"You have more memory space than is \nallocated for the AEC calibration...",
			"Memory size error",
			JOptionPane.ERROR_MESSAGE,
			getIcon(ConstantIcon.IC_DELETE_ATTR)
		);
	}
	
	/**
	 * Process called when the memory allocated for the calibration is over if a component is added!
	 */
	public void displayErrorAddingAECattributeSize(){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"You cannot add a new component since the memory\n allocated for the AEC calibration will be over",
			"Memory size error",
			JOptionPane.ERROR_MESSAGE,
			getIcon(ConstantIcon.IC_DELETE_ATTR)
		);
	}
	
	/**
	 * Process called when an error with the attribute size occur
	 */
	public void displayErrorAECattributeSize(){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"You have more attribute memory space than is allocated for the AEC calibration...",
			"Memory size error",
			JOptionPane.ERROR_MESSAGE,
			getIcon(ConstantIcon.IC_DELETE_ATTR)
		);
	}
	
	/**
	 * Process called to display an AEC error with double name in the AEC list
	 */
	public void displayErrorAECdoubleName(){
		String name = DataStore.getInstance().getAEClist().get(DataStore.getInstance().getAECkey()).getAECname();
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"The AEC list has 2 equals AEC name : "+name+" !",
			"AEC double "+name,
			JOptionPane.ERROR_MESSAGE,
			getIcon(ConstantIcon.IC_DELETE_ATTR)
		);
	}
	
	/**
	 * Process called to display an AEC error with double name in the AEC list
	 */
	public void displayErrorAECdoubleAttributeName(){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"The AEC list has 2 equals AEC attribute name !",
			"AEC same attribute",
			JOptionPane.ERROR_MESSAGE,
			getIcon(ConstantIcon.IC_DELETE_ATTR)
		);
	}
	
	/**
	 * Process called to display an error if the AEC reserved are not in the bottom of the list
	 */
	public void displayErrorAECreservedNotInTheBottom(){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"The AEC Reserved must be at the end of the AEC list !",
			"AEC Reserved not in the bottom",
			JOptionPane.ERROR_MESSAGE,
			getIcon(ConstantIcon.IC_DELETE_ATTR)
		);
	}
	
	/**
	 * Process called to display an error if the AEC reserved byte are not in the bottom of the list
	 */
	public void displayErrorAECreservedByteNotInTheBottom(){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"The AEC Reserved Byte must be at the end of the AEC attribute list !",
			"AEC Reserved byte not in the bottom",
			JOptionPane.ERROR_MESSAGE,
			getIcon(ConstantIcon.IC_DELETE_ATTR)
		);
	}
	
	/**
	 * Process called to inform the user that the component aec has been copied
	 * @param aec
	 */
	public void displaySuccessCreationAEC(String aec){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"The component "+aec+" has been copied successfully",
			aec+" copy success!",
			JOptionPane.INFORMATION_MESSAGE,
			getIcon(ConstantIcon.IC_VALID)
		);
	}
	
	/**
	 * Process called to inform the user that a new attribute has been created
	 */
	public void displaySuccessCreationAttribute(){
		JOptionPane.showMessageDialog(
			this.mainFrame, 
			"A new attribute has been created !!",
			"Attribute creation success !",
			JOptionPane.INFORMATION_MESSAGE,
			getIcon(ConstantIcon.IC_VALID)
		);
	}
	
	/**
	 * Process called to have a user confirmation when deleting attribute
	 */
	public boolean displayDeleteAttributeConfirmation(){
		String[] options = new String[2];
		options[0] = new String("Yes");
		options[1] = new String("No");
		AECcomponent aec = DataStore.getInstance().getAEClist().get(DataStore.getInstance().getAECattributeKey());
		String attr = aec.getAECattributes().get(DataStore.getInstance().getAECattributeKey()).getAttributeName();
		int result = JOptionPane.showOptionDialog(
			this.mainFrame,
			new JLabel("Are you sure to delete the attribute : "+attr+" ?"),
			"Confirm attribute suppression", 
			0,			
			JOptionPane.INFORMATION_MESSAGE,
			getIcon(ConstantIcon.IC_QUESTION),
			options,
			null
		);
		if(result==JOptionPane.YES_OPTION){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Process called to ask the user if he want to change the attribute name
	 */
	public boolean displayChangeAECattributeStructureName(String name,String beginString){
		String[] options = new String[2];
		options[0] = new String("Yes");
		options[1] = new String("No");
		int result = JOptionPane.showOptionDialog(
			this.mainFrame,
			new JLabel("Do you want to change this name : "+name+" into this name : "+beginString+name+" ?"),
			"Confirm attribute structure change name", 
			0,			
			JOptionPane.INFORMATION_MESSAGE,
			getIcon(ConstantIcon.IC_QUESTION),
			options,
			null
		);
		if(result==JOptionPane.YES_OPTION){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Process called to have a user confirmation when deleting component
	 */
	public boolean displayDeleteAECconfirmation(){
		String[] options = new String[2];
		options[0] = new String("Yes");
		options[1] = new String("No");
		AECcomponent aec = DataStore.getInstance().getAEClist().get(DataStore.getInstance().getAECkey());
		int result = JOptionPane.showOptionDialog(
			this.mainFrame,
			new JLabel("Are you sure to delete the "+aec.getAECname()+"?"),
			"Confirm AEC suppression", 
			0,			
			JOptionPane.INFORMATION_MESSAGE,
			getIcon(ConstantIcon.IC_QUESTION),
			options,
			null
		);
		if(result==JOptionPane.YES_OPTION){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Process called to display a dialog where the user can get the generation folder path
	 */
	public int displayGenerationCHfolderPathDialog(){
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select the generation C and H folder path");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		if(chooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
			String relativePath = managePath(new java.io.File("").getAbsoluteFile(),chooser.getSelectedFile());
			DataStore.getInstance().setPathGenerationCHfolder(relativePath);
			this.mainFrame.centerPanelConfiguration.getPanelConfiguration().setGenerationCHpathPanel();
			this.mainFrame.centerPanelConfiguration.getPanelConfiguration().repaint();
			return 0;
		}else{
			return 1;
		}
	}
	
	
	/**
	 * Process called to display a dialog where the user can get the generation folder path
	 */
	public int displayGenerationARXMLfolderPathDialog(){
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select the generation ARXML folder path");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		if(chooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
			String relativePath = managePath(new java.io.File("").getAbsoluteFile(),chooser.getSelectedFile());
			DataStore.getInstance().setPathGenerationARXMLfolder(relativePath);
			this.mainFrame.centerPanelConfiguration.getPanelConfiguration().setGenerationARXMLpathPanel();
			this.mainFrame.centerPanelConfiguration.getPanelConfiguration().repaint();
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * Process called to display a dialog where the user can get the generation folder path
	 */
	public int displayGenerationS00folderPathDialog(){
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select the generation S00 folder path");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		if(chooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
			String relativePath = managePath(new java.io.File("").getAbsoluteFile(),chooser.getSelectedFile());
			DataStore.getInstance().setPathGenerationS00folder(relativePath);
			this.mainFrame.centerPanelConfiguration.getPanelConfiguration().setGenerationS00pathPanel();
			this.mainFrame.centerPanelConfiguration.getPanelConfiguration().repaint();
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * Process called to manage the path between the two OS : Windows and Linux
	 */
	private String managePath(File base, File path){
		String relativePath = new String();
		boolean reLoop=true;
		int i=0;
		while(reLoop){
			if(path.toString().contains(base.toString())){
				reLoop=false;
				if(i==0){
					relativePath="./";
				}
			}else{
				relativePath=relativePath+"../";
				base=base.getParentFile();
				if(base == null){
					displayErrorFolder();
					return "./";
				}
			}
			i++;
		}
		return relativePath+base.toURI().relativize(path.toURI()).getPath().toString();
	}
	
	/**
	 * Process called to display a dialog where the user can get the generation JSON file path
	 */
	public void displayGenerationJSONfilePathDialog(){
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("select a valid JSON file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(true);
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File", "txt"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON file", "file"));
		if(chooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
			DataStore.getInstance().setPathGenerationJSONfile(chooser.getSelectedFile().toString());
		}else{
			System.exit(0);
		}
	}
	
	/**
	 * process called to display the new structure parameter dialog
	 */
	public AECstructure displayAddStructureParameter(){
		ModelTextField attributeName = new ModelTextField("",new Dimension(100,30),ConstantFont.FONT_INPUT_AEC_INFO, true);
		ModelTextField attributeDescription = new ModelTextField("",new Dimension(100,30),ConstantFont.FONT_INPUT_AEC_INFO, true);
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel,BoxLayout.PAGE_AXIS));
		
		ControlComboBoxType comboBoxType = new ControlComboBoxType();
		comboBoxType.setFont(ConstantFont.FONT_INPUT_AEC_INFO);

		JLabel labelType = new JLabel("Structure attribute Type");
		labelType.setFont(ConstantFont.FONT_INPUT_AEC_INFO);
		labelType.setForeground(Color.BLACK);		
		dialogPanel.add(labelType);
		dialogPanel.add(comboBoxType);
		dialogPanel.add(Box.createVerticalStrut(15));

		JLabel labelName = new JLabel("Structure attribute Name");
		labelName.setFont(ConstantFont.FONT_INPUT_AEC_INFO);
		labelName.setForeground(Color.BLACK);		
		dialogPanel.add(labelName);
		dialogPanel.add(attributeName);
		dialogPanel.add(Box.createVerticalStrut(15));
		
		JLabel labelDescription = new JLabel("Structure attribute Description");
		labelDescription.setFont(ConstantFont.FONT_INPUT_AEC_INFO);
		labelDescription.setForeground(Color.BLACK);	
		dialogPanel.add(labelDescription);
		dialogPanel.add(attributeDescription);
		
		while(true){
			int result = JOptionPane.showConfirmDialog(
				this.mainFrame,
				dialogPanel,
				"Please enter the new structure's attribute (use in the code)", 
				JOptionPane.OK_CANCEL_OPTION,
				0,
				getIcon(ConstantIcon.IC_QUESTION)
			);
			if(result==JOptionPane.OK_OPTION){
				boolean error=false;
				error|=AECcontroller.analyseTextFieldVariableText(attributeName);
				error|=AECcontroller.analyseTextFieldText(attributeDescription);
				if(!error){
					AECstructure aecStruct = new AECstructure();
					Double size = Math.pow(2, comboBoxType.getSelectedIndex());
					aecStruct.setAttributeSize(size.intValue());
					aecStruct.setAttributeType((String)comboBoxType.getSelectedItem());
					aecStruct.setAttributeName(AECcontroller.analyseAECattributeStructureName(aecStruct,attributeName.getText()));
					aecStruct.setAttributeDescription(attributeDescription.getText());
					return aecStruct;
				}
				DialogStore.getInstance().displayErrorTextFieldDialog();
			}else{
				return null;
			}
		}
	}
	
	/**
	 * Process called to set the parameter label
	 * @param str
	 * @return
	 */
	private JLabel setParamLabel(String str){
		JLabel label = new JLabel(str);
		label.setFont(ConstantFont.FONT_INPUT_AEC_INFO);
		label.setForeground(Color.BLACK);	
		return label;
	}
	
	/**
	 * process called to add components to a panel
	 * @param panel
	 * @param label
	 * @param textField
	 */
	private void addPanelComponent(JPanel panel,JLabel label,JTextField textField){
		panel.add(label);
		panel.add(textField);
		panel.add(Box.createVerticalStrut(15));
	}
	
	/**
	 * display a dialog to set all the AEC component default value
	 */
	public AECattribute displayDefineAllComponentDefaultValue(int size){
		ModelTextField paramName = 			new ModelTextField("", new Dimension(200,30), ConstantFont.FONT_INPUT_AEC_INFO,true);
		ModelTextField paramDescription = 	new ModelTextField("", new Dimension(200,30), ConstantFont.FONT_INPUT_AEC_INFO, true);
		ModelTextField paramValue = 		new ModelTextField("", new Dimension(200,30), ConstantFont.FONT_INPUT_AEC_INFO, true);
		ModelTextField paramUnit = 			new ModelTextField("", new Dimension(200,30), ConstantFont.FONT_INPUT_AEC_INFO, true);
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel,BoxLayout.PAGE_AXIS));
		
		addPanelComponent(dialogPanel,setParamLabel("Parameter name"),paramName);
		addPanelComponent(dialogPanel,setParamLabel("Parameter description"),paramDescription);
		addPanelComponent(dialogPanel,setParamLabel("Parameter value"),paramValue);
		addPanelComponent(dialogPanel,setParamLabel("Parameter unit"),paramUnit);
		
		while(true){
			int result = JOptionPane.showConfirmDialog(
				this.mainFrame,
				dialogPanel,
				"Please enter the new attributes params", 
				JOptionPane.OK_CANCEL_OPTION,
				0,
				getIcon(ConstantIcon.IC_QUESTION)
			);
			if(result==JOptionPane.OK_OPTION){
				boolean error = false;
				error|=AECcontroller.analyseTextFieldText(paramName);
				error|=AECcontroller.analyseTextFieldText(paramDescription);
				error|=AECcontroller.analyseTextFieldText(paramUnit);
				error|=AECcontroller.analyseTextFieldPositiveNumber(paramValue, size*2);
				if(!error){
					AECattribute aECattribute = new AECattribute();
					aECattribute.setParamName(paramName.getText());
					aECattribute.setParamDescription(paramDescription.getText());
					aECattribute.setParamValue(Integer.valueOf(paramValue.getText()));
					aECattribute.setParamUnit(paramUnit.getText());
					return aECattribute;
				}
				DialogStore.getInstance().displayErrorTextFieldDialog();
			}else{
				return null;
			}
		}
	}
	
	/**
	 * process called to display the search dialog
	 */
	public void displaySearchDialog(){
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		String[] aecListString = new String[aecList.size()];
		for(int i = 0 ;i<aecList.size();i++){
			aecListString[i]=aecList.get(i).getAECname();
		}
		JComboBox<String> comboBox = new JComboBox<String>(aecListString);
		comboBox.setFont(ConstantFont.FONT_INPUT_AEC_INFO);
		AutoCompleteDecorator.decorate(comboBox);
		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel,BoxLayout.PAGE_AXIS));
		
		dialogPanel.add(comboBox);
		
		int result = JOptionPane.showConfirmDialog(
			this.mainFrame,
			dialogPanel,
			"Enter the AEC you are looking for", 
			JOptionPane.OK_CANCEL_OPTION,
			0,
			getIcon(ConstantIcon.IC_QUESTION)
		);
		if(result==JOptionPane.OK_OPTION){
			AECcontroller.AECselected(comboBox.getSelectedIndex());
		}
	}
	
	/**
	 * The panel where the AEC group is define
	 */
	private PanelDialogSetAECgroup panelDialogSetAECgroup;
	
	/**
	 * Process called to manage the AEC group
	 */
	public void displayManageAECgroupDialog(){
		this.mainFrame.setFrameState(MainFrame.STATE_MANAGE_GROUP);
		
		this.panelDialogSetAECgroup = new PanelDialogSetAECgroup();
		
		boolean error=true;
		while(error){
			int result = JOptionPane.showConfirmDialog(
				this.mainFrame,
				panelDialogSetAECgroup,
				"Manage the AEC group : ", 
				JOptionPane.OK_CANCEL_OPTION,
				0,
				getIcon(ConstantIcon.IC_EDIT)
			);
			if(result==JOptionPane.OK_OPTION){
				error = panelDialogSetAECgroup.saveConfiguration();
				if(error){
					displayErrorTextFieldDialog();
				}
			}else{
				error=false;
			}
		}
		this.mainFrame.setFrameState(MainFrame.STATE_CONFIGURATION);
	}
	
	/**
	 * Getter of the Panel dialog set aec group
	 * @return
	 */
	public PanelDialogSetAECgroup getPanelDialogSetAECgroup(){
		return this.panelDialogSetAECgroup;
	}
	
	/**
	 * Process called to get the icon at the given address
	 */
	private ImageIcon getIcon(String addr){
		URL url = getClass().getResource(addr);
    	Image image = null;
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon();
		icon.setImage(image);
		return icon;
	}
}