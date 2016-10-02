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
	Class use to convert the JSON file into AEC model
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.file.convertor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.autoliv.pp4g.erh.aecConfigurator.model.AECexception;
import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECgroup;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECstructure;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.FileToGenerate;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.json.JSONprotocolKey;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;

public class ConvertJSONtoAEC {
	
	/**
	 * The AEC component list
	 */
	private ArrayList<AECcomponent> aecList;
	
	/**
	 * Constructor to convert the JSON file into NVPcomponent
	 */
	public ConvertJSONtoAEC() throws AECexception{
		ColorStore.getInstance().setColorTheme(ColorStore.COLOR_THEME_BRIGHT);
		this.aecList=new ArrayList<AECcomponent>();
		String lineFile = convertFileToLineString();
		
		JSONObject mainObj;
		try {
			mainObj = convertStringToJson(lineFile);
			createGenerationFolderPath(mainObj);
			createSoftwareAliasInRam(mainObj);
			createPaddingByteValue(mainObj);
			createFirstMemoryValue(mainObj);
			createAECcalibrationMaxSize(mainObj);
			createAECgroup(mainObj);
			createStructure(mainObj);
			createComponent(mainObj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AECexception("json error",AECexception.JSON_FILE_ERROR);
		}
	}
	
	/**
	 * Process called to get the generation c source folder path
	 * @param mainObj
	 * @throws JSONException
	 */
	public void createGenerationFolderPath(JSONObject mainObj) throws JSONException{
		String generationCHsourceFolderPath = new String(mainObj.getString(JSONprotocolKey.GENERATION_CH_FOLDER_PATH));
		DataStore.getInstance().setPathGenerationCHfolder(generationCHsourceFolderPath);
		
		String generationARXMLsourceFolderPath = new String(mainObj.getString(JSONprotocolKey.GENERATION_ARXML_FOLDER_PATH));
		DataStore.getInstance().setPathGenerationARXMLfolder(generationARXMLsourceFolderPath);
		
		String generationS00sourceFolderPath = new String(mainObj.getString(JSONprotocolKey.GENERATION_S00_FOLDER_PATH));
		DataStore.getInstance().setPathGenerationS00folder(generationS00sourceFolderPath);
	}
	
	/**
	 * Process called to get the software alias in ram
	 * @param mainObj
	 * @throws JSONException
	 */
	private void createSoftwareAliasInRam(JSONObject mainObj) throws JSONException{
		String softwareAliasInRam = new String(mainObj.getString(JSONprotocolKey.SOFTWARE_ALIAS_IN_RAM));
		DataStore.getInstance().setSoftwareAliasInRam(softwareAliasInRam);
	}
	
	/**
	 * Process called to get the padding byte value
	 * @param mainObj
	 * @throws JSONException
	 */
	private void createPaddingByteValue(JSONObject mainObj) throws JSONException{
		String paddingByteValue = new String(mainObj.getString(JSONprotocolKey.PADDING_BYTE_VALUE));
		DataStore.getInstance().setPaddingByteValue(paddingByteValue);
	}
	
	/**
	 * Process called to get the first memory value
	 * @param mainObj
	 * @throws JSONException
	 */
	private void createFirstMemoryValue (JSONObject mainObj) throws JSONException{
		Integer firstMemoryValue = new Integer(mainObj.getInt(JSONprotocolKey.FIRST_MEMORY_VALUE));
		DataStore.getInstance().setFirstMemoryValue(firstMemoryValue);
	}
	
	/**
	 * Process called to get the first memory value
	 * @param mainObj
	 * @throws JSONException
	 */
	private void createAECcalibrationMaxSize (JSONObject mainObj) throws JSONException{
		Integer aecCalibrationMaxSize = new Integer(mainObj.getInt(JSONprotocolKey.AEC_CALIBRATION_MAX_SIZE));
		DataStore.getInstance().setAECcalibrationMaxSize(aecCalibrationMaxSize);
	}
	
	/**
	 * Process called to create the AEC group
	 * @param mainObj
	 * @throws Exception
	 */
	public void createAECgroup(JSONObject mainObj) throws Exception{
		JSONArray jsonGroupList = mainObj.getJSONArray(JSONprotocolKey.AEC_GROUP_TAB);
		
		ArrayList<AECgroup> groupList = new ArrayList<AECgroup>();
		
		for(int i=0;i<jsonGroupList.length();i++){
			AECgroup aecGroup = new AECgroup();
			aecGroup.setAECgroupARXMLvalue(jsonGroupList.getJSONObject(i).getString(JSONprotocolKey.AEC_GROUP_VALUE_ARXML));
			aecGroup.setAECgroupName(jsonGroupList.getJSONObject(i).getString(JSONprotocolKey.AEC_GROUP_NAME));
			groupList.add(i,aecGroup);
		}
		DataStore.getInstance().setAECgroupList(groupList);
	}
	
	/**
	 * Process called to create the structure information 
	 * @param mainObj
	 * @throws Exception
	 */
	public void createStructure(JSONObject mainObj) throws Exception{
		DataStore.getInstance().setAECstructureName(mainObj.getString(JSONprotocolKey.AEC_STRUCTURE_NAME));
		JSONArray jsonStructList = mainObj.getJSONArray(JSONprotocolKey.AEC_STRUCTURE_TAB);
		
		ArrayList<AECstructure> structList = new ArrayList<AECstructure>();
		
		for(int i=0;i<jsonStructList.length();i++){
			AECstructure structComponent = new AECstructure();
			
			structComponent.setAttributeType(jsonStructList.getJSONObject(i).getString(JSONprotocolKey.ATTRIBUTE_TYPE));
			structComponent.setAttributeName(jsonStructList.getJSONObject(i).getString(JSONprotocolKey.ATTRIBUTE_NAME));
			structComponent.setAttributeDescription(jsonStructList.getJSONObject(i).getString(JSONprotocolKey.ATTRIBUTE_DESCRIPTION));
			structComponent.setAttributeSize(jsonStructList.getJSONObject(i).getInt(JSONprotocolKey.ATTRIBUTE_SIZE));

			structList.add(structComponent);
		}
		DataStore.getInstance().setAECstructureList(structList);
		
	}
	
	/**
	 * Process called to create the NVP component from the JSON object
	 * @param mainObj
	 * @throws JSONException
	 */
	public void createComponent(JSONObject mainObj) throws JSONException{
		JSONArray JsonListNVPcomponent = mainObj.getJSONArray(JSONprotocolKey.AEC_COMPONENT_LIST);
		for(int i=0;i<JsonListNVPcomponent.length();i++){
			AECcomponent theNVPcomponent = new AECcomponent();
			theNVPcomponent.setComponentTitle(JsonListNVPcomponent.getJSONObject(i).getString(JSONprotocolKey.COMPONENT_TITLE));
			
			JSONArray params = JsonListNVPcomponent.getJSONObject(i).getJSONArray(JSONprotocolKey.PARAMS);
			for(int j=0;j<params.length();j++){
				theNVPcomponent.createParam(j);
				theNVPcomponent.setParamName(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_NAME).getString(JSONprotocolKey.THIS));
				theNVPcomponent.setParamNameDescription(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_NAME).getString(JSONprotocolKey.DESCRIPTION));
				
				theNVPcomponent.setParamDescription(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_DESCRIPTION).getString(JSONprotocolKey.THIS));
				theNVPcomponent.setParamDescriptionDescription(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_DESCRIPTION).getString(JSONprotocolKey.DESCRIPTION));
				
				theNVPcomponent.setParamValue(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_VALUE).getInt(JSONprotocolKey.THIS));
				theNVPcomponent.setParamValueDescription(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_VALUE).getString(JSONprotocolKey.DESCRIPTION));
				
				theNVPcomponent.setParamUnit(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_UNIT).getString(JSONprotocolKey.THIS));
				theNVPcomponent.setParamUnitDescription(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_UNIT).getString(JSONprotocolKey.DESCRIPTION));
				
				theNVPcomponent.setParamScalingFactor(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_SCALING_FACTOR).getInt(JSONprotocolKey.THIS));
				theNVPcomponent.setParamScalingFactorDescription(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_SCALING_FACTOR).getString(JSONprotocolKey.DESCRIPTION));
				
				theNVPcomponent.setParamScalingOffset(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_SCALING_OFFSET).getInt(JSONprotocolKey.THIS));
				theNVPcomponent.setParamScalingOffsetDescription(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_SCALING_OFFSET).getString(JSONprotocolKey.DESCRIPTION));
				
				theNVPcomponent.setParamInterpretedValue(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_INTERPRETED_VALUE).getInt(JSONprotocolKey.THIS));
				theNVPcomponent.setParamInterpretedValueDescription(j,params.getJSONObject(j).getJSONObject(JSONprotocolKey.PARAM_INTERPRETED_VALUE).getString(JSONprotocolKey.DESCRIPTION));
			}
			this.aecList.add(theNVPcomponent);
		}
	}
	
	/**
	 * Process called to convert a String into a JSON
	 * @param lineFile
	 * @return
	 * @throws JSONException
	 */
    private JSONObject convertStringToJson(String lineFile) throws JSONException{
    	JSONObject mainObj = new JSONObject();
		mainObj = new JSONObject(lineFile);
		return mainObj;
    }
    
    /**
     * Process called to convert a file into line String
     * @return
     */
    private String convertFileToLineString()throws AECexception{
    	BufferedReader br;
    	String lineFile=new String();
    	File srcFile;
    	try{
	    	if(DataStore.getInstance().getPathGenerationJSONfile()!=null){
	    		srcFile = new File(DataStore.getInstance().getPathGenerationJSONfile());
	    		br = new BufferedReader(new FileReader(srcFile));
	    	}else{
	    		srcFile = new File(FileToGenerate.JSON_AEC_DATA);
	    		br = new BufferedReader(new FileReader(srcFile));
	    		DataStore.getInstance().setPathGenerationJSONfile(FileToGenerate.JSON_AEC_DATA);
	    	}
    	}catch(FileNotFoundException fnfe){
    		throw new AECexception("file not found", AECexception.JSON_FILE_NOT_FOUND);
    	}
		
		try{
			String line;
			while((line=br.readLine()) != null){
				lineFile=lineFile+manageLine(line);
			}
			br.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
			throw new AECexception("Json error", AECexception.JSON_FILE_ERROR);
		}
		return lineFile;
    }
    
    /**
     * process called to manage the line of the JSON file
     * @param line
     * @return
     */
    private String manageLine(String line){    	
    	if(line.contains("\t")){
    		line.replace("\t", "");
    	}    	
    	return line;
    }
    
    /**
     * Getter of the NVP component list
     * @return
     */
    public ArrayList<AECcomponent> getAEClist(){
    	return aecList;
    }
}
