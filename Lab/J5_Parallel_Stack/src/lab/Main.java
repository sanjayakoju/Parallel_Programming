package lab;

import java.util.Date;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Date start, end;

        System.out.println("Start");
        start = new Date();

        Thread threadPush = new Thread(new TaskThread("a", "Push"));
        Thread threadPop = new Thread(new TaskThread("Pop"));
        Thread threadClear = new Thread(new TaskThread("Clear"));

        threadPush.start();
        threadPop.start();
        threadClear.start();

        threadPush.join();
        threadPop.join();
        threadClear.join();

        end = new Date();
        System.out.printf("Thread Time Elapsed: %d%n", end.getTime() - start.getTime());
    }
}
