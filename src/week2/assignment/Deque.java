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
	private Node<Item> head;	// link to most recently added node
	private Node<Item> tail;	// link to least recently added node

	public Deque() { // construct an empty deque
		head = new Node<Item>();
		tail = new Node<Item>();

		head.setPrevious(tail);
		tail.setNext(head);
		size = 0;
	}

	private class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> previous;
		
		Node(){
			this.setItem(null);
			this.setNext(null);
			this.setPrevious(null);
		}

		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}

		public Node<Item> getNext() {
			return next;
		}

		public void setNext(Node<Item> next) {
			this.next = next;
		}
		
		public Node<Item> getPrevious() {
			return previous;
		}

		public void setPrevious(Node<Item> previous) {
			this.previous = previous;
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
		if (this.isEmpty()) {
			head.setItem(item);
		} else {
			Node<Item> oldHead = head;
			head.setItem(item);
			head.setNext(null); 	// there are no nodes before head so head.next = null;
			head.setPrevious(oldHead);
			oldHead.setNext(head);
		}
		size++;
	}

	public Item removeFirst() { 	// remove and return the item from the front
		if (this.isEmpty()) {
			throw new NoSuchElementException("Can't remove item form empty queue");
		}
		Item removedHeadValue = (Item) head.getItem();
		head = head.getPrevious(); 	// point to second item as the new head
		head.setNext(null);			// there are no nodes after head so head.next = null;
		size--;
		return removedHeadValue;
	}

	public void addLast(Item item) {// add the item to the end
		if (item == null) {
			throw new IllegalArgumentException("Can't add unexisting item");
		}
		if (this.isEmpty()) {
			head.setItem(item);
		} else {
			Node<Item> oldTail = tail;
			tail.setItem(item);
			tail.setNext(oldTail);
			oldTail.setPrevious(tail);
			tail.setPrevious(null); // there are no nodes after tail so tail.previous = null;
		}
		size++;
	}

	public Item removeLast() { // remove and return the item from the end
		if (this.size() == 0) {
			throw new NoSuchElementException("Can't remove item form empty queue");
		}
		Item item = (Item) tail.getItem();
		tail = tail.getPrevious(); // point to second-to-last as the new tail Node
		tail.setNext(null); // there are no nodes after tail so tail.previous = null
		return item;
	}

	@Override
	public Iterator<Item> iterator() { // return an iterator over items in order from front to end
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node<Item> current = head;
		
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
			Item item = (Item) current.getItem();
			current = current.next;
			return item;
		}
	}
}