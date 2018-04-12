package com.some.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;


public class Parser {
	
	public static void main(String[] args) throws Exception {
	
		 String inputFile="cran/cran.all.1400"; 
         BufferedReader br = new BufferedReader(new FileReader(new File(inputFile)));
         String line=null;
         StringBuilder sb = new StringBuilder();
         int count=1;
        try {
            while((line = br.readLine()) != null){
                if(line.startsWith(".I")){
                    if(sb.length()!=0){
                        File file = new File("Documents/DOC_ID_"+count+".txt");
                        PrintWriter writer = new PrintWriter(file, "UTF-8");
                        writer.println(sb.toString());
                        writer.close();
                        sb.delete(0, sb.length());
                        count++;
                    }
                    continue;
                }
                sb.append(line);
            }

           } catch (Exception ex) {
             ex.printStackTrace();
           }
           finally {
                  br.close();

              }
    }
}


