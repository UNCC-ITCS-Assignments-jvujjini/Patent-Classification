package com.data.patents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.tartarus.snowball.ext.PorterStemmer;

import com.data.util.StopWordsFilter;

public class Patents {
	
	public static ArrayList<Patent> get() throws IOException, ParseException {
		
		ArrayList<JSONObject> patents = JSONReader.readObjectsFromJSON("455Telecom.json");
		Patent p;
		ArrayList<Patent> processedPatents = new ArrayList<Patent>(); 
		TreeMap<String,Integer> uniqueClasses = new TreeMap<String,Integer>();
		TreeSet<String> processedClasses = new TreeSet<String>();
		//PrintWriter out = null;
		//int counter = 0;
		
		for(JSONObject ptnt : patents) {
			int count = 1;
			if(uniqueClasses.containsKey(ptnt.get("Sub-Class"))) {
				count = uniqueClasses.get(ptnt.get("Sub-Class"));
				count++;
				uniqueClasses.put((String) ptnt.get("Sub-Class"), count);
			} else {
				uniqueClasses.put((String) ptnt.get("Sub-Class"), count);
			}
		}
		
		for(Entry<String, Integer> entry : uniqueClasses.entrySet()) {
			if(entry.getValue() > 300) {
				processedClasses.add(entry.getKey());
			}
		}
		
		for(JSONObject patent : patents) {
			
			if(processedClasses.contains((String) patent.get("Sub-Class"))) {
				String description = (String) patent.get("AbstractText");
				p = new Patent(patent.get("PID"), patent.get("Title"), processAbstract(description), patent.get("Sub-Class"));
				processedPatents.add(p);
				//counter++;
			}
			
		}
		
		return processedPatents;
		
		/*out = new PrintWriter(new FileWriter("/home/jvujjini/Desktop/newPatentClassCount"), true);
		System.out.println("Writing output to file...");
		
		for(Entry<String, Integer> entry : processedClasses.entrySet()) {
			//System.out.println(entry.toString());
			out.write(entry.toString());
			out.write("\n");
		}
		
		System.out.println("Done!");
		out.close();*/
		
	}
	
	public static ArrayList<String> processAbstract(String patentDescription) throws IOException {
		
		String strippedPatent = patentDescription.replaceAll("[^a-zA-Z0-9]+", " ");
		ArrayList<String> words = StopWordsFilter.stopFilter(strippedPatent);
		ArrayList<String> stemmedWords = new ArrayList<String>();
		PorterStemmer stemmer = new PorterStemmer();
		
		for(String word : words) {
			stemmer.setCurrent(word);
			stemmer.stem();
			stemmedWords.add(stemmer.getCurrent());
		}
		
		return stemmedWords;
		
	}

}