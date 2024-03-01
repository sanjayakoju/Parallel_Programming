import util.MatrixUtil;

public class Main {
    private static int n = 10000;
    private static double tolerance = 0.1;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("n: "+n);
        System.out.println("Tolerance: "+tolerance);
        Matrix serialMatrix = new Matrix(n);
        Matrix parallelMatrix = serialMatrix.cloneMatrix();

        long serialStart = System.currentTimeMillis();
        SerialJacobiRelaxation.run(serialMatrix,tolerance);
        long serialEnd = System.currentTimeMillis();
        System.out.println("Serial Elapsed Time:"+ (serialEnd-serialStart));
        long parallelStart = System.currentTimeMillis();
        ParallelJacobiRelaxation.run(parallelMatrix,tolerance);
        long parallelEnd = System.currentTimeMillis();
        System.out.println("Parallel Elapsed Time:"+ (parallelEnd-parallelStart));

    }
}
