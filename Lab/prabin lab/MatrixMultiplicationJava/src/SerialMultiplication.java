import util.MatrixGenerator;


public class SerialMultiplication {
    static final int rowsA=1000;
    static final int columnsA=1000;
    static final int columnsB=1000;

    public static void main() {
        double[][] matrixA = MatrixGenerator.generateMatrix(rowsA,columnsA);
        double[][] matrixB = MatrixGenerator.generateMatrix(columnsA,columnsB);
        double[][] matrixC = new double[rowsA][columnsB];
        long startTime = System.currentTimeMillis();
        multiply(matrixA,matrixB,matrixC);
        System.out.printf("%50s %5s\n","Serial Elapsed Time:",(System.currentTimeMillis()-startTime));
    }

    public static void multiply(double[][] A,double[][] B,double[][] C){
        for(int i=0;i<rowsA;i++){
            for(int j=0;j<columnsB;j++){
                for(int k=0;k<columnsA;k++){
                    C[i][j]+=A[i][k]*B[k][j];
                }
            }
        }
    }
}