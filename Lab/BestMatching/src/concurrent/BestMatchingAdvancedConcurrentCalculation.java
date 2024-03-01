package concurrent;

import common.BestMatchingData;
import distance.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BestMatchingAdvancedConcurrentCalculation {

    public static BestMatchingData getBestMatchingWords(String word, List<String> dictionary) throws InterruptedException, ExecutionException {

        int numCores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor =  Executors.newFixedThreadPool(numCores);

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
//            BestMatchingBasicTask task = new BestMatchingBasicTask(startIndex, endIndex, dictionary, word);

            int finalStartIndex = startIndex;
            int finalEndIndex = endIndex;
            Callable<BestMatchingData> task = () -> {
                List<String> resultss=new ArrayList<String>();
                int minDistance=Integer.MAX_VALUE;
                int distance;
                for (int j = finalStartIndex; j< finalEndIndex; j++) {
                    distance= LevenshteinDistance.calculate(word,dictionary.get(j));
                    if (distance<minDistance) {
                        resultss.clear();
                        minDistance=distance;
                        resultss.add(dictionary.get(j));
                    } else if (distance==minDistance) {
                        resultss.add(dictionary.get(j));
                    }
                }

                BestMatchingData result=new BestMatchingData();
                result.setWords(resultss);
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

