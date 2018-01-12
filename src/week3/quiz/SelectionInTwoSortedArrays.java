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

}
