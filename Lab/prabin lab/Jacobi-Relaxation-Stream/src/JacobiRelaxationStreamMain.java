import util.MatrixUtil;

public class JacobiRelaxationStreamMain {
    private static final int n = 10000;
    private static final double tolerance = .1;

    public static void main(String[] args) {
        double [][] matrix = MatrixUtil.generate(n);
        long serialStart = System.currentTimeMillis();
        double[][] matrix0 =SequentialStreamRunner.run(matrix,tolerance);
        long time = System.currentTimeMillis()-serialStart;
        System.out.println("Sequential Execution Time: "+time +" millisecond");
//        MatrixUtil.print(matrix0);


        long parallelStart = System.currentTimeMillis();
        double[][] matrix1 = ParallelStreamRunner.run(matrix,tolerance);
        long parallelTime = System.currentTimeMillis()-parallelStart;
        System.out.println("Parallel Execution Time: "+parallelTime +" millisecond");
        System.out.println("Speed Up: "+(double)time/parallelTime);
//        MatrixUtil.print(matrix1);
    }
}
