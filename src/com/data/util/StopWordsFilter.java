package com.data.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public class StopWordsFilter {
	
	public static ArrayList<String> stopFilter(String input) throws IOException {
		
		TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_46, new StringReader(input));
		tokenStream = new StopFilter(Version.LUCENE_46, tokenStream, EnglishAnalyzer.getDefaultStopSet());
		ArrayList<String> tokens = new ArrayList<String>();
		
	    CharTermAttribute token = tokenStream.addAttribute(CharTermAttribute.class);
	    
	    tokenStream.reset();
	    
	    try {
	        while (tokenStream.incrementToken()) {
	            tokens.add(token.toString());
	        }
	    } catch (IOException e) {
	        // log
	    }
	    
	    return tokens;
		
	}

}