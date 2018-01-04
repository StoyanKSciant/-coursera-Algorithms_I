package book.algorithms.forth.edition._2sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*******************************************************************************************
 * Insertion sort:   ~(N^2)/4 compares and (N^2)/4 exchanges to sort random array
 * 
 * For each i from 0 to N-1, exchange a[i] with the entries that are smaller in a[0] through
 * a[i-1]. As the index i travels from left to right, the entries to its left are in sorted 
 * order in the array, so the array is fully sorted when i reaches the right end.
 * 
 * We need to make space to insert the current item by moving larger items 
 * one position to the right, before inserting the current item into the vacated position
 * 
 * As in selection sort, the items to the left of the current index are in sorted order during
 * the sort, but they are not in their final position, as they may have to be moved to
 * make room for smaller items encountered later.
 *******************************************************************************************/

public class Insertion {

	public static void sort(Comparable[] a) {
		// Sort a[] into increasing order.
		int N = a.length;
		for (int i = 1; i < N; i++) {
			// Insert a[i] among a[i-1], a[i-2], a[i-3]..
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
				exch(a, j, j - 1);
			}
		}
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static void show(Comparable[] a) { // Print the array, on a single line.
		for (int i = 0; i < a.length; i++) {
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
	}

	public static boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

	public static void main(String[] args) { // Read strings from standard input, sort them, and print.
		String[] a = In.readStrings();
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
