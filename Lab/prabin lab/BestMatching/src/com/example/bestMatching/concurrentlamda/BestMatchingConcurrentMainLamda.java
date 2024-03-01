package com.example.bestMatching.concurrentlamda;

import com.example.bestMatching.common.BestMatchingData;
import com.example.bestMatching.data.WordsLoader;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class BestMatchingConcurrentMainLamda {

    public static void main(String[] args) {
        try {
            Date startTime, endTime;
            List<String> dictionary= WordsLoader.load("data/UK Advanced Cryptics Dictionary.txt");

            System.out.println("Dictionary Size: "+dictionary.size());

            startTime=new Date();

            String word="stitter";

            if (args.length == 1) {
                word=args[0];
            }

            BestMatchingData result;
            result = BestMatchingBasicConcurrentCalculationLamda.getBestMatchingWords(word, dictionary);
            List<String> results=result.getWords();
            endTime=new Date();
            System.out.println("Word: "+word);
            System.out.println("Minimun distance: "+result.getDistance());
            System.out.println("List of best matching words: "+results.size());
            for (String wordOut: results) {
                System.out.println(wordOut);
            }
            System.out.println("Execution Time: "+(endTime.getTime()-startTime.getTime()));
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
