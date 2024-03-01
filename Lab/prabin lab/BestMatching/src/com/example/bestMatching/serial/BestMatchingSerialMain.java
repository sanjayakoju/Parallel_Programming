package com.example.bestMatching.serial;

import java.util.Date;
import java.util.List;

import com.example.bestMatching.common.BestMatchingData;
import com.example.bestMatching.data.WordsLoader;


public class BestMatchingSerialMain {

	public static void main(String[] args) {

		Date startTime, endTime;
		List<String> dictionary=WordsLoader.load("data/UK Advanced Cryptics Dictionary.txt");
		
		System.out.println("Dictionary Size: "+dictionary.size());
		
		startTime=new Date();
		
		String word="stitter";
		
		if (args.length == 1) {
			word=args[0];
		}
		BestMatchingData result=BestMatchingSerialCalculation.getBestMatchingWords(word, dictionary);
		List<String> results=result.getWords();
		endTime=new Date();
		System.out.println("Word: "+word);
		System.out.println("Minimun distance: "+result.getDistance());
		System.out.println("List of best matching words: "+results.size());
		results.forEach(System.out::println);
		System.out.println("Execution Time: "+(endTime.getTime()-startTime.getTime()));
	}

}
