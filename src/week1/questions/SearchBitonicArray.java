package week1.questions;

/* Search in a bitonic array.

An array is bitonic if it is comprised of an increasing sequence of integers followed 
immediately by a decreasing sequence of integers. Write a program that, given a bitonic
array of N distinct integer values, determines whether a given integer is in the array.

Standard version: Use ∼3lgN compares in the worst case.
Signing bonus: Use ∼2lgN compares in the worst case (and prove that 
no algorithm can guarantee to perform fewer than ∼2lgN compares in the worst case).
 */

public class SearchBitonicArray {

	/**
	 * Solution: 
	 * 1) Locate the point in the array where ascending switchines to
	 * descending 
	 * 2) Perform binary search on each half of the array for the target
	 * integer
	 */

	private static boolean binsearch(int[] a, int left, int right, int key) {
		if (a[left] < a[right]) {
			while (left < right) {
				int mid = left + (right - left) / 2;
				if (a[mid] < key)
					left = mid + 1;
				else if (a[mid] > key)
					right = mid - 1;
				else
					return true;
			}
		} else {
			while (left < right) {
				int mid = left + (right - left) / 2;
				if (a[mid] < key)
					right = mid - 1;
				else if (a[mid] > key)
					left = mid + 1;
				else
					return true;
			}
		}
		return false;
	}

	public static boolean search(int[] a, int key) {
		int left = 0;
		int right = a.length - 1;

		while (left < right) {
			int mid = left + (right - left) / 2;
			if (a[mid] < key) {
				if (a[mid] < a[mid + 1]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else if (a[mid] > key) {
				if (a[mid] < a[mid + 1]) {
					if (binsearch(a, 0, mid - 1, key)) {
						return true;
					} else {
						left = mid + 1;
					}
				} else {
					if (binsearch(a, mid + 1, a.length - 1, key)) {
						return true;
					} else {
						right = mid - 1;
					}
				}
			} else
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 15, 10, 9, 8, 7, 6 };

		int target = 0;
		System.out.println("For target " + target + ": " + search(array, target));

		target = 1;
		System.out.println("For target " + target + ": " + search(array, target));

		target = 8;
		System.out.println("For target " + target + ": " + search(array, target));

		target = 20;
		System.out.println("For target " + target + ": " + search(array, target));
	}
}