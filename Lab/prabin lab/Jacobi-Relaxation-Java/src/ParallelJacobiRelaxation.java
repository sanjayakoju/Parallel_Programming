import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class ParallelJacobiRelaxation {

    private static int threadCount = 12;

    public static void run(Matrix matrix,double tolerance) throws InterruptedException {
        int chunkSize = (matrix.getSize()-2)/threadCount;
        do{
        matrix.setDone(true);
        int start = 1;
        int end = chunkSize+1;
        List<Thread> threads = new ArrayList<>();
            CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount,new BarrierAction(matrix,tolerance));
            for(int i=0;i<threadCount;i++){
            Thread th = new Thread(new JacobiRelaxationRunner(matrix,start,end,tolerance,cyclicBarrier));
            threads.add(th);
            th.start();
            start=end;
            end = i==threadCount-1?matrix.getSize()-2:end+chunkSize;
        }
        for (Thread thread : threads){
            thread.join();
        }
            threads.clear();

        }while (!matrix.isDone());
    }
}
