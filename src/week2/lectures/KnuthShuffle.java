package week2.lectures;

import java.util.Arrays;

public class KnuthShuffle {

	/**
	 * Knuth shuffle. In iteration i, pick integer r between i and N-1 uniformly at
	 * random, Or between 0 to i uniformly at random Swap a[i] and a[r]
	 * 
	 * While traveling through the array, swap the current item with a random item
	 * after current.
	 */
	public void shuffle(Object[] a) {
		for (int i = 0; i < a.length; i++) {
			// uniformly random number between i and n-1 inclusive
			// random() -> [0, 1)
			// random() * (N - i) -> [0, N - i)
			// i + random() * (N - i) -> [i, N)
			int random = i + (int) (Math.random() * (a.length - i));
			swap(a, i, random);
		}
	}

	private void swap(Object[] a, int i, int random) {
		Object tmp = a[i];
		a[i] = a[random];
		a[random] = tmp;
	}

	public static void main(String[] args) {
		Comparable[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(Arrays.toString(a));
		KnuthShuffle sf = new KnuthShuffle();
		sf.shuffle(a);
		System.out.println(Arrays.toString(a));
	}
}