
public class StackPopThread implements Runnable{
    StackMultiThread stackMultiThread;

    public StackPopThread(StackMultiThread stackMultiThread) {
        this.stackMultiThread = stackMultiThread;
    }

    @Override
    public void run() {
        stackMultiThread.pop();
    }
}
