package week1.lectures;

import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUFWithlargestElementInComponent {

	private int parent[]; 		// parent[i] = parent of node i
	private int size[]; 	// size[i] = size of node i
	private int largest[]; 	// largest[i] = largestst element in component

	public QuickUnionUFWithlargestElementInComponent(int N) {
        parent = new int[N];
        size = new int[N];
        largest = new int[N];
        for (int i = 0; i < N; ++i) {
            parent[i] = i;
            size[i] = 1;
            largest[i] = i;
        }
    }

	private int root(int p) {
		while (parent[p] != p) {
			parent[p] = parent[parent[p]]; // path compression
			p = parent[p];
		}
		return p;
	}

	// return the largest element in the connected component containing p
	public int find(int p) {
		return largest[root(p)];
	}

	public void union(int p, int q) {
		int rootp = root(p);
		int rootq = root(q);
		if (rootp == rootq)
			return;
		int largeststP = largest[rootp];
		int largeststQ = largest[rootq];
		if (size[rootp] < size[rootq]) {
			parent[rootp] = rootq;
			size[rootq] += size[rootp];

			if (largeststP > largeststQ)
				largest[rootq] = largeststP;
		} else {
			parent[rootq] = rootp;
			size[rootp] += size[rootq];

			if (largeststQ > largeststP) {
				largest[rootp] = largeststQ;
			}
		}
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	/* Union-find with specific canonical element.
	 * Add a method find() to the union-find data type so that find(i) 
	 * returns the largest element in the connected component containing i. 
	 * The operations, union(), connected(), and find() should all take logarithmic time or better.
	 * 
	 * For example, if one of the connected components is {1,2,6,9}, 
	 * then the find() method should return 9 for each of the four elements in the connected components.
	 */
	
	
	public static void main(String[] args) {
		QuickUnionUFWithlargestElementInComponent uf = new QuickUnionUFWithlargestElementInComponent(10);
		uf.union(0, 2);
		uf.union(8, 4);
		StdOut.println(uf.find(0) == 2);
		StdOut.println(uf.find(4) == 8);
		uf.union(0, 4);
		StdOut.println(uf.find(0) == 8);
		StdOut.println(uf.find(2) == 8);
		uf.union(0, 6);
		StdOut.println(uf.find(6) == 8);
		uf.union(1, 9);
		uf.union(1, 2);
		StdOut.println(uf.find(4) == 9);
	}
}
