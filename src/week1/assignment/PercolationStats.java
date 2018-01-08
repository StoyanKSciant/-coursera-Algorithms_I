package week1.assignment;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private double[] opened;
	private int T; // trials > 0.
	private int N; // number of matrix side

	public PercolationStats(int n, int trials) {
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException();
		}
		this.T = trials;
		this.N = n;

		opened = new double[T];
		for (int i = 0; i < T; i++) {
			test(i);
		}
	}

	private void test(int indexTest) {
		final Percolation calcPerc = new Percolation(N);
		double count = 0.0;

		// while instance hasn't perculated, run test
		while (!calcPerc.percolates()) {
			// Use StdRandom to generate random numbers;
			int randomRow = StdRandom.uniform(1, N + 1);
			int randomColumn = StdRandom.uniform(1, N + 1);
			// open at that random
			if (!calcPerc.isOpen(randomRow, randomColumn)) {
				calcPerc.open(randomRow, randomColumn);
				count++;
			}
		}
		double maxSize = N * N;
		opened[indexTest] = count / maxSize;
	}

	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(opened);
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(opened);
	}

	public double confidenceLo() {
		// low endpoint of 95% confidence interval
		return mean() - ((1.96 * Math.sqrt(stddev())) / Math.sqrt(T));
	}

	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		return mean() + ((1.96 * Math.sqrt(stddev())) / Math.sqrt(T));
	}

	public static void main(String[] args) { // test client

		if (args.length != 2) {
			System.out.println("Usage: PercolationStats N T");
			return;
		}

		final int N = Integer.valueOf(args[0]);
		final int T = Integer.valueOf(args[1]);
		final PercolationStats ps = new PercolationStats(N, T);
		System.out.printf("mean                      = %f\n", ps.mean());
		System.out.printf("stddev                    = %f\n", ps.stddev());
		System.out.printf("95%% confidence interval  = %f, %f\n", ps.confidenceHi(), ps.confidenceLo());

	}
}
