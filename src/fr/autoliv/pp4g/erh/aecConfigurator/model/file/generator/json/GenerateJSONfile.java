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
	Class called to create the AEC_Configurator_Data.json
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.json;

import java.awt.Cursor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import fr.autoliv.pp4g.erh.aecConfigurator.model.DataStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECattribute;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECcomponent;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECgroup;
import fr.autoliv.pp4g.erh.aecConfigurator.model.aec.AECstructure;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;

public class GenerateJSONfile {
	
	/**
	 * Different tag that could be open or close in a JSON file
	 */
	public static final int HOOK = 1;
	public static final int BRACKET = 2;
	
	
	/**
	 * The destination file
	 */
	private File destFile;  
	
	/**
	 * The file output stream 
	 */
	private FileOutputStream fileOutStream;
	
	/**
	 * The buffer writer
	 */
	private BufferedWriter bw;
	
	/**
	 * The NVP component list
	 */
	private ArrayList<AECcomponent> aecList;
	
	/**
	 * The NVP component parameter list
	 */
	private ArrayList<AECattribute> aecAttributes;
	
	
	/**
	 * The current number of tabulation
	 */
	private int nbTabulation;
	
	/**
	 * The generate JSON thread
	 */
	private GenerateJSONthread generateJSONthread;
	
	/**
	 * iteration to know if the user has tap on the save button
	 */
	public boolean forceToSave;
	
	/**
	 * Constructor to generate the JSONfile
	 */
	public GenerateJSONfile(){
		this.forceToSave=false;
		this.generateJSONthread = new GenerateJSONthread();
	}
	
	/**
	 * Process called to execute the generation of the JSON file
	 */
	synchronized public void execute(){
		DialogStore.getInstance().getMainFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if(forceToSave&&this.generateJSONthread.getState()==Thread.State.RUNNABLE){
			while(this.generateJSONthread.getState()!=Thread.State.TERMINATED){}
			this.generateJSONthread=new GenerateJSONthread();
			this.generateJSONthread.start();
		}else{
			if(this.generateJSONthread.getState()==Thread.State.TERMINATED){
				this.generateJSONthread=new GenerateJSONthread();
				this.generateJSONthread.start();
			}
			if(this.generateJSONthread.getState()==Thread.State.NEW){
				this.generateJSONthread.start();
			}
		}
		DialogStore.getInstance().getMainFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	private class GenerateJSONthread extends Thread{
		
		@Override
		public void run(){
			nbTabulation=0;
			aecList = DataStore.getInstance().getAEClist();
	    	try {
	        	if(DataStore.getInstance().getPathGenerationJSONfile()!=null){
	        		destFile = new File(DataStore.getInstance().getPathGenerationJSONfile());
	        		fileOutStream = new FileOutputStream(destFile);
	    			bw = new BufferedWriter(new OutputStreamWriter(fileOutStream,"UTF-8"));
	        	}else{
	        		throw new Exception();
	        	}
	
				this.beginOfJsonFile();
	
				for(int i = 0; i < aecList.size(); i++) {
	    	    	this.writeComponentInfo(i);
	
					aecAttributes=aecList.get(i).getAECattributes();
					for(int j=0;j<aecAttributes.size();j++){
						
						this.writeComponentParams(j);
						this.writeClosingTag(i,j);
						
		        	}
	    	    }
	        	closeTag(HOOK,false);
	    	    closeTag(BRACKET,false);
	    	    bw.close();    	
	    	} catch(FileNotFoundException ffe){
	    		
	    	} catch(Exception ioe) {
	    	    ioe.printStackTrace();
	    	    DialogStore.getInstance().displayGenerationJSONfilePathDialog();
	    	}
	    	forceToSave=false;
		}
		
		/**
		 * Process called to create a tag
		 */
		public void openTag(int num)throws Exception{
			if(num==BRACKET)
				bw.write(manageTabulations()+"{");
			if(num==HOOK)
				bw.write(manageTabulations()+"[");
			bw.newLine();
			nbTabulation++;
		}
		
		/**
		 * Process called to close a tag
		 */
		public void closeTag(int num,boolean comma)throws Exception{
			nbTabulation--;
			if(num==BRACKET){
				if(comma){
					bw.write(manageTabulations()+"},");
				}else{
					bw.write(manageTabulations()+"}");
				}				
			}else if(num==HOOK){
				if(comma){
					bw.write(manageTabulations()+"],");
				}else{
					bw.write(manageTabulations()+"]");
				}
			}
			bw.newLine();
		}
		
		
		
		/**
		 * Process called to write the tabulation
		 */
		public String manageTabulations(){
			String str=new String();
			for(int i=0;i<nbTabulation;i++){
				str=str+"\t";
			}
			return str;	
		}
		
		/**
		 * Process called to create the first tag and information of the JSON file
		 * @throws Exception
		 */
		private void beginOfJsonFile() throws Exception{
			this.openTag(BRACKET);
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.GENERATION_CH_FOLDER_PATH+"\":\""+DataStore.getInstance().getPathGenerationCHfolder()+"\",");
			bw.newLine();
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.GENERATION_ARXML_FOLDER_PATH+"\":\""+DataStore.getInstance().getPathGenerationARXMLfolder()+"\",");
			bw.newLine();
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.GENERATION_S00_FOLDER_PATH+"\":\""+DataStore.getInstance().getPathGenerationS00folder()+"\",");
			bw.newLine();
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.SOFTWARE_ALIAS_IN_RAM+"\":\""+DataStore.getInstance().getSoftwareAliasInRam()+"\",");
			bw.newLine();
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.PADDING_BYTE_VALUE+"\":\""+DataStore.getInstance().getPaddingByteValue()+"\",");
			bw.newLine();
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.FIRST_MEMORY_VALUE+"\":\""+DataStore.getInstance().getFirstMemoryValue()+"\",");
			bw.newLine();
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.AEC_CALIBRATION_MAX_SIZE+"\":\""+DataStore.getInstance().getAECcalibrationMaxSize()+"\",");
			bw.newLine();
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.AEC_STRUCTURE_NAME+"\":\""+DataStore.getInstance().getAECstructureName()+"\",");
			bw.newLine();
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.AEC_GROUP_TAB+"\":");
			bw.newLine();
			this.createAECgroupTab();
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.AEC_STRUCTURE_TAB+"\":");
			bw.newLine();
			this.createAECstructureTab();
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.AEC_COMPONENT_LIST+"\":");
			bw.newLine();
			
			openTag(HOOK);
		}
		
		/**
		 * Process called to create the AEC group tab in the json file
		 */
		private void createAECgroupTab() throws Exception{
			openTag(HOOK);
			ArrayList<AECgroup> groupList = DataStore.getInstance().getAECgroupList();
			for(int i=0;i<groupList.size();i++){
				openTag(BRACKET);
				
				bw.write(manageTabulations()+"\""+JSONprotocolKey.AEC_GROUP_NAME+"\":\""+groupList.get(i).getAECgroupName()+"\",");
				bw.newLine();
				bw.write(manageTabulations()+"\""+JSONprotocolKey.AEC_GROUP_VALUE_ARXML+"\":\""+groupList.get(i).getAECgroupValue()+"\",");
				bw.newLine();
				
				closeTag(BRACKET, i<(groupList.size()-1));
			}
			closeTag(HOOK, true);
		}
		
		/**
		 * Process called to create the AEC structure tab in the json file
		 */
		private void createAECstructureTab() throws Exception{
			openTag(HOOK);
			ArrayList<AECstructure> structList = DataStore.getInstance().getAECstructureList();
			for(int i=0;i<structList.size();i++){
				openTag(BRACKET);
				
				bw.write(manageTabulations()+"\""+JSONprotocolKey.ATTRIBUTE_TYPE+"\":\""+structList.get(i).getAttributeType()+"\",");
				bw.newLine();
				bw.write(manageTabulations()+"\""+JSONprotocolKey.ATTRIBUTE_NAME+"\":\""+structList.get(i).getAttributeName()+"\",");
				bw.newLine();
				bw.write(manageTabulations()+"\""+JSONprotocolKey.ATTRIBUTE_DESCRIPTION+"\":\""+structList.get(i).getAttributeDescription()+"\",");
				bw.newLine();
				bw.write(manageTabulations()+"\""+JSONprotocolKey.ATTRIBUTE_SIZE+"\":\""+structList.get(i).getAttributeSize()+"\"");
				bw.newLine();
				
				closeTag(BRACKET, i<(structList.size()-1));
			}
			closeTag(HOOK, true);
		}
		
		/**
		 * Process called to write the component info
		 * @param i
		 * @throws Exception
		 */
		private void writeComponentInfo(int i) throws Exception{
			openTag(BRACKET);
	    	
	    	bw.write(manageTabulations()+"\""+JSONprotocolKey.COMPONENT_TITLE+"\":\""+aecList.get(i).getAECname()+"\",");
			bw.newLine();
			bw.write(manageTabulations()+"\""+JSONprotocolKey.PARAMS+"\":");
			bw.newLine();
			
			openTag(HOOK);
		}
		
		/**
		 * Process called to write the component parameters
		 * @param j
		 * @throws Exception
		 */
		private void writeComponentParams(int j) throws Exception{
			openTag(BRACKET);
		
			bw.write(manageTabulations()+"\""+JSONprotocolKey.PARAM_NAME+"\":");
			bw.newLine();
			openTag(BRACKET);
			bw.write(manageTabulations()+"\""+JSONprotocolKey.THIS+"\":\""+aecAttributes.get(j).getAttributeName()+"\",");
			bw.newLine();
			bw.write(manageTabulations()+"\""+JSONprotocolKey.DESCRIPTION+"\":\""+aecAttributes.get(j).getAttributeNameDescription()+"\"");
			bw.newLine();
			closeTag(BRACKET,true);
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.PARAM_DESCRIPTION+"\":");
			bw.newLine();
			openTag(BRACKET);
			bw.write(manageTabulations()+"\""+JSONprotocolKey.THIS+"\":\""+aecAttributes.get(j).getAttributeDescription()+"\",");
			bw.newLine();
			bw.write(manageTabulations()+"\""+JSONprotocolKey.DESCRIPTION+"\":\""+aecAttributes.get(j).getAttributeDescriptionDescription()+"\"");
			bw.newLine();
			closeTag(BRACKET,true);
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.PARAM_VALUE+"\":");
			bw.newLine();
			openTag(BRACKET);
			bw.write(manageTabulations()+"\""+JSONprotocolKey.THIS+"\":\""+aecAttributes.get(j).getAttributeValue()+"\",");
			bw.newLine();
			bw.write(manageTabulations()+"\""+JSONprotocolKey.DESCRIPTION+"\":\""+aecAttributes.get(j).getAttributeValueDescription()+"\"");
			bw.newLine();
			closeTag(BRACKET,true);
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.PARAM_UNIT+"\":");
			bw.newLine();
			openTag(BRACKET);
			bw.write(manageTabulations()+"\""+JSONprotocolKey.THIS+"\":\""+aecAttributes.get(j).getAttributeUnit()+"\",");
			bw.newLine();
			bw.write(manageTabulations()+"\""+JSONprotocolKey.DESCRIPTION+"\":\""+aecAttributes.get(j).getAttributeUnitDescription()+"\"");
			bw.newLine();
			closeTag(BRACKET,true);
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.PARAM_SCALING_FACTOR+"\":");
			bw.newLine();
			openTag(BRACKET);
			bw.write(manageTabulations()+"\""+JSONprotocolKey.THIS+"\":\""+aecAttributes.get(j).getAttributeScalingFactor()+"\",");
			bw.newLine();
			bw.write(manageTabulations()+"\""+JSONprotocolKey.DESCRIPTION+"\":\""+aecAttributes.get(j).getAttributeScalingFactorDescription()+"\"");
			bw.newLine();
			closeTag(BRACKET,true);
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.PARAM_SCALING_OFFSET+"\":");
			bw.newLine();
			openTag(BRACKET);
			bw.write(manageTabulations()+"\""+JSONprotocolKey.THIS+"\":\""+aecAttributes.get(j).getAttributeScalingOffset()+"\",");
			bw.newLine();
			bw.write(manageTabulations()+"\""+JSONprotocolKey.DESCRIPTION+"\":\""+aecAttributes.get(j).getAttributeScalingOffsetDescription()+"\"");
			bw.newLine();
			closeTag(BRACKET,true);
			
			bw.write(manageTabulations()+"\""+JSONprotocolKey.PARAM_INTERPRETED_VALUE+"\":");
			bw.newLine();
			openTag(BRACKET);
			bw.write(manageTabulations()+"\""+JSONprotocolKey.THIS+"\":\""+aecAttributes.get(j).getAttributeInterpretedValue()+"\",");
			bw.newLine();
			bw.write(manageTabulations()+"\""+JSONprotocolKey.DESCRIPTION+"\":\""+aecAttributes.get(j).getAttributeInterpretedValueDescription()+"\"");
			bw.newLine();
			closeTag(BRACKET,false);
		}
		
		/**
		 * Process called to manage the tag of the different component
		 * @param i
		 * @param j
		 * @throws Exception
		 */
		private void writeClosingTag(int i,int j) throws Exception{
			if(j==aecAttributes.size()-1){
				
				closeTag(BRACKET,false);
				
				closeTag(HOOK,false);					
				
				if(i==aecList.size()-1){
					
					closeTag(BRACKET,false);
				}else{
					closeTag(BRACKET,true);
				}
			}else{
				closeTag(BRACKET,true);
			}
		}
	}
}
