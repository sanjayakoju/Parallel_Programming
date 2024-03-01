import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HistogramV2 {
    private int size;
    private int max;
    private int threadCount;

    public HistogramV2(int size, int threadCount) {
        this.size = size;
        this.max = 100;
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
        for(int t=0;t<threadCount;t++){
            int finalStart = start;
            int finalEnd = end;
            Thread thread = new Thread(()->{
                for(int i = finalStart; i< finalEnd; i++){
                    for(int j=0;j<images.length;j++){
                        int intensity = images[i][j]%max;
                        histArray[intensity].incrementAndGet();
                    }
                }
            });
            thread.start();
            threadList.add(thread);
            start = end;
            end = t==threadCount-2?size:end+chunk;
            if(t==threadCount-1){
                for (Thread th:threadList) {
                    th.join();
                }
                threadList.clear();
            }
        }
        long v2Time = System.currentTimeMillis()-startTime;
        System.out.println("Max 100 Atomic Parallel V2 Time: "+v2Time);
        System.out.println(Arrays.toString(histArray));
    }
}
