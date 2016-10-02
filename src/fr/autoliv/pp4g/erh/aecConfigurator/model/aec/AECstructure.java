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
	Class of the AEC structure where all the AEC structure information are instantiate
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.aec;


public class AECstructure {

	/**
	 * The attribute type
	 */
	private String attributeType;
	
	/**
	 * The attribute name
	 */
	private String attributeName;
	
	/**
	 * The attribute description
	 */
	private String attributeDescription;
	
	/**
	 * The attribute size
	 */
	private Integer attributeSize;
	
	/**
	 * Empty constructor
	 */
	public AECstructure(){
	}

	/**
	 * Getter of the attribute type
	 * @return
	 */
	public String getAttributeType() {
		return attributeType;
	}

	/**
	 * Setter of the attribute type
	 * @param attributeType
	 */
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	/**
	 * Getter of the attribute name
	 * @return
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * Setter of the attribute name
	 * @param attributeName
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * Getter of the attribute description
	 * @return
	 */
	public String getAttributeDescription() {
		return attributeDescription;
	}
	
	/**
	 * Setter of the attribute description
	 * @param attributeDescription
	 */
	public void setAttributeDescription(String attributeDescription) {
		this.attributeDescription = attributeDescription;
	}
	
	/**
	 * Getter of the attribute size
	 * @return
	 */
	public Integer getAttributeSize(){
		return attributeSize;
	}
	
	/**
	 * Setter of the attribute size
	 * @param size
	 */
	public void setAttributeSize(int size){
		this.attributeSize = size;
	}
}