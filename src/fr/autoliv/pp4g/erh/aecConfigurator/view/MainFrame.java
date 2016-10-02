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
	Class of the main frame. The second class called when the user launch the program
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.aec.AECcontroller;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.Executor;
import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECstructure;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.json.GenerateJSONfile;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.CenterPanelConfiguration;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.CenterPanelDetailList;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.CenterPanelEditAECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.CenterPanelEditAECvalue;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.CenterPanelShowDifference;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ConstantColor;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantIcon;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;

public class MainFrame extends JFrame implements WindowFocusListener{
	
	/**
	 * The different state of the screen
	 */
	public static final int NO_STATE 				= 0;
	public static final int STATE_EDIT_VALUE 		= 1;
	public static final int STATE_EDIT_AEC		 	= 2;
	public static final int STATE_NEW_AEC 			= 3;
	public static final int STATE_CONFIGURATION 	= 4;
	public static final int STATE_DETAIL_LIST	 	= 5;
	public static final int STATE_SHOW_DIFFERENCE	= 6;
	
	public static final int STATE_MANAGE_GROUP		= 7;
	
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The panel containing the AEC list : 
	 */
	public CenterPanelEditAECvalue centerPanelEditAECvalue;
	
	/**
	 * The panel where the user set the generation parameters
	 */
	public CenterPanelConfiguration centerPanelConfiguration;
	
	/**
	 * The panel where the user set the component attributes
	 */
	public CenterPanelEditAECcomponent centerPanelEditAECcomponent;
	
	/**
	 * The panel where the AEC will be displayed as a big list
	 */
	public CenterPanelDetailList centerPanelDetailList;
	
	/**
	 * The panel where the files differences will be displayed
	 */
	public CenterPanelShowDifference centerPanelShowDifference;
	
	/**
	 * The panel where the title will be displayed
	 */
    private JPanel titlePanel;
    
    /**
     * The main panel where every other panel will be added
     */
    private JPanel mainPanel;
    
    /**
     * The top panel
     */    
    private JPanel topPanel;

    /**
     * The action bar panel where all the icon and the screen title will be displayed
     */
    public ActionBarPanel actionBarPanel;
    
    /**
     * The panel of the autoliv Logo
     */
    private JPanel panelLogoAutoliv;
    
    /**
     * The frame state (see the 5 states)
     */
    private int frameState;
    
    /**
     * Constructor of the main frame
     */
    public MainFrame(){
    	// Init the frame
        this.setTitle("AEC_Configurator");
        this.setSize(new Dimension(30000,30000));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.frameState=MainFrame.NO_STATE;
        
        // Set the icon of the frame
        ImageIcon img = new ImageIcon(getClass().getResource(ConstantIcon.IC_GENERATE));
        this.setIconImage(img.getImage());
        
        // Init the dialog store
        DialogStore.getInstance().setMainFrame(this);
        
        // Init the data store
        DataStore.getInstance().setAECkey(0);
        
        // Init the control store
        ControlStore.getInstance().setExecutor(new Executor());
        
        // Create the main panel
        this.mainPanel= new JPanel();
        this.mainPanel.setPreferredSize(new Dimension(30000,30000));
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel,BoxLayout.Y_AXIS));
        this.mainPanel.setBackground(ColorStore.getInstance().getColorCenterPan());
        this.mainPanel.setOpaque(true);
        
        // Create the edit value panel
        this.centerPanelEditAECvalue = new CenterPanelEditAECvalue(this);
        
        // Create the setting panel
        this.centerPanelConfiguration = new CenterPanelConfiguration(this);
        
        // Create the edition panel
        this.centerPanelEditAECcomponent = new CenterPanelEditAECcomponent(this);
        
        // Create the list panel 
        this.centerPanelDetailList = new CenterPanelDetailList(this);
        
        // Create the show diff panel
        this.centerPanelShowDifference = new CenterPanelShowDifference(this);
        
        // Create the top panel
        this.createTopPanel();
        
        // Create the action bar panel
        this.actionBarPanel = new ActionBarPanel(this);
        
        // Add the component to the main panel
        this.mainPanel.add(this.topPanel);
        this.mainPanel.add(this.actionBarPanel);
        this.mainPanel.add(this.centerPanelEditAECvalue);
        
        // Init the generation file
        DataStore.getInstance().setGenerateJSONfileClass(new GenerateJSONfile());
        
        // Add the main panel to the main frame
        this.setContentPane(mainPanel);
        
        // Add the window focus listener : use to know if the frame has the focus or not
        this.addWindowFocusListener(this);
        
        // Display the main frame !
        this.setVisible(true); 
        
        // Manage the first panel
        this.manageFirstPanel();
    }
    
    /**
     * Process called to set the first panel view
     */
    private void manageFirstPanel(){
    	DataStore.getInstance().getAEClabelList().get(0).setForeground(ColorStore.getInstance().getColorSelectedAEC());
        this.changePanel(STATE_EDIT_VALUE);
        try{
        	AECcontroller.analyseMemorySpace();
        	AECcontroller.analyseAttributeSpace();
        }catch(AECexception aecException){
			if(aecException.getExceptionCause()==AECexception.AEC_CALIBRATION_SIZE){
				DialogStore.getInstance().displayErrorAECcalibrationSize();
				DialogStore.getInstance().getMainFrame().changePanel(MainFrame.STATE_CONFIGURATION);
			}
			if(aecException.getExceptionCause()==AECexception.AEC_ATTRIBUTE_SIZE){
				DialogStore.getInstance().displayErrorAECattributeSize();
				DialogStore.getInstance().getMainFrame().changePanel(MainFrame.STATE_DETAIL_LIST);
			}
        }
    }
    
    /**
     * Process called to create the top panel
     */
    public void createTopPanel(){
        // Init the top panel
        this.topPanel = new JPanel();
        this.topPanel.setPreferredSize(ConstantDimension.PANEL_TOP);
        this.topPanel.setMinimumSize(ConstantDimension.PANEL_TOP);
        this.topPanel.setMaximumSize(ConstantDimension.PANEL_TOP);
        this.topPanel.setLayout(new BorderLayout());
        this.topPanel.setOpaque(true);
        this.topPanel.setBackground(ColorStore.getInstance().getColorCenterPan());
        
        // Init title panel where the AEC configurator label will be display   
        this.createLogoAECconfigurator();
        
        // Create the logo autolov
        this.createLogoAutoliv(BorderLayout.EAST);
    }
    
    /**
     * Process called to create the AEC configurator title
     */
    private void createLogoAECconfigurator(){
    	this.titlePanel = new JPanel();
    	this.titlePanel.setLayout(new BorderLayout());
    	this.titlePanel.setOpaque(false);
    	JLabel label = new JLabel("AEC Configurator",JLabel.CENTER);
    	label.setFont(ConstantFont.FONT_TITLE_AEC_CONFIGURATOR);
    	label.setForeground(ConstantColor.COLOR_SELECTED_STRUCT_LINE_DARK);
    	label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    	this.titlePanel.add(label);
    	this.topPanel.add(this.titlePanel,BorderLayout.WEST);
    }
    
    /**
     * Process called to create the autoliv logo
     */
    public void createLogoAutoliv(String orientation){
    	this.panelLogoAutoliv = new JPanel();
    	URL url = getClass().getResource(ConstantIcon.IC_AUTOLIV);
    	Image image;
		try {
			image = ImageIO.read(url);
			ImageIcon icon = new ImageIcon();
			icon.setImage(image);
			JLabel labelLogoAutoliv = new JLabel(icon,JLabel.CENTER);
			panelLogoAutoliv.setBackground(ColorStore.getInstance().getColorCenterPan());
			panelLogoAutoliv.add(labelLogoAutoliv);
			this.topPanel.add(panelLogoAutoliv,orientation);
		} catch (IOException e) {
			e.printStackTrace();
		}			
    }
    
    /**
     * Process called to delete the current attribute
     */
    public void deleteAECattribute(){
    	ArrayList<AECstructure> listStruct = DataStore.getInstance().getAECstructureList();
		// condition to know if the component has just one parameter.
		if(!(listStruct.size()==1)){
			AECcontroller.deleteAECattribute();
			if(frameState==STATE_EDIT_AEC||frameState==STATE_NEW_AEC){
				this.centerPanelEditAECcomponent.getPanelEditAEC().setAECattribute();
			}
			if(frameState==STATE_CONFIGURATION){
				this.centerPanelConfiguration.getPanelConfiguration().setStructurePanel();
			}
		}else{
			DialogStore.getInstance().displayErrorLastAECattribute();
		}
    }
   
    /**
     * Process called to save the NVP calibration
     */
    public void saveCalibration() throws AECexception{
    	switch(frameState){
    		case STATE_EDIT_VALUE:
    			this.centerPanelEditAECvalue.saveEditionValue();
    			break;
    			
    		case STATE_CONFIGURATION:
    			this.centerPanelConfiguration.saveConfiguration();
    			break;
    			
    		case STATE_EDIT_AEC:
    			this.centerPanelEditAECcomponent.saveEditionAEC();
    			break;
    			
    		case STATE_NEW_AEC:
    			this.centerPanelEditAECcomponent.saveEditionAEC();
    			break;
    			
    		case STATE_DETAIL_LIST:
    			this.centerPanelDetailList.saveConfiguration();
    	}
		DataStore.getInstance().getGenerateJSONfileClass().execute();
    }
    
    /**
     * Change the panel : depends on the state machine
     */
    public void changePanel(int state){
    	
    	// Manage Panel
    	manageRemoveCenterPanel(state);
    	
    	// Set the current state
    	this.frameState = state;
		
    	// State machine of the changePanel
    	switch(frameState){
    		case STATE_EDIT_VALUE:
    			this.actionBarPanel.updateActionBarPanel(ActionBarPanel.STATE_EDIT_VALUE);
    			this.centerPanelEditAECvalue.displayPanelEditValue();
    			this.mainPanel.add(this.centerPanelEditAECvalue,BorderLayout.CENTER);
    			break;    		
    			
    		case STATE_EDIT_AEC:
    			this.actionBarPanel.updateActionBarPanel(ActionBarPanel.STATE_EDIT_AEC);
    			this.centerPanelEditAECcomponent.setPanelEditAEC();
    			this.mainPanel.add(this.centerPanelEditAECcomponent,BorderLayout.CENTER);
    			break;
    			
    		case STATE_NEW_AEC:
    			this.actionBarPanel.updateActionBarPanel(ActionBarPanel.STATE_NEW_AEC);
    			this.centerPanelEditAECcomponent.setPanelEditAEC();
    			this.mainPanel.add(this.centerPanelEditAECcomponent,BorderLayout.CENTER);
    			break;
    			
    		case STATE_CONFIGURATION:
    			this.actionBarPanel.updateActionBarPanel(ActionBarPanel.STATE_CONFIGURATION);
    			this.centerPanelConfiguration.setPanelConfiguration();
    			this.mainPanel.add(this.centerPanelConfiguration,BorderLayout.CENTER);
    			break;
    			
    		case STATE_DETAIL_LIST:
    			this.actionBarPanel.updateActionBarPanel(ActionBarPanel.STATE_DETAIL_LIST);
    			this.centerPanelDetailList.displayPanelList();
    			this.mainPanel.add(this.centerPanelDetailList,BorderLayout.CENTER);
    			break;
    			
    		case STATE_SHOW_DIFFERENCE:
    			this.actionBarPanel.updateActionBarPanel(ActionBarPanel.STATE_SHOW_DIFFERENCE);
    			this.centerPanelShowDifference.displayPanelShowDifference();
    			this.mainPanel.add(this.centerPanelShowDifference,BorderLayout.CENTER);
    	}
    	// request the focus to be able to select an input field with tab button
    	this.requestFocus();
    	
    	// Repaint and re-validate
    	this.mainPanel.revalidate();
    	this.mainPanel.repaint();
    }
    
    /**
     * Process called to manage the center panel 
     * @param newState
     */
    private void manageRemoveCenterPanel(int newState){
    	// Remove all the center panel 
		this.mainPanel.remove(this.centerPanelEditAECvalue);
    	this.mainPanel.remove(this.centerPanelConfiguration); 
    	this.mainPanel.remove(this.centerPanelEditAECcomponent); 
    	this.mainPanel.remove(this.centerPanelDetailList); 
    	this.mainPanel.remove(this.centerPanelShowDifference); 
    }
    
    /**
     * Process called to repaint the mainFrame component
     */
    public void repaintAll(){
    	this.actionBarPanel.setBackground(ColorStore.getInstance().getColorCenterPan());
    	this.actionBarPanel.getTitleLabel().setForeground(ColorStore.getInstance().getColorActionBarTitle());
        this.actionBarPanel.setBorder(BorderFactory.createLineBorder(ColorStore.getInstance().getColorFontInfo()));
    	this.actionBarPanel.repaint();
		this.topPanel.setBackground(ColorStore.getInstance().getColorCenterPan());
		this.topPanel.repaint();
    	this.mainPanel.repaint();
    	this.topPanel.add(new JLabel(),BorderLayout.EAST);
    	this.panelLogoAutoliv.setBackground(ColorStore.getInstance().getColorCenterPan());
    }
    
    /**
     * Getter of the current state of the main frame
     * @return
     */
    public int getFrameState(){
    	return this.frameState;
    }
    
    /**
     * Setter of the current state of the main frame
     */
    public void setFrameState(int frameState){
    	this.frameState = frameState;
    }
    
	/**
	 * Process called when the frame has the focus on
	 */
	@Override
	public void windowGainedFocus(WindowEvent e) {
		// Register to native hook : use to have the key listener every where 
		try{
			GlobalScreen.registerNativeHook();
		}catch(NativeHookException nhe){
			nhe.printStackTrace();
		}		
	}

	/**
	 * Process called when the frame loose the focus 
	 */
	@Override
	public void windowLostFocus(WindowEvent e) {
		// Unregister to native hook : use to have the key listener every where 
		ControlStore.getInstance().setCtrlKeyPressed(false);
		try{
			GlobalScreen.unregisterNativeHook();
		}catch(NativeHookException nhe){
			nhe.printStackTrace();
		}
	}
}