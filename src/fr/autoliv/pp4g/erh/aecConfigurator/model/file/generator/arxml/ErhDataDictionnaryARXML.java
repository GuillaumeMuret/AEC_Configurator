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
	Class of the file ErhDataDictionnary.arxml
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.arxml;

import java.util.ArrayList;

import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECgroup;

public class ErhDataDictionnaryARXML {
	
	/**
	 * Process called to create the "ECUextractERH.arxml"
	 */
	public String createFile(){
		return BEGIN_PART+this.createGroupCompuScale() +AFTER_GROUP_SCALE+ this.createAECcompuScale() +END_PART;
	}
	
	/**
	 * Process called to create the compu-scale of the AEC group for the arxml file
	 * @return
	 */
	private String createGroupCompuScale(){
		String scaling=new String();
		ArrayList<AECgroup> aecGroupList = DataStore.getInstance().getAECgroupList();
		for(int i=0;i<aecGroupList.size();i++){
			String name = "KU16_AEC_GROUP_MASK_"+aecGroupList.get(i).getAECgroupName();
			
			scaling+=OFFSET_SPACE_LINE+COMPU_SCALE_OPEN_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+"  "+SHORT_LABEL_OPEN_TAG
					+name
					+SHORT_LABEL_CLOSE_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+"  "+LOWER_LIMIT_INTERVAL_TYPE_CLOSED_OPEN_TAG+Integer.parseInt(aecGroupList.get(i).getAECgroupValue(),16)
					+LOWER_LIMIT_INTERVAL_TYPE_CLOSED_CLOSE_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+"  "+UPPER_LIMIT_INTERVAL_TYPE_CLOSED_OPEN_TAG+Integer.parseInt(aecGroupList.get(i).getAECgroupValue(),16)
					+UPPER_LIMIT_INTERVAL_TYPE_CLOSED_CLOSE_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+"  "+COMPU_CONST_OPEN_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+"    "+VT_OPEN_TAG
					+name+VT_CLOSE_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+"  "+COMPU_CONST_CLOSE_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+COMPU_SCALE_CLOSE_TAG+"\r\n";
		}
		return scaling;
	}
	
	/**
	 * Process called to create the compu-scale of the AEC for the arxml file
	 * @return
	 */
	private String createAECcompuScale(){
		String scaling=new String();
		ArrayList<AECcomponent> aecList = DataStore.getInstance().getAEClist();
		int offset = 0;
		for(int i=-1;i<aecList.size();i++){
			String name = new String();
			if(i==-1){
				name="ERH_KU8_UNDEFINED_AEC_CFG";
			}else{
				if(aecList.get(i).getAECname().toUpperCase().contains("RESERVE")){
					if(offset == 0){
						offset = i;
					}
					name = "ERH_KU8_RESERVED_AEC_"+(i-offset+1);
				}else{
					name="ERH_KU8_"+aecList.get(i).getAECname()+"_AEC_CFG";
				}
			}
			scaling+=OFFSET_SPACE_LINE+COMPU_SCALE_OPEN_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+"  "+SHORT_LABEL_OPEN_TAG
					+name
					+SHORT_LABEL_CLOSE_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+"  "+LOWER_LIMIT_INTERVAL_TYPE_CLOSED_OPEN_TAG+(i+1)
					+LOWER_LIMIT_INTERVAL_TYPE_CLOSED_CLOSE_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+"  "+UPPER_LIMIT_INTERVAL_TYPE_CLOSED_OPEN_TAG+(i+1)
					+UPPER_LIMIT_INTERVAL_TYPE_CLOSED_CLOSE_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+"  "+COMPU_CONST_OPEN_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+"    "+VT_OPEN_TAG
					+name+VT_CLOSE_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+"  "+COMPU_CONST_CLOSE_TAG+"\r\n";
			scaling+=OFFSET_SPACE_LINE+COMPU_SCALE_CLOSE_TAG+"\r\n";
		}
		return scaling;
	}
	
	/**
	 * The compu Scale open tag
	 */
	public static final String OFFSET_SPACE_LINE = "                       ";
	
	/**
	 * The compu Scale open tag
	 */
	public static final String COMPU_SCALE_OPEN_TAG = "<COMPU-SCALE>";
	
	/**
	 * The compu Scale close tag
	 */
	public static final String COMPU_SCALE_CLOSE_TAG = "</COMPU-SCALE>";
	
	/**
	 * The short label open tag
	 */
	public static final String SHORT_LABEL_OPEN_TAG = "<SHORT-LABEL>";
	
	/**
	 * The short label close tag
	 */
	public static final String SHORT_LABEL_CLOSE_TAG = "</SHORT-LABEL>";
	
	/**
	 * The lower limit interval type closed open tag
	 */
	public static final String LOWER_LIMIT_INTERVAL_TYPE_CLOSED_OPEN_TAG = "<LOWER-LIMIT INTERVAL-TYPE=\"CLOSED\">";
	
	/**
	 * The lower limit interval type closed close tag
	 */
	public static final String LOWER_LIMIT_INTERVAL_TYPE_CLOSED_CLOSE_TAG = "</LOWER-LIMIT>";
	
	/**
	 * The upper limit interval type closed open tag
	 */
	public static final String UPPER_LIMIT_INTERVAL_TYPE_CLOSED_OPEN_TAG = "<UPPER-LIMIT INTERVAL-TYPE=\"CLOSED\">";
	
	/**
	 * The upper limit interval type closed close tag
	 */
	public static final String UPPER_LIMIT_INTERVAL_TYPE_CLOSED_CLOSE_TAG = "</UPPER-LIMIT>";
	
	/**
	 * The compu const open tag
	 */
	public static final String COMPU_CONST_OPEN_TAG = "<COMPU-CONST>";
	
	/**
	 * The compu const close tag
	 */
	public static final String COMPU_CONST_CLOSE_TAG = "</COMPU-CONST>";
	
	/**
	 * The VT open tag
	 */
	public static final String VT_OPEN_TAG = "<VT>";
	
	/**
	 * The VT close tag
	 */
	public static final String VT_CLOSE_TAG = "</VT>";

	/**
	 * The begin part of the ARXML file
	 */
	public static final String BEGIN_PART =
			 "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+"<AUTOSAR xmlns=\"http://autosar.org/schema/r4.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://autosar.org/schema/r4.0 autosar_4-0-3.xsd\" T=\"2016-07-26T14:54:59+02:00\">\r\n"
			+"  <AR-PACKAGES>\r\n"
			+"    <AR-PACKAGE T=\"2016-07-26T14:54:56+02:00\">\r\n"
			+"      <SHORT-NAME>AUTOLIV</SHORT-NAME>\r\n"
			+"      <AR-PACKAGES>\r\n"
			+"        <AR-PACKAGE T=\"2016-07-26T14:54:54+02:00\">\r\n"
			+"          <SHORT-NAME>GlobalDictionary</SHORT-NAME>\r\n"
	        +"          <AR-PACKAGES>\r\n"
	        +"            <AR-PACKAGE T=\"2016-07-26T14:55:20+02:00\">\r\n"
	        +"              <SHORT-NAME>CompuMethods</SHORT-NAME>\r\n"
	        +"              <ELEMENTS>\r\n"
			+"                <COMPU-METHOD T=\"2016-06-02T09:47:25+02:00\" UUID=\"aa25e404-7551-45c3-96d0-240cbc3a3922\">\r\n"
			+"                  <SHORT-NAME>scAecGroupsStatusScaling</SHORT-NAME>\r\n"
			+"                  <CATEGORY>TEXTTABLE</CATEGORY>\r\n"
			+"                  <COMPU-INTERNAL-TO-PHYS T=\"2016-06-02T09:47:25+02:00\">\r\n"
			+"                    <COMPU-SCALES>\r\n";
			
			
			
	/**
	 * The begin part of the ARXML file
	 */
	public static final String AFTER_GROUP_SCALE =
			 "                    </COMPU-SCALES>\r\n"
			+"                    <COMPU-DEFAULT-VALUE T=\"2016-06-02T09:47:25+02:00\">\r\n"
			+"                      <VT>KU16_AEC_GROUP_MASK_OTHER</VT>\r\n"
			+"                    </COMPU-DEFAULT-VALUE>\r\n"
			+"                  </COMPU-INTERNAL-TO-PHYS>\r\n"
			+"                </COMPU-METHOD>\r\n"
			+"                <COMPU-METHOD>\r\n"
			+"                  <SHORT-NAME>scAecIdentifierScaling</SHORT-NAME>\r\n"
			+"                  <CATEGORY>TEXTTABLE</CATEGORY>\r\n"
			+"                  <COMPU-INTERNAL-TO-PHYS>\r\n"
			+"                    <COMPU-SCALES>\r\n";
	
	public static final String END_PART = 
			"                     </COMPU-SCALES>\r\n"
			+"                  </COMPU-INTERNAL-TO-PHYS>\r\n"
			+"                </COMPU-METHOD>\r\n"
			+"              </ELEMENTS>\r\n"
			+"            </AR-PACKAGE>\r\n"
			+"          </AR-PACKAGES>\r\n"
			+"        </AR-PACKAGE>\r\n"
			+"      </AR-PACKAGES>\r\n"
			+"    </AR-PACKAGE>\r\n"
			+"  </AR-PACKAGES>\r\n"
			+"</AUTOSAR>\r\n";
}
