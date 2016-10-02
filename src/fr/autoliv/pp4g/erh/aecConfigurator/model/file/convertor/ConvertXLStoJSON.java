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
	Process called to convert the XLS document into JSON file for 
	the AEC_Configurator_Data.json
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.file.convertor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import fr.autoliv.pp4g.erh.aecConfigurator.model.file.generator.json.JSONprotocolKey;

public class ConvertXLStoJSON {
	
	private File srcFile;    		
	private File destFile;    		
	private FileOutputStream fileOutStream;
	private BufferedWriter bw;	
	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private int nbRows;
	
	private HSSFRow row;
    private HSSFCell cell;

	/**
	 * Constructor to convert an excel file into json file
	 */
    public ConvertXLStoJSON() {
    	
    	try {
    		this.srcFile =new File("SBE_4G_NVP_layoutZ.xls");    		
    		this.destFile = new File("HELLO.txt");    		
    		this. fileOutStream = new FileOutputStream(destFile);
			this.bw = new BufferedWriter(new OutputStreamWriter(fileOutStream));
    		
    	    this.fs = new POIFSFileSystem(new FileInputStream(srcFile));
    	    this.wb = new HSSFWorkbook(fs);
    	    this.sheet = wb.getSheetAt(0);

    	    this.nbRows = sheet.getPhysicalNumberOfRows();
    	    
    	    System.out.println("nb rows : "+nbRows);
    	    
    	    this.createJSONfile();
    	    
    	    
    	} catch(Exception ioe) {
    	    ioe.printStackTrace();
    	}
    }
    
    /**
     * Process called to create the JSON file
     * @throws Exception
     */
    private void createJSONfile() throws Exception{    	
    	bw.write("{");
		bw.newLine();
		bw.write("\t\""+JSONprotocolKey.AEC_COMPONENT_LIST+"\":");
		bw.newLine();
		bw.write("\t\t[");
		bw.newLine();
					
	    for(int i = 298; i < nbRows; i++) {
	        row = sheet.getRow(i);
        	if((i-298)%7==0||i==298){
        		
        		this.createComponentInfo();
        		
        	}else{
        		
        		this.createComponentParams();
        		
				
				if((i-298)%7==6){
					bw.write("\t\t\t\t\t\t\t}");
					bw.newLine();
					
					bw.write("\t\t\t\t\t]");
					bw.newLine();						
					
					if(i>660){
						
						bw.write("\t\t\t}");
						bw.newLine();
					}else{
						bw.write("\t\t\t},");
						bw.newLine();
					}
				}else{
					bw.write("\t\t\t\t\t\t\t},");
					bw.newLine();
				}				
        	}
	    }
    	bw.write("\t\t]");
		bw.newLine();
	    bw.write("}");
		bw.newLine();
	    bw.close();
    }
    
    /**
     * Process called to create the component info
     * @throws Exception
     */
    private void createComponentInfo() throws Exception{
    	bw.write("\t\t\t{");
		bw.newLine();
    	cell=row.getCell(9);
    	String componentTitle = new String();
    	componentTitle=cell.toString();
    	componentTitle=componentTitle.substring(17);
    	
    	bw.write("\t\t\t\t\"componentTitle\":\""+componentTitle+"\",");
		bw.newLine();
		bw.write("\t\t\t\t\"componentBlockID\":\""+cell.toString()+"\",");
		bw.newLine();
		bw.write("\t\t\t\t\"params\":");
		bw.newLine();
		bw.write("\t\t\t\t\t[");
		bw.newLine();
    }
    
    /**
     * Process called to create the component params
     * @throws Exception
     */
    private void createComponentParams() throws Exception{
    	bw.write("\t\t\t\t\t\t\t{");
		bw.newLine();			
		bw.write("\t\t\t\t\t\t\t\t\"paramName\":");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t{");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"this\":\""+row.getCell(1).toString()+"\",");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"description\":\"/\"");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t},");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\"paramDescription\":");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t{");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"this\":\""+row.getCell(7).toString()+"\",");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"description\":\"/\"");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t},");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\"paramSize\":");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t{");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"this\":\""+Double.valueOf(row.getCell(2).toString()).intValue()+"\",");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"description\":\"/\"");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t},");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\"paramValue\":");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t{");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"this\":\""+Double.valueOf(row.getCell(4).toString()).intValue()+"\",");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"description\":\"/\"");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t},");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\"paramUnit\":");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t{");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"this\":\""+row.getCell(3).toString()+"\",");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"description\":\"/\"");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t},");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\"paramScalingFactor\":");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t{");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"this\":\""+Double.valueOf(row.getCell(13).toString()).intValue()+"\",");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"description\":\"/\"");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t},");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\"paramScalingOffset\":");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t{");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"this\":\""+Double.valueOf(row.getCell(14).toString()).intValue()+"\",");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"description\":\"/\"");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t},");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\"paramInterpretedValue\":");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t{");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"this\":\""+Double.valueOf(row.getCell(15).getNumericCellValue()).intValue()+"\",");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t\t\"description\":\"/\"");
		bw.newLine();
		bw.write("\t\t\t\t\t\t\t\t}");
		bw.newLine();
    }
}
