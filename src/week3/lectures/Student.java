package week3.lectures;

import java.util.Comparator;

/*
 * to implement a comparator: 1. Define a (nested) class that implements the
 * Comparator interface 2. Implement the compare() method
 */

public class Student {

	public static final Comparator<Student> BY_NAME = new ByName();
	public static final Comparator<Student> BY_SECTION = new BySection();

	private final String name;
	private final int section;

	// static - one comparator for the class
	private static class ByName implements Comparator<Student> {

		public int compare(Student v, Student w) {
			return v.name.compareTo(w.name);
		}
	}

	private static class BySection implements Comparator<Student> {

		public int compare(Student v, Student w) {
			return v.section - w.section;
		}
	}
}
