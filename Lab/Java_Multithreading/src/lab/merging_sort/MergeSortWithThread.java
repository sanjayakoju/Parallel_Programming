package lab.merging_sort;

import java.util.Date;

public class MergeSortWithThread {

	public static void mergeSortThread(int x[], int y[], int z[]) throws InterruptedException {
		Date start, end;

		System.out.println("Start");
		start = new Date();

		Thread threadX = new Thread(new Mergetask(x, y, z));
		Thread threadY = new Thread(new Mergetask(y, x, z));
		threadX.start();
		threadY.start();
		threadX.join();
		threadY.join();

		end = new Date();
		System.out.printf("Merge Sort With Thread Time Elapsed: %d%n", end.getTime() - start.getTime());
	}


}
