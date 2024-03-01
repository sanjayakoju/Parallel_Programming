import util.BinarySearch;

public class ParallelMergeAction implements Runnable{

    int [] arr;
    int [] searchArr;
    int [] result;

    public ParallelMergeAction(int[] arr, int[] searchArr, int[] result) {
        this.arr = arr;
        this.searchArr = searchArr;
        this.result = result;
    }

    @Override
    public void run() {
        for(int i=0;i< arr.length;i++){
            int j= BinarySearch.search(searchArr,arr[i]);
            if(result[i+j]==arr[i])
                result[i+j+1]=arr[i];
            else
                result[i+j]=arr[i];
        }
    }
}
