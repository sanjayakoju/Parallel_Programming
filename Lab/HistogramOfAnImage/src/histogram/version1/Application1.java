package histogram.version1;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Application1 {
    static int n = 20000;
    static int max = 10;
    static int p = Runtime.getRuntime().availableProcessors();
    static int image[][] = new int[n][n];

    static AtomicInteger hist[] = new AtomicInteger[max];
//    static int hist[] = new int[max+1];

    public static void run() {
        initializationOfImage();

        for (int i=0;i<max;i++) {
            hist[i] = new AtomicInteger();
        }

        double currentTime = 0d;
        Date start, end;
        start = new Date();

        AtomicInteger returnHist [] =ConcurrentVersion1.execute(image,hist,p);

        end = new Date();
        currentTime = end.getTime() - start.getTime();
        System.out.println("Execution Time Version 1: " + (currentTime / 1000) + " seconds.");

        System.out.println(Arrays.toString(hist));
    }

    private static void initializationOfImage() {
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                image[i][j] = (i+j) % max;
            }
        }
    }
}
