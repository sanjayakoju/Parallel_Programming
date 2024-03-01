

public class ParallelMerge {

    public static int[] merge(int[]X,int[]Y){
        int[] Z = new int[X.length+Y.length];
        Thread td1 = new Thread(new ParallelMergeAction(X,Y,Z));
        Thread td2 = new Thread(new ParallelMergeAction(Y,X,Z));
        td1.start();
        td2.start();
        try {
            td1.join();
            td2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Z;
    }


}
