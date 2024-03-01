package histogram.version1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static histogram.version1.Application1.n;

public class ConcurrentVersion1 {

    public static AtomicInteger[] execute(int[][] image, AtomicInteger[] hist, int p) {
        ExecutorService executorService = Executors.newFixedThreadPool(p);
        int step = n / p;
        int startIndex, endIndex;
        List<Callable<Boolean>> tasks = new ArrayList<>();

        for (int i = 0; i< p; i++) {
            startIndex = i * step;
            if (i == p - 1) {
                endIndex = n;
            } else {
                endIndex = (i+1) * step;
            }

            int finalStartIndex = startIndex;
            int finalEndIndex = endIndex;
            Callable<Boolean> task = () -> {
                int j;
                for (j= finalStartIndex; j< finalEndIndex; j++) {
                    int k, intensity;
                    for (k=0; k< n; k++) {
                        intensity = image[j][k];
//                        hist[intensity] = hist[intensity]+1;
                        hist[intensity].addAndGet(1);
                    }

                    if (Thread.interrupted()) {
                        return false;
                    }
                }
                return true;
            };

            tasks.add(task);
        }

        try {
           executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        return hist;
    }
}
