import util.MatrixGenerator;

import java.util.ArrayList;
import java.util.List;

public class ThreadPerElement {
    static final int rowsA=1000;
    static final int columnsA=1000;
    static final int columnsB=1000;
    static final int threadCount = 6;
    static double[][] matrixA,matrixB, matrixC;

    public static void main() throws InterruptedException {
        matrixA = MatrixGenerator.generateMatrix(rowsA,columnsA);
        matrixB = MatrixGenerator.generateMatrix(columnsA,columnsB);
        matrixC = new double[rowsA][columnsB];
        long startTime = System.currentTimeMillis();
        multiply();
        System.out.printf("%50s %5s\n","Thread Per Element Elapsed Time:",(System.currentTimeMillis()-startTime));

    }

    public static void multiply() throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        for(int i=0;i<rowsA;i++){
            for(int j=0;j<columnsB;j++){
                Thread th = new Thread(new MultiplierTask(i,j));
                threadList.add(th);
                th.start();
                if(threadList.size()%threadCount==0){
                    joinThreads(threadList);
                }
            }
        }
    }

    private static void joinThreads(List<Thread> threadList) throws InterruptedException {
        for (Thread thread: threadList) {
            thread.join();
        }
        threadList.clear();
    }

    private static class MultiplierTask implements Runnable{
        int i;
        int j;
        public MultiplierTask(int i,int j ) {
            this.i=i;
            this.j=j;
        }

        @Override
        public void run() {
            for(int k=0;k<columnsA;k++){
                matrixC[i][j]+=matrixA[i][k]*matrixB[k][j];
            }
        }
    }
}
