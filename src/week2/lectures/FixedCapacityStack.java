package week2.lectures;

public class FixedCapacityStack<Item> {

	private Item[] s;
	private int N = 0;

	public FixedCapacityStack(int capacity) {
		s = (Item[]) new Object[capacity];	// this will prompt warnings
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public void push(Item item) {
		if (N == s.length) {
			resize(2 * s.length);
		}
		s[N++] = item;
	}

	private void resize(int capacity) {

		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = s[i];
		}
		s = copy;
	}

	public Item pop() {

		// avoid loitering by removing unused references
		Item item = s[--N];
		s[N] = null;

		// shrink array size only if it gets 3/4 empty
		if (N > 0 && N == s.length / 4) {
			resize(s.length / 2);
		}

		return item;
	}
}
