package practise;

public class SimpleThread implements Runnable {

	int myId;

	public SimpleThread(int myId) {
		this.myId = myId;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.print("Hello from thread \n");
		System.out.println(myId);
	}

	public static void main(String args[]) {
		Thread t1 = new Thread(new SimpleThread(1));
		Thread t2 = new Thread(new SimpleThread(2));
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
		}
	}

}
