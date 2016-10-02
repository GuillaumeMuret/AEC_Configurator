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
	Class which contain all the key to access the data of the json file.
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.json;

public class JSONprotocolKey {

	/**
	 * The different key used for the ERH protocol
	 */
	public static final String GENERATION_CH_FOLDER_PATH =			"generationCHfolderPath";
	public static final String GENERATION_ARXML_FOLDER_PATH =		"generationARXMLfolderPath";
	public static final String GENERATION_S00_FOLDER_PATH =			"generationS00folderPath";

	public static final String SOFTWARE_ALIAS_IN_RAM =				"softwareAliasInRam";
	public static final String PADDING_BYTE_VALUE =					"paddingByteValue";
	public static final String FIRST_MEMORY_VALUE =					"firstMemoryValue";
	public static final String AEC_CALIBRATION_MAX_SIZE =			"aecCalibrationMaxSize";
	
	public static final String AEC_STRUCTURE_NAME =					"aecStructureName";
	public static final String ATTRIBUTE_TYPE =						"attributeType";
	public static final String ATTRIBUTE_NAME =						"attributeName";
	public static final String ATTRIBUTE_DESCRIPTION =				"attributeDescription";
	public static final String ATTRIBUTE_SIZE =						"attributeSize";
	
	public static final String AEC_GROUP_TAB = 						"aecGroupTab";
	public static final String AEC_GROUP_VALUE_ARXML = 				"aecGroupValueARXML";
	public static final String AEC_GROUP_NAME = 					"aecGroupName";
	
	public static final String AEC_STRUCTURE_TAB =					"aecStructureTab";
	public static final String AEC_COMPONENT_LIST =					"aecComponentList";
	public static final String COMPONENT_TITLE =					"componentTitle";
	public static final String PARAMS =								"params";
	public static final String PARAM_NAME =							"paramName";
	public static final String PARAM_DESCRIPTION =					"paramDescription";
	public static final String PARAM_SIZE =							"paramSize";
	public static final String PARAM_VALUE =						"paramValue";
	public static final String PARAM_UNIT =							"paramUnit";
	public static final String PARAM_SCALING_FACTOR =				"paramScalingFactor";
	public static final String PARAM_SCALING_OFFSET =				"paramScalingOffset";
	public static final String PARAM_INTERPRETED_VALUE =			"paramInterpretedValue";
	public static final String THIS =								"this";
	public static final String DESCRIPTION =						"description";
	
}
