public class BarrierAction implements Runnable{
    Matrix matrix;
    double tolerance;

    public BarrierAction(Matrix matrix, double tolerance) {
        this.matrix = matrix;
        this.tolerance = tolerance;
    }

    @Override
    public void run() {
        double[][] A = matrix.getA();;
        double[][] B = matrix.getB();;
        boolean done = true;
        for (int i = 1; i < matrix.getSize(); i++) {
            for (int j = 1; j < matrix.getSize() - 1; j++) {
                double change = Math.abs(A[i][j] -B[i][j]);
                if (change > tolerance) done = false;
                A[i][j]=B[i][j];
            }
        }
        matrix.and(done);
    }
}
