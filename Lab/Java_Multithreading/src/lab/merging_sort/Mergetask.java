package lab.merging_sort;

public class Mergetask implements Runnable {
	
	int Z[];
	int X[];
	int Y[];
	
	public Mergetask(int x[], int y[], int z[]) {
		this.X = x;
		this.Y = y;
		this.Z = z;
	}

	@Override
	public void run() {
		
		for (int i = 0; i < X.length; i++) {

			int j = BinarySearch.binarySearch(X[i], Y);
			if (Z[i+j] == X[i]) {
				Z[i + j + 1] = X[i];
			} else {
				Z[i + j] = X[i];
			}
			
		}
		
	}

}
