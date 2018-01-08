package week2.quiz;

import edu.princeton.cs.algs4.Shell;

/**********************************************************************************************
 * Given two integer arrays of size n, design a sub-quadratic algorithm to determine whether one
 * is a permutation of the other. That is, do they contain exactly the same entries but, 
 * possibly, in a different order.
 **********************************************************************************************/

public class Permutation {

	public boolean isPern(Integer[]a, Integer[]b) {
		// check if array size is different
		if(a.length != b.length) {
			return false;
		}
		
		// if size is equal sort arrays
		Shell.sort(a);	// edu.princeton.cs.algs4.Shell;
		Shell.sort(b);
		
		// iterate trough arrays, if there is difference in their sorted
		// members, arrays can't be permutation of one another
		for(int i = 0; i < a.length; i++) {
			if(a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}
	
	

	public static void main(String[] args) {

		Permutation perm = new Permutation();
		Integer[] integerArr1 = { 1, 7, 9, 12, 6, 14, 19};
		Integer[] integerArr2 = { 19, 7, 9, 1, 12, 6, 14};

		System.out.println("Is array b permutation of array a: " + perm.isPern(integerArr1, integerArr2));
		
		Integer[] integerArr3 = { 11, 7, 6, 1, 8, 6, 0};

		System.out.println("Is array c permutation of array b: " + perm.isPern(integerArr2, integerArr3));
	}
}
