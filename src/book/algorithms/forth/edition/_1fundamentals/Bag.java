package book.algorithms.forth.edition._1fundamentals;

/*
 * Implementing our Bag API using a linked-list data structure is
 * simply a matter of changing the name of push() in Stack to add() 
 * and removing the implementation of pop()
 * */

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {

	private Node first; // first node in list

	private class Node {
		Item item;
		Node next;
	}

	public void add(Item item) { // same as push() in Stack
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
		}

		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}