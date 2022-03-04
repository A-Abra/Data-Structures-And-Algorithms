import java.util.Arrays;
import java.util.PriorityQueue;

public class DeterministicSelect {
	
	public static void main(String[] args) {

//		Integer[] nums = {12,2,5,10,3,19,27};
		//2,3,5,10,12,19,27
		Integer[] nums = {12,3,5,7,4,19,26};
		//3,4,5,7,12,19,26
		//Integer[] nums = {12,3,5,7,19};
		//System.out.println(randomizedSelect(nums,0,nums.length-1,2));
		System.out.println(deterministicSelect(nums,0,nums.length-1,7));
	}
	
	//choosing decent pivot
	public static int findmed(Integer A[],int l,int r) {
		int mid = (r+l)/2;
		int piv = r;
		if(A[l] < A[mid]) {
			if(A[mid] < A[r])
				piv = mid;
		}
		else if(A[l] < A[r])
			piv = l;
		return piv;
	}
	
	//partition array for with small nums left of kth elmt and bigger on right
	public static int partitions(Integer[] arr, int l,int r) {
		int piv = arr[findmed(arr,l,r)], i = l;
		for (int j = l; j <= r - 1; j++) {
			if (arr[j] <= piv) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;

				i++;
			}
		}
		
		int temp = arr[i];
		arr[i] = arr[r];
		arr[r] = temp;

		return i;
	}

	//get kth smallest element
	public static <E extends Comparable<E>> E deterministicSelect(E[] A, int i, int j, int k){
		
		if (k > 0 && k <= j - i + 1) {
			int pos = partitions((Integer[]) A, i, j);

			if (pos - i == k - 1)
				return A[pos];
			else if (pos - i > k - 1)
				return deterministicSelect(A, i, pos - 1, k);
			else
				return deterministicSelect(A, pos + 1, j, k - pos + i - 1);
		}
		return null;
	}
	    
	
}
