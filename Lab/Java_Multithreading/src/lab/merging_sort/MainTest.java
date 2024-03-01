package lab.merging_sort;

import java.util.Date;

public class MainTest {

	public static final int n = 2000000;
	public static void main(String[] args) throws InterruptedException {
		int x[] = new int[n];
		int y[] = new int[n];
		int z[] = new int[x.length + y.length];

		for (int i=0;i<n;i++) {
			x[i] = i * 2;
			y[i] = 2 * i + 1;
		}
		
		Date start, end;
		

		System.out.println("Start");
		start = new Date();

		 MergeSort.mergeSort(x, y, z, n);

		end = new Date();
		System.out.printf("Merge Sort Time Elapsed: %d%n", end.getTime() - start.getTime());
		
		MergeSortWithThread.mergeSortThread(x, y, z);
	}
}
