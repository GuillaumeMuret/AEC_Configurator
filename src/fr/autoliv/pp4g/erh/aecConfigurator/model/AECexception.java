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
	Class called to create the AEC_calibration.S00 file
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model;
 
import java.lang.Exception;

public class AECexception extends Exception{
	
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
		
	/**
	 * The different AEC exception cause
	 */
	public static final int AEC_CALIBRATION_SIZE=					1;
	public static final int AEC_ATTRIBUTE_SIZE=						2;
	public static final int ADDING_AEC_CALIBRATION_SIZE=			3;
	public static final int BLANK_FIELD=							4;
	public static final int DOUBLE_AEC_NAME=						5;
	public static final int DOUBLE_AEC_ATTRIBUTE_NAME =				6;
	public static final int FOLDER_CH_NOT_FOUND=					7;
	public static final int FOLDER_S00_NOT_FOUND=					8;
	public static final int FOLDER_ARXML_NOT_FOUND=					9;
	public static final int TEXT_FIELD_EXCEPTION=					10;
	public static final int AEC_RESERVED_NOT_IN_THE_BOTTOM=			11;
	public static final int AEC_RESERVED_ATTR_NOT_IN_THE_BOTTOM=	12;
	public static final int JSON_FILE_NOT_FOUND=					13;
	public static final int JSON_FILE_ERROR =						14;
	public static final int NOTHING_TO_DO=							15;
	
	/**
	 * The message to display
	 */
	public String message;
	
	/**
	 * The cause of the Exception
	 */
	public int cause;
	
	/**
	 * Constructor of the Exception
	 * @param message
	 * @param cause
	 */
	public AECexception(String message, int cause){
		this.message=message;
		this.cause=cause;
	}
	
	/**
	 * Getter of the cause of the Exception
	 * @return
	 */
	public int getExceptionCause(){
		return this.cause;
	}
}