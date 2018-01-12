/*******************************************************************************************************
 * To speed up the union() operation we use QuickUnion method. It is based on the same data structure:
 * the site-indexed id[] array BUT WE INTERPRET THE VALUES DIFFERENTLY, to define more complicated 
 * structures. Specifically, the id[] entry for each site is the name of another site in the same 
 * component (possibly itself)—we refer to this connection as a link. 
 * To implement find(), we start  at the given site, follow its link to another site, follow that site’s
 * link to yet another site, and so forth, following links until reaching a root, a site that has a link
 * to itself (which is guaranteed to happen).
 * 
 * Two sites are in the same component iff this process leads them to the same root. 
 * We take QuickUnion as an improvement over QuickFind because it removes QuickFind’s main liability 
 * that union() always takes linear time. 
*******************************************************************************************************/

package book.algorithms.forth.edition._1fundamentals;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {



	private int[] id; 					// access to component id (site indexed)
	private int count; 					// number of components

	public QuickUnionUF(int N) { 		// Initialize component id array.
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

	private int find(int p) {
		// Find component name.
		while (p != id[p]){
			p = id[p];
		}
		return p;
	}
	public void union(int p, int q) { 
		// Give p and q the same root.
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot) return;
		id[pRoot] = qRoot;
		count--;
	}
	
	public static void main(String[] args) { 	// Solve dynamic connectivity problem on StdIn.
	int N = StdIn.readInt(); 					// Read number of sites.
	QuickUnionUF quickUnionUF = new QuickUnionUF(N);	// Initialize N components.
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();			// Read pair to connect.
			if (quickUnionUF.connected(p, q)) {
				continue; 						// Ignore if connected.
			}
			quickUnionUF.union(p, q); 			// Combine components
			StdOut.println(p + " " + q);		// and print connection.
		}
		StdOut.println(quickUnionUF.count() + " components");
	}
}

/*******************************************************************************************************
 * QuickUnion allows for low-performance structure where larger trees get connected to smaller:
 * 					Suppose that the input pairs come in the order 0-1, then 0-2, 
 * 			  *		then 0-3, and so forth. After N  1 such pairs, we have N sites all in the same set,
 * 			 * *	and the tree that is formed by the quick-union algorithm has height N  1, with 0 
 * 			*	*	linking to 1, which links to 2, which links to 3, and so forth.
 *		   * *				
 *		  * * *		Fortunately, there is an easy modification to quick-union that allows us to guarantee 
 *		 *  *  *	that bad cases such as this one do not occur. Rather than arbitrarily connecting the
 *		*  * *  *	second tree to the first for union(), we keep track of the size of each tree and always
 *					connect the smaller tree to the larger.This algorithm is called WeightedQuickUnion.
T 
 *
 *******************************************************************************************************/
