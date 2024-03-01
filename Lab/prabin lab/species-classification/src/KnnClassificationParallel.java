import data.BankMarketing;
import parallel.KnnParallelClassifier;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class KnnClassificationParallel {
    private final List<BankMarketing> test;
    private final List<BankMarketing> train;
    private int k;

    private int numThreads;

    public KnnClassificationParallel(int k, List<BankMarketing> train, List<BankMarketing> test,int numThreads) {
        this.k = k;
        this.test=test;
        this.train=train;
        this.numThreads = numThreads;
    }

    public void run() throws InterruptedException {
        KnnParallelClassifier classifier = new KnnParallelClassifier(train, k, numThreads);
        for (BankMarketing example : test) {
            classifier.classify(example);
        }
        classifier.destroy();
    }
}
