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
	Class of the Panel show difference
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import fr.autoliv.pp4g.erh.aecConfigurator.model.file.FileStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.arxml.ErhDataDictionnaryARXML;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.header.ERHcfgPrivateH;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.header.ERHcfgPublicH;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.s00.AECcalibrationS00;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelLabel;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelPanel;

public class CenterPanelShowDifference extends ModelPanel{
	
	private static final int SCROLL_INCREMENT = 30;
	private static final int MAX_LINE_NUMBER  = 5;
	
	/**
	 * the serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the split pane of the list and the info panels
	 */
	private JSplitPane splitPaneOldNew;
	
	/**
	 * The scroll pane where the old value will be displayed
	 */
	private JScrollPane scrollPanePanelOldVersion;
	
	/**
	 * The scroll pane where the old value will be displayed
	 */
	private JScrollPane scrollPanePanelNewVersion;
	
	/**
	 * The panel of the old file version
	 */
	private JPanel panelOldVersion;
	
	/**
	 * The panel of the new file version
	 */
	private JPanel panelNewVersion;
	
	/**
	 * The list of old label of the old file
	 */
	private ArrayList<JLabel> listLabelOld;
	
	/**
	 * The list of new label of the new file
	 */
	private ArrayList<JLabel> listLabelNew;
	
	/**
	 * The title of the new file label
	 */
	private JLabel titleNewFileLabel;
	
	/**
	 * The main frame where all the component are displayed
	 */
	private MainFrame mainFrame;
	
	/**
	 * Main constructor of the edit value panel
	 * @param mainFrame
	 */
	public CenterPanelShowDifference(MainFrame mainFrame){
		super(ConstantDimension.PANEL_CENTER,true,new BorderLayout(),ModelPanel.PANEL_CENTER);
		this.mainFrame=mainFrame;
		
		// Init the panel old version
		this.panelOldVersion = new JPanel();
		this.panelOldVersion.setOpaque(false);
		this.panelOldVersion.setLayout(new BoxLayout(this.panelOldVersion,BoxLayout.PAGE_AXIS));
		
		// Init the panel new version
		this.panelNewVersion = new JPanel();
		this.panelNewVersion.setOpaque(false);
		this.panelNewVersion.setLayout(new BoxLayout(this.panelNewVersion,BoxLayout.PAGE_AXIS));
		
		// Init the scroll pane old version
	    this.scrollPanePanelOldVersion = new JScrollPane(this.panelOldVersion);
	    this.scrollPanePanelOldVersion.setOpaque(false);
	    this.scrollPanePanelOldVersion.getViewport().setOpaque(false);
	    this.scrollPanePanelOldVersion.setBorder(BorderFactory.createEmptyBorder());
	    this.scrollPanePanelOldVersion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    this.scrollPanePanelOldVersion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    this.scrollPanePanelOldVersion.getVerticalScrollBar().setUnitIncrement(5);
	    this.scrollPanePanelOldVersion.getVerticalScrollBar().setValue(0);
	    
	    // Init the scroll pane old version
	    this.scrollPanePanelNewVersion = new JScrollPane(this.panelNewVersion);
	    this.scrollPanePanelNewVersion.setOpaque(false);
	    this.scrollPanePanelNewVersion.getViewport().setOpaque(false);
	    this.scrollPanePanelNewVersion.setBorder(BorderFactory.createEmptyBorder());
	    this.scrollPanePanelNewVersion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    this.scrollPanePanelNewVersion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    this.scrollPanePanelNewVersion.getVerticalScrollBar().setUnitIncrement(5);
	    this.scrollPanePanelNewVersion.getVerticalScrollBar().setValue(0);
	    
        //Split of the panels west and center
		this.splitPaneOldNew = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.scrollPanePanelOldVersion,this.scrollPanePanelNewVersion);
		this.splitPaneOldNew.setOpaque(false);
		this.splitPaneOldNew.setDividerSize(5);
		this.splitPaneOldNew.setContinuousLayout(true);
		this.splitPaneOldNew.setBorder(BorderFactory.createEmptyBorder());
		
		// Add the container of the list
		this.add(this.splitPaneOldNew,BorderLayout.CENTER);
	}
	
	/**
	 * Process called to display the panel which show the difference between old and new file
	 */
	public void displayPanelShowDifference(){
		this.splitPaneOldNew.setDividerLocation(this.mainFrame.getSize().width/2);
		
		switch(FileStore.getInstance().getCurrentPage()){
				
			case FileStore.ERH_CFG_PUBLIC_H:
				this.displayOldFile(FileStore.getInstance().getERHcfgPublicH());
				this.displayNewFile(new ERHcfgPublicH().createFile());
				break;
				
			case FileStore.ERH_CFG_PRIVATE_H:
				this.displayOldFile(FileStore.getInstance().getERHcfgPrivateH());
				this.displayNewFile(new ERHcfgPrivateH().createFile());
				break;
				
			case FileStore.ERH_DATA_DICTIONNARY_ARXML:
				this.displayOldFile(FileStore.getInstance().getECUextractERHarxml());
				this.displayNewFile(new ErhDataDictionnaryARXML().createFile());
				break;
				
			case FileStore.AEC_CALIBRATION_S00:
				this.displayOldFile(FileStore.getInstance().getAECcalibrationS00());
				this.displayNewFile(new AECcalibrationS00().createFile());
				break;
		}
	    this.scrollPanePanelOldVersion.getVerticalScrollBar().setValue(0);
	    this.scrollPanePanelNewVersion.getVerticalScrollBar().setValue(0);
	    this.scrollPanePanelNewVersion.getHorizontalScrollBar().setValue(0);
		this.scrollPanePanelOldVersion.getHorizontalScrollBar().setValue(0);
	
		this.compareFilesLine();
	}
	
	/**
	 * Process called to compare the files line
	 */
	private void compareFilesLine(){
		int differentLine=0;
		for(int i=0;i<listLabelOld.size()||i<listLabelNew.size();i++){
			if(i<listLabelOld.size()){
				if(i<listLabelNew.size()){
					if(!listLabelOld.get(i).getText().equals(listLabelNew.get(i).getText())){
						listLabelNew.get(i).setBackground(ColorStore.getInstance().getColorDifferentLine());
						listLabelNew.get(i).setOpaque(true);
						differentLine++;
					}
				}
			}
			if(i>=listLabelOld.size()){
				listLabelNew.get(i).setBackground(ColorStore.getInstance().getColorNewLine());
				listLabelNew.get(i).setOpaque(true);
				differentLine++;
			}
		}
		this.titleNewFileLabel.setText("New File : "+differentLine+" different line(s)");
	}
	
	/**
	 * Process called to display the old file
	 */
	private void displayOldFile(ArrayList<String> strFile){
		this.panelOldVersion.removeAll();
		this.listLabelOld=new ArrayList<JLabel>();
		
		this.addTitleOldVersion();
		for(int i=0;i<strFile.size();i++){
			JLabel labelOld= new ModelLabel(
				manageLine(this.getLineNumber(i+1)+strFile.get(i)),
				ConstantFont.FONT_COMPARE_FILE, 
				ColorStore.getInstance().getColorAECnameList(),
				JLabel.LEFT
			);
			this.listLabelOld.add(labelOld);
			this.panelOldVersion.add(labelOld);
		}
		this.panelOldVersion.repaint();
		this.panelOldVersion.revalidate();
	}
	
	/**
	 * Process called to display the new file
	 */
	private void displayNewFile(String strFile){
		this.panelNewVersion.removeAll();
		this.listLabelNew=new ArrayList<JLabel>();
		
		this.addTitleNewVersion();
		int offset = 0;
		for(int i=0;i<strFile.length()-1;i++){
			if(strFile.substring(i, i+2).equals("\r\n")){
				JLabel labelNew = new ModelLabel(
					manageLine(this.getLineNumber(this.listLabelNew.size()+1)+strFile.substring(offset,i)),
					ConstantFont.FONT_COMPARE_FILE, 
					ColorStore.getInstance().getColorAECnameList(),
					JLabel.LEFT
				);
				offset=i+2;
				this.listLabelNew.add(labelNew);
				this.panelNewVersion.add(labelNew);
			}
		}
		this.panelNewVersion.repaint();
		this.panelNewVersion.revalidate();
	}
	
	/**
	 * Process called to set the color of the text
	 * @param str
	 * @return
	 */
	private String manageLine(String str){
		return str;
	}
	
	/**
	 * Process called to add a title to the panel new version
	 */
	private void addTitleNewVersion(){
		this.titleNewFileLabel = new ModelLabel(
			"New File : ",
			ConstantFont.FONT_INPUT_AEC_INFO, 
			ColorStore.getInstance().getColorAECnameList(),
			JLabel.CENTER
		);
		this.titleNewFileLabel.setForeground(ColorStore.getInstance().getColorSelectedStructLine());
		this.panelNewVersion.add(this.titleNewFileLabel);
	}
	
	/**
	 * Process called to add a title to the panel old version
	 */
	private void addTitleOldVersion(){
		JLabel labelOld = new ModelLabel(
			"Old File : ",
			ConstantFont.FONT_INPUT_AEC_INFO, 
			ColorStore.getInstance().getColorAECnameList(),
			JLabel.CENTER
		);
		labelOld.setForeground(ColorStore.getInstance().getColorSelectedStructLine());
		this.panelOldVersion.add(labelOld);
	}
	
	/**
	 * Process called to get the line number and the right alignment
	 * @param num
	 * @return
	 */
	private String getLineNumber(int num){
		String space = new String();
		for(int i=String.valueOf(num).length();i<MAX_LINE_NUMBER;i++){
			space+=" ";
		}
		return num+space;
	}
	
	/**
	 * Process called to scroll the scroll pane down
	 */
	public void scrollDown(){
		this.scrollPanePanelNewVersion.getVerticalScrollBar().setValue(this.scrollPanePanelNewVersion.getVerticalScrollBar().getValue()+SCROLL_INCREMENT);
		this.scrollPanePanelOldVersion.getVerticalScrollBar().setValue(this.scrollPanePanelOldVersion.getVerticalScrollBar().getValue()+SCROLL_INCREMENT);
	}

	/**
	 * Process called to scroll the scroll pane up
	 */
	public void scrollUp(){
		this.scrollPanePanelNewVersion.getVerticalScrollBar().setValue(this.scrollPanePanelNewVersion.getVerticalScrollBar().getValue()-SCROLL_INCREMENT);
		this.scrollPanePanelOldVersion.getVerticalScrollBar().setValue(this.scrollPanePanelOldVersion.getVerticalScrollBar().getValue()-SCROLL_INCREMENT);
	}
}