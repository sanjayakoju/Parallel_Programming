package util;

import java.util.Random;

public class MatrixUtil {

    public static double [][] generate(int size){
        double [][] arr = new double[size][size];
        for(int i=0;i<size;i++) {
            arr[0][new Random().nextInt(size)] = new Random().nextInt(100);
            arr[size - 1][new Random().nextInt(size)] = new Random().nextInt(100);
            arr[new Random().nextInt(size)][0] = new Random().nextInt(100);
            arr[new Random().nextInt(size)][size - 1] = new Random().nextInt(100);
        }
        return arr;
    }

    public static void copy(double[][]A,double[][]B){
        for(int i =0; i< A.length;i++){
            for(int j=0;j<A[i].length;j++){
                B[i][j] = A[i][j];
            }
        }
    }
}
