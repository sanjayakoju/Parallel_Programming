import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class JacobiRelaxationRunner implements Runnable{
    private Matrix matrix;
    private int start;
    private int end;
    private double tolerance;

    private CyclicBarrier barrier;

    public JacobiRelaxationRunner(Matrix matrix, int start, int end,double tolerance, CyclicBarrier barrier) {
        this.matrix = matrix;
        this.start = start;
        this.end = end;
        this.tolerance=tolerance;
        this.barrier = barrier;
    }

    @Override
    public void run() {
            double [][]A = matrix.getA();
            double [][]B = matrix.getB();
            for (int i = start; i < end; i++) {
                for (int j = 1; j < matrix.getSize() - 1; j++) {
                    B[i][j] = (A[i-1][j] + A[i + 1][j] + A[i][j-1] + A[i][j+1]) / 4;
                }
            }
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
    }
}
