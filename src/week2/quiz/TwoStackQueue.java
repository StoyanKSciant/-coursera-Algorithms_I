package week2.quiz;

import java.awt.Frame;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Stack;

/***********************************************************************************
 * Implement a queue with two stacks so that each queue operations takes a constant
 * amortized number of stack operations.
 * 
 * Hint: If you push elements onto a stack and then pop them all, they appear in 
 * reverse order. If you repeat this process, they're now back in order.
 ***********************************************************************************/

public class TwoStackQueue<Item> {
	
	private Stack<Item> first;		// edu.princeton.cs.algs4.Stack
	private Stack<Item> second;
	
	public TwoStackQueue() {
		first = new Stack<Item>();
		second = new Stack<Item>();
	}
	
	public boolean isEmpty(){
		return first.isEmpty() && second.isEmpty();
	}
	
	public int size(){
		return first.size() + second.size();
	}
	
	// pop from first stack, push to second stack
	private void move(){
		while(!first.isEmpty()) {
			Item temp = first.pop();
			second.push(temp);
		}
	}
	
	// push to first stack
	public void enqueue(Item item){
		first.push(item);
	}
	
	// peek at second stack
	public Item peek(){
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		if(second.isEmpty()) {
			move();
		}
		return second.peek();
	}
	
	// pop from second stack
	public Item dequeue(){
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		if(second.isEmpty()) {
			move();
		}
		return second.pop();
	}
}
