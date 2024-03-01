package lab.sequential;

import java.util.Date;

public class JacobiRelaxationSequential {

    static float tolerance;
    static int n;
    static float[][] A;
    static float[][] B;
    float C[][];
    public static long sequentialTime;

    public JacobiRelaxationSequential(float a[][], float b[][], int n, float tolerance) {
        this.A = a;
        this.B = b;
        this.n = n;
        this.tolerance = tolerance;
    }

    public static long sequentialJacobiRelaxation() {
        Date start = new Date();
        boolean done = false;
        do {
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < n - 1; j++) {
                    B[i][j] = (A[i - 1][j] + A[i + 1][j] + A[i][j - 1] + A[i][j + 1]) / 4.0f;
                }
            }
            done = true;
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < n - 1; j++) {
                    if (Math.abs(A[i][j] - B[i][j]) > tolerance)
                        done = false;
                    A[i][j] = B[i][j];
                }
            }
        } while (!done);
        Date end = new Date();
        sequentialTime = end.getTime() - start.getTime();
        System.out.printf("Sequential Jacobi Relaxation Elapsed Time : %d ms.\n", sequentialTime);
        return sequentialTime;

    }
}
