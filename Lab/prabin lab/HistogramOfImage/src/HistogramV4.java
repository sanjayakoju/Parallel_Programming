import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HistogramV4 {
    private int size;
    private int max;
    private int threadCount;

    public HistogramV4(int size, int threadCount) {
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
        CountDownLatch latch = new CountDownLatch(threadCount);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        for(int t=0;t<threadCount;t++){
            int finalStart = start;
            int finalEnd = end;
            executor.submit(()->{
                int hist [] = new int[max];
                for(int i = finalStart; i< finalEnd; i++){
                    for(int j=0;j<images.length;j++){
                        int intensity = images[i][j]%max;
                        hist[intensity]+=1;
                    }
                }
                for(int i=0;i<max;i++) {
                    histArray[i].getAndAdd(hist[i]);
                }
                latch.countDown();
            });
            start = end;
            end = t==threadCount-2?size:end+chunk;
        }
        latch.await();
        executor.shutdown();
        long v1Time = System.currentTimeMillis()-startTime;
        System.out.println("Max Global Atomic 100 Parallel V4 Time: "+v1Time);
        System.out.println(Arrays.toString(histArray));
    }
}
