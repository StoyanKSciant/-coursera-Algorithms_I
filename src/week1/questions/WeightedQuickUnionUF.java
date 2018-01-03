package week1.questions;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * the weighted algorithm makes sure that always the small tree
 * comes bellow on union operation
 */
/************************************************
 * algorithm | initialize | union | connected |
 * ------------------------------------------------- quick-find | N | N | 1 |
 * quick-union | N | N | N | weighted QU | N | lgN | lgN |
 *************************************************/

public class WeightedQuickUnionUF {
	private int[] parent; // parent[i] = parent of i
	private int[] size; // size[i] = number of sites in subtree rooted at i
	private int count; // number of components

	public WeightedQuickUnionUF(int n) {
		count = n;
		parent = new int[n];
		size = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}

	public int count() {
		return count;
	}

	public int find(int p) {
		validate(p);
		while (p != parent[p])
			parent[p] = parent[parent[p]]; // path compression
		p = parent[p];
		return p;
	}

	// validate that p is a valid index
	private void validate(int p) {
		int n = parent.length;
		if (p < 0 || p >= n) {
			throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
		}
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ)
			return;
		// THE ONLY DIFFERENCE COMPARED TO QUICK UNION:

		// link root of smaller tree to root of larger tree
		// depth at most is lgN
		if (size[rootP] < size[rootQ]) {
			parent[rootP] = rootQ;
			size[rootQ] += size[rootP];
		} else {
			parent[rootQ] = rootP;
			size[rootP] += size[rootQ];
		}
		count--;
	}

	/*	Testing
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
    */
	
	/**
	 * Given a social network containing n members and a log file containing m
	 * timestamps at which times pairs of members formed friendships, design an
	 * algorithm to determine the earliest time at which all members are connected
	 * (i.e., every member is a friend of a friend of a friend ... of a friend).
	 * Assume that the log file is sorted by timestamp and that friendship is an
	 * equivalence relation. The running time of your algorithm should be mlogn or
	 * better and use extra space proportional to n.
	 */
	public static void main(String[] args) {
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
		StdOut.println(uf.count() == 10);
		uf.union(0, 1);
		StdOut.println(uf.count() == 9);
		uf.union(2, 3);
		StdOut.println(uf.count() == 8);
		uf.union(0, 2);
		StdOut.println(uf.count() == 7);
		uf.union(1, 3);
		StdOut.println(uf.count() == 7);
	}
}