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
	Class which contain all the file and the old files
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.file;

import java.util.ArrayList;


public class FileStore {
	
	public static final int ERH_CFG_PUBLIC_H			= 1;
	public static final int ERH_CFG_PRIVATE_H 			= 2;
	public static final int ERH_DATA_DICTIONNARY_ARXML	= 3;
	public static final int AEC_CALIBRATION_S00			= 4;
	
	public static final int FILE_NUMBER 				= 4;
	
	
	/**
     * Singleton management
     */
    private static FileStore instance;    
    
    /**
     * Getter of the instance DataStore
     * @return the instance DataStore
     */
    public static FileStore getInstance () {
        if (instance == null)
            instance = new FileStore();
        return instance;
    }
    
    // Current title
    /**
     * The current file title
     */
    private String currentTitle;
    
    /**
     * Setter of the file title
     * @param title
     */
    public void setFileTitle(String title){
    	this.currentTitle=title;
    }
    
    /**
     * Getter of the file title
     * @return
     */
    public String getFileTitle(){
    	return this.currentTitle;
    }
    
    // Current page
    /**
     * The current page of the file
     */
    private int currentPage;
    
    /**
     * Setter of the current page
     * @param currentPage
     */
    public void setCurrentPage(int currentPage){
    	this.currentPage=currentPage;
    	switch(currentPage){
    	
			case ERH_CFG_PUBLIC_H:
				setFileTitle(FileToGenerate.ERH_CFG_PUBLIC);
				break;
				
			case ERH_CFG_PRIVATE_H:
				setFileTitle(FileToGenerate.ERH_CFG_PRIVATE);
				break;
				
			case ERH_DATA_DICTIONNARY_ARXML:
				setFileTitle(FileToGenerate.ERH_DATA_DICTIONNARY_ARXML);
				break;
				
			case AEC_CALIBRATION_S00:
				setFileTitle(FileToGenerate.AEC_CALIBRATION_S00);
				break;
    	}
    }
    
    /**
     * Getter of the current page
     * @return
     */
    public int getCurrentPage(){
    	return currentPage;
    }
    
    // File : ERH_private.h
    /**
     * The file
     */
    private ArrayList<String> ERH_private_h;
    
    /**
     * The setter of this file
     * @param ERH_private_h
     */
    public void setERHprivateH(ArrayList<String> ERH_private_h){
    	this.ERH_private_h=ERH_private_h;
    }
    
    /**
     * The getter of this file
     * @return
     */
    public ArrayList<String> getERHprivateH(){
    	return ERH_private_h;
    }

    // File : ERH_public.h
    /**
     * The file
     */
    private ArrayList<String> ERH_public_h;
    
    /**
     * The setter of this file
     * @param ERH_private_h
     */
    public void setERHpublicH(ArrayList<String> ERH_public_h){
    	this.ERH_public_h=ERH_public_h;
    }
    
    /**
     * The getter of this file
     * @return
     */
    public ArrayList<String> getERHpublicH(){
    	return ERH_public_h;
    }

    // File : ERH_cfg_private.h
    /**
     * The file
     */
    private ArrayList<String> ERH_cfg_private_h;
    
    /**
     * The setter of this file
     * @param ERH_private_h
     */
    public void setERHcfgPrivateH(ArrayList<String> ERH_cfg_private_h){
    	this.ERH_cfg_private_h=ERH_cfg_private_h;
    }
    
    /**
     * The getter of this file
     * @return
     */
    public ArrayList<String> getERHcfgPrivateH(){
    	return ERH_cfg_private_h;
    }
    
    // File : ERH_cfg_public.h
    /**
     * The file
     */
    private ArrayList<String> ERH_cfg_public_h;
    
    /**
     * The setter of this file
     * @param ERH_private_h
     */
    public void setERHcfgPublicH(ArrayList<String> ERH_cfg_public_h){
    	this.ERH_cfg_public_h=ERH_cfg_public_h;
    }
    
    /**
     * The getter of this file
     * @return
     */
    public ArrayList<String> getERHcfgPublicH(){
    	return ERH_cfg_public_h;
    }
    
    // File : ERH_AEC_cfg_generated.h
    /**
     * The file
     */
    private ArrayList<String> ERH_AEC_cfg_generated_h;
    
    /**
     * The setter of this file
     * @param ERH_private_h
     */
    public void setERHaecCfgGeneratedH(ArrayList<String> ERH_AEC_cfg_generated_h){
    	this.ERH_AEC_cfg_generated_h=ERH_AEC_cfg_generated_h;
    }
    
    /**
     * The getter of this file
     * @return
     */
    public ArrayList<String> getERHaecCfgGeneratedH(){
    	return ERH_AEC_cfg_generated_h;
    }
    
    // File : ERH_AEC_cfg_generated.c
    /**
     * The file
     */
    private ArrayList<String> ERH_AEC_cfg_generated_c;
    
    /**
     * The setter of this file
     * @param ERH_private_h
     */
    public void setERHaecCfgGeneratedC(ArrayList<String> ERH_AEC_cfg_generated_c){
    	this.ERH_AEC_cfg_generated_c=ERH_AEC_cfg_generated_c;
    }
    
    /**
     * The getter of this file
     * @return
     */
    public ArrayList<String> getERHaecCfgGeneratedC(){
    	return ERH_AEC_cfg_generated_c;
    }
    
    // File : AEC_calibration.S00
    /**
     * The file
     */
    private ArrayList<String> AEC_calibration_S00;
    
    /**
     * The setter of this file
     * @param ERH_private_h
     */
    public void setAECcalibrationS00(ArrayList<String> AEC_calibration_S00){
    	this.AEC_calibration_S00=AEC_calibration_S00;
    }
    
    /**
     * The getter of this file
     * @return
     */
    public ArrayList<String> getAECcalibrationS00(){
    	return AEC_calibration_S00;
    }
    
    // File : ECUextract_ERH.arxml
    /**
     * The file
     */
    private ArrayList<String> ECUextract_ERH_arxml;
    
    /**
     * The setter of this file
     * @param ERH_private_h
     */
    public void setECUextractERHarxml(ArrayList<String> ECUextract_ERH_arxml){
    	this.ECUextract_ERH_arxml=ECUextract_ERH_arxml;
    }
    
    /**
     * The getter of this file
     * @return
     */
    public ArrayList<String> getECUextractERHarxml(){
    	return ECUextract_ERH_arxml;
    }
}