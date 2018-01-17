/*************************************************************************
 * Selection in two sorted arrays:
 * Given two sorted arrays a[] and b[], of sizes n1 and n2, respectively,
 * design an algorithm to find the k-th largest key. The order of growth 
 * of the worst case running time of your algorithm should be logn,
 * where n=n1+n2.
 * 
 * Version 1: n1=n2 and k=n/2
 * Version 2: k=n/2
 * Version 3: no restrictions
 * 
 * Hint: there are two basic approaches:
 * >Approach A: Compute the median in a[] and the median in b[]. Recur in
 *  a subproblem of roughly half the size.
 *  
 * >Approach B: Design a constant-time algorithm to determine whether a[i]
 *  is the kth largest key. Use this subroutine and binary search.
 *  
 * Dealing with corner cases can be tricky.
 *************************************************************************/

package week3.quiz;

public class SelectionInTwoSortedArrays {

	static int kth(int arr1[], int arr2[], int m, int n, int k) {
		int[] sorted1 = new int[m + n];
		int i = 0, j = 0, d = 0;
		while (i < m && j < n) {
			if (arr1[i] < arr2[j])
				sorted1[d++] = arr1[i++];
			else
				sorted1[d++] = arr2[j++];
		}
		while (i < m)
			sorted1[d++] = arr1[i++];
		while (j < n)
			sorted1[d++] = arr2[j++];
		return sorted1[k - 1];
	}

	// main function
	public static void main(String[] args) {
		int arr1[] = { 2, 3, 6, 7, 9 };
		int arr2[] = { 1, 4, 8, 10 };
		int k = 5;
		System.out.print(kth(arr1, arr2, 5, 4, k));
	}
}