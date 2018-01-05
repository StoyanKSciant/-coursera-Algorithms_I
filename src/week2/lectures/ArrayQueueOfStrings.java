package week2.lectures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Array implementation of queue

public class ArrayQueueOfStrings {

	private String[] queue; 		// queue array
	private int elementsCount; 		// number of elements in queue
	private int first; 				// index of first element
	private int last; 				// index of last element

	public ArrayQueueOfStrings() {
		this.queue = new String[2];
		this.elementsCount = 0;
		this.first = 0;
		this.last = 0;
	}

	public boolean isEmpty() {
		return elementsCount == 0;
	}

	public int size() {
		return elementsCount;
	}

	// resize and repopulate new array
	public void resize(int capacity) {
		if (capacity > elementsCount) {
			String[] temp = new String[capacity];
			// repopulate array with new size
			for (int i = 0; i < this.elementsCount; i++) {
				temp[i] = queue[(first + i) % queue.length];
			}
			queue = temp;
			first = 0;
			last = elementsCount;
		}
	}

	// add element to the back, resize if necessary
	public void enqueue(String element) {
		if (elementsCount == queue.length) {
			// Double size of array if necessary
			resize(2 * queue.length);
		}
		// add element
		queue[last++] = element;
		// wrap-around
		if (last == queue.length) {
			last = 0;
		}
		elementsCount++;
	}

	// add element to the back, shrink if necessary
	public String dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue underflow");
		}
		String element = queue[first];
		queue[first] = null; // delete head
		elementsCount--; // count decrease
		first++; // move first
		if (first == queue.length) {
			first = 0; // // wrap-around
		}
		// shrink size of array if necessary
		if (elementsCount > 0 && elementsCount == queue.length / 4) {
			resize(queue.length / 2);
		}
		return element;
	}

	// return first
	public String peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue underflow");
		}
		return queue[first];
	}

	/**
	 * Returns an iterator that iterates over the items in this queue in FIFO order.
	 */
	public Iterator<String> iterator() {
		return new ArrayIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ArrayIterator implements Iterator<String> {
		private int i = 0;

		public boolean hasNext() {
			return i < elementsCount;
		}

		// doesn't support remove()
		public void remove() {
			throw new UnsupportedOperationException();
		}

		public String next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			String item = queue[(i + first) % queue.length];
			i++;
			return item;
		}
	}

	public static void main(String[] args) {
		ArrayQueueOfStrings stringQueue = new ArrayQueueOfStrings();
		System.out.println("Queue is empty: " + stringQueue.isEmpty());
		
		stringQueue.enqueue("sock");
		stringQueue.enqueue("duck");
		stringQueue.enqueue("rock");
		stringQueue.enqueue("roll");
		System.out.println("add sock, duck, rock, roll :" + '\n' + Arrays.toString(stringQueue.queue));
		
		System.out.println("peek item: " + stringQueue.peek());
		
		for(int i = 0; i < 3; i++) {
			stringQueue.dequeue();
		}
		System.out.println("dequeue 3 items: " + Arrays.toString(stringQueue.queue));
		
		stringQueue.enqueue("find");
		stringQueue.enqueue("hide");
		stringQueue.enqueue("side");
		stringQueue.enqueue("glass");
		stringQueue.enqueue("rim");
		stringQueue.enqueue("flor");
		stringQueue.enqueue("ball");
		stringQueue.enqueue("pen");
		stringQueue.enqueue("soul");
		stringQueue.enqueue("ghost");
		stringQueue.enqueue("peer");
		stringQueue.enqueue("bike");
		System.out.println("add find, hide, side, glass, rim, flor, ball, pen, soul, ghost, peer, bike :" + '\n' + Arrays.toString(stringQueue.queue));
		System.out.println("queue size is " + Integer.toString(stringQueue.size()));
		
		for(int i = 0; i < 12; i++) {
			stringQueue.dequeue();
		}
		System.out.println("dequeue 12 items: " + Arrays.toString(stringQueue.queue));
		
	}
}
