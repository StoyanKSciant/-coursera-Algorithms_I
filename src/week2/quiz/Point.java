package week2.quiz;

import edu.princeton.cs.algs4.Shell;

public class Point implements Comparable<Point> {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point that) {
		if (that.x > this.x) {
			return -1;
		}
		if (that.x < this.x) {
			return 1;
		}
		if (that.y > this.y) {
			return -1;
		}
		if (that.y < this.y) {
			return 1;
		}
		return 0; 	// if equal return 0
	}
}