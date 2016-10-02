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
	Class of the AEC group, all the AEC group are instantiate here
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.aec;

public class AECgroup {
	
	/**
	 * The AEC group name
	 */
	private String aecGroupName;
	
	/**
	 * The ARXML group value
	 */
	private String aecGroupARXMLvalue;
	
	/**
	 * Main constructor of the AEC group 
	 */
	public AECgroup(){
		this.aecGroupARXMLvalue="";
		this.aecGroupName="";
	}
	
	/**
	 * Setter of AEC group name
	 * @param aecGroupName
	 */
	public void setAECgroupName(String aecGroupName){
		this.aecGroupName=aecGroupName;
	}
	
	/**
	 * Setter of AEC group name
	 * @param componentTitle
	 */
	public void setAECgroupARXMLvalue(String aecGroupARXMLvalue){
		this.aecGroupARXMLvalue=aecGroupARXMLvalue;
	}
	
	/**
	 * Getter of the AEC group name
	 * @return
	 */
	public String getAECgroupName(){
		return this.aecGroupName;
	}
	
	/**
	 * Getter of the AEC group ARXML value
	 * @return
	 */
	public String getAECgroupValue(){
		return this.aecGroupARXMLvalue;
	}
	
}
