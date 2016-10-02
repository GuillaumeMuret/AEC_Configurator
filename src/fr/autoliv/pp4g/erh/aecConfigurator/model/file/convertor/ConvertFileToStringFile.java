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
	Class use to convert a file into a String file
 */

package fr.autoliv.pp4g.erh.aecConfigurator.model.file.convertor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ConvertFileToStringFile {
	
	/**
	 * Constructor to convert a file into an other file
	 */
	public ConvertFileToStringFile(){
		try {
			
			File srcFile =new File("TODO/ECUextract_ERH.arxml");
			File destFile = new File("TODO/String_ECUextract_ERH.txt");
			
			FileOutputStream fileOutStream = new FileOutputStream(destFile);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutStream));
			BufferedReader br = new BufferedReader(new FileReader(srcFile));
			try{
				String line;
				int i = 0;
				while((line=br.readLine()) != null){
					
					line=manageLine(line);
					
					if(i==0){
						bw.write("\""+line+"\\r\\n\"");
						bw.newLine();
					}else{
						bw.write("+\""+line+"\\r\\n\"");
						bw.newLine();
					}
					i++;
				}
				br.close();
				bw.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * Called to manage the different line
	 * @param line
	 * @return
	 */
	public String manageLine(String line){
		String newLine = new String();
		newLine = line;
		int i=0;
		while(i<newLine.length()){
			if(line.codePointAt(i)==34){
				//line.replace("\"", "\\\"");
				newLine = line.substring(0, i)+"\\\""+line.substring(i+1);
				line = newLine;
				i++;
			}
			if(line.codePointAt(i)==92){
				//line.replace("\"", "\\\"");
				newLine = line.substring(0, i)+"\\\\"+line.substring(i+1);
				line = newLine;
				i++;
			}
			i++;
		}
		return newLine;		
	}
}
