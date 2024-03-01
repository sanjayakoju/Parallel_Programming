import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HistogramV1 {
    private int size;
    private int max;
    private int threadCount;

    public HistogramV1(int size, int threadCount) {
        this.size = size;
        this.max = 10;
        this.threadCount = threadCount;
    }

    public void run() throws InterruptedException {
        int[][] images = ImageDataLoader.getImageData(size,max);

        AtomicInteger[] histArray = new AtomicInteger[max];
        for (int i=0;i<max;i++){
            histArray[i] = new AtomicInteger();
        }

        int chunk = size/threadCount;
        int start = 0;
        int end = chunk;
        long startTime = System.currentTimeMillis();
        List<Thread> threadList = new ArrayList<>();
        for(int th=0;th<threadCount;th++){
            int finalStart = start;
            int finalEnd = end;
            Thread thread = new Thread(()->{
                for(int i = finalStart; i< finalEnd; i++){
                for(int j=0;j<images.length;j++){
                    int intensity = images[i][j]%max;
                    histArray[intensity].incrementAndGet();
                }
            }});
            thread.start();
            threadList.add(thread);
            start = end;
            end = th==threadCount-2?size:end+chunk;
            if(th==threadCount-1){
                for (Thread thr:threadList) {
                    thr.join();
                }
                threadList.clear();
            }
        }
        long v1Time = System.currentTimeMillis()-startTime;
        System.out.println("Max 10 Parallel V1 Time: "+v1Time);
        System.out.println(Arrays.toString(histArray));
    }
}
