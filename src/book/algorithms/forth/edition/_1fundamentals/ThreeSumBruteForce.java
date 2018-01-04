package book.algorithms.forth.edition._1fundamentals;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class ThreeSumBruteForce {
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
		 int N = Integer.parseInt(args[0]);
		 int[] a = new int[N];
		 for (int i = 0; i < N; i++)
		 a[i] = StdRandom.uniform(-1000000, 1000000);
		 Stopwatch timer = new Stopwatch();
		 int cnt = ThreeSumBruteForce.count(a);
		 double time = timer.elapsedTime();
		 StdOut.println(cnt + " triples " + time);
	}
}
