/*********************************************************************************************
 * Assignment 3: Given a set of n distinct points in the plane, find every (maximal) line 
 * segment that connects a subset of 4 or more of the points.
 * *******************************************************************************************
 * Given a point p, the following method determines whether p participates in a set of 4 or 
 * more collinear points:
 *
 * > Think of p as the origin.
 * 
 * > For each other point q, determine the slope it makes with p.
 * 
 * > Sort the points according to the slopes they makes with p.
 * 
 * > Check if any 3 (or more) adjacent points in the sorted order have equal slopes with 
 *  respect to p. If so, these points, together with p, are collinear.
 *  
 * >Applying this method for each of the n points in turn yields an efficient algorithm to the
 *  problem. The algorithm solves the problem because points that have equal slopes with
 *  respect to p are collinear, and sorting brings such points together. The algorithm is fast
 *  because the bottleneck operation is sorting.
 * *******************************************************************************************
 * Write a program FastCollinearPoints.java that implements the former algorithm:
 * 
 * The method segments() should include each maximal line segment containing 4 (or more) 
 * points exactly once. For example, if 5 points appear on a line segment in the order 
 * p→q→r→s→t, then do not include the subsegments p→s or q→t.
 *
 * Corner cases:Throw a java.lang.IllegalArgumentException if the argument to the constructor
 * is null, if any point in the array is null, or if the argument to the constructor contains
 * a repeated point.
 * 
 * Performance requirement: The order of growth of the running time of your program should be
 * n2 log n in the worst case and it should use space proportional to n plus the number of 
 * line segments returned. FastCollinearPoints should work properly even if the input has 5
 * or more collinear points.
 *********************************************************************************************/

package week3.assignment;

public class FastCollinearPoints {
	
	public FastCollinearPoints(Point[] points) {	// finds all line segments containing 4 or more points
		
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

	public int numberOfSegments() {					// the number of line segments
		
	}
	
	public LineSegment[] segments() {				// the line segments
		
	}
}
