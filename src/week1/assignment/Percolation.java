package week1.assignment;

/*******************************************************************************************************
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
 *******************************************************************************************************/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private int N;
	private boolean[][] grid;
	private WeightedQuickUnionUF first;
	private WeightedQuickUnionUF second;
	private int virtualTopKey;
	private int virtualBottomKey;

	public Percolation(int N) { // create n-by-n grid, with all sites blocked
		if (N <= 0) {
			throw new IllegalArgumentException("Initialize the grid only with positive integers.");
		}
		this.N = N;
		this.grid = new boolean[N][N];
		int maxIndex = N * N - 1;

		virtualTopKey = maxIndex + 1;
		virtualBottomKey = maxIndex + 2;
		first = new WeightedQuickUnionUF(virtualTopKey + 1);
		second = new WeightedQuickUnionUF(virtualBottomKey + 1);
	}

	private boolean isInBounds(int row, int col) {
		// check if argument is outside its prescribed range.
		if (row <= 0 || row >= N || col <= 0 || col >= N) {
			throw new IndexOutOfBoundsException("Outside bounds of matrix.");
		}
		return true;
	}

	public boolean isOpen(int row, int col) { // is site (row, col) open?
		isInBounds(row, col);
		return grid[row - 1][col - 1];
	}

	private int getCoordinatesPosition(int row, int col) {
		int pos = row + (col * N);
		return pos;
	}
	public boolean isFull(int row, int col) { // is site (row, col) full?
		isInBounds(row, col);
		return second.connected(virtualTopKey, getCoordinatesPosition(row, col));
	}

	private void union(int row, int col, int rowOffset, int columnOffset) {
		final int currentKey = getCoordinatesPosition(row, col);
		final int column2 = col + columnOffset;
		final int row2 = row + rowOffset;
		if (isOpen(row2, column2)) {
			first.union(currentKey, getCoordinatesPosition(row2, column2));
			second.union(currentKey, getCoordinatesPosition(row2, column2));
		}
	}

	// Connect to virtual top and bottom in 
	private void unionVirtual(int row, int col, int virtualKey, boolean allowBottomUnion) {
		final int currentKey = getCoordinatesPosition(row, col);
		first.union(currentKey, virtualKey);
		if (!allowBottomUnion) {
			second.union(currentKey, virtualKey);
		}
	}

	public void open(int row, int col) {
		
		// matrix keys[1, N][1, N], while array keys [0... N-1]
		
		grid[row - 1][col - 1] = true;
		
        if (!isLeftEdge(row)) {
        	// Connect to left
            union(row, col, 0, -1); 		
        }
       
        if (!isRightEdge(col)) {
        	// Connect to right
            union(row, col, 0, +1); 		
        }
        
        if (!isTopEdge(row)) {
        	// connect to top
            union(row, col, -1, 0); 		
        } else {
        	// Connect to top virtual
            unionVirtual(row, col, virtualTopKey, false); 
        }
        if (!isBottomEdge(row)) {
        	// connect to bottom
            union(row, col, 1, 0); 			
        } else {
            // Connect to bottom virtual
            unionVirtual(row, col, virtualBottomKey, true);
        }
	}

	public boolean percolates() { // does the system percolate?
		return first.connected(virtualBottomKey, virtualTopKey);
	}

	private boolean isBottomEdge(int row) {
		return row == N;	// bottom [N][]
	}

	private boolean isTopEdge(int row) {
		return row == 1;	// top [1][]
	}

	private boolean isRightEdge(int col) {
		return col == N;	// right [][N]
	}

	private boolean isLeftEdge(int col) {
		return col == 1;	// left [1][]
	}
}
