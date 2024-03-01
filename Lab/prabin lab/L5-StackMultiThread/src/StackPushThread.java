import java.util.Random;

public class StackPushThread implements Runnable{
    StackMultiThread stackMultiThread;

    public StackPushThread(StackMultiThread stackMultiThread) {
        this.stackMultiThread = stackMultiThread;
    }

    @Override
    public void run() {
        stackMultiThread.push(new Random().nextInt());
    }
}
