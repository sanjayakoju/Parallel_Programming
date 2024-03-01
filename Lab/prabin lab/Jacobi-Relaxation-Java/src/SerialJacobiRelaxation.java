public class SerialJacobiRelaxation {


    public static void run(Matrix matrix,double tolerance) {
        int size = matrix.getSize();
        double [][]A = matrix.getA();
        double [][]B = matrix.getB();
        do {
            matrix.setDone(true);
            for (int i = 1; i < size - 1; i++) {
                for (int j = 1; j < size - 1; j++) {
                    B[i][j] = (A[i-1][j] + A[i + 1][j] + A[i][j-1] + A[i][j+1]) / 4;
                }
            }
            for (int i = 1; i < size-1; i++) {
                for (int j = 1; j < size-1; j++) {
                    double change = Math.abs(B[i][j]-A[i][j]);
                    if(change>tolerance) matrix.setDone(false);
                    A[i][j] = B[i][j];
                }
            }
        }while (!matrix.isDone());
    }
}
