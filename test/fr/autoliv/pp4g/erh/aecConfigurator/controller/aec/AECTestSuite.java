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

package fr.autoliv.pp4g.erh.aecConfigurator.controller.aec;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AECTestSuite {

	/**
	 * Call the different test from the package inside the aec package
	 * @return
	 */
	public static Test suite() {
		
		final TestSuite suite = new TestSuite("Test Suite of the package fr.autoliv.pp4g.erh.aecConfigurator.model.aec");

		// Call the test case of the AECcontrollerTest class
		suite.addTest(new TestSuite(AECcontrollerTest.class));

		return suite;
	}

}
