package com.example.bestMatching.concurrentlamda;

import com.example.bestMatching.common.BestMatchingData;
import com.example.bestMatching.distance.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BestMatchingAdvancedConcurrentCalculationLamda {

	public static BestMatchingData getBestMatchingWords(String word, List<String> dictionary) throws InterruptedException, ExecutionException {

		int numCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(numCores);

		int size = dictionary.size();
		int step = size / numCores;
		int startIndex, endIndex;
		List<Future<BestMatchingData>> results;
		List<Callable<BestMatchingData>> tasks = new ArrayList<>();

		for (int i = 0; i < numCores; i++) {
			startIndex = i * step;
			if (i == numCores - 1) {
				endIndex = dictionary.size();
			} else {
				endIndex = (i + 1) * step;
			}
			int finalStartIndex = startIndex;
			int finalEndIndex = endIndex;
			Callable task = ()->{
				List<String> stringResults=new ArrayList<String>();
				int minDistance=Integer.MAX_VALUE;
				int distance;
				for (int j = finalStartIndex; j< finalEndIndex; j++) {
					distance= LevenshteinDistance.calculate(word,dictionary.get(j));
					if (distance<minDistance) {
						stringResults.clear();
						minDistance=distance;
						stringResults.add(dictionary.get(j));
					} else if (distance==minDistance) {
						stringResults.add(dictionary.get(j));
					}
				}

				BestMatchingData result=new BestMatchingData();
				result.setWords(stringResults);
				result.setDistance(minDistance);
				return result;
			};
			tasks.add(task);
		}

		results = executor.invokeAll(tasks);
		executor.shutdown();
		
		List<String> words = new ArrayList<String>();
		int minDistance = Integer.MAX_VALUE;
		for (Future<BestMatchingData> future : results) {
			BestMatchingData data = future.get();
			if (data.getDistance() < minDistance) {
				words.clear();
				minDistance = data.getDistance();
				words.addAll(data.getWords());
			} else if (data.getDistance() == minDistance) {
				words.addAll(data.getWords());
			}
		}

		BestMatchingData result = new BestMatchingData();
		result.setDistance(minDistance);
		result.setWords(words);
		return result;

	}

}
