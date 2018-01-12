/***********************************************************************
 * Merging with smaller auxiliary array: Suppose that the subarray a[0]
 * to a[n−1] is sorted and the subarray a[n] to a[2*n−1] is sorted. How
 * can you merge the two subarrays so that a[0] to a[2*n−1] is sorted 
 * using an auxiliary array of length n (instead of 2n)?
 * 
 * Hint: copy only the left half into the auxiliary array.
 ***********************************************************************/

package week3.quiz;

import java.util.Arrays;

public class MergeWithSmallAux {

	public void mergeWithAuxArr(Comparable[] a, Comparable[] aux, int N) {

		// implementaion of mergeSort
		for (int k = 0; k < N; k++) {
			aux[k] = a[k];
		}

		// i - index of aux array
		// j - index of right part of a
		// k - index of merged array
		int i = 0, j = N, k = 0;
		while (k < a.length) {
			if (i >= N)
				a[k++] = a[j++];
			else if (j >= a.length)
				a[k++] = aux[i++];
			else if (aux[i].compareTo(a[j]) < 0)
				a[k++] = aux[i++];
			else {
				a[k++] = a[j++];
			}
		}
	}

	public static void main(String[] args) {
		/********************************first/second ****************/
		Comparable[] a = { 40, 61, 70, 71, 99, 20, 51, 55, 75, 100 };
		System.out.println("Before merging:");
		Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
		System.out.println();
		MergeWithSmallAux m = new MergeWithSmallAux();
		int N = a.length / 2;
		Comparable[] aux = new Comparable[N];
		m.mergeWithAuxArr(a, aux, N);
		System.out.println("After merging:");
		Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
		System.out.println();
	}
}
