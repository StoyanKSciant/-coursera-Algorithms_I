package book.algorithms.forth.edition._2sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


/************************************************************************************************
 * ShellSort is a simple extension of insertion sort that gains speed by allowing exchanges of
 * array entries that are far apart, to produce partially sorted arrays that can be efficiently
 * sorted, eventually by insertion sort. An h-sorted array is h independent sorted subsequences,
 *  interleaved together. By h-sorting for some large values of h, we can move items in the array 
 *  long distances and thus make it easier to h-sort for smaller values of h.
 * 
 * If we modify insertion sort to h-sort the array and add an outer loop to decrease h through a 
 * sequence of increments starting at an increment as large as a constant fraction of the array
 * length and ending at 1, we are led to this compact ShellSort implementation.
 *
 ************************************************************************************************/


public class ShellSort {

	public static void sort(Comparable[] a) {
		// Sort a[] into increasing order.
		int N = a.length;
		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
		while (h >= 1) { // h-sort the array.
			for (int i = h; i < N; i++) { 
				// Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
					exch(a, j, j - h);
				}
			}
			h = h / 3;
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