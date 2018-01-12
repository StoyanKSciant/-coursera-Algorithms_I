/********************************************************************************************************
 * Given a composite systems comprised of randomly distributed insulating and metallic materials: what 
 * fraction of the materials need to be metallic so that the composite system is an electrical conductor?
 * Given a porous landscape with water on the surface (or oil below), under what conditions will the 
 * water be able to drain through to the bottom (or the oil to gush through to the surface)? Scientists 
 * have defined an abstract process known as percolation to model such situations.
 * 
 * When n is sufficiently large, there is a threshold value p* such that when p < p* a random n-by-n grid
 * almost never percolates, and when p > p*, a random n-by-n grid almost always percolates. No 
 * mathematical solution for determining the percolation threshold p* has yet been derived. Your task is
 * to write a computer program to estimate p*.
 * 
 * Corner cases:
 * By convention, the row and column indices are integers between 1 and n, where (1, 1) is the upper-left
 * site: 
 * Throw a java.lang.IllegalArgumentException if any argument to open(), isOpen(), or isFull() is 
 * outside its prescribed range. 
 * The constructor should throw a java.lang.IllegalArgumentException if n ≤ 0.

 * Performance requirements: 
 * The constructor should take time proportional to n^2; 
 * All methods should take constant time plus a constant number of calls to the union–find methods
 * union(), find(), connected(), and count().
 ********************************************************************************************************/

package week1.assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int n;
	private int numberOfOpenSites;
	private boolean[][] openState;
	private WeightedQuickUnionUF firstUF;
	private WeightedQuickUnionUF secondUF; // avoid backwash with second WeightedQuickUnionUF
	
	
	public Percolation(int N) {				// Create NxN grid, with all sites blocked
		if(N <= 0) {
			throw new IllegalArgumentException("Matrix size must be a positive number.");
		}
		n = N;
		numberOfOpenSites = 0;
		openState = new boolean[N][N];
		firstUF = new WeightedQuickUnionUF(N * N + 2);
		secondUF = new WeightedQuickUnionUF(N * N + 2);
	}

	// open site (row i, column j) if it is not already open and check for merging
	public void open(int row, int col) {
		if (isOpen(row, col)) {
			return;
		}
		openState[row - 1][col - 1] = true;	// change open record to true
		numberOfOpenSites++;
		
		// unite with open neighbors
		int currentSiteIndex = xyTo1D(row, col);
		checkAndOpen(row - 1, col, currentSiteIndex);
		checkAndOpen(row + 1, col, currentSiteIndex);
		checkAndOpen(row, col - 1, currentSiteIndex);
		checkAndOpen(row, col + 1, currentSiteIndex);
		
		if (row == 1) {					// if is on top, connect to virtual top
			checkAndOpen(1, col, 0);
		}
		if (row == n) {					// if is on bottom, connect virtual bottom
			firstUF.union(n * n + 1, xyTo1D(row, col));
		}
	}

	private void checkAndOpen(int r, int c, int root) {
		try {
			// try to unite with either first or second UF
			if (isOpen(r, c)) {
				secondUF.union(root, xyTo1D(r, c));
				firstUF.union(root, xyTo1D(r, c));
			}
		} catch (IndexOutOfBoundsException e) {
			return;
		}
	}

	// Is site (row, column) open?
	public boolean isOpen(int row, int col) {
		checkBoundaries(row - 1, col - 1);	// check arguments
		return openState[row - 1][col - 1];	// return record
	}

	// convert grid coordinates to array index
	private int xyTo1D(int row, int col) {
		return n * (row - 1) + col;
	}

	/* A full site is an open site that is connected to an open site on the top
	 * row via a chain of neighboring (left, right, up, down) open sites. */
	public boolean isFull(int row, int col) {
		checkBoundaries(row - 1, col - 1);
		return secondUF.connected(0, xyTo1D(row, col));
	}

	public int numberOfOpenSites() {      // number of open sites
		return numberOfOpenSites;
	}
	
	// To percolate the two UFs must connect
	public boolean percolates() {
		if (n == 1) {
			return openState[0][0];
		}
		return firstUF.connected(0, n * n + 1);
	}

	private void checkBoundaries(int row, int col) {
		if (row >= n || col >= n || row < 0 || col < 0) {
			throw new java.lang.IndexOutOfBoundsException("Outside bounds of matrix." + "\n" + 
				String.format("N:%d N>=row:%d N>=col:%d", n, row, col));
		}
	}
}
