package week1.assignment;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

	private double[] openedRatio;

	// private StringBuilder out;
	public PercolationStats(int matrixSize, int trials) {
		if (matrixSize < 1) {
			throw new IllegalArgumentException("N should be bigger than 0.");
		}
		if (trials < 1) {
			throw new IllegalArgumentException("T should be bigger than 0.");
		}
		openedRatio = new double[trials];
		Percolation perc;
		for (int x = 0; x < trials; x++) {
			perc = new Percolation(matrixSize);	// new percolation for each trial
			int count = 0;
			
			do {			// Randomly open sites while percolation doesn't exists
				int i = StdRandom.uniform(1, matrixSize + 1);
				int j = StdRandom.uniform(1, matrixSize + 1);
				if (!perc.isOpen(i, j)) {
					perc.open(i, j);
					count++;
				}
				
			} while (!perc.percolates());
			// save trial result
			openedRatio[x] = (double) count / (double) (matrixSize * matrixSize);
		}
	}

	// Sample mean of percolation threshold.
	// (Returns the average value in the specified array)
	public double mean() {
		return StdStats.mean(openedRatio);
	}

	// Sample standard deviation of percolation threshold.
	// (Returns the sample standard deviation in the specified array)
	public double stddev() {
		return StdStats.stddev(openedRatio);
	}

	// Returns lower bound of the 95% confidence interval.
	public double confidenceLo() {
		return mean() - (1.96 * stddev() / Math.sqrt(openedRatio.length));
	}

	// Returns upper bound of the 95% confidence interval.
	public double confidenceHi() {
		return mean() + (1.96 * stddev() / Math.sqrt(openedRatio.length));
	}

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);

		Stopwatch sw = new Stopwatch();
		PercolationStats perStat = new PercolationStats(N, T);
		System.out.printf("mean                    = %f\n", perStat.mean());
		System.out.printf("stddev                  = %f\n", perStat.stddev());
		System.out.printf("95%% confidence interval = %f, %f\n", perStat.confidenceLo(), perStat.confidenceHi());
		System.out.println();
		System.out.printf("Elapsed calculation time: %f secs. (for N=%d, T=%d)", sw.elapsedTime(), N, T);
	}
}
