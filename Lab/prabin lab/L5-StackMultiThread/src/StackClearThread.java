
public class StackClearThread implements Runnable{
    StackMultiThread stackMultiThread;

    public StackClearThread(StackMultiThread stackMultiThread) {
        this.stackMultiThread = stackMultiThread;
    }

    @Override
    public void run() {
        stackMultiThread.clear();
    }
}
