package util;

import java.util.Random;

public class MatrixGenerator {

    public static double[][] generateMatrix(int rows, int columns){
        double[][] matrix = new double[rows][columns];
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                matrix[i][j]=new Random().nextInt(100);
            }
        }
        return matrix;
    }
}
