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
	Class use to convert the old files into model before the replace
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.file.convertor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.FileStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.FileToGenerate;

public class ConvertOldFiles {
	
	/**
	 * Constructor of the c source generator
	 */
	public ConvertOldFiles() throws AECexception{
		String genCH = DataStore.getInstance().getPathGenerationCHfolder();
		String genARXML = DataStore.getInstance().getPathGenerationARXMLfolder();
		String genS00 = DataStore.getInstance().getPathGenerationS00folder();
		File ERH_cfg_private = 			new File(genCH+FileToGenerate.ERH_CFG_PRIVATE);
		File ERH_cfg_public = 			new File(genCH+FileToGenerate.ERH_CFG_PUBLIC);
		File AEC_calibration_S00 =		new File(genS00+FileToGenerate.AEC_CALIBRATION_S00);
		File ECUextract_ERH_arxml = 	new File(genARXML+FileToGenerate.ERH_DATA_DICTIONNARY_ARXML);
		
		FileStore.getInstance().setERHcfgPrivateH(convertFileToStringData(ERH_cfg_private));
		FileStore.getInstance().setERHcfgPublicH(convertFileToStringData(ERH_cfg_public));

		FileStore.getInstance().setAECcalibrationS00(convertFileToStringData(AEC_calibration_S00));

		FileStore.getInstance().setECUextractERHarxml(convertFileToStringData(ECUextract_ERH_arxml));
	}
	
	/**
	 * Process called to convert the file to an Array list of string
	 */
	public static ArrayList<String> convertFileToStringData(File srcFile){
		ArrayList<String> lineString= new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(srcFile));
			try{
				String line;
				while((line=br.readLine()) != null){
					lineString.add(line);
				}
				br.close();
			}catch(IOException ioe){
				lineString.add("Problem...");
				ioe.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			lineString.add("Old file not found...");
		}
		return lineString;
	}	
}
