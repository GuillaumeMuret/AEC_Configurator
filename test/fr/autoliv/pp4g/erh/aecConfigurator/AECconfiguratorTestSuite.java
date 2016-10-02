/******************************************************************************

AUTOLIV ELECTRONIC document.

-------------------------------------

Copyright Autoliv Inc. All rights reserved.

*******************************************************************************
JAVA-File Template Version: 
******************************************************************************/
/* PRQA S 0288 ++ */
/*
 * Explanation:
 *    Disabled for MKS keywords
 */
/*
$Revision: 0.1 $
$ProjectName: e:/MKSProjects/ALE Core Software/ALE Process Standards/ProductDevelopment/Template/Template.pj $
*/
/* PRQA S 0288 -- */
/*!****************************************************************************

@details
   This module is the first class called when the application start
 */

package fr.autoliv.pp4g.erh.aecConfigurator;

import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.jnativehook.GlobalScreen;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControllerTestSuite;

public class AECconfiguratorTestSuite {

	/**
	 * Call the different test from the package inside the aecConfigurator package
	 * @return
	 */
	public static Test suite() {
		// This line disable the print on the eclipse's console by the "GlobalScreen"
		// GlobalScreen is the plug-in use to listen the key every where
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		logger.setUseParentHandlers(true);
		
		final TestSuite suite = new TestSuite("Test Suite of the package fr.autoliv.pp4g.erh.aecConfigurator");

		// Test suite of the package model
		//suite.addTest(ModelTestSuite.suite());
		
		// Test suite of the package controller
		suite.addTest(ControllerTestSuite.suite());
		
		return suite;
	}

}

