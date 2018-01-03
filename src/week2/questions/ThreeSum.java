package week2.questions;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ThreeSum {
	// check how many variations of tree numbers sum there is
	public static int count(int[] a) {

		int N = a.length;
		int count = 0;
		// brute forse algorithm
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				for (int k = j + 1; k < N; k++) {
					if (a[i] + a[j] + a[k] == 0) {
						// ignore overflow
						count++;
					}
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = In.readInts(args[0]);
		Stopwatch stopwatch = new Stopwatch();
		StdOut.println(count(a));
		double time = stopwatch.elapsedTime();
	}
}
