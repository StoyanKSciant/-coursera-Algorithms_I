package week2.assignment;

/*********************************************************************************************
 * Write a generic data type for a deque. The goal of this assignment is to implement 
 * elementary data structures using arrays and linked lists, and to introduce you to generics
 * and iterators. Do not use either java.util.LinkedList or java.util.ArrayList!
 * 
 * Deque: 
 * A double-ended queue or deque (pronounced "deck") is a generalization of a stack and a 
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
 *********************************************************************************************/

/*
 * Throw a java.lang.IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument. 
 * Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is
 *  empty. 
 * Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no
 *  more items to return. 
 * Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator.
 */

//This implementation of deque uses double linked list as its data structure

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private int size;			// number of items on the deque
	private Node<Item> first;	// link to most recently added node
	private Node<Item> last;	// link to least recently added node

	public Deque() { // construct an empty deque
		first = null;
		last = null;
		size = 0;
	}

	private class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> prev;
	}

	private class DequeIterator implements Iterator<Item> {
		private Node<Item> current = first;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}
		
		@Override
		public void remove() {	// doesn't support remove()
			throw new UnsupportedOperationException("remove() method is not supported");
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("There are no more items left");
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
	public boolean isEmpty() { // is the deque empty?
		return size == 0;
	}

	public int size() { // return the number of items on the deque
		return size;
	}

	public void addFirst(Item item) { // add the item to the front
		if (item == null) {
			throw new IllegalArgumentException("Can't add unexisting item");
		}
		if (isEmpty()) {
			first = new Node<Item>();
			first.item = item;
			last = first;
		} else {
			Node<Item> oldfirst = first;
			first = new Node<Item>();
			first.item = item;
			first.next = oldfirst;
			oldfirst.prev = first;
		}
		size++;
	}

	public void addLast(Item item) {	// add the item to the end
		if (item == null) {
			throw new IllegalArgumentException("Can't add unexisting item");
		}
		Node<Item> newLast = new Node<Item>();
		newLast.item = item;
		if (last != null) {
			newLast.prev = last;
			last.next = newLast;
		}
		last = newLast;
		if (first == null) {
			first = last;
		}
		size++;
	}
	
	public Item removeFirst() { 		// remove and return the item from the front
		if (isEmpty()) {
			throw new NoSuchElementException("Can't remove item form empty queue");
		}
		Item remFirstItem = first.item;
		if(size == 1) {
			last = null;
			first = null;
		} else {
			first.next.prev = null; 	// there are no nodes after first so first.next = null	
			first = first.next;			// point to second item as the new first
		}
		size--;
		return remFirstItem;
	}

	public Item removeLast() { // remove and return the item from the end
		if (isEmpty()) {
			throw new NoSuchElementException("Can't remove item form empty queue");
		}
		Item remLastItem = last.item;
		if(last.prev == null) {
			last = null;
			first = null;
		} else {
			last.prev.next = null; 	// there are no nodes after tail so tail.previous = null
			last = last.prev; 		// point to second-to-last as the new tail Node
		}
		size--;
		return remLastItem	;
	}

	@Override
	public Iterator<Item> iterator() { // return an iterator over items in order from front to end
		return new DequeIterator();
	}
}