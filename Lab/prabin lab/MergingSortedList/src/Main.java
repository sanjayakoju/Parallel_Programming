import util.GenerateArray;

import java.util.Arrays;

public class Main {
    static int[] X;
    static int[] Y;
    static int sizeX=100000;
    static int sizeY=100000;
    public static void main(String[] args){
        X= GenerateArray.generate(sizeX);
        Y= GenerateArray.generate(sizeY);

        Arrays.sort(X);
        Arrays.sort(Y);
        long time = System.currentTimeMillis();
        int[] Z1 = SequentialMerge.merge(X,Y);
        long sequentialTotalTime=System.currentTimeMillis()-time;
        System.out.printf("%30s:%5s\n","Sequential Elapsed Time",sequentialTotalTime);

        time = System.currentTimeMillis();
        int[] Z2 = ParallelMerge.merge(X,Y);
        long parallelTotalTime=System.currentTimeMillis()-time;
        System.out.printf("%30s:%5s\n","Parallel Elapsed Time",parallelTotalTime);
        System.out.printf("%30s:%5s\n","SpeedUp",(double)sequentialTotalTime/parallelTotalTime);

    }


}