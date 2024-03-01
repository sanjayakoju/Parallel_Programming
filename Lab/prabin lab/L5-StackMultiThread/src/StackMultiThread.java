import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StackMultiThread {
    int[] data;
    int pointer=0;
    int stackSize;

    Lock lock = new ReentrantLock();

    public StackMultiThread(int stackSize) {
        this.stackSize=stackSize;
        data = new int[stackSize];
    }

    public void push(int value){
        System.out.println("Before Push Pointer: "+pointer);
        if(pointer==stackSize) throw new RuntimeException("Stack full");
//        synchronized (this) {
        lock.lock();
        data[pointer++] = value;
        lock.unlock();
//        }
        System.out.println("After Push Pointer: "+pointer);
    }
    public int pop(){
        System.out.println("Before Pop Pointer: "+pointer);
        if(pointer==0) throw new RuntimeException("Stack Empty");
        int value;
//        synchronized (this) {
        lock.lock();
        value = data[pointer--];
        lock.unlock();
//        }
        System.out.println("After Pop Pointer: "+pointer);
        return value;
    }
//    public synchronized void clear(){
    public void clear(){
        System.out.println("Before Clear Pointer: "+pointer);
        lock.lock();
        pointer = 0;
        lock.unlock();
        System.out.println("After Clear Pointer: "+pointer);
    }
}
