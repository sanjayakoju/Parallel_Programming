package concurrent;

import distance.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.*;

public class ExistBasicConcurrentCalculation {

    public static boolean existWord(String word, List<String> dictionary) throws InterruptedException, ExecutionException {
        int numCores = Runtime.getRuntime().availableProcessors();
//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numCores);
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
//            ExistBasicTask task = new ExistBasicTask(startIndex, endIndex, dictionary, word);

            int finalEndIndex = endIndex;
            int finalStartIndex = startIndex;
            Callable<Boolean> task = () -> {
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

