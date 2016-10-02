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
	Class singleton which has all the object use in this program
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model;

import java.util.ArrayList;

import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECgroup;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECstructure;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.json.GenerateJSONfile;
import fr.autoliv.pp4g.erh.aecConfigurator.view.centerpanel.inside.LabelAEC;

public class DataStore {
	/**
     * Singleton management
     */
    private static DataStore instance;    
    
    /**
     * Getter of the instance DataStore
     * @return the instance DataStore
     */
    public static DataStore getInstance () {
        if (instance == null)
            instance = new DataStore();
        return instance;
    }
    
    ////// Generation CH source path //////
    /**
     * Object generationCsourceFolderPath
     */
    private String pathGenerationCHsourceFolder;

    /**
     * Setter of the generation folder path
     * @param generationFolderPath
     */
    public void setPathGenerationCHfolder(String pathGenerationCHsourceFolder){
    	if(pathGenerationCHsourceFolder.length()==0){
    		pathGenerationCHsourceFolder="./";
    	}else{
            this.pathGenerationCHsourceFolder=pathGenerationCHsourceFolder;
    	}
    }

    /**
     * Getter of the generation folder path
     * @return
     */
    public String getPathGenerationCHfolder(){
        return pathGenerationCHsourceFolder;
    }
    
    ////// Generation ARXML source path //////
    /**
     * Object generationCsourceFolderPath
     */
    private String pathGenerationARXMLsourceFolder;

    /**
     * Setter of the generation folder path
     * @param generationFolderPath
     */
    public void setPathGenerationARXMLfolder(String pathGenerationARXMLsourceFolder){
    	if(pathGenerationARXMLsourceFolder.length()==0){
    		pathGenerationARXMLsourceFolder="./";
    	}else{
            this.pathGenerationARXMLsourceFolder=pathGenerationARXMLsourceFolder;
    	}
    }

    /**
     * Getter of the generation folder path
     * @return
     */
    public String getPathGenerationARXMLfolder(){
        return pathGenerationARXMLsourceFolder;
    }
    
    ////// Generation S00 source path //////
    /**
     * Object generationCsourceFolderPath
     */
    private String pathGenerationS00sourceFolder;

    /**
     * Setter of the generation folder path
     * @param generationFolderPath
     */
    public void setPathGenerationS00folder(String pathGenerationS00sourceFolder){
    	if(pathGenerationS00sourceFolder.length()==0){
    		pathGenerationS00sourceFolder="./";
    	}else{
            this.pathGenerationS00sourceFolder=pathGenerationS00sourceFolder;
    	}
    }

    /**
     * Getter of the generation folder path
     * @return
     */
    public String getPathGenerationS00folder(){
        return pathGenerationS00sourceFolder;
    }
    
    ////// AEC structure name //////
    /**
     * Object nvpComponentStructureName
     */
    private String aecStructureName;

    /**
     * Setter of the generation folder path
     * @param generationFolderPath
     */
    public void setAECstructureName(String aecStructureName){
        this.aecStructureName=aecStructureName;
    }

    /**
     * Getter of the generation folder path
     * @return
     */
    public String getAECstructureName(){
        return aecStructureName;
    }
        
    
    ////// Generation JSON path //////
    /**
     * Object generationJSONfilePath
     */
    private String pathGenerationJSONfile;

    /**
     * Setter of the generation folder path
     * @param generationFolderPath
     */
    public void setPathGenerationJSONfile(String pathGenerationJSONfile){
        this.pathGenerationJSONfile=pathGenerationJSONfile;
    }

    /**
     * Getter of the generation folder path
     * @return
     */
    public String getPathGenerationJSONfile(){
        return pathGenerationJSONfile;
    }

    ////// Group list //////
    /**
     * Object Group List
     */
    private ArrayList<AECgroup> aecGroupList;

    /**
     * Setter of the Group List
     * @param generationFolderPath
     */
    public void setAECgroupList(ArrayList<AECgroup> aecGroupList){
        this.aecGroupList=aecGroupList;
        this.createAECgroupStringList();
    }

    /**
     * Getter of the Group List
     * @return
     */
    public ArrayList<AECgroup> getAECgroupList(){
        return aecGroupList;
    }
    
    ////// Group String list //////
    /**
     * Object String Group List
     */
    private String[] aecGroupStringList;

    /**
     * Setter of the Group List
     * @param generationFolderPath
     */
    public void createAECgroupStringList(){
    	this.aecGroupStringList = new String[this.aecGroupList.size()];
    	for(int i=0;i<aecGroupList.size();i++){
    		this.aecGroupStringList[i]=this.aecGroupList.get(i).getAECgroupName();
    	}
    }

    /**
     * Getter of the Group String List
     * @return
     */
    public String[] getAECgroupStringList(){
        return aecGroupStringList;
    }
    
    ////// Structure list //////
    /**
     * Object generationJSONfilePath
     */
    private ArrayList<AECstructure> aecStructureList;

    /**
     * Setter of the generation folder path
     * @param generationFolderPath
     */
    public void setAECstructureList(ArrayList<AECstructure> aecStructureList){
        this.aecStructureList=aecStructureList;
    }

    /**
     * Getter of the generation folder path
     * @return
     */
    public ArrayList<AECstructure> getAECstructureList(){
        return aecStructureList;
    }

    ////// AEC List //////
    /**
     * Object listNVPcomponent
     */
    private ArrayList<AECcomponent> aecList;

    /**
     * Setter of the NVP component list
     * @param listNVPcomponent
     */
    public void setAEClist(ArrayList<AECcomponent> aecList){
        this.aecList=aecList;
    }

    /**
     * Getter of the NVP component list
     * @return
     */
    public ArrayList<AECcomponent> getAEClist(){
        return aecList;
    }
    
    ////// CURRENT NVP COMPONENT KEY //////
    /**
     * Object NVP component Key
     */
    private Integer aecKey;

    /**
     * Setter of the NVP component Key
     * @param key
     */
    public void setAECkey(Integer key){
        this.aecKey=key;
    }

    /**
     * Getter of the NVP component Key
     * @return
     */
    public Integer getAECkey(){
        return aecKey;
    }
    
    ////// SOFTWARE ALIAS IN RAM //////
    /**
     * Object software alias in ram
     */
    private String softwareAliasInRam;

    /**
     * Setter of the software alias in ram
     * @param key
     */
    public void setSoftwareAliasInRam(String softwareAliasInRam){
        this.softwareAliasInRam=softwareAliasInRam;
    }

    /**
     * Getter of the software alias in ram
     * @return
     */
    public String getSoftwareAliasInRam(){
        return softwareAliasInRam;
    }
    
    
    ////// PADDING BYTE VALUE //////
    /**
     * Object padding byte value
     */
    private String paddingByteValue;

    /**
     * Setter of the padding byte value
     * @param key
     */
    public void setPaddingByteValue(String paddingByteValue){
    	if(paddingByteValue.length()==1){
    		this.paddingByteValue="0"+paddingByteValue;
    	}else{
    		this.paddingByteValue=paddingByteValue;
    	}
    	
    }

    /**
     * Getter of the padding byte value
     * @return
     */
    public String getPaddingByteValue(){
        return paddingByteValue;
    }
    
    ////// FIRST MEMORY VALUE //////
    /**
     * Object first memory value
     */
    private Integer firstMemoryValue;

    /**
     * Setter of the first memory value
     * @param key
     */
    public void setFirstMemoryValue(Integer firstMemoryValue){
        this.firstMemoryValue=firstMemoryValue;
    }

    /**
     * Getter of the first memory value
     * @return
     */
    public Integer getFirstMemoryValue(){
        return firstMemoryValue;
    }
    
    ////// AEC calibration max size //////
    /**
     * Object aecCalibrationMaxSize
     */
    private Integer aecCalibrationMaxSize;

    /**
     * Setter of the aecCalibrationMaxSize
     * @param key
     */
    public void setAECcalibrationMaxSize(Integer aecCalibrationMaxSize){
        this.aecCalibrationMaxSize=aecCalibrationMaxSize;
    }

    /**
     * Getter of the aecCalibrationMaxSize
     * @return
     */
    public Integer getAECcalibrationMaxSize(){
        return aecCalibrationMaxSize;
    }
    
    ////// AEC calibration size //////
    /**
     * Object aecCalibrationSize
     */
    private Integer aecCalibrationSize;

    /**
     * Setter of the aecCalibrationMaxSize
     * @param key
     */
    public void setAECcalibrationSize(Integer aecCalibrationSize){
        this.aecCalibrationSize=aecCalibrationSize;
    }

    /**
     * Getter of the aecCalibrationMaxSize
     * @return
     */
    public Integer getAECcalibrationSize(){
        return aecCalibrationSize;
    }
    
    ////// CURRENT EDIT PARAMETER //////
    /**
     * Object current edition parameter
     */
    private int aecAttributeKey;

    /**
     * Setter of the current edition parameter
     * @param key
     */
    public void setAECattributeKey(int aecAttributeKey){
        this.aecAttributeKey=aecAttributeKey;
    }

    /**
     * Getter of the current edition parameter
     * @return
     */
    public int getAECattributeKey(){
        return aecAttributeKey;
    }   
    
    
    ////// THE LABEL LIST //////
    /**
     * Object current edition parameter
     */
    private ArrayList<LabelAEC> aecLabelList;

    /**
     * Setter of the current edition parameter
     * @param key
     */
    public void setAEClabelList(ArrayList<LabelAEC> aecLabelList){
        this.aecLabelList=aecLabelList;
    }
    
    /**
     * Process called to initialize all the label color of the NVP component list
     */
    public void setInitialLabel(){
    	ArrayList<LabelAEC> listLabel = this.getAEClabelList();
		for(int i = 0;i<listLabel.size();i++){
			listLabel.get(i).setInitialLabel();
		}
    } 
    
    /**
     * Getter of the current edition parameter
     * @return
     */
    public ArrayList<LabelAEC> getAEClabelList(){
        return this.aecLabelList;
    } 
    
    /**
     * The generate json file 
     */
    private GenerateJSONfile generateJSONfile;
    
    /**
     * Getter of the generateJsonfile instance
     * @return
     */
    public GenerateJSONfile getGenerateJSONfileClass(){
    	return generateJSONfile;
    }
    
    /**
     * Setter of the generateJSONfile instance
     * @param gen
     */
    public void setGenerateJSONfileClass(GenerateJSONfile gen){
    	this.generateJSONfile = gen;
    }
    
    /**
     * The new AEC selected
     */
    private int newAECselected;
    
    /**
     * Getter of the new AEC selected
     * @return
     */
    public int getNewAECselected(){
    	return newAECselected;
    }
    
    /**
     * Setter of the new AEC selected
     * @param newAECselected
     */
    public void setNewAECselected(int newAECselected){
    	this.newAECselected = newAECselected;
    }
    
    /**
     * The new attribute selected
     */
    private int newAttributeselected;
    
    /**
     * Getter of the new attribute selected
     * @return
     */
    public int getNewAttributeSelected(){
    	return newAttributeselected;
    }
    
    /**
     * Setter of the new attribute selected
     * @param newAECselected
     */
    public void setNewAttributeSelected(int newAttributeselected){
    	this.newAttributeselected = newAttributeselected;
    }
}
