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
	Class of the AEC component, all the AEC are instantiate here
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.aec;

import java.util.ArrayList;

public class AECcomponent {
	
	/**
	 * The component title
	 */
	private String componentTitle;
	
	/**
	 * the component parameters
	 */
	private ArrayList<AECattribute> listAECattribute;
	
	/**
	 * Constructor of the NVP component without value
	 */
	public AECcomponent(){
		this.listAECattribute=new ArrayList<AECattribute>();
	}
	
	/**
	 * Constructor of the NVP component with value
	 */
	public AECcomponent(AECcomponent nvpComponent){
		this.componentTitle=nvpComponent.getAECname();
		this.listAECattribute=new ArrayList<AECattribute>();
		for(int i=0;i<nvpComponent.getAECattributes().size();i++){
			this.listAECattribute.add(new AECattribute(nvpComponent.getAECattributes().get(i)));
		}
	}
	
	/**
	 * Setter of the component title
	 * @param componentTitle
	 */
	public void setComponentTitle(String componentTitle){
		this.componentTitle=componentTitle;
	}
	
	/**
	 * Process called to create the component param
	 * @param numParam
	 */
	public void createParam(int numParam){
		this.listAECattribute.add(new AECattribute());
	}
	
	/**
	 * Setter of the param Name
	 * @param numParam
	 * @param paramName
	 */
	public void setParamName(int numParam,String paramName){
		this.listAECattribute.get(numParam).setParamName(paramName);
	}
	
	/**
	 * Setter of the param Name description
	 * @param numParam
	 * @param paramName
	 */
	public void setParamNameDescription(int numParam,String paramNameDescription){
		this.listAECattribute.get(numParam).setAttributeNameDescription(paramNameDescription);
	}
	
	/**
	 * Setter of the param description
	 * @param numParam
	 * @param paramDescription
	 */
	public void setParamDescription(int numParam,String paramDescription){
		this.listAECattribute.get(numParam).setParamDescription(paramDescription);
	}
	
	/**
	 * Setter of the param description description
	 * @param numParam
	 * @param paramDescription
	 */
	public void setParamDescriptionDescription(int numParam,String paramDescriptionDescription){
		this.listAECattribute.get(numParam).setAttributeDescriptionDescription(paramDescriptionDescription);
	}
	
	/**
	 * Setter of the parameter value
	 * @param numParam
	 * @param paramValue
	 */
	public void setParamValue(int numParam,Integer paramValue){
		this.listAECattribute.get(numParam).setParamValue(paramValue);
	}
	
	/**
	 * Setter of the parameter value description
	 * @param numParam
	 * @param paramValue
	 */
	public void setParamValueDescription(int numParam,String paramValueDescription){
		this.listAECattribute.get(numParam).setAttributeValueDescription(paramValueDescription);
	}
	
	/**
	 * Setter of the parameter unit
	 * @param numParam
	 * @param paramUnit
	 */
	public void setParamUnit(int numParam,String paramUnit){
		this.listAECattribute.get(numParam).setParamUnit(paramUnit);
	}
	
	/**
	 * Setter of the parameter unit description
	 * @param numParam
	 * @param paramUnit
	 */
	public void setParamUnitDescription(int numParam,String paramUnitDescription){
		this.listAECattribute.get(numParam).setAttributeUnitDescription(paramUnitDescription);
	}
	
	/**
	 * Setter of the scaling factor unit
	 * @param numParam
	 * @param paramScalingFactor
	 */
	public void setParamScalingFactor(int numParam,Integer paramScalingFactor){
		this.listAECattribute.get(numParam).setAttributeScalingFactor(paramScalingFactor);
	}
	
	/**
	 * Setter of the scaling factor unit description
	 * @param numParam
	 * @param paramScalingFactor
	 */
	public void setParamScalingFactorDescription(int numParam,String paramScalingFactorDescription){
		this.listAECattribute.get(numParam).setAttributeScalingFactorDescription(paramScalingFactorDescription);
	}
	
	/**
	 * Setter of the parameter scaling offset
	 * @param numParam
	 * @param paramScalingOffset
	 */
	public void setParamScalingOffset(int numParam,Integer paramScalingOffset){
		this.listAECattribute.get(numParam).setAttributeScalingOffset(paramScalingOffset);
	}
	
	/**
	 * Setter of the parameter scaling offset
	 * @param numParam
	 * @param paramScalingOffset
	 */
	public void setParamScalingOffsetDescription(int numParam,String paramScalingOffsetDescription){
		this.listAECattribute.get(numParam).setAttributeScalingOffsetDescription(paramScalingOffsetDescription);
	}
	
	/**
	 * Setter of the parameter interpreted value
	 * @param numParam
	 * @param paramInterpretedValue
	 */
	public void setParamInterpretedValue(int numParam,Integer paramInterpretedValue){
		this.listAECattribute.get(numParam).setAttributeInterpretedValue(paramInterpretedValue);
	}
	
	/**
	 * Setter of the parameter interpreted value description
	 * @param numParam
	 * @param paramInterpretedValue
	 */
	public void setParamInterpretedValueDescription(int numParam,String paramInterpretedValueDescription){
		this.listAECattribute.get(numParam).setAttributeInterpretedValueDescription(paramInterpretedValueDescription);
	}

	/**
	 * Getter of the component title
	 * @return
	 */
	public String getAECname() {
		return componentTitle;
	}

	/**
	 * Getter of the component parameters
	 * @return
	 */
	public ArrayList<AECattribute> getAECattributes(){
		return listAECattribute;
	}	
	
	/**
	 * Getter of the aec attributes of this parameter number
	 * @param num
	 * @return
	 */
	public AECattribute getComponentParamNum(Integer num) {
		return listAECattribute.get(num);
	}
}
