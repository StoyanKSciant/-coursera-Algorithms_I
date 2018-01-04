package week2.questions;

public class FixedCapacityStackOfStrings {

	/*
	 * Resizing array stack implementation 1. every operation takes constant
	 * amortized time. 2. less wasted space
	 */
	
	// implementation using Array
	private String[] s;
	private int N = 0;
	
	public FixedCapacityStackOfStrings(int capacity) {
		s = new String[capacity];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public FixedCapacityStackOfStrings() {
		s = new String[1];
	}
	
	public void push(String item) {
		if(N == s.length) {
			resize(2 * s.length);
		}
		s[N++] = item;
	}
	
	private void resize(int capacity) {
		
		String[] copy = new String[capacity];
		for(int i = 0; i < N; i++) {
			copy[i] = s[i];
		}
		s = copy;
	}
	
	public String pop() {
		
		// avoid loitering by removing unused references
		String item = s[--N];
		s[N] = null;
		
		// shrink array size only if it gets 3/4 empty
		if(N > 0 && N == s.length/4) {
			resize(s.length/2);
		}
		
		return item;
	}
}
