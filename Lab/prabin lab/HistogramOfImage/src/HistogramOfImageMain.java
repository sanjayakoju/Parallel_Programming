
public class HistogramOfImageMain {

    private static final int size=20000;//20,000
    private static final int noOfThread=6;

    public static void main(String[] args) throws InterruptedException {
        new HistogramSerial(size,10).run();
        new HistogramV1(size,noOfThread).run();
        new HistogramV2(size,noOfThread).run();
        new HistogramV3(size,noOfThread).run();
        new HistogramV4(size,noOfThread).run();
    }
}
