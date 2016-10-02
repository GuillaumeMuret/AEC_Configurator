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
	Class of the Panel edit AEC value where the AEC edit value screen is displayed and the 
	save is made
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.aec.AECcontroller;
import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECattribute;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECstructure;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.convertor.ConvertJSONtoAEC;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.LabelAEC;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.PanelAEClist;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.PanelEditAECvalue;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelPanel;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelTextField;

public class CenterPanelEditAECvalue extends ModelPanel {
	
	/**
	 * the serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The panel of the AEC list
	 */
	private PanelAEClist panelAEClist;
	
	/**
	 * The container of the different panel : NVP calibration panel
	 */
	private JPanel containerPanelAEClist;
	
	/**
	 * The edit value panel
	 */
	private PanelEditAECvalue panelEditAECvalue;
	
	/**
	 * the split pane of the list and the info panels
	 */
	private JSplitPane splitPaneEastCenter;
	
	/**
	 * The scroll pane where the list is displayed
	 */
	private JScrollPane scrollPanePanelAEClist;
	
	/**
	 * The main frame where all the component are displayed
	 */
	private MainFrame mainFrame;
	
	/**
	 * Main constructor of the edit value panel
	 * @param mainFrame
	 */
	public CenterPanelEditAECvalue(MainFrame mainFrame){
		super(ConstantDimension.PANEL_CENTER,true,new BorderLayout(),ModelPanel.PANEL_CENTER);
		this.mainFrame=mainFrame;
		this.panelAEClist = new PanelAEClist(mainFrame);
		
		// Init the AEC list
		this.displayPanelAEClist();
		
		// Init the scroll pane
	    this.scrollPanePanelAEClist = new JScrollPane(this.panelAEClist);
	    this.scrollPanePanelAEClist.setOpaque(false);
	    this.scrollPanePanelAEClist.getViewport().setOpaque(false);
	    this.scrollPanePanelAEClist.setBorder(BorderFactory.createEmptyBorder());
	    this.scrollPanePanelAEClist.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    this.scrollPanePanelAEClist.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    this.scrollPanePanelAEClist.getVerticalScrollBar().setUnitIncrement(5);
	    this.scrollPanePanelAEClist.getVerticalScrollBar().setValue(0);
	    
	    // Init the container panel AEC list
	    this.containerPanelAEClist=new JPanel();
	    this.containerPanelAEClist.setOpaque(false);
		this.containerPanelAEClist.setLayout(new BorderLayout());
		this.containerPanelAEClist.add(this.scrollPanePanelAEClist,BorderLayout.CENTER);
		
		// Create info panel
		this.panelEditAECvalue=new PanelEditAECvalue();
		
        //Split of the panels west and center
		this.splitPaneEastCenter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.containerPanelAEClist,this.panelEditAECvalue);
		this.splitPaneEastCenter.setOpaque(false);
		this.splitPaneEastCenter.setDividerSize(5);
		this.splitPaneEastCenter.setContinuousLayout(true);
		this.splitPaneEastCenter.setBorder(BorderFactory.createEmptyBorder());
		
		// Add the container of the list
		this.add(this.splitPaneEastCenter,BorderLayout.CENTER);
	}
	
	/**
	 * Process called to set the AEC list
	 */
	public void displayPanelAEClist(){
		try{
			// Set the panel AEC list where the list of AEC is displayed
			this.setPanelAEClist();
		}catch(AECexception e){
			if(e.cause==AECexception.JSON_FILE_NOT_FOUND){
				DialogStore.getInstance().displayErrorJsonFileNotFound();
			}
			if(e.cause==AECexception.JSON_FILE_ERROR){
				DialogStore.getInstance().displayErrorJsonFileData();
			}
			// Display the dialog to set the JSON file path dialog
			DialogStore.getInstance().displayGenerationJSONfilePathDialog();
			this.displayPanelAEClist();
		}
	}
	
	/**
	 * Process called to set the AEC list panel
	 */
	private void setPanelAEClist() throws AECexception{
		ArrayList<AECcomponent> aecList = new ArrayList<AECcomponent>();
		ConvertJSONtoAEC fileComponent = new ConvertJSONtoAEC();
		
		aecList = fileComponent.getAEClist();
		DataStore.getInstance().setAEClist(aecList);
		for(int i=0;i<aecList.size();i++){
			// convert the model to view
			this.panelAEClist.addAECcomponent(i,new LabelAEC(aecList.get(i),i,this.mainFrame,this.panelAEClist));
		}
		this.panelAEClist.repaint();
	}
	
	/**
	 * Process called to create the component that had been copied before
	 */
	public void createNewAEC(int componentNum){
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		aecList.add(componentNum, new AECcomponent(aecList.get(componentNum)));
		LabelAEC labelAEC = new LabelAEC(
			DataStore.getInstance().getAEClist().get(componentNum),
			componentNum,this.mainFrame,this.panelAEClist
		);
		this.panelAEClist.addAECcomponent(componentNum,labelAEC);
	}
	
	/**
	 * Process called to add the edit value panel
	 * @param nvpComponent
	 */
	public void displayPanelEditValue(){
		int aecKey = DataStore.getInstance().getAECkey();
		AECcomponent aecComponnet = DataStore.getInstance().getAEClist().get(aecKey);
		this.addEditValueAttribute(aecComponnet);
		
		this.splitPaneEastCenter.setDividerLocation(this.mainFrame.getSize().width/2);
		
		this.panelEditAECvalue.setFloatingButtonPanel();
		
		this.manageScrollPane();
	}
	
	/**
	 * Process called to copy the current component
	 */
	public void createNewAEC(){
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		ArrayList<LabelAEC> listLabel = DataStore.getInstance().getAEClabelList();
		
		int currentComponent = DataStore.getInstance().getAECkey();
		if(currentComponent==-1)
			currentComponent++;		
		
		DataStore.getInstance().setAECkey(currentComponent);
		
		// Create the component copy 
		this.createNewAEC(currentComponent);
		
		this.getPanelAEClist().remakeLabelList();
		for(int i = 0;i<aecList.size();i++){
			// Switch the NVP component from the label listener (change the key)
			listLabel.get(i).getLabelAECcontrol().setkey(i);
		}
		DialogStore.getInstance().displaySuccessCreationAEC(aecList.get(currentComponent).getAECname());
	}
	
	/**
	 * Process called to change the AEC position in the list
	 */
	public void changeAECposition(int pos){

		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		ArrayList<LabelAEC> listLabel = DataStore.getInstance().getAEClabelList();
		
		int aecKey = DataStore.getInstance().getAECkey();
		
		// Switch the AEC component from the list
		AECcomponent currentNVPcomponent = aecList.get(aecKey);
		aecList.remove(aecKey);
		aecList.add(aecKey+pos,currentNVPcomponent);
		
		// Switch the AEC component from the label listener (change the key)
		listLabel.get(aecKey).getLabelAECcontrol().setkey(aecKey+pos);
		listLabel.get(aecKey+pos).getLabelAECcontrol().setkey(aecKey);
		
		// Switch the AEC component from the label
		LabelAEC labelAEC = listLabel.get(aecKey);
		listLabel.remove(aecKey);
		listLabel.add(aecKey+pos,labelAEC);
		
		DataStore.getInstance().setAECkey(aecKey+pos);
		
		this.getPanelAEClist().remakeLabelList();
		
		this.manageScrollPane();

		this.panelAEClist.revalidate();
		this.panelAEClist.repaint();
		this.revalidate();
	}
	
	/**
	 * Process called to manage the scrolling pane
	 */
	public void manageScrollPane(){
		int key=DataStore.getInstance().getAECkey();
		int componentHeight = DataStore.getInstance().getAEClabelList().get(0).getHeight();
		
		int scrollValue = this.scrollPanePanelAEClist.getVerticalScrollBar().getValue();
		
		int scrollBarDimension = this.scrollPanePanelAEClist.getVerticalScrollBar().getHeight();
		int nbItemVisible=scrollBarDimension/(componentHeight+1);
		nbItemVisible--;
		
		if(scrollValue>(key-1)*componentHeight)
			this.scrollPanePanelAEClist.getVerticalScrollBar().setValue((key-1)*componentHeight);
		if(scrollValue<(key-nbItemVisible+1)*componentHeight)
			this.scrollPanePanelAEClist.getVerticalScrollBar().setValue((key-nbItemVisible+1)*componentHeight);
	}
	
	/**
	 * Process called to delete the current component
	 */
	public void deleteAECcomponent(){
		// Get the list to update
		ArrayList<AECcomponent> listComponent = DataStore.getInstance().getAEClist();
		ArrayList<LabelAEC> listLabel = DataStore.getInstance().getAEClabelList();
		
		// If there is just one component, don't delete it
		if(listComponent.size()>1){
			if(DialogStore.getInstance().displayDeleteAECconfirmation()){
				int currentComponentNum = DataStore.getInstance().getAECkey();
				
				listComponent.remove(currentComponentNum);
				listLabel.remove(currentComponentNum);
				if(currentComponentNum==listComponent.size()){
					DataStore.getInstance().setAECkey(currentComponentNum-1);
				}
				this.getPanelAEClist().remakeLabelList();
				this.mainFrame.changePanel(MainFrame.STATE_EDIT_VALUE);
			}
		}
	}
	
	
	/**
	 * Remove the info panel from the center panel
	 */
	public void removePanelEditValue(){
		this.remove(this.splitPaneEastCenter);
		this.remove(this.containerPanelAEClist);
		this.add(this.containerPanelAEClist);
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Add the AEC attribute to the edit value panel
	 * @param aecComponent
	 */
	public void addEditValueAttribute(AECcomponent aecComponent){
		ArrayList<AECattribute> aecAttributes = aecComponent.getAECattributes();
		this.panelEditAECvalue.removeParams();
		this.panelEditAECvalue.setAECtitle(aecComponent.getAECname());
		for(int i=0;i<aecAttributes.size();i++){
			if(aecAttributes.get(i).getAttributeName().toUpperCase().equals("GROUP")){
				this.panelEditAECvalue.addGroupAttribute(aecAttributes.get(i));
			}else if (aecAttributes.get(i).getAttributeName().toUpperCase().contains("RESERVED BYTE")){
				this.panelEditAECvalue.addReservedAttribute(aecAttributes.get(i));
			}else{
				this.panelEditAECvalue.addAttribute(aecAttributes.get(i));
			}
		}
	}
	
	/**
	 * Getter of the NVP component panel
	 * @return the NVP component panel
	 */
	public PanelAEClist getPanelAEClist(){
		return this.panelAEClist;
	}
	
	/**
	 * Getter of the Info panel 
	 * @return
	 */
	public PanelEditAECvalue getInfoPanel(){
		return this.panelEditAECvalue;
	}
	
	/**
	 * process called to load the modification of the NVP component
	 */
	public void saveEditionValue() throws AECexception{
		ArrayList<ModelTextField> listInput = this.getInfoPanel().getListInput();
		AECcomponent aec = DataStore.getInstance().getAEClist().get(DataStore.getInstance().getAECkey());
		boolean error = false;
		int offset=0;
		for(int i=0;i<aec.getAECattributes().size();i++){
			if(aec.getAECattributes().get(i).getAttributeName().toUpperCase().equals("GROUP")){
				// set the group
				offset++;
				aec.getAECattributes().get(i).setParamValue(this.panelEditAECvalue.getComboBoxGroup().getSelectedIndex());
			}else{
				AECstructure aecStruct = DataStore.getInstance().getAECstructureList().get(i);
				if(!listInput.get(i-offset).getText().contains("Padding byte value")){
					if(AECcontroller.analyseTextFieldPositiveNumber(listInput.get(i-offset), aecStruct.getAttributeSize()*2)){
						error=true;
					}else{
						aec.getAECattributes().get(i).setParamValue(Integer.valueOf(listInput.get(i-offset).getText()));
					}
				}
			}
		}
		if(error){
			throw new AECexception("Error...", AECexception.TEXT_FIELD_EXCEPTION);
		}
	}
}
