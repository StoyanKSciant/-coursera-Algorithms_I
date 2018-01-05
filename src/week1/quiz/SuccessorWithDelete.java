package week1.quiz;

import edu.princeton.cs.algs4.QuickUnionUF;

public class SuccessorWithDelete {
	
	private boolean data[];  // data[i] == false if removed
	private int N;                 // N integers in S
	private QuickUnionUF uf; 
                                      // used to find largest unremoved element
    
    public SuccessorWithDelete(int N) {
        this.N = N;
        data = new boolean[N];
        for (int i = 0; i < N; ++i) 
            data[i] = true;           // populate boolean[] true vals
        uf = new QuickUnionUF(N);
    }
}