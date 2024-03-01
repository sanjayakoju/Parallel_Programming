package com.example.bestMatching.concurrentlamda;

import com.example.bestMatching.distance.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.*;

public class ExistBasicConcurrentCalculationLamda {

	public static boolean existWord(String word, List<String> dictionary) throws InterruptedException, ExecutionException {
		int numCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(numCores);

		int size = dictionary.size();
		int step = size / numCores;
		int startIndex, endIndex;
		List<Callable<Boolean>> tasks = new ArrayList<>();

		for (int i = 0; i < numCores; i++) {
			startIndex = i * step;
			if (i == numCores - 1) {
				endIndex = dictionary.size();
			} else {
				endIndex = (i + 1) * step;
			}
			int finalStartIndex = startIndex;
			int finalEndIndex = endIndex;
			Callable task = ()-> {
				for (int j = finalStartIndex; j< finalEndIndex; j++) {
					if (LevenshteinDistance.calculate(word, dictionary.get(j))==0) {
						return true;
					}
					if (Thread.interrupted()) {
						return false;
					}
				}
				throw new Exception("The word "+word+" doesn't exists.");
			};
			tasks.add(task);
		}
		try {
			Boolean result = executor.invokeAny(tasks);
			return result;
		} catch (ExecutionException e) {
			if (e.getCause() instanceof NoSuchElementException)
				return false;
			throw e;
		} finally {
			executor.shutdown();
		}
	}
}
