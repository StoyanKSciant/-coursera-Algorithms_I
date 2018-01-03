package week1.questions;

public class QuickUnionUF {
	
	private int[] parent;  // parent[i] = parent of i
	private int count;     // number of components
	
	public QuickUnionUF(int N){
		parent = new int[N];
		for(int i = 0; i < N; i++) {
			// set id of each object to itself
			parent[i] = i;
		}
	}
	
	public int count() {
        return count;
    }
	
	public int find(int p) {
        validate(p);
        while (p != parent[p]) { // chase parent pointers until reach root
    		p = parent[p];
        }
		return p;
    }

	// checks if p and q have the same root
		public boolean connected (int p, int q) {
			return root(p) == root(q);
	}
	
	// validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }
	
	private int root(int i) {
		while (i != parent[i]) {
			// path compression - connect sub roots directly to tree root
			parent[i] = parent[parent[i]];
			
			i = parent[i];
		}
		return i;
	}
	
	public void union (int p, int q) {
		int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        // change root of p to pint ot root of q
        parent[rootP] = rootQ; 
        count--;
	}
	
	/* Testing
	public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(n);
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
}
