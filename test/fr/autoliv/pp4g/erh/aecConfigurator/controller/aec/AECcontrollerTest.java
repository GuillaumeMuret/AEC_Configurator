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

import java.awt.Dimension;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;
import fr.autoliv.pp4g.erh.aecConfigurator.view.model.ModelTextField;

public class AECcontrollerTest extends TestCase{
	
	/**
	 * The variable that will be tested
	 */
	private ModelTextField textFieldTest;
	
	/**
	 * The different data test
	 */
	private static final String DATA_TEST_EMPTY_TEXT  = "";
	private static final String DATA_TEST_VARIABLE_TEXT  = "bonjour";
	private static final String DATA_TEST_TEXT  = "bonjour monsieur";
	private static final String DATA_TEST_ERROR_VARIABLE_TEXT  = "1bonjour";
	private static final String DATA_TEST_BIG_NUMBER  = "256";
	private static final String DATA_TEST_GOOD_NUMBER  = "255";
	
	private static final int DATA_SIZE_1 	 = 2;

	/**
	 * Process called before the test launching
	 */
	@Before
	public void setUp() throws Exception
	{
		textFieldTest=new ModelTextField("", new Dimension(1,1), ConstantFont.FONT_INPUT_AEC_INFO, true);
	}
	
	/**
	 * Process called after all the unit test
	 */
	@After
	public void tearDown() throws Exception 
	{
		// nothing to do
	}
	
	/**
	 * Process called before all the test
	 */
	@Test
	public void testPreconditions() 
	{
		assertNotNull(this.textFieldTest);
	}
	
	/**
	 * Test of an empty text
	 */
	@Test
	public void testEmptyText() 
	{
		this.textFieldTest.setText(DATA_TEST_EMPTY_TEXT);
		assertTrue(AECcontroller.analyseTextFieldText(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldVariableText(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldDecimalNumber(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldPositiveNumber(textFieldTest,DATA_SIZE_1));
	}
	
	/**
	 * Test of a variable text (without space and without special first character)
	 */
	@Test
	public void testVariableText() 
	{
		this.textFieldTest.setText(DATA_TEST_VARIABLE_TEXT);
		assertFalse(AECcontroller.analyseTextFieldText(textFieldTest));
		assertFalse(AECcontroller.analyseTextFieldVariableText(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldDecimalNumber(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldPositiveNumber(textFieldTest,DATA_SIZE_1));
	}
	
	/**
	 * Test of a banal text (with space)
	 */
	@Test
	public void testNormalText() 
	{
		this.textFieldTest.setText(DATA_TEST_TEXT);
		assertFalse(AECcontroller.analyseTextFieldText(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldVariableText(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldDecimalNumber(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldPositiveNumber(textFieldTest,DATA_SIZE_1));
	}
	
	/**
	 * Test of an error text (with special first character)
	 */
	@Test
	public void testErrorVariableText() 
	{
		this.textFieldTest.setText(DATA_TEST_ERROR_VARIABLE_TEXT);
		assertFalse(AECcontroller.analyseTextFieldText(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldVariableText(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldDecimalNumber(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldPositiveNumber(textFieldTest,DATA_SIZE_1));
	}
	
	/**
	 * Test a number higher that the size 
	 */
	@Test
	public void testTooBigNumberText() 
	{
		this.textFieldTest.setText(DATA_TEST_BIG_NUMBER);
		assertFalse(AECcontroller.analyseTextFieldText(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldVariableText(textFieldTest));
		assertFalse(AECcontroller.analyseTextFieldDecimalNumber(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldPositiveNumber(textFieldTest,DATA_SIZE_1));
	}
	
	/**
	 * Process called to test a good number (good size)
	 */
	@Test
	public void testGoodNumberText() 
	{
		this.textFieldTest.setText(DATA_TEST_GOOD_NUMBER);
		assertFalse(AECcontroller.analyseTextFieldText(textFieldTest));
		assertTrue(AECcontroller.analyseTextFieldVariableText(textFieldTest));
		assertFalse(AECcontroller.analyseTextFieldDecimalNumber(textFieldTest));
		assertFalse(AECcontroller.analyseTextFieldPositiveNumber(textFieldTest,DATA_SIZE_1));
	}
}
