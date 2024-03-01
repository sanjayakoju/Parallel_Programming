package lab;

import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TaskThread implements Runnable {

    private final Stack<String> stringStack = new Stack<>();

    String st;
    String operation;

    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public TaskThread(String st, String operation) {
        this.st = st;
        this.operation = operation;
    }

    public TaskThread(String operation) {
        this.operation = operation;
    }

    @Override
    public void run() {

        stringStack.push("aa");
        stringStack.push("bb");

        if (operation.equalsIgnoreCase("Push")) {
            String a = stringStack.push(st);
            System.out.println("Push "+a);
        } else if (operation.equalsIgnoreCase("Pop")) {
            if (!stringStack.isEmpty()) {
                String b = stringStack.pop();
                System.out.println("Pop : "+b);
            }
        } else if (operation.equalsIgnoreCase("clear")) {
            stringStack.clear();
            System.out.println("Stack Clear");
        }
    }

    public String pop(String key) { // Read Operation
        r.lock();
        try {
            return stringStack.pop();
        } finally {
            r.unlock();
        }
    }

    public String push(String st) { // Write
        w.lock();
        try {
            return stringStack.push(st);
        } finally {
            w.unlock();
        }
    }

    public void clear() {
        w.lock();
        try {
            stringStack.clear();
        } finally {
            w.unlock();
        }
    }

}
