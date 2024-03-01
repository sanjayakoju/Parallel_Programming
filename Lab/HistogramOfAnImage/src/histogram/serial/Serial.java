package histogram.serial;

import java.util.Arrays;
import java.util.Date;

public class Serial {


    public static void run() {
        int n = 20000;
        int max = 10;
        int image[][] = new int[n][n];
        int hist[] = new int[max];

        initializationOfImage(n,image,max);

        double currentTime = 0d;
        Date start, end;
        start = new Date();

        int returnData[] =execute(n,image,hist);

        end = new Date();
        currentTime = end.getTime() - start.getTime();
        System.out.println("Execution Time Serial: " + (currentTime / 1000) + " seconds.");

        System.out.println(Arrays.toString(returnData));
    }

    private static int[][] initializationOfImage(int n, int image[][], int max) {
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                image[i][j] = (i+j) % max;
            }
        }

        return image;
    }

    public static int[] execute(int n, int image[][], int hist[]) {
        for (int i=0;i<n;i++) {
            int j, intensity;
            for (j=0;j<n;j++) {
                intensity = image[i][j];
                hist[intensity] = hist[intensity]+1;
            }
        }
        return hist;
    }
}
