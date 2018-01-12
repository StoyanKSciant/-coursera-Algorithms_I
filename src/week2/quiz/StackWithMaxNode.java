/*******************************************************************************
 * Create a data structure that efficiently supports the stack operations (push
 * and pop) and also a return-the-maximum operation. Assume the elements are
 * real numbers so that you can compare them.
 * 
 *******************************************************************************/

package week2.quiz;

public class StackWithMaxNode {

	private int N;
	private Node first;
	private Node max;

	private class Node {
		double value;
		Node next;
	}

	StackWithMaxNode() {
		this.N = 0;
		this.first = null;
		this.max = null;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public double getMax() {
		return max.value;
	}

	// Add item to top of stack.
	public void push(double element) {
		Node oldfirst = first;
		first = new Node();
		first.value = element;
		first.next = oldfirst;
		N++;
		if (element >= getMax()) {
			Node oldmax = max;
			max = new Node();
			max.next = oldmax;
		}
	}

	// Remove item from top of stack.
	public double pop() {
		double tmp = first.value;
		first = first.next;
		N--;
		if (tmp == getMax()) {
			max = max.next;
		}
		return tmp;
	}
}
