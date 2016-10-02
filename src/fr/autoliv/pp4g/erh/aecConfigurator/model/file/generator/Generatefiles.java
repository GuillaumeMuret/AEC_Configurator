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
	Class used to generate all the files
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.FileToGenerate;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.arxml.ErhDataDictionnaryARXML;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.header.ERHcfgPrivateH;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.header.ERHcfgPublicH;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.s00.AECcalibrationS00;

public class Generatefiles {
	
	/**
	 * Constructor of the c source generator
	 */
	public Generatefiles() throws AECexception{
		String genCH = DataStore.getInstance().getPathGenerationCHfolder();
		String genARXML = DataStore.getInstance().getPathGenerationARXMLfolder();
		String genS00 = DataStore.getInstance().getPathGenerationS00folder();
		File ERH_cfg_private = 			new File(genCH+FileToGenerate.ERH_CFG_PRIVATE);
		File ERH_cfg_public = 			new File(genCH+FileToGenerate.ERH_CFG_PUBLIC);
		File AEC_calibration_S00 =		new File(genS00+FileToGenerate.AEC_CALIBRATION_S00);
		File ERHdataDictionnary_arxml = new File(genARXML+FileToGenerate.ERH_DATA_DICTIONNARY_ARXML);
		
		try{
			generateFile(ERH_cfg_private,			new ERHcfgPrivateH().createFile());
			generateFile(ERH_cfg_public,			new ERHcfgPublicH().createFile());
		}catch(FileNotFoundException fnfe){
			throw new AECexception("folder .c .h not found", AECexception.FOLDER_CH_NOT_FOUND);
		}
		try{
			generateFile(AEC_calibration_S00, 		new AECcalibrationS00().createFile());
		}catch(FileNotFoundException fnfe){
			throw new AECexception("folder not found", AECexception.FOLDER_S00_NOT_FOUND);
		}
		try{
			generateFile(ERHdataDictionnary_arxml, 		new ErhDataDictionnaryARXML().createFile());
		}catch(FileNotFoundException fnfe){
			throw new AECexception("folder not found", AECexception.FOLDER_ARXML_NOT_FOUND);
		}
		
	}
	
	/**
	 * Main process, called when the user want to generate a file
	 * @return if the generation passed
	 */
	public static void generateFile(File destFile,String textFile) throws FileNotFoundException{
		FileOutputStream fileOutStream = new FileOutputStream(destFile);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutStream));
		try{
			bw.write(textFile);
			bw.close();
		}catch(Exception ioe){
			ioe.printStackTrace();
		}
	}
}
