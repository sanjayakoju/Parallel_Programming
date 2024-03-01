package lab.merging_sort;

public class BinarySearch {

	public static int binarySearch(int K, int arr[]) {
		int start = 0;
		int end = arr.length - 1;
		
		while (start <= end) {
			
			int mid = (start + end) / 2;
			 
	        // If K is found
	        if (arr[mid] == K)
	            return mid;
	 
	        else if (arr[mid] < K)
	            start = mid + 1;
	 
	        else
	            end = mid - 1;
		}
		
		return end + 1;
	}

}
