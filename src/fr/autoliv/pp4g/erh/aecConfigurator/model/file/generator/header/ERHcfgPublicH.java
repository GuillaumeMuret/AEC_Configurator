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
	Class use to create the ERH_cfg_public.h file
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.header;

import java.util.ArrayList;

import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;

public class ERHcfgPublicH {
	
	/**
	 * The prefix macro used to generate the "ERH_cfg_public.h" file
	 */
	private static final String PREFIX_MACRO_ERH_CFG_PUBLIC_NUMBER_OF_AEC = 
		"#define ERH_KU8_NUMBER_OF_AEC                                                   ((uint8)0x";
	
	/**
	 * The first element of the file
	 */
	private static final String BEGIN_FILE =
		"/*************************************************************************\r\n"
		+"\r\n"
		+"*************************************************************************/\r\n"
		+"\r\n"
		+"#ifndef I_ERH_PUBLIC_CFG\r\n"
		+"#define I_ERH_PUBLIC_CFG 1\r\n"
		+"\r\n"
		+"#include \"Std_Types.h\"\r\n"
		+"\r\n"		
		+"/*************************************************************************\r\n"
		+"Public inclusions\r\n"
		+"*************************************************************************/\r\n"
		+"#include \"ERH_cfg_private.h\"\r\n"
		+"/*************************************************************************\r\n"
		+"Declaration of constants\r\n"
		+"*************************************************************************/\r\n";
	
	/**
	 * The end of the file
	 */
	private static final String END_FILE = 
		"\r\n"
		+"#endif   /* I_ERH_DATA */\r\n"
		+"\r\n"
		+"/*************************************************************************\r\n"
		+"\r\n"
		+"*************************************************************************/\r\n"
		+"\r\n"
		+"/* end of file */\r\n";
	
	public static final String afterStartAddress = 
			"\r\n"
			+"/*************************************************************************\r\n"
			+"Declaration of macros\r\n"
			+"*************************************************************************/\r\n"
			+"\r\n"
			+"#ifndef __AEC_CALIB__(x)\r\n"
			+"#define __AEC_CALIB__(x)        					(ERH_CALIBRATION_START_ADDRESS+x)\r\n"
			+"#endif\r\n"
			+"\r\n";
	
	/**
	 * Process called to create the "ERH_cfg_public.h"
	 * @param listNVPcalibration
	 * @return the created file into string
	 */
	public String createFile(){
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		String macro = new String();
		boolean sizeDone=false;
		for(int i=0;i<aecList.size()&&!sizeDone;i++){
			if(aecList.get(i).getAECname().toUpperCase().contains("RESERVED")){
				macro=macro
						+ "\r\n"
						+ PREFIX_MACRO_ERH_CFG_PUBLIC_NUMBER_OF_AEC
						+ printHexString(i-1)
						+ ")"
						+ "\r\n\r\n";
				sizeDone=true;
			}
		}
		return 	BEGIN_FILE
				+createMacroStartAddress()
				+afterStartAddress+createMacroConstERH()
				+macro
				+END_FILE;
	}
	
	/**
	 * Create the macro with the start address
	 * @return
	 */
	private String createMacroStartAddress(){
		String macro = new String();
		macro = macro + "#define ERH_CALIBRATION_START_ADDRESS     (0x";
		macro = macro + Integer.toHexString(DataStore.getInstance().getFirstMemoryValue());
		macro = macro + ")\r\n";
		return macro;
	}
	
	/**
	 * Create the macro with the const ERH
	 * @return
	 */
	private String createMacroConstERH(){
		String macro = new String();
		macro = macro + "const extern ";
		macro = macro + DataStore.getInstance().getAECstructureName()+ "   ";
		macro = macro + DataStore.getInstance().getSoftwareAliasInRam()+ "[]   ";
		macro = macro + "@__AEC_CALIB__(0x0);\r\n";
		
		return macro;
	}
	
	/**
	 * Process called to cast an Integer into an hexadecimal value
	 * @param i
	 * @return
	 */
	private String printHexString(int i){
		if(i>14){
			return Integer.toHexString(i+1).toUpperCase();
		}else{
			return "0"+Integer.toHexString(i+1).toUpperCase();
		}
	}
}