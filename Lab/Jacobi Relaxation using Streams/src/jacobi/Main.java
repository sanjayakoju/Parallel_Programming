package jacobi;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.OptionalInt;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {

    private static float tolerance = 0.1f;
    private static int n = 10;
    private static float A[][];

    public static long sequentialTime;
    public static long parallelTime;

    static boolean done = false;

    public static void main(String[] args) {

        OptionalInt a = IntStream.range(1,100).reduce((x, y)-> x+y);
        System.out.println(a.getAsInt());

        int a1 = IntStream.range(1,100).reduce(0,(x, y)-> x+y);
        System.out.println(a1);

        LongAdder longAdder = new LongAdder();
        int a2 = IntStream.range(1,100).map(x-> {longAdder.increment(); return x;}).sum();
        System.out.println(a2);
        System.out.println(longAdder.sum());

//        LongAdder longAdder = new LongAdder();
//        int a2 = IntStream.range(1,100).peek(x-> longAdder.add(x)).sum();
//        System.out.println(a2);
//        System.out.println(longAdder.longValue());

        A = new float[n][n];

        System.out.println("Start");

        sequential();

        parallelStream();

        System.out.println("End\n");
    }

    private static void loadData() {
        for (int i = 1; i < n-1; i++)
            for (int j = 1; j < n-1; j++)
                A[i][j] = 0;
        for (int i = 0; i < n; i++) {
            A[i][0] = 10; A[i][n-1] = 10;
            A[0][i] = 10; A[n-1][i] = 10;
        }

    }

    private static void sequential() {
        loadData();
        Date start = new Date();
        do {

            float rtn1[][] = IntStream.range(0,n)
                    .mapToObj(i -> {
                        float arr[] = new float[n];
                        for (int j = 0; j < n; j++) {
                            if (i == 0 || i == n-1 || j==0|| j==n-1) {
                                arr[j] = A[i][j];
                            } else {
                                arr[j] = (A[i - 1][j] + A[i + 1][j] + A[i][j - 1] + A[i][j + 1]) / 4.0f;
                            }
                        }
                        return arr;
                    }).toArray(float[][]::new);

            done = true;
            A = IntStream.range(0,n)
                    .mapToObj(i -> {
                        float arr[] = new float[n];
                        for (int j = 0; j < n ; j++) {
                            if (Math.abs(A[i][j] - rtn1[i][j]) > tolerance)
                                done = false;
                            arr[j] = rtn1[i][j];
                        }
                        return arr;
                    }).toArray(float[][]::new);


        } while (!done);
        Date end = new Date();
        sequentialTime = end.getTime() - start.getTime();
        System.out.printf("Sequential Jacobi Relaxation Elapsed Time : %d ms.\n", sequentialTime);
        printMethod();
    }

    private static void printMethod() {
        for(int i=0;i<A.length;i++) {
            for (int j=0;j<A.length;j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void parallelStream() {
        loadData();
        Date start = new Date();
        do {

            float rtn1[][] = IntStream.range(0,n)
                    .parallel()
                    .mapToObj(i -> {
                        float arr[] = new float[n];
                        for (int j = 0; j < n; j++) {
                            if (i == 0 || i == n-1 || j==0|| j==n-1) {
                                arr[j] = A[i][j];
                            } else {
                                arr[j] = (A[i - 1][j] + A[i + 1][j] + A[i][j - 1] + A[i][j + 1]) / 4.0f;
                            }
                        }
                        return arr;
                    }).toArray(float[][]::new);

            done = true;
            A = IntStream.range(0,n)
                    .parallel()
                    .mapToObj(i -> {
                        float arr[] = new float[n];
                        for (int j = 0; j < n; j++) {
                            if (Math.abs(A[i][j] - rtn1[i][j]) > tolerance)
                                done = false;
                            arr[j] = rtn1[i][j];
                        }
                        return arr;
                    }).toArray(float[][]::new);


        } while (!done);
        Date end = new Date();
        parallelTime = end.getTime() - start.getTime();
        System.out.printf("Parallel Stream Jacobi Relaxation Elapsed Time : %d ms.\n", parallelTime);
        printMethod();

        Path path = Paths.get("output.PNG");

    }
}
