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
	Class of the AEC attribute. All the attribute are instantiate here
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.aec;

public class AECattribute {
	
	/**
	 * The parameter name
	 */
	private String paramName;
	
	/**
	 * The parameter name description
	 */
	private String paramNameDescription;
	
	/**
	 * The parameter description
	 */
	private String paramDescription;
	
	/**
	 * The parameter description description
	 */
	private String paramDescriptionDescription;
	
	/**
	 * The parameter size description
	 */
	private String paramSizeDescription;
	
	/**
	 * The parameter value
	 */
	private Integer paramValue;
	
	/**
	 * The parameter value description
	 */
	private String paramValueDescription;
	
	/**
	 * The parameter unit
	 */
	private String paramUnit;
	
	/**
	 * The parameter unit description
	 */
	private String paramUnitDescription;
	
	/**
	 * The parameter scaling factor
	 */
	private Integer paramScalingFactor;
	
	/**
	 * The parameter scaling factor description
	 */
	private String paramScalingFactorDescription;
	
	/**
	 * The parameter scaling offset
	 */
	private Integer paramScalingOffset;
	
	/**
	 * The parameter scaling offset description
	 */
	private String paramScalingOffsetDescription;
	
	/**
	 * The parameter interpreted value
	 */
	private Integer paramInterpretedValue;
	
	/**
	 * The parameter interpreted value description
	 */
	private String paramInterpretedValueDescription;
	
	/**
	 * The constructor of the component parameter with nothing
	 */
	public AECattribute(){
	}
	
	/**
	 * The constructor of the component parameter
	 * @param aecAttribute
	 */
	public AECattribute(AECattribute aecAttribute){
		this.paramName = 						aecAttribute.getAttributeName();
		this.paramNameDescription = 			aecAttribute.getAttributeNameDescription();
		this.paramDescription = 				aecAttribute.getAttributeDescription();
		this.paramDescriptionDescription = 		aecAttribute.getAttributeDescriptionDescription();
		this.paramValue = 						aecAttribute.getAttributeValue();
		this.paramValueDescription = 			aecAttribute.getAttributeValueDescription();
		this.paramUnit =						aecAttribute.getAttributeUnit();
		this.paramUnitDescription = 			aecAttribute.getAttributeUnitDescription();
		this.paramScalingFactor = 				aecAttribute.getAttributeScalingFactor();
		this.paramScalingFactorDescription = 	aecAttribute.getAttributeScalingFactorDescription();
		this.paramScalingOffset = 				aecAttribute.getAttributeScalingOffset();
		this.paramScalingOffsetDescription = 	aecAttribute.getAttributeScalingOffsetDescription();
		this.paramInterpretedValue= 			aecAttribute.getAttributeInterpretedValue();
		this.paramInterpretedValueDescription= 	aecAttribute.getAttributeInterpretedValueDescription();
	}
	
	/**
	 * Setter of the parameter name
	 * @param paramName
	 */
	public void setParamName(String paramName){
		this.paramName=paramName;
	}
	
	/**
	 * Setter of the parameter description
	 * @param paramDescription
	 */
	public void setParamDescription(String paramDescription){
		this.paramDescription=paramDescription;
	}
	
	/**
	 * Setter of the parameter value
	 * @param paramValue
	 */
	public void setParamValue(Integer paramValue){
		this.paramValue=paramValue;
	}
	
	/**
	 * Setter of the parameter unit
	 * @param paramUnit
	 */
	public void setParamUnit(String paramUnit){
		this.paramUnit=paramUnit;
	}
	
	/**
	 * Setter of the parameter scaling factor
	 * @param paramScalingFactor
	 */
	public void setAttributeScalingFactor(Integer paramScalingFactor){
		this.paramScalingFactor=paramScalingFactor;
	}
	
	/**
	 * Setter of the parameter scaling offset
	 * @param paramScalingOffset
	 */
	public void setAttributeScalingOffset(Integer paramScalingOffset){
		this.paramScalingOffset=paramScalingOffset;
	}
	
	/**
	 * Setter of the parameter interpreted value
	 * @param paramInterpretedValue
	 */
	public void setAttributeInterpretedValue(Integer paramInterpretedValue){
		this.paramInterpretedValue=paramInterpretedValue;
	}
	
	/**
	 * Getter of the parameter name
	 * @return
	 */
	public String getAttributeName() {
		return paramName;
	}

	/**
	 * Getter of the parameter description
	 * @return
	 */
	public String getAttributeDescription() {
		return paramDescription;
	}

	/**
	 * Getter of the parameter value
	 * @return
	 */
	public Integer getAttributeValue() {
		return paramValue;
	}

	/**
	 * Getter of the parameter unit
	 * @return
	 */
	public String getAttributeUnit() {
		return paramUnit;
	}

	/**
	 * Getter of the parameter scaling factor
	 * @return
	 */
	public Integer getAttributeScalingFactor() {
		return paramScalingFactor;
	}

	/**
	 * Getter of the parameter scaling offset
	 * @return
	 */
	public Integer getAttributeScalingOffset() {
		return paramScalingOffset;
	}

	/**
	 * Getter of the parameter interpreted value
	 * @return
	 */
	public Integer getAttributeInterpretedValue() {
		return paramInterpretedValue;
	}

	/**
	 * Getter of the parameter name description
	 * @return
	 */
	public String getAttributeNameDescription() {
		return paramNameDescription;
	}

	/**
	 * Setter of the param name description
	 * @param paramNameDescription
	 */
	public void setAttributeNameDescription(String paramNameDescription) {
		this.paramNameDescription = paramNameDescription;
	}

	/**
	 * Getter of the param description description
	 * @return
	 */
	public String getAttributeDescriptionDescription() {
		return paramDescriptionDescription;
	}

	/**
	 * Setter of the param description description
	 * @param paramDescriptionDescription
	 */
	public void setAttributeDescriptionDescription(String paramDescriptionDescription) {
		this.paramDescriptionDescription = paramDescriptionDescription;
	}

	/**
	 * Getter of the param size description
	 * @return
	 */
	public String getAttributeSizeDescription() {
		return paramSizeDescription;
	}

	/**
	 * Setter of the param size description
	 * @param paramSizeDescription
	 */
	public void setParamSizeDescription(String paramSizeDescription) {
		this.paramSizeDescription = paramSizeDescription;
	}

	/**
	 * Getter of the param value description
	 * @return
	 */
	public String getAttributeValueDescription() {
		return paramValueDescription;
	}

	/**
	 * Setter of the param value description
	 * @param paramValueDescription
	 */
	public void setAttributeValueDescription(String paramValueDescription) {
		this.paramValueDescription = paramValueDescription;
	}

	/**
	 * Getter of the param unit description
	 * @return
	 */
	public String getAttributeUnitDescription() {
		return paramUnitDescription;
	}

	/**
	 * Setter of the param unit description
	 * @param paramUnitDescription
	 */
	public void setAttributeUnitDescription(String paramUnitDescription) {
		this.paramUnitDescription = paramUnitDescription;
	}

	/**
	 * Getter of the param Scaling factor description
	 * @return
	 */
	public String getAttributeScalingFactorDescription() {
		return paramScalingFactorDescription;
	}

	/**
	 * Setter of the scaling factor description
	 * @param paramScalingFactorDescription
	 */
	public void setAttributeScalingFactorDescription(
			String paramScalingFactorDescription) {
		this.paramScalingFactorDescription = paramScalingFactorDescription;
	}

	/**
	 * Getter of the scaling offset description
	 * @return
	 */
	public String getAttributeScalingOffsetDescription() {
		return paramScalingOffsetDescription;
	}

	/**
	 * Setter of the scaling offset description
	 * @param paramScalingOffsetDescription
	 */
	public void setAttributeScalingOffsetDescription(
			String paramScalingOffsetDescription) {
		this.paramScalingOffsetDescription = paramScalingOffsetDescription;
	}

	/**
	 * Getter of the interpreted value description
	 * @return
	 */
	public String getAttributeInterpretedValueDescription() {
		return paramInterpretedValueDescription;
	}

	/**
	 * Setter of the interpreted value description
	 * @param paramInterpretedValueDescription
	 */
	public void setAttributeInterpretedValueDescription(
			String paramInterpretedValueDescription) {
		this.paramInterpretedValueDescription = paramInterpretedValueDescription;
	}
}
