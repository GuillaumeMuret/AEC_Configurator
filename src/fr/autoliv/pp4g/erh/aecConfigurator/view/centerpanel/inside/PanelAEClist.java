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
	Class where the AEC list is displayed
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.panel.PanelAEClistControl;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;

public class PanelAEClist extends JPanel{
	
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The list of the different label displayed
	 */
	private ArrayList<LabelAEC> labelAEClist;

	/**
	 * The component panel listener
	 */
	private PanelAEClistControl panelAEClistControl;
	
	/**
	 * Constructor of the component panel list
	 * @param mainFrame
	 */
	public PanelAEClist(MainFrame mainFrame){
		// Init the AEC list panel
		super();
		this.setBackground(Color.BLACK);
		this.setOpaque(false);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		// Init the AEC list view and control
		this.labelAEClist=new ArrayList<LabelAEC>();
		this.panelAEClistControl=new PanelAEClistControl(mainFrame);	

		this.addMouseListener(this.panelAEClistControl);
	}
	
	/**
	 * process called to add a new component to this panel
	 * @param num
	 * @param aecLabel
	 */
	public void addAECcomponent(int num,LabelAEC aecLabel){
		aecLabel.setEnabled(true);
		this.labelAEClist.add(Integer.valueOf(num),aecLabel);
		this.add(aecLabel);
		DataStore.getInstance().setAEClabelList(labelAEClist);
	}
	
	/**
	 * Process called to repaint the list of the label displayed on the screen
	 */
	public void remakeLabelList(){
		this.removeAll();
		DataStore.getInstance().setInitialLabel();
		ArrayList<LabelAEC> listLabel = DataStore.getInstance().getAEClabelList();
		for(int i=0;i<listLabel.size();i++){
			this.add(listLabel.get(i));
			listLabel.get(i).getLabelAECcontrol().setkey(i);
			if(i==DataStore.getInstance().getAECkey()){
				listLabel.get(i).setForeground(ColorStore.getInstance().getColorSelectedAEC());
			}
		}
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Setter of the NVP component label list
	 * @param listLabel
	 */
	public void setLabelAEClist(ArrayList<LabelAEC> listNVPcomponentLabel) {
		this.labelAEClist = listNVPcomponentLabel;
	}
	
	/**
	 * Getter of the AEC label list
	 * @return
	 */
	public ArrayList<LabelAEC> getLabelAEClist(){
		return this.labelAEClist;
	}
}
