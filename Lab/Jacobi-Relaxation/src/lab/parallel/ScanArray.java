package lab.parallel;

import lab.Main;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ScanArray implements Runnable {
    private float A[][];
    private float B[][];
    private int start;
    private int end;
    private float tolerance = 0.1f;
    private CyclicBarrier barrier;
    private CyclicBarrier barrierDone;
    private Map<String, Boolean> map;

    public ScanArray(float[][] A, float[][] B, int start, int end, float tolerance, CyclicBarrier barrier,
                     CyclicBarrier barrierDone, Map<String, Boolean> map) {
        super();
        this.A = A;
        this.B = B;
        this.start = start;
        this.end = end;
        this.tolerance = tolerance;
        this.barrier = barrier;
        this.barrierDone = barrierDone;
        this.map = map;
    }

    @Override
    public void run() {
        boolean done = false;
        do {
            for (int i = start; i < end; i++) {
                for (int j = 1; j < A.length - 1; j++) {
                    B[i][j] = (A[i - 1][j] + A[i + 1][j] + A[i][j - 1] + A[i][j + 1]) / 4.0f;
                }
            }

            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e1) {
                e1.printStackTrace();
            }

            done = true;
            for (int i = start; i < end; i++) {
                for (int j = 1; j < A.length - 1; j++) {
                    if (Math.abs(A[i][j] - B[i][j]) > tolerance)
                        done = false;
                    A[i][j] = B[i][j];
                }
            }
            map.put(Thread.currentThread().getName(), done);
            try {
                barrierDone.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        } while (!Main.done);
    }

}