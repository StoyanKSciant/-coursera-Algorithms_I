/*********************************************************************************************
 * Assignment 3: Given a set of n distinct points in the plane, find every (maximal) line
 * segment that connects a subset of 4 or more of the points.
 *
 * Write a program BruteCollinearPoints.java that examines 4 points at a time and  checks
 * whether they all lie on the same line segment, returning all such line segments. To
 * check whether the 4 points p, q, r, and s are collinear, check whether the three slopes
 * between p and q, between p and r, and between p and s are all equal.
 *
 * >The method segments() should include each line segment containing 4 points exactly once.
 *  If 4 points appear on a line segment in the order p→q→r→s, then you should include either
 *  the line segment p→s or s→p (but not both) and you should not include subsegments such as
 *  p→r or q→r. For simplicity, we will not supply any input to BruteCollinearPoints that has
 *  5 or more collinear points.
 *
 * >Corner cases: Throw a java.lang.IllegalArgumentException if the argument to the
 *  constructor is null, if any point in the array is null, or if the argument to the
 *  constructor contains a repeated point.
 *
 * >Performance requirement: The order of growth of the running time of your program should be
 *  n^`4 in the worst case and it should use space proportional to n plus the number of line
 *  segments returned.
 *
 *********************************************************************************************/

package week3.assignment;


public class BruteCollinearPoints {

	public BruteCollinearPoints(Point[] points) { 	// finds all line segments containing 4 points

		for(Point point: points) {
			if(point == null) {
				throw new IllegalArgumentException("null input");
			}
			if(checkDuplicates(points)) {
				throw new IllegalArgumentException("duplicates input is not allowed");
			}
		}
	}

	private boolean checkDuplicates(Point[] input) {// check if array contains duplicates
		boolean duplicates = false;
		for (int j=0;j < input.length;j++) {
			for (int k=j+1;k < input.length;k++) {
				if ( k!=j && input[k].equals(input[j])) {
					duplicates = true;
				}
			}
		}
		return duplicates;
	}

	public int numberOfSegments() {
		return 0;
	}

	public LineSegment[] segments() {
		return null;				// the line segments
	}
}
