package com.data.extract;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.parser.ParseException;

import com.data.patents.Patent;
import com.data.patents.Patents;

public class ExtractData {
	
	public static void writeToFile() throws IOException, ParseException {
		
		StringBuilder sb = null;
		ArrayList<Patent> patents = Patents.get();
		int counter = 10;
		
		while(counter != 0) {
			Collections.shuffle(patents);
			counter--;
		}
		
		FileWriter f = new FileWriter("data-for-mallet.txt");
		
		System.out.println("Starting to write to file...");
		
		for(Patent p : patents) {
			
			ArrayList<String> desc = p.getDescription();
			sb = new StringBuilder();
			
			for(String s : desc) {
				sb.append(s);
				sb.append(" ");
			}
			
			//f.write("["+p.getPatentID()+"]"+ " " +"["+ p.getTarget() +"]"+ " " +"["+ sb +"]");
			f.write(p.getPatentID() + " " + p.getTarget() + " " + sb);
			f.write("\n");
			
		}
		
		f.close();
		
		System.out.println("Done!");
		
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		writeToFile();
	}

}