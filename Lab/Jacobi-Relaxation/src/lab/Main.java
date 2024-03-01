package lab;


import lab.parallel.JacobiRelaxationParallel;
import lab.parallel.ScanArray;
import lab.sequential.JacobiRelaxationSequential;

import java.util.*;
import java.util.concurrent.CyclicBarrier;

public class Main {
    private static float tolerance = 0.1f;
    private static int n = 100;
    private static float A[][], B[][], C[][];
    public static long sequentialTime;
    private static CyclicBarrier barrierDone;
    private static CyclicBarrier barrier;
    private static Map<String, Boolean> map;
    public static boolean done = false;

    public static void main(String[] args) {
        A = new float[n][n];
        B = new float[n][n];
        C = new float[n][n];

        for (int i = 0; i < n; i++) {
            A[0][i] = C[0][i] = 5;
            A[n - 1][i] = C[n - 1][i] = 8;
            A[i][0] = C[i][0] = 12;
            A[i][n - 1] = C[i][n - 1] = 3;
        }

        System.out.println("Start\n");


        JacobiRelaxationSequential jacobiRelaxationSequential = new JacobiRelaxationSequential(A,B,n,tolerance);
        sequentialTime = jacobiRelaxationSequential.sequentialJacobiRelaxation();

//        JacobiRelaxationParallel.parallelJacobiRelaxation(A,B,C,n,tolerance,done,sequentialTime);

        parallelJacobiRelaxation();

        System.out.println("End\n");

    }


    private static void parallelJacobiRelaxation() {
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
        int remender = n % threadCount;

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            int j = 0;
            if (i == threadCount - 1) {
                j = remender - 1;
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
        System.out.printf("Parallel Jacobi Relaxation Time Elapsed %d ms (SpeedUp: %.2f).\n\n", t,
                (float) sequentialTime / t);
    }

}
