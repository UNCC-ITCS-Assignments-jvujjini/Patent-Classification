package com.data.patents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {
	
	static ArrayList<JSONObject> readObjectsFromJSON(String filePath) throws IOException, ParseException {
		
		BufferedReader rd = new BufferedReader(new FileReader(filePath));
		
		ArrayList<JSONObject> patents = new ArrayList<JSONObject>();
        
        for(String line = rd.readLine(); line != null; line = rd.readLine()) {
        	JSONObject object = (JSONObject) new JSONParser().parse(line);
        	patents.add(object);
        }
        
        rd.close();
      
		return patents;
        
    }

}