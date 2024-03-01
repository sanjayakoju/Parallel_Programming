import data.BankMarketing;
import loader.BankMarketingLoader;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BankMarketingLoader loader = new BankMarketingLoader();
        List<BankMarketing> train = loader.load("data\\bank.data");
        System.out.println("Train: " + train.size());
        List<BankMarketing> test = loader.load("data\\bank.test");
        System.out.println("Test: " + test.size());
        double serialCurrentTime = 0d;

        int k = 10;
        if (args.length==1) {
            k = Integer.parseInt(args[0]);
        }
        try {
            Date start, end;
            start = new Date();
            KnnClassificationSerial serial = new KnnClassificationSerial(k,train,test);
            serial.run();
            end = new Date();
            serialCurrentTime = end.getTime() - start.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("******************************************");
        System.out.println("Serial Execution Time: " + (serialCurrentTime / 1000) + " seconds.");
        System.out.println("******************************************");
        int thread = Runtime.getRuntime().availableProcessors();
        double currentTime = 0d;
        for(int i=1;i<=thread;i++) {
            try {
                Date start, end;
                start = new Date();
                KnnClassificationParallel parallel = new KnnClassificationParallel(k, train, test,i);
                parallel.run();
                end = new Date();
                currentTime = end.getTime() - start.getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Parallel with "+i+" Thread Execution Time: " + (currentTime / 1000) + " seconds.");
            System.out.println("Parallel with "+i+" SpeedUp: " + (serialCurrentTime/currentTime) + ".");
            System.out.println("******************************************");
        }
    }
}