package lab.parallel;

import java.util.*;
import java.util.concurrent.CyclicBarrier;

public class JacobiRelaxationParallel {

//    private static float tolerance;
//
//    private static long sequentialTime;
//    private static int n;
//    private static float A[][], B[][], C[][];
    private static CyclicBarrier barrierDone;
    private static CyclicBarrier barrier;
    private static Map<String, Boolean> map;
    public static boolean done;
//
//    public JacobiRelaxationParallel(float a[][], float b[][], float c[][], int n, float tolerance, boolean done, long sequentialTime) {
//        this.A = a;
//        this.B = b;
//        this.C = c;
//        this.n = n;
//        this.tolerance = tolerance;
//        this.done = done;
//        this.sequentialTime = sequentialTime;
//    }

    public static void parallelJacobiRelaxation(float A[][], float B[][], float C[][], int n, float tolerance, boolean dones, long sequentialTime) {
        done = dones;
        Date start = new Date();
        int threadCount = Runtime.getRuntime().availableProcessors();
        barrierDone = new CyclicBarrier(threadCount, new Runnable() {
            @Override
            public void run() {
                boolean isDone = true;
                for (Boolean b : map.values()) {
                    isDone = isDone && b;
                }
                done = isDone;
            }
        });
        barrier = new CyclicBarrier(threadCount);
        map = new HashMap<>();
        int slice = n / threadCount;
        int reminder = n % threadCount;

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            int j = 0;
            if (i == threadCount - 1) {
                j = reminder - 1;
            }
            ScanArray scanArray = new ScanArray(C, B, i * slice + 1, (i + 1) * slice + j, tolerance, barrier,
                    barrierDone, map);
            Thread thread = new Thread(scanArray);
            thread.start();
            list.add(thread);
        }
        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Date end = new Date();
        long t = end.getTime() - start.getTime();
        System.out.printf("Parallel Jacobi Relaxation Time Elapsed %d ms (SpeedUp: %.2f).\n", t,
                (float) sequentialTime / t);
    }
}
