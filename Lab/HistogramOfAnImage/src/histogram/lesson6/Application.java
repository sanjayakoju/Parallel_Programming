package histogram.lesson6;

import java.util.Date;

public class Application {
    static int n = 20000;
    static int max = 10;
    static int image[][] = new int[n][n];
    static int hist[] = new int[max+1];

    public static void run(String[] args) {

        initializationOfImage();

        double currentTime = 0d;
        Date start, end;
        start = new Date();

        // create one thread for top half of image
        Runnable top = new ScanArray(image,hist,0,n/2);
        Thread ttop = new Thread(top);

        // create one thread for bottom half of image
        Runnable botton = new ScanArray(image,hist,n/2,n);
        Thread tbotton = new Thread(botton);

        ScanArray.initLocks(); //initialize lock array
        ttop.start();
        tbotton.start();
        try {
            ttop.join();
            tbotton.join();
        } catch (InterruptedException ex) {

        }

        end = new Date();

        currentTime = end.getTime() - start.getTime();
        System.out.println("Execution Time: " + (currentTime / 1000) + " seconds.");
    }

    private static void initializationOfImage() {
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                image[i][j] = (i+j) % max;
            }
        }
    }
}
