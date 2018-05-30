/**
 * TASK: Quick-Select selection on average takes linear time to find K-th smallest
 *
 * @link https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array
 *
 * Input: array[] = {7, 10, 4, 3, 20, 15} k = 3 Output: 7
 * Input: array[] = {7, 10, 4, 3, 20, 15} k = 4 Output: 10
 *
 */

package week3.lectures;

import java.util.Arrays;
import java.util.Random;

public class FindKthSmallestQuickSelect {

	/*
	 * In QuickSort, we pick a pivot element, then move the pivot element to its
	 * correct position and partition the array around it. The idea is, not to do
	 * complete quick sort, but stop at the point where pivot itself is k’th
	 * smallest element. Also, not to recur for both left and right sides of pivot,
	 * but recur for one of them according to the position of pivot. The worst case
	 * time complexity of this method is O(n^2), but it works in O(n) on average.
	 */

	public int k = 0;

	FindKthSmallestQuickSelect(int num){
		this.k = num;
	}

	public static int partition(Integer[] arr, int l, int r) {
		int x = arr[r], i = l;
		for (int j = l; j <= r - 1; j++) {
			if (arr[j] <= x) {
				swap(arr[i], arr[j]);
				i++;
			}
		}
		swap(arr[i], arr[r]);
		return i;
	}
	public static int kthSmallest(Integer[] arr, int l, int r, int k) {
		if (k > 0 && k <= r - l + 1) {

			// Partition the array around last
            // element and get position of pivot
            // element in sorted array
			int pos = partition(arr, l, r);

			// If position is same as k
			if (pos - l == k - 1)
				return arr[pos];


			// If position is more, recur for
            // left subarray
			if (pos - l > k - 1)
				return kthSmallest(arr, l, pos - 1, k);


			// Else recur for right subarray
			return kthSmallest(arr, pos + 1, r, k - pos + l - 1);
		}
		return Integer.MAX_VALUE;
	}

	public static void swap(int a, int b)
    {
        int temp = a;
        a = b;
        b = temp;
    }


	// Picks a random pivot element between l and r and partitions
	// array[l..r] arount the randomly picked element using partition()
	private int randomPartition(Integer array[], int left, int right) {
		int n = right - left + 1;
		Random rand = new Random();
		int pivot = rand.nextInt(n) + 1;
		swap(array[left + pivot], array[right]);
		return partition(array, left, right);
	}



	// sort array select kth in order
	private Integer simpleSolution(Integer[] array, int k) {
		Arrays.sort(array);
		return array[k - 1];
	}

	public static void main(String[] args) {

		FindKthSmallestQuickSelect instance = new FindKthSmallestQuickSelect(5);
		Integer array[] = new Integer[] { 12, 3, 5, 7, 4, 19 };
		Integer display[] = new Integer[] { 12, 3, 5, 7, 4, 19 };
		Arrays.sort(display);
		System.out.println("In array: " + Arrays.toString(display) + ", " + instance.k + "th smallest element is: ");
		Integer arr[] = new Integer[] { 12, 3, 5, 7, 4, 19 };

		System.out.println("Simple solution: " + instance.simpleSolution(array, instance.k));
		System.out.print("QuickSelect solution: " + instance.kthSmallest(arr, 0, array.length - 1, instance.k));
	}
}
