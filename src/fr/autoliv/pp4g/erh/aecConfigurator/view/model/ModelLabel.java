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
	Class model of the labels
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.model;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class ModelLabel extends JLabel{

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ModelLabel(String str, Font font, Color foreground,int orientation){
		super(str,orientation);
		this.setFont(font);
		this.setForeground(foreground);
	}	
}
