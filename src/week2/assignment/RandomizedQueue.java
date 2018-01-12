/********************************************************************************************
 * Write a generic data type for a randomized queue. The goal of this assignment is to 
 * implement elementary data structures using arrays and linked lists, and to introduce you 
 * to generics and iterators. Do not use either java.util.LinkedList or java.util.ArrayList!
 * 
 * Randomized queue: 
 * A randomized queue is similar to a stack or queue, except that the item removed is chosen 
 * uniformly at random from items in the data structure. Create a generic data type 
 * RandomizedQueue that implements the following API:
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
 * Lose points for loitering: Yes.
 ********************************************************************************************/

/*
 * Throw a java.lang.IllegalArgumentException if the client calls enqueue() with a null argument.
 * Throw a java.util.NoSuchElementException if the client calls either sample() or dequeue() when the randomized queue 
 *  is empty.
 * Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no
 *  more items to return.
 * Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator.
 */

/*
 * Randomized queue implementation is based on an auto-resizing array data structure
 * Note: Java prohibits the creation of arrays of generic types. Instead, use a cast.
 * 
 * Item[] a = (Item[]) new Object[1];
 * 
 * Unfortunately, this leads to a compiler warning.
 */

package week2.assignment;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] queue; 				// queue elements
	private int first; 					// index of first element of queue
	private int last; 					// index of next available slot
	private int size; 					// number of items on the queue

	public RandomizedQueue() { 			// construct an empty randomized queue
		queue = (Item[]) new Object[2];
		first = 0;						// used as dummy node
		last = 0;						// used as dummy node
		size = 0;
	}
	

	private class RandomizedQueueIterator implements Iterator<Item> {
		private int current;
		private int initialSize;
	    private int[] order;
	    
	    public RandomizedQueueIterator() {
            initialSize = size;
            current = 0;
            order = new int[size];
            for (int i = 0; i < size; i++) {
                order[i] = i;
            }
            StdRandom.shuffle(order);	// order keys at random
        }
	    
		@Override
		public boolean hasNext() {
			return current < size || size == 0;
		}

		@Override
		public void remove() { 			// doesn't support remove()
			throw new UnsupportedOperationException("remove() method is not supported");
		}

		@Override
		public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = queue[order[current]];
            if (size() != initialSize) {
                throw new java.util.ConcurrentModificationException();
            }
            current++;
            return item;
		}
	}
	
	public boolean isEmpty() { 			// is the randomized queue empty?
		return size == 0;
	}

	public int size() { 				// return the number of items on the randomized queue
		return size;
	}

	private void resizeQueue(int capacity) { // resize the underlying array
		if (capacity >= size) {
			Item[] temp = (Item[]) new Object[capacity];
			for (int i = 0; i < size; i++) {
				temp[i] = queue[(first + i) % queue.length];
			}
			queue = temp;
			first = 0;
			last = size;
		}
	}

	public void enqueue(Item item) {	// add the item
		if (item == null) {
			throw new IllegalArgumentException("can't enqueue unexisting item");
		}
		if (size == queue.length) {		// double size of array if necessary
			resizeQueue(2 * queue.length);	
		}
		queue[last++] = item; 			// add item
		if (last == queue.length) {
			last = 0; 				  	// wrap-around
		}
		size++;							// increase size
	}

	public Item dequeue() { 			// remove and return a random item
		if (isEmpty()) {
			throw new NoSuchElementException("queue is empty - nothing to dequeue");
		}
		
		int randomIndex = StdRandom.uniform(size); //dequeue operation must be at random choosing
		Item item = queue[randomIndex];
		queue[randomIndex] = queue[first]; // to avoid loitering
		queue[first] = null;
		size--;
		first++;
		if (first == queue.length) {
			first = 0;          		 // wrap-around
		}
		if (size > 0 && size == queue.length / 4) // shrink size of array if necessary
			resizeQueue(queue.length / 2);
		return item;
	}

	public Item sample() { 				// return a random item (but do not remove it)
		if (size == 0) {
			throw new NoSuchElementException("queue is empty - nothing to dequeue");
		}
		return queue[StdRandom.uniform(0, size)];
	}

	@Override
	public Iterator<Item> iterator() { // return an independent iterator over items in random order
		return new RandomizedQueueIterator();
	}
}