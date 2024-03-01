import java.util.stream.IntStream;

public class ParallelStreamRunner {
    static volatile boolean done;

    public static double[][] run(double[][] matrix, double tolerance) {
        int size = matrix.length;
        do {
            done = true;
            final double[][] arr = matrix;
            final double[][] arr1 = IntStream.range(0, size).parallel()
                    .mapToObj(i -> {
                        double [] temp = new double[size];
                        for (int j = 0; j < size; j++) {
                            if(i==0||i==size-1||j==0||j==size-1){
                                temp[j]=arr[i][j];
                            }else{
                                temp[j]= (arr[i - 1][j] + arr[i + 1][j] + arr[i][j - 1] + arr[i][j + 1]) / 4;
                            }
                        }
                        return temp;
                    })
                    .toArray(double[][]::new);
            matrix = IntStream.range(0, size).parallel()
                    .mapToObj(i ->{
                                double [] temp = new double[size];
                                for(int j=0;j<size;j++){
                                    double change = Math.abs(arr[i][j] - arr1[i][j]);
                                    if (change > tolerance) done = false;
                                    temp[j]=arr1[i][j];
                                }
                                return temp;
                            }
                    ).toArray(double[][]::new);
        } while (!done);
        return matrix;
    }
}
