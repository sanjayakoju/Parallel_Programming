import util.BinarySearch;

public class SequentialMerge {

    public static int[] merge(int[]X,int[]Y){
        int[] Z = new int[X.length+Y.length];
        for(int i=0;i< X.length;i++){
            int j= BinarySearch.search(Y,X[i]);
            if(Z[i+j]==X[i])
                Z[i+j+1]=X[i];
            else
                Z[i+j]=X[i];
        }
        for(int i=0;i< Y.length;i++){
            int j= BinarySearch.search(X,Y[i]);
            if(Z[i+j]==Y[i])
                Z[i+j+1]=Y[i];
            else
                Z[i+j]=Y[i];
        }
        return Z;
    }
}
