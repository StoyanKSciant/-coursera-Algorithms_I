package book.algorithms.forth.edition._1fundamentals;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*********************************************************************************************
 * One approach is to maintain the invariant that p and q are connected  iff (if and only if)
 * id[p] is equal to id[q]. In other words, all sites in a component must have the same value 
 * in id[]. This method is called quick-find because find(p) just returns id[p], which 
 * immediately implies that connected(p, q) reduces to just the test id[p] == id[q] and returns 
 * true if and only if p and q are in the same component. 
 * To combine the two components into one, we have to make all of the id[] entries corresponding
 * to both sets of sites the same value.
 *********************************************************************************************/

public class QuickFindUF {

	
	
	private int[] id; 					// access to component id (site indexed)
	private int count; 					// number of components

	public QuickFindUF(int N) { 					// Initialize component id array.
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
		return find(p) == find(q);
	}

	public int find(int p) {
		return id[p];
	}

	public void union(int p, int q){
		// Put p and q into the same component.
		int pID = find(p);
		int qID = find(q);
		// Nothing to do if p and q are already
		// in the same component.
		if (pID == qID) return;
		// Rename p’s component to q’s name.
		for (int i = 0; i < id.length; i++)
		if (id[i] == pID) id[i] = qID;
		count--;
	}
	
	public static void main(String[] args) { // Solve dynamic connectivity problem on StdIn.
		
	int N = StdIn.readInt(); 			// Read number of sites.
	QuickFindUF quickFindUF = new QuickFindUF(N);				// Initialize N components.
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();	// Read pair to connect.
			if (quickFindUF.connected(p, q)) {
				continue; 				// Ignore if connected.
			}
			quickFindUF.union(p, q); 			// Combine components
			StdOut.println(p + " " + q);// and print connection.
		}
		StdOut.println(quickFindUF.count() + " components");
	}
	
}

/*********************************************************************************************
 * The find() operation is certainly quick, as it only accesses the id[] array once in order 
 * to complete the operation. But QuickFind is typically not useful for large problems 
 * because union() needs to scan through the whole id[] array for each input pair.
***********************************************************************************************/
