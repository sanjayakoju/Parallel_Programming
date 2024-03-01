package util;

public class MatrixUtil {

    public static double [][] generate(int size){
        double [][] arr = new double[size][size];
        for(int i=0;i<size;i++) {
            arr[0][i] = 10;
            arr[i][0] = 10;
            arr[i][size-1] = 10;
            arr[size-1][i] = 10;
        }
        return arr;
    }
    public static void print(double[][] A) {
        System.out.println("=============================================================");
        for(int i =0; i<A.length;i++){
            for(int j=0;j<A.length;j++){
                System.out.printf("%5s ",A[i][j]);
            }
            System.out.println();
        }
        System.out.println("=============================================================");
    }
}
