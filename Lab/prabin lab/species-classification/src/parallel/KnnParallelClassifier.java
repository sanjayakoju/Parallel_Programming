package parallel;

import util.Distance;
import util.EuclideanDistanceCalculator;
import util.Sample;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class KnnParallelClassifier {

    private final List<? extends Sample> dataSet;
    private final int k;

    private final ThreadPoolExecutor executor;
    private final int numThreads;

    public KnnParallelClassifier(List<? extends Sample> dataSet, int k, int numThreads) {
        this.dataSet=dataSet;
        this.k=k;
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);
        this.numThreads = numThreads;
    }

    public String classify (Sample example) throws InterruptedException {
        Distance[] distances=new Distance[dataSet.size()];
        CountDownLatch endController=new CountDownLatch(numThreads);
        int length = dataSet.size()/numThreads;
        int startIndex=0;
        int endIndex = length;
        for (int i=0;i<numThreads;i++) {
            final int lStartIndex = startIndex;
            final int lEndIndex = endIndex;
            Runnable task= ()->{
                for(int index=lStartIndex;index<lEndIndex;index++) {
                    Sample localExample = dataSet.get(index);
                    Distance distance = new Distance();
                    distance.setIndex(index);
                    distance.setDistance(EuclideanDistanceCalculator.calculate(localExample, example));
                    distances[index] = distance;
                }
                endController.countDown();
            };
            executor.execute(task);
            startIndex=endIndex;
            endIndex= i==numThreads-2?dataSet.size():endIndex+length;
        }
        endController.await();
        Arrays.parallelSort(distances);
        Map<String, Integer> results = new HashMap<>();
        for (int i = 0; i < k; i++) {
            Sample localExample = dataSet.get(distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag, 1, (a, b) -> a+b);
        }

        return Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public void destroy() {
        executor.shutdown();
    }

}
