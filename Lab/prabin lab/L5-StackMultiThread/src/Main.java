public class Main {

    public static void main(String[] args) throws InterruptedException {
        StackMultiThread stack = new StackMultiThread(1000);
        for(int i=0;i<100;i++){
            Thread pushTh = new Thread(new StackPushThread(stack));
            pushTh.start();
            Thread pushTh1 = new Thread(new StackPushThread(stack));
            pushTh1.start();
            Thread popTh = new Thread(new StackPopThread(stack));
            popTh.start();
            pushTh.join();
            pushTh1.join();
            popTh.join();
            Thread thClear = new Thread(new StackClearThread(stack));
            thClear.start();
        }

    }
}