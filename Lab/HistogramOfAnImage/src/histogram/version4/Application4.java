package histogram.version4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Application4 {

    public static void run() throws InterruptedException {
        int n = 20000;
        int max = 100;
        int p = Runtime.getRuntime().availableProcessors();
        int image[][] = new int[n][n];
        AtomicInteger hist[] = new AtomicInteger[max];

        for (int i=0;i<max;i++) {
            hist[i] = new AtomicInteger();
        }

        initializationOfImage(n, image, max);

        double currentTime = 0d;
        Date start, end;
        start = new Date();

        AtomicInteger returnData[] = execute(n, image, hist);

        end = new Date();
        currentTime = end.getTime() - start.getTime();
        System.out.println("Execution Time Version 4: " + (currentTime / 1000) + " seconds.");

        System.out.println(Arrays.toString(returnData));

    }

    private static int[][] initializationOfImage(int n, int image[][], int max) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                image[i][j] = (i + j) % max;
            }
        }

        return image;
    }

    public static AtomicInteger[] execute(int n, int image[][], AtomicInteger hist[]) throws InterruptedException {
        int p = Runtime.getRuntime().availableProcessors();
        int step = n / p;
        int startIndex, endIndex;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i< p; i++) {
            startIndex = i * step;
            if (i == p - 1) {
                endIndex = n;
            } else {
                endIndex = (i+1) * step;
            }

            int finalStartIndex = startIndex;
            int finalEndIndex = endIndex;
            Runnable task = () -> {
                int j;
                int histArray[] = new int[hist.length];
                for (j= finalStartIndex; j< finalEndIndex; j++) {
                    int k, intensity;
                    for (k=0; k< n; k++) {
                        intensity = image[j][k];
                        histArray[intensity] = histArray[intensity]+1;
                    }
                }
                for(int k =0;k< hist.length;k++) {
                    hist[k].addAndGet(histArray[k]);
                }
            };
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
        }
        for (Thread thread: threads) {
            thread.join();
        }
        return hist;
    }
}
