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
	This class is the first class called (see the main) when the application is launch.
 */

package fr.autoliv.pp4g.erh.aecConfigurator;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import org.jnativehook.GlobalScreen;

import fr.autoliv.pp4g.erh.aecConfigurator.view.MainFrame;

public class AECconfigurator{
	
	/**
	 * The executable method
	 * @param args : different parameter that could be entered
	 */
	public static void main(String... args){
		// This line disable the print on the eclipse's console by the "GlobalScreen"
		// GlobalScreen is the plug-in use to listen the key every where
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		logger.setUseParentHandlers(true);
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// Creation of the main frame
				MainFrame mainFrame = new MainFrame();
				mainFrame.setVisible(true);		
			}
		});
	}
}