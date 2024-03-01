import util.MatrixGenerator;

import java.util.ArrayList;
import java.util.List;

public class ThreadPerCore {
    static final int rowsA=1000;
    static final int columnsA=1000;
    static final int columnsB=1000;
    static int threadCount = Runtime.getRuntime().availableProcessors();
    static double[][] matrixA,matrixB, matrixC;

    public static void main() throws InterruptedException {
        matrixA = MatrixGenerator.generateMatrix(rowsA,columnsA);
        matrixB = MatrixGenerator.generateMatrix(columnsA,columnsB);
        matrixC = new double[rowsA][columnsB];
        long startTime = System.currentTimeMillis();
        multiply();
        System.out.printf("%50s %5s\n","Thread Per Core Elapsed Time:",(System.currentTimeMillis()-startTime));
    }

    public static void multiply() throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        int step = rowsA/threadCount;
        if(step == 0){
            threadCount=rowsA;
            step=1;
        }
        int startIndex=0;
        int endIndex=step;
        for(int i=0;i<threadCount;i++) {
            Thread th = new Thread(new MultiplierTask(startIndex,endIndex));
            threadList.add(th);
            th.start();
            if (threadList.size() % threadCount == 0) {
                joinThreads(threadList);
            }
            startIndex=endIndex+1;
            endIndex=i==threadCount-2?rowsA:endIndex+step;
        }

    }

    private static void joinThreads(List<Thread> threadList) throws InterruptedException {
        for (Thread thread: threadList) {
            thread.join();
        }
        threadList.clear();
    }

    private static class MultiplierTask implements Runnable{

        int startIndex;
        int endIndex;

        public MultiplierTask(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            for(int i=startIndex;i<endIndex;i++) {
                for (int j = 0; j < columnsB; j++) {
                    for (int k = 0; k < columnsA; k++) {
                        matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
                    }
                }
            }
        }
    }
}
