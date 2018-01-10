package week2.assignment;

// Randomized queue implementation is based on a linked-list data structure

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Draft_LListRanQueue<Item> implements Iterable<Item> {
	
	private Node<Item> first;	// link to least recently added node
	private Node<Item> last; 	// link to most recently added node
	private int size; 			// number of items on the queue
	
	private class Node<Item> {
		private Item item;
		private Node<Item> next;
		
		Node(){
			this.setItem(null);
			this.setNext(null);
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
	}
	
	public Draft_LListRanQueue() { 	// construct an empty randomized queue
		first = new Node<Item>();
		last = new Node<Item>();

		last.setNext(first);
		size = 0;
	}
	
	

	public boolean isEmpty() { 		// is the randomized queue empty?
		if (size == 0) {
			return true;
		}
		return false;
	}

	public int size() { 			// return the number of items on the randomized queue
		return size;
	}

	public void enqueue(Item item) {// add the item
		if(item == null) {
			throw new IllegalArgumentException("can't enqueue unexisting item");
		}
		Node oldlast = last;
		last = new Node();
		last.setItem(item);
		last.setNext(null);
		if (isEmpty()) {
			first = last;
		} else {
			oldlast.setNext(last);
		}
		size++;
	}

	public Item dequeue() { 		// remove and return a random item
		if(size == 0) {
			throw new NoSuchElementException("queue is empty - nothing to dequeue");
		}
		Item item = first.getItem();
		first = first.getNext();
		if (isEmpty())
			last = null;
		size--;
		return item;
	}

	public Item sample() { 			// return a random item (but do not remove it)
		if(size == 0) {
			throw new NoSuchElementException("queue is empty - nothing to dequeue");
		}
		return null;
	}

	@Override
	public Iterator<Item> iterator() { // return an iterator over items in order from front to end
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {
		// implement random current item;
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
			Item item = (Item) current.getItem();
			current = current.getNext();
			return item;
		}
	}
}