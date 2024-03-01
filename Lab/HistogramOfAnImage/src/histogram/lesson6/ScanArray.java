package histogram.lesson6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static histogram.lesson6.Application.max;
import static histogram.lesson6.Application.n;

public class ScanArray implements Runnable {
    int image[][];
    int hist[];
    int start;
    int end;

    public ScanArray(int[][] image, int[] hist, int start, int end) {
        this.image = image;
        this.hist = hist;
        this.start = start;
        this.end = end;
    }

    private static Lock[] L = new ReentrantLock[max+1];

    @Override
    public void run() {
        int i;
        for (i=start;i<end;i++) {
            int j, intensity;
            for (j=0;j<n;j++) {
                intensity = image[i][j];
                L[intensity].lock();
                hist[intensity] = hist[intensity]+1;
                L[intensity].unlock();
            }
        }
    }

    public static void initLocks() {
        int i;
        for (i=0;i<=max;i++) {
            L[i] = new ReentrantLock();
        }
    }
}
