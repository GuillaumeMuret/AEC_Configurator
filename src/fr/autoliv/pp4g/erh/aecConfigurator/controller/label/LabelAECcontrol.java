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
	Class of the label of the first screen where the AEC list is displayed.
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.label;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.IDCommand;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.LabelAEC;

public class LabelAECcontrol implements MouseListener{	
	
	/**
	 * The label where the component name is written
	 */
	private LabelAEC theLabel;
	
	/**
	 * The NVP key on the ListNVPComponent
	 */
	private Integer aecKey;
	
	/**
	 * Constructor of the NVPcomponentLabel listener
	 * @param mainFrame
	 * @param theLabel
	 * @param panelAEClist
	 * @param nvpKey
	 */
	public LabelAECcontrol(LabelAEC theLabel,Integer nvpKey){
		this.theLabel = theLabel;
		this.aecKey = nvpKey;
	}
	
	/**
	 * Process called when the user click on the mouse (press and release in
	 * the component zone)
	 */
	@Override
	public void mouseClicked(MouseEvent mouseEvent){

	}
	
	/**
	 * Process called when the mouse entered in the component zone
	 */
	@Override
	public void mouseEntered(MouseEvent mouseEvent){
		this.theLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DataStore.getInstance().setNewAECselected(this.aecKey);
		ControlStore.getInstance().getExecutor().executeCommand(IDCommand.SELECT_AEC);
	}
	
	/**
	 * Process called when the mouse exit the component zone
	 */
	@Override
	public void mouseExited(MouseEvent mouseEvent){
		this.theLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	/**
	 * Process called when the user click on the component
	 */
	@Override
	public void mousePressed(MouseEvent mouseEvent){
		
	}
	
	/**
	 * Process called when the user release the click
	 */
	@Override
	public void mouseReleased(MouseEvent mouseEvent){

	}	
	
	/**
	 * Setter of the NVP component key
	 * @param key
	 */
	public void setkey(int key){
		this.aecKey=key;
	}
}
