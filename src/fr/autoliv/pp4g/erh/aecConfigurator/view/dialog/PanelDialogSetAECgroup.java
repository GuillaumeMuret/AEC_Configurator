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
	Class of the Panel dialog where the user manage the AEC group
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.dialog;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.aec.AECcontroller;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECattribute;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECgroup;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelPanel;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelTextField;

public class PanelDialogSetAECgroup extends ModelPanel{
	
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The panel where the AEC group list will be displayed
	 */
	private JPanel panelAECgroupList;
	
	/**
	 * The list of all the panel AEC list
	 */
	public ArrayList<PanelAECgroup> listPanelAECgroupList;
	
	/**
	 * The group selected
	 */
	private int selectedGroup;

	/**
	 * Main constructor
	 */
	public PanelDialogSetAECgroup(){
		super(new Dimension(600,600),true,new BorderLayout(),ModelPanel.PANEL_CENTER);
		this.listPanelAECgroupList=new ArrayList<PanelAECgroup>();
		this.createPanelListAECgroup();
		this.createPanelIconAECgroup();
		aecGroupSelected(0);
	}
	
	/**
	 * Procedure to create the AEC list group
	 */
	public void createPanelListAECgroup(){
		this.panelAECgroupList = new JPanel();
		this.panelAECgroupList.setLayout(new BoxLayout(this.panelAECgroupList,BoxLayout.PAGE_AXIS));
		this.panelAECgroupList.setOpaque(false);
		
		ArrayList<AECgroup> aecGroupList = DataStore.getInstance().getAECgroupList();
		
		this.panelAECgroupList.add(Box.createVerticalStrut(20));
		this.panelAECgroupList.add(new PanelAECgroupLegend());
		this.panelAECgroupList.add(Box.createVerticalStrut(20));
		for(int i=0;i<aecGroupList.size();i++){
			PanelAECgroup panelAECgroup = new PanelAECgroup(i,aecGroupList.get(i));
			this.panelAECgroupList.add(panelAECgroup);
			this.listPanelAECgroupList.add(panelAECgroup);
		}
		this.add(this.panelAECgroupList,BorderLayout.CENTER);
	}
	
	/**
	 * Process called to create the icon AEC group
	 */
	public void createPanelIconAECgroup(){
		JPanel actionBarPanel = new JPanel();
		actionBarPanel.setLayout(new BorderLayout());
		actionBarPanel.setOpaque(false);
		
		JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new BoxLayout(iconPanel,BoxLayout.LINE_AXIS));
        iconPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		iconPanel.setOpaque(false);
		
		iconPanel.add(ControlStore.getInstance().getButtonTakeGroupDown());
		iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
		iconPanel.add(ControlStore.getInstance().getButtonTakeGroupUp());
		iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
		iconPanel.add(ControlStore.getInstance().getButtonDeleteGroup());
		iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
		iconPanel.add(ControlStore.getInstance().getButtonAddGroup());
		iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
		
		actionBarPanel.add(iconPanel,BorderLayout.EAST);
		this.add(actionBarPanel,BorderLayout.NORTH);
	}
	
	/**
	 * Process called to init the panel color
	 */
	public void initPanelColor(){
		for(int i=0;i<listPanelAECgroupList.size();i++){
			listPanelAECgroupList.get(i).getLabelNumGroup().setOpaque(false);
			listPanelAECgroupList.get(i).getLabelNumGroup().repaint();
			listPanelAECgroupList.get(i).gettFieldName().setColor(ColorStore.getInstance().getColorInputBackground(),ColorStore.getInstance().getColorInputBackgroundGradient());
			listPanelAECgroupList.get(i).gettFieldValue().setColor(ColorStore.getInstance().getColorInputBackground(),ColorStore.getInstance().getColorInputBackgroundGradient());
		}
	}
	
	/**
	 * Process called to select the AEC group in param
	 * @param i
	 */
	public void aecGroupSelected(int i){
		initPanelColor();
		this.selectedGroup=i;
		listPanelAECgroupList.get(i).getLabelNumGroup().setOpaque(true);
		listPanelAECgroupList.get(i).getLabelNumGroup().setBackground(ColorStore.getInstance().getColorSelectedStructLine());
		listPanelAECgroupList.get(i).getLabelNumGroup().repaint();
		listPanelAECgroupList.get(i).gettFieldName().setColor(ColorStore.getInstance().getColorSelectedStructLine(),ColorStore.getInstance().getColorSelectedStructLine());
		listPanelAECgroupList.get(i).gettFieldValue().setColor(ColorStore.getInstance().getColorSelectedStructLine(),ColorStore.getInstance().getColorSelectedStructLine());
	}
	
	/**
	 * Switch the position of the AEC group with the previous or the next
	 * @param pos
	 */
	public void switchAECgroup(int pos){
		if(listPanelAECgroupList.size()>1){
			if((pos==-1&&this.selectedGroup>0)||(pos==1&&this.selectedGroup<listPanelAECgroupList.size()-1)){
				this.removeTheGroupPanel();
				PanelAECgroup tmp = listPanelAECgroupList.get(this.selectedGroup);
				this.listPanelAECgroupList.set(this.selectedGroup, listPanelAECgroupList.get(this.selectedGroup+pos));
				this.listPanelAECgroupList.set(this.selectedGroup+pos, tmp);
				this.addTheGroupPanel();
				this.aecGroupSelected(this.selectedGroup+pos);
			}
		}
		this.requestFocus();
	}
	
	/**
	 * Process called to add a new AEC group
	 */
	public void addAECgroup(){
		this.removeTheGroupPanel();
		PanelAECgroup newPanelAECgroup = new PanelAECgroup(this.selectedGroup+1, new AECgroup());
		this.listPanelAECgroupList.add(this.selectedGroup+1, newPanelAECgroup);
		this.addTheGroupPanel();
		this.aecGroupSelected(this.selectedGroup+1);
		this.listPanelAECgroupList.get(this.selectedGroup).requestFocus();
	}
	
	/**
	 * Process called to delete the selected AEC group
	 */
	public void deleteAECgroup(){
		if(this.listPanelAECgroupList.size()>1){
			this.removeTheGroupPanel();
			this.listPanelAECgroupList.remove(this.selectedGroup);
			this.addTheGroupPanel();
			if(this.selectedGroup>this.listPanelAECgroupList.size()-1){
				this.aecGroupSelected(this.selectedGroup-1);
			}else{
				this.aecGroupSelected(this.selectedGroup);
			}
			this.listPanelAECgroupList.get(this.selectedGroup).requestFocus();
		}
	}
	
	/**
	 * Process called to remove the group panel
	 */
	public void removeTheGroupPanel(){
		for(int i=0;i<listPanelAECgroupList.size();i++){
			this.panelAECgroupList.remove(listPanelAECgroupList.get(i));
		}
	}
	
	/**
	 * Process called to add the group panel
	 */
	public void addTheGroupPanel(){
		for(int i=0;i<listPanelAECgroupList.size();i++){
			this.panelAECgroupList.add(listPanelAECgroupList.get(i));
			listPanelAECgroupList.get(i).setGroupNumLabel(i);
		}
		this.panelAECgroupList.revalidate();
		this.panelAECgroupList.repaint();
	}
	
	/**
	 * Process called to manage the value of the box when the user switch place or delete or add a group
	 */
	private void manageAECcomboBox(ArrayList<AECgroup> newAECgroupList){
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		ArrayList<AECgroup> oldAECgroupList = DataStore.getInstance().getAECgroupList();
		for(int i=0;i<aecList.size();i++){
			ArrayList<AECattribute> aecAttrList = aecList.get(i).getAECattributes();
			for(int j=0;j<aecAttrList.size();j++){
				if(aecAttrList.get(j).getAttributeName().toUpperCase().contains("GROUP")){
					int k=0;
					boolean done=false;
					do{
						if(oldAECgroupList.get(aecAttrList.get(j).getAttributeValue()).getAECgroupName().toUpperCase().contains(newAECgroupList.get(k).getAECgroupName().toUpperCase())){
							aecAttrList.get(j).setParamValue(k);
							done=true;
						}
						k++;
					}while(!done&&k<newAECgroupList.size());
				}
			}
		}
	}
	
	/**
	 * Process called to save the configuration of the AEC group
	 */
	public boolean saveConfiguration(){
		ArrayList<AECgroup> newAECgroupList = new ArrayList<AECgroup>();
		
		boolean error = false;
		for(int i=0;i<listPanelAECgroupList.size();i++){
			error|=AECcontroller.analyseAECgroupValue(listPanelAECgroupList.get(i).gettFieldValue(),4);
			error|=AECcontroller.analyseTextFieldVariableText(listPanelAECgroupList.get(i).gettFieldName());
		}
		if(error){
			return error;
		}
		
		for(int i=0;i<listPanelAECgroupList.size();i++){
			listPanelAECgroupList.get(i).getAecGroup().setAECgroupARXMLvalue(listPanelAECgroupList.get(i).gettFieldValue().getText());
			listPanelAECgroupList.get(i).getAecGroup().setAECgroupName(listPanelAECgroupList.get(i).gettFieldName().getText());
			newAECgroupList.add(listPanelAECgroupList.get(i).getAecGroup());
		}
		
		// Manage the AEC group with the older
		manageAECcomboBox(newAECgroupList);
		
		DataStore.getInstance().setAECgroupList(newAECgroupList);
		return false;
	}
	
	private class PanelAECgroup extends JPanel implements MouseListener{

		/**
		 * The serial Version UID
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * The AEC group
		 */
		private AECgroup aecGroup;
		
		/**
		 * The AEC group number
		 */
		private Integer aecGroupNum;
		
		/**
		 * The Text Field of the name
		 */
		private ModelTextField tFieldName;
		
		/**
		 * The text field of the value
		 */
		private ModelTextField tFieldValue;
		
		/**
		 * The label of the group
		 */
		private JLabel labelNumGroup;
		
		/**
		 * The main constructor of the Panel AEC group
		 * @param i
		 */
		public PanelAECgroup(int i,AECgroup aecGroup){
			super();
			this.setLayout(new BorderLayout());
			this.setOpaque(false);
			this.aecGroup=aecGroup;
			
			this.createThePanel(i);

		}
		
		/**
		 * Process called to create the Panel group list
		 */
		private void createThePanel(int i){
			this.aecGroupNum = i;
			this.labelNumGroup = new JLabel("Group n°"+(i+1));
			labelNumGroup.setFont(ConstantFont.FONT_INPUT_AEC_INFO);
			labelNumGroup.setForeground(ColorStore.getInstance().getColorInputForeground());
			labelNumGroup.setPreferredSize(new Dimension(130,30));
			
			this.tFieldName = new ModelTextField(
				this.aecGroup.getAECgroupName(),
				new Dimension(200,30),
				ConstantFont.FONT_INPUT_AEC_INFO,
				true
			);
			this.tFieldName.addMouseListener(this);
			this.tFieldValue = new ModelTextField(
				String.valueOf(this.aecGroup.getAECgroupValue()),
				new Dimension(250,30),
				ConstantFont.FONT_INPUT_AEC_INFO,
				true
			);
			this.tFieldValue.addMouseListener(this);
			this.add(labelNumGroup,BorderLayout.WEST);
			this.add(tFieldName,BorderLayout.CENTER);
			this.add(tFieldValue,BorderLayout.EAST);
			
			this.addMouseListener(this);
		}
		
		/**
		 * Process called to set the group number label
		 */
		public void setGroupNumLabel(int i){
			this.aecGroupNum = i;
			this.labelNumGroup.setText("Group n°"+(i+1));
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			aecGroupSelected(this.aecGroupNum);
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

		public AECgroup getAecGroup() {
			return aecGroup;
		}

		public ModelTextField gettFieldName() {
			return tFieldName;
		}

		public ModelTextField gettFieldValue() {
			return tFieldValue;
		}

		public JLabel getLabelNumGroup() {
			return labelNumGroup;
		}
	}
	
	private class PanelAECgroupLegend extends JPanel{

		/**
		 * The serial Version UID
		 */
		private static final long serialVersionUID = 1L;
		
		public PanelAECgroupLegend(){
			super();
			this.setLayout(new BorderLayout());
			this.setOpaque(false);	
			JLabel labelNumGroup = new JLabel("Group num",JLabel.LEFT);
			labelNumGroup.setFont(ConstantFont.FONT_INPUT_AEC_INFO);
			labelNumGroup.setForeground(ColorStore.getInstance().getColorInputForeground());
			labelNumGroup.setPreferredSize(new Dimension(130,30));
			
			JLabel labelNameGroup = new JLabel("Group Name",JLabel.LEFT);
			labelNameGroup.setFont(ConstantFont.FONT_INPUT_AEC_INFO);
			labelNameGroup.setForeground(ColorStore.getInstance().getColorInputForeground());
			labelNameGroup.setPreferredSize(new Dimension(200,30));
			
			JLabel labelValueGroup = new JLabel("ARXML Group value (hex)",JLabel.LEFT);
			labelValueGroup.setFont(ConstantFont.FONT_INPUT_AEC_INFO);
			labelValueGroup.setForeground(ColorStore.getInstance().getColorInputForeground());
			labelValueGroup.setPreferredSize(new Dimension(250,30));
			
			this.add(labelNumGroup,BorderLayout.WEST);
			this.add(labelNameGroup,BorderLayout.CENTER);
			this.add(labelValueGroup,BorderLayout.EAST);
		}
	}
}
