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
	Class called to create the AEC_calibration.S00 file
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.s00;

import java.util.ArrayList;

import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECstructure;


public class AECcalibrationS00 {
	
	/**
	 * The first element of the file
	 */
	public static final String BEGIN = "S0170000454D554C415445445F454550524F4D5F53545542D3\r\n";
	
	/**
	 * The last element of the file
	 */
	public static final String END = "S70500000000FA\r\n";
	
	/**
	 * The zero's number to write for the address
	 */
	private String nb0toWriteAddress;
	
	/**
	 * The data size
	 */
	private int dataSize;
	
	/**
	 * The current memory value
	 */
	private Integer currentMemoryValue;
	
	/**
	 * The offset used
	 */
	private int offset;
	
	/**
	 * Process called to create the "ERH_AEC_cfg_generated.h"
	 * @param listNVPcalibration
	 * @return the created file into string
	 */
	public String createFile(){
		return BEGIN+createAECbitFile()+END;
	}
	
	/**
	 * Create the macro with the start address
	 * @return
	 */
	private String createAECbitFile(){
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		String file = new String();
		offset=0;
		this.dataSize = createDataSize();
		String SX = createSX();
		String lineSize = createLineSize();
		this.currentMemoryValue=DataStore.getInstance().getFirstMemoryValue();
		for(int i=0;i<aecList.size();i++){
			file+=SX;
			String line = new String();
			line+=lineSize;
			line+=getAddressValue();
			line+=getDataLine(i);
			line+=getCheckSum(line);
			
			file+=line;
			file+="\r\n";
			
			this.currentMemoryValue+=this.dataSize;
		}
		return file.toUpperCase();
	}
	
	/**
	 * Process called to get the checksum value
	 */
	private String getCheckSum(String line){
		Integer sum = 0;
		// Get all the byte of the line and calculate the sum
		for(int i=0;i<line.length()/2;i++){
			String octetString = line.substring(i*2, i*2+2);
			sum += Integer.decode("0x"+octetString);
		}
		// Calculate the hex value of the sum
		String maskSumString = Integer.toHexString(sum);
		Integer maskSum;
		// Get the sum on 1 byte
		if(maskSumString.length()>2){
			maskSum = Integer.decode("0x"+maskSumString.substring(maskSumString.length()-2, maskSumString.length()));
		}else{
			maskSum = Integer.decode("0x"+maskSumString);
		}
		// Calculate FF - SUM
		Integer checkSum = 255-maskSum;
		if(Integer.toHexString(checkSum).length()%2==0){
			return Integer.toHexString(checkSum);
		}else{
			return "0"+Integer.toHexString(checkSum);
		}
	}
	
	/**
	 * Process called to set the aec component data of the line
	 */
	private String getDataLine(int i){
		String dataLine = new String();
		AECcomponent currentAEC = DataStore.getInstance().getAEClist().get(i);
		ArrayList<AECstructure> aecStructList = DataStore.getInstance().getAECstructureList();
		for(int j=0;j<currentAEC.getAECattributes().size();j++){
			String zero = new String();
			if(aecStructList.get(j).getAttributeName().toUpperCase().contains("RESERVED")){
				dataLine+=DataStore.getInstance().getPaddingByteValue();
			}else{
				Integer currentAttrValue = currentAEC.getAECattributes().get(j).getAttributeValue();
				Integer currentAttrSize = aecStructList.get(j).getAttributeSize();
				int nb0 = currentAttrSize*2 - Integer.toHexString(currentAttrValue).length();
				for(int k=0;k<nb0;k++){
					zero+="0";
				}
				dataLine+=zero+Integer.toHexString(currentAttrValue);
			}
		}
		return dataLine;
	}
	
	/**
	 * Process called to return the memory address 
	 */
	private String getAddressValue(){
		while(!(this.currentMemoryValue%4==0)){
			this.currentMemoryValue+=1;
		}
		Integer firstMemoryValue = DataStore.getInstance().getFirstMemoryValue();
		if(Integer.toHexString(this.currentMemoryValue).length()==Integer.toHexString(firstMemoryValue).length()+offset+1){
			nb0toWriteAddress=nb0toWriteAddress.substring(1);
			offset++;
		}
		return nb0toWriteAddress+Integer.toHexString(this.currentMemoryValue);
	}
	
	/**
	 * Process called to know the data size
	 */
	private Integer createDataSize(){
		ArrayList<AECstructure> aecStructList = DataStore.getInstance().getAECstructureList();
		Integer dataSize = 0;
		for(int i=0;i<aecStructList.size();i++){
			dataSize+=aecStructList.get(i).getAttributeSize();
		}
		return dataSize;
	}
	
	/**
	 * Process called to know the Address size
	 */
	private String createSX(){
		nb0toWriteAddress=new String("");
		Integer firstMemoryValue = DataStore.getInstance().getFirstMemoryValue();
		if(Integer.toHexString(firstMemoryValue).length()%2 == 0){
			if(Integer.toHexString(firstMemoryValue+dataSize*DataStore.getInstance().getAEClist().size()).length()>Integer.toHexString(firstMemoryValue).length()){
				nb0toWriteAddress+="00";
				return "S"+((Integer.toHexString(firstMemoryValue).length()/2));
			}else{
				return "S"+((Integer.toHexString(firstMemoryValue).length()/2)-1);
			}
		}else{
			nb0toWriteAddress+="0";
			if(Integer.toHexString(firstMemoryValue+dataSize*DataStore.getInstance().getAEClist().size()).length()>Integer.toHexString(firstMemoryValue).length()){
				nb0toWriteAddress+="00";
				return "S"+((Integer.toHexString(firstMemoryValue).length()/2)+1);
			}else{
				return "S"+((Integer.toHexString(firstMemoryValue).length()/2));
			}
		}
	}
	
	/**
	 * Process called to return the data size to hexadecimal format
	 * @return
	 */
	private String createLineSize(){
		ArrayList<AECstructure> aecStructList = DataStore.getInstance().getAECstructureList();
		Integer lineSize = (nb0toWriteAddress+Integer.toHexString(DataStore.getInstance().getFirstMemoryValue())).length()/2;
		for(int i=0;i<aecStructList.size();i++){
			lineSize += aecStructList.get(i).getAttributeSize();
		}
		// Add the checksum 
		lineSize+=1;
		if(Integer.toHexString(lineSize).length()==2){
			return Integer.toHexString(lineSize);
		}else{
			return "0"+Integer.toHexString(lineSize);
		}
		
	}
}
