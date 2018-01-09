package week2.assignment;

/********************************************************************************************
 * Write a generic data type for a randomized queue. The goal of this assignment is to 
 * implement elementary data structures using arrays and linked lists, and to introduce you 
 * to generics and iterators. Do not use either java.util.LinkedList or java.util.ArrayList!
 * 
 * Randomized queue: 
 * A randomized queue is similar to a stack or queue, except that the item 
 * removed is chosen uniformly at random from items in the data structure. Create a generic 
 * data type RandomizedQueue that implements the following API:
 * 
 * Iterator: 
 * Each iterator must return the items in uniformly random order. The order of two or more 
 * iterators to the same randomized queue must be mutually independent;
 * Each iterator must maintain its own random order.
 * 
 * Performance requirements:
 * Your randomized queue implementation must support each randomized queue operation (besides
 * creating an iterator) in constant amortized time. That is, any sequence of m randomized 
 * queue operations (starting from an empty queue) must take at most cm steps in the worst 
 * case, for some constant c. A randomized queue containing n items must use at most 48n + 
 * 192 bytes of memory. Additionally, your iterator implementation must support operations 
 * next() and hasNext() in constant worst-case time; and construction in linear time; you may
 * (and will need to) use a linear amount of extra memory per iterator.
 ********************************************************************************************/

/*
 * Throw a java.lang.IllegalArgumentException if the client calls enqueue() with a null argument.
 * Throw a java.util.NoSuchElementException if the client calls either sample() or dequeue() when the randomized queue is empty.
 * Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
 * Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	public RandomizedQueue() { 		// construct an empty randomized queue

	}

	public boolean isEmpty() { 		// is the randomized queue empty?

	}

	public int size() { 			// return the number of items on the randomized queue

	}

	public void enqueue(Item item) {// add the item
		if(item == null) {
			throw new IllegalArgumentException("can't enqueue unexisting item");
		}
	}

	public Item dequeue() { 		// remove and return a random item
		if(this.size() == 0) {
			throw new NoSuchElementException("queue is empty - nothing to dequeue");
		}
	}

	public Item sample() { 			// return a random item (but do not remove it)
		if(this.size() == 0) {
			throw new NoSuchElementException("queue is empty - nothing to dequeue");
		}
	}

	@Override
	public Iterator<Item> iterator() { // return an iterator over items in order from front to end
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

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