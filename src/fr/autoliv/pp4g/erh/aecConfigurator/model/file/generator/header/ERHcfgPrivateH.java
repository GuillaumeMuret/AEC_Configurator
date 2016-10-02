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
	Class use to create the ERH_cfg_private.h
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.header;

import java.util.ArrayList;

import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECstructure;

public class ERHcfgPrivateH {
	
	/**
	 * Process called to create the ERH_public.h
	 */
	public String createFile(){
		String structure = new String();
		structure = structure +"typedef struct\r\n";
		structure = structure +"{\r\n";
		
		ArrayList<AECstructure> structList = DataStore.getInstance().getAECstructureList();
		for(int i=0;i<structList.size();i++){
			structure = structure 
				+"   "
				+structList.get(i).getAttributeType()
				+" "
				+structList.get(i).getAttributeName()
				+";";
			
			structure = structure +manageSpace(3+structList.get(i).getAttributeType().length()+structList.get(i).getAttributeName().length());
			
			structure = structure 
				+"/* "
				+structList.get(i).getAttributeDescription()
				+" */\r\n";
		}
		structure = structure
			+"} "
			+DataStore.getInstance().getAECstructureName()
			+";\r\n";
		
		return beforeStructure + structure + afterStructure;
	}
	
	/**
	 * Process called to manage the space between name and description
	 * @param space
	 */
	private String manageSpace(int length){
		String space = new String();
		for(int i=length;i<37;i++){
			space = space + " ";
		}
		return space;
	}
	
	/**
	 * The file ERH_cfg_private.h
	 */
	public static final String beforeStructure =
			
		"/*************************************************************************\r\n"
		+"\r\n"
		+"*************************************************************************/\r\n"
		+"\r\n"
		+"#ifndef ERH_PRIVATE_CFG\r\n"
		+"#define ERH_PRIVATE_CFG 1\r\n"
		+"\r\n"
		+"#include \"Std_Types.h\"\r\n"
		+"\r\n"
		+"/*************************************************************************\r\n"
		+" Other components external interface inclusion **\r\n"
		+"*************************************************************************/\r\n"
		+"\r\n"
		+"/*************************************************************************\r\n"
		+" Declaration of types\r\n"
		+"*************************************************************************/\r\n"
		+"\r\n"
		+"/*************************************************************************\r\n"
		+" Declaration of macros\r\n"
		+"*************************************************************************/\r\n"
		+" \r\n"
		+"/*************************************************************************\r\n"
		+" Declaration of constants\r\n"
		+"*************************************************************************/\r\n"
		+"\r\n";
	
	
	public static final String afterStructure =

		 "\r\n"
		+"/*************************************************************************\r\n"
		+" Declaration of constants\r\n"
		+"*************************************************************************/\r\n"
		+"\r\n"
		+"/*************************************************************************\r\n"
		+" Exported function prototypes\r\n"
		+"*************************************************************************/\r\n"
		+"\r\n"
		+"#endif   /* I_ERH_CONFIG */\r\n"
		+"\r\n"
		+"/*************************************************************************\r\n"
		+"\r\n"
		+"*************************************************************************/\r\n"
		+"\r\n"
		+"/* end of file */\r\n";
			
}
