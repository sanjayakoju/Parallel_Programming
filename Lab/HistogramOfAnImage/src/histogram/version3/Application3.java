package histogram.version3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Application3 {

    public static void run() throws InterruptedException {
        int n = 20000;
        int max = 100;
        int p = Runtime.getRuntime().availableProcessors();
        int image[][] = new int[n][n];
        int hist[] = new int[max];

        initializationOfImage(n, image, max);

        double currentTime = 0d;
        Date start, end;
        start = new Date();

        int returnData[] = execute(n, image, hist);

        end = new Date();
        currentTime = end.getTime() - start.getTime();
        System.out.println("Execution Time Version 3: " + (currentTime / 1000) + " seconds.");

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

    public static int[] execute(int n, int image[][], int hist[]) throws InterruptedException {
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
                for (j= finalStartIndex; j< finalEndIndex; j++) {
                    int k, intensity;
                    for (k=0; k< n; k++) {
                        intensity = image[j][k];
                        hist[intensity] = hist[intensity]+1;
                    }
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
