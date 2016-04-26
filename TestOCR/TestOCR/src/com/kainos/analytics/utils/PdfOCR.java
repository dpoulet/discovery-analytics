package com.kainos.analytics.utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;

public class PdfOCR {

	public static void main(String[] args) {
		File imageFile = new File("test.pdf");
	// Pass in parent of tessdata folder, avoiding issues with TESSDATA_PREFIX	
        ITesseract instance = new Tesseract("/usr/local/share");  // JNA Interface Mapping

        try {
        	System.out.println("File to OCR: " + imageFile);
            String result = instance.doOCR(imageFile);
            System.out.println("Text extracted: " + result);
            
            Pattern p = Pattern.compile("(?<=Policy No: ).*?(?=\\s)"); 
            //Search for matches of the string between the phrase "Policy No: " and the first occurrence of whitespace "\\s"
            Matcher m = p.matcher(result);
            //0 is the first match
            if (m.find()) {                               
                System.out.println("Got Policy Number:" + m.group(0));                       
            } else {
            	System.out.println("No Policy number found.");
            }
            
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }

	}

}
