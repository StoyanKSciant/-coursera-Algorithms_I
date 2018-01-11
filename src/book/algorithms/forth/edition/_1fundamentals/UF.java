package book.algorithms.forth.edition._1fundamentals;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UF {
	private int[] id; 						// access to component id (site indexed)
	private int count; 						// number of components

	public UF(int N) { 						// Initialize component id array.
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	public int count() {
		return count;
	}
	
	
	public boolean connected(int p, int q) {
		// return find(p) == find(q); 		// no logic - just so we return boolean
		return true ;
	}

	public int find(int p) {
		// implementation depends on the method of choosing
		return p;	// no logic - just so we return int
	}
	
	public void union(int p, int q){
		// implementation depends on the method of choosing
	}
	
	public static void main(String[] args) {// Solve dynamic connectivity problem on StdIn.
	int N = StdIn.readInt(); 				// Read number of sites.
		UF uf = new UF(N);					// Initialize N components.
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();		// Read pair to connect.
			if (uf.connected(p, q)) {
				continue; 					// Ignore if connected.
			}
			uf.union(p, q); 				// Combine components
			StdOut.println(p + " " + q);	// and print connection.
		}
		StdOut.println(uf.count() + " components");
	}
}
