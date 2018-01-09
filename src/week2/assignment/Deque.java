package week2.assignment;

/********************************************************************************************
 * Write a generic data type for a deque. The goal of this assignment is to implement 
 * elementary data structures using arrays and linked lists, and to introduce you to generics
 * and iterators. Do not use either java.util.LinkedList or java.util.ArrayList!
 * 
 * Dequeue: 
 * A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a 
 * queue that supports adding and removing items from either the front or the back of the 
 * data structure.
 * 
 * Performance requirements: 
 * Your deque implementation must support each deque operation (including construction) in 
 * constant worst-case time. A deque containing n items must use at most 48n + 192 bytes of
 * memory and use space proportional to the number of items currently in the deque. 
 * 
 * Additionally, your iterator implementation must support each operation (including 
 * construction) in constant worst-case time.
 ********************************************************************************************/

/*
 * Throw a java.lang.IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument. 
 * Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is empty. 
 * Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return. 
 * Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	public Deque() { // construct an empty deque

	}

	public boolean isEmpty() { // is the deque empty?

	}

	public int size() { // return the number of items on the deque

	}

	public void addFirst(Item item) { // add the item to the front
		if (item == null) {
			throw new IllegalArgumentException("Can't add unexisting item");
		}
	}

	public void addLast(Item item) { // add the item to the end
		if (item == null) {
			throw new IllegalArgumentException("Can't add unexisting item");
		}
	}

	public Item removeFirst() { // remove and return the item from the front
		if (this.size() == 0) {
			throw new NoSuchElementException("Can't remove item form empty queue");
		}
	}

	public Item removeLast() { // remove and return the item from the end
		if (this.size() == 0) {
			throw new NoSuchElementException("Can't remove item form empty queue");
		}
	}

	@Override
	public Iterator<Item> iterator() { // return an iterator over items in order from front to end
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<Item> {

		@Override
		public void remove() {
			// doesn't support remove()
			throw new UnsupportedOperationException("Remove method is not supported");
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("There are no more items left");
			}
			return null;
		}
	}
}