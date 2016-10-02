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
	Class which has all the file name
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.file;

public class FileToGenerate {

	/**
	 * The different file path name 
	 */
	public static final String ERH_CFG_PRIVATE = 			"ERH_cfg_private.h";
	public static final String ERH_CFG_PUBLIC =				"ERH_cfg_public.h";
	
	public static final String AEC_CALIBRATION_S00 = 		"AEC_calibration.S00";
	
	public static final String ERH_DATA_DICTIONNARY_ARXML = "ErhDataDictionnary.arxml";
	
	/**
	 * The JSON file use to generate the different data
	 */
	public static final String JSON_AEC_DATA =				"AEC_Configurator_Data.json";
}
