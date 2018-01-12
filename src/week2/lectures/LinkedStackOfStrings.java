/*  Linked-list stack implementation
	1. every operation takes constant time in the worst case.
	2. Uses extra time and space to deal with the links. (slower)
	*/
package week2.lectures;

public class LinkedStackOfStrings {
	
	
	
	private Node first = null;
	
	private class Node{
		String item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void push(String item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
	
	public String pop() {
		String item = first.item;
		first = first.next;
		return item;
	}
}	