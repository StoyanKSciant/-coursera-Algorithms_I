/**********************************************************************************************
 * Given two arrays a[] and b[], each containing n distinct 2D points in the
 * plane, design a sub-quadratic algorithm to count the number of points that
 * are contained both in array a[] and array b[].
 **********************************************************************************************/

package week2.quiz;

import java.lang.reflect.Array;

import edu.princeton.cs.algs4.Shell;

public class TwoSetsIntersectionImpl {

	public int countIntersection(Point[] a, Point[] b) {
		Shell.sort(a);
		Shell.sort(b); // edu.princeton.cs.algs4.Shell

		int i = 0;
		int j = 0;
		int count = 0;

		while (i < a.length && j < b.length) {
			if (a[i].compareTo(b[j]) == 0) {
				count++; // count only equal points
				i++;
				j++;
			} else if (a[i].compareTo(b[j]) < 0) {
				i++;
			} else {
				j++;
			}
		}
		return count;
	}

	public static void main(String[] args) {

		Point[] arr1 = { new Point(3, 7), new Point(9, 1), new Point(-12, 4), new Point(-7, 8), new Point(6, 4),
				new Point(9, 8), new Point(3, 3), new Point(6, 6), new Point(4, 2), new Point(5, 3) };

		Point[] arr2 = { new Point(3, 7), new Point(13, 1), new Point(-7, 1), new Point(4, 4), new Point(6, 4),
				new Point(9, 8), new Point(3, 8), new Point(6, 6)};

		TwoSetsIntersectionImpl test = new TwoSetsIntersectionImpl();

		System.out.println("the two array sets intersection count is: " + test.countIntersection(arr1, arr2));
	}
}
