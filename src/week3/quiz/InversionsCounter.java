/********************************************************************************
 * Counting inversions:
 * An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j
 * but a[i]>a[j]. 
 * Given an array, design a linearithmic algorithm to count the number of
 * inversions.
 * 
 * Example: In array: [2, 4, 1, 3, 5], there are three inversions: 2,1; 4,1; 4,3
 * 
 * Inversion Count for an array indicates � how far (or close) the array is from
 * being sorted. 
 * 
 * If array is already sorted then inversion count is 0. 
 * If array is sorted in reverse order that inversion count is the maximum.
 * 
 * Hint: count while merge-sorting.
 ********************************************************************************/

package week3.quiz;

import java.util.Arrays;

public class InversionsCounter {

	private void bruteInversionsCount(int[] arr) {
		int countInversions = 0;
		StringBuilder couples = new StringBuilder();
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					couples.append("|" + arr[i] + " & " + arr[j] + "|");
					countInversions++;
				}
			}
		}
		System.out.println("For array: " + Arrays.toString(arr) + " Inversions are: " + countInversions);
		System.out.println(couples);
	}

	private int merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

		int countInversions = 0;

		for (int n = lo; n <= hi; n++) {	// Copy to auxiliary array
			aux[n] = a[n];
		}

		// i: idx of left part, j: idx of right part, k: idx of the merged one.
		int i = lo, j = mid + 1;

		for (int k = lo; k <= hi; k++) {
			// i > mid
			if (i > mid)					// if left part is consumed, move to next pointer j
				a[k] = aux[j++];
			// else: j > mid
			else if (j > hi)				// if right part is consumed, move to previous pointer i
				a[k] = aux[i++];
			// else: i > j
			else if (aux[i].compareTo(aux[j]) > 0) { // left side is bigger
				a[k] = aux[j++];
				/*
				 *  If aux[i] > aux[j], then following items in left part are all > aux[j].
				 * So for aux[j], there are totally (mid - i + 1) reversed items:
				 * aux[i], aux[i + 1], ..., aux[mid] > aux[j],
				 * since at this stage, sub-array [lo, mid], [mid + 1, hi] are sorted repectively.
				 */

			// else i < j
			countInversions += mid - i + 1;

			} else { 						// right side is bigger
				a[k] = aux[i++];
			}
		}

		return countInversions;
	}

	/**
	 * @return all the counted inversions from all merges over the algorithm
	 */
	private int sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (lo >= hi) {
			return 0;
		}
		int mid = lo + (hi - lo) / 2;
		
		// recursively sort 2 halfs
		int leftCount = sort(a, aux, lo, mid);
		int rightCount = sort(a, aux, mid + 1, hi);
		// merge them together
		int combineCount = merge(a, aux, lo, mid, hi);
		
		return leftCount + rightCount + combineCount;
	}

	public int inverseiOnCount(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		return sort(a, aux, 0, a.length - 1);
	}

	public static void main(String[] args) {
		int[] a = { 50, 61, 44, 32, 99, 87, 51, 50, 50, 12 };
		InversionsCounter ic = new InversionsCounter();
		ic.bruteInversionsCount(a);

		Comparable[] b = { 50, 61, 44, 32, 99, 87, 51, 50, 50, 12 };
		System.out.println("\r\n" + ic.inverseiOnCount(b));
	}
}