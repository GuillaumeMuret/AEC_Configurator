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
	Class called to instantiate the label AEC in the first screen
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside;

import javax.swing.JLabel;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.label.LabelAECcontrol;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;

public class LabelAEC extends JLabel{

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the NVP component
	 */
	public AECcomponent nvpComponent;
	
	/**
	 * The NVP list panel
	 */
	public PanelAEClist panelAEClist;
	
	/**
	 * The main frame
	 */
	public MainFrame mainFrame;

	/**
	 * 
	 */
	private LabelAECcontrol labelListener;
	
	/**
	 * The listener on the label displayed on the list NVP calibration panel
	 * @param enumNVPcalibration
	 * @param frame
	 */
	public LabelAEC(AECcomponent nvpComponent,Integer nvpKey,MainFrame mainFrame, PanelAEClist panelAEClist){
		super(nvpComponent.getAECname());
		this.labelListener = new LabelAECcontrol(this,nvpKey);
		this.panelAEClist=panelAEClist;
		
		this.nvpComponent = nvpComponent;
		this.mainFrame=mainFrame;
		
		this.setFont(ConstantFont.FONT_LABEL_AEC);
		this.setForeground(ColorStore.getInstance().getColorInitialAEC());
		
		this.addMouseListener(this.labelListener);
	}	
	
    /**
     * Process called to initialize the label color
     */
	public void setInitialLabel(){
		this.setFont(ConstantFont.FONT_LABEL_AEC);
		this.setForeground(ColorStore.getInstance().getColorInitialAEC());
	}
	
	/**
	 * Getter of the NVP component label listener
	 * @return
	 */
	public LabelAECcontrol getLabelAECcontrol(){
		return this.labelListener;
	}
	
}
