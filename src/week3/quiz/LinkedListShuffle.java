/***********************************************************************
 * Shuffling a linked list. 
 * Given a singly-linked list containing n items, rearrange the items 
 * uniformly at random. Your algorithm should consume a logarithmic (or 
 * constant) amount of extra memory and run in time proportional to 
 * nlogn in the worst case.
 * 
 * Hint: design a linear-time subroutine that can take two uniformly 
 * shuffled linked lists of sizes n1 and n2 and combined them into a 
 * uniformly shuffled linked lists of size n1+n2.
 ***********************************************************************/

package week3.quiz;

import edu.princeton.cs.algs4.StdRandom;


/*
	Efficient Solution O(n):
	1) Find the middle point using tortoise and hare method.
	2) Split the linked list in two halves using found middle point in step 1.
	3) Reverse the second half.
	4) Do alternate merge of first and second halves. 
*/

public class LinkedListShuffle<Item> {

    static Node head;  // head of list
	
	private static class Node {
		Object data;
		Node next;
		
		Node(Object data) {
            this.data = data;
            next = null;
        }
	}

	void printLinkedList(Node node) {
        if (node == null) {
            return;
        }
        while (node != null) {
            System.out.print("|"+node.data+"|");
            node = node.next;
        }
    }
	
	Node reverselist(Node node) {
	    Node prev = null, curr = node, next;
	    while (curr != null) {
	        next = curr.next;
	        curr.next = prev;
	        prev = curr;
	        curr = next;
	    }
	    node = prev;
	    return node;
    }
	
	void rearrange(Node node) {
		 
		// 1) Find the middle point using tortoise and hare method 
		Node slow = node;
		Node fast = slow.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		// 2) Split the linked list in two halves
		// node1, head of first half    1 -> 2 -> 3 -> 4 -> 5
		// node2, head of second half	6 -> 7 -> 8 -> 9 -> 10
		Node node1 = node;
		Node node2 = slow.next;		// found middle
		slow.next = null;

		// 3) Reverse the second half, i.e., 10 -> 9 -> 8 -> 7 -> 6
		node2 = reverselist(node2);

		// 4) Merge alternate nodes
		node = new Node(0); // Assign dummy Node

		// curr is the pointer to this dummy Node, which will
		// be used to form the new list
		Node curr = node;
		while (node1 != null || node2 != null) {
			
			// First add the element from first list
			if (node1 != null) {
				curr.next = node1;
				curr = curr.next;
				node1 = node1.next;
			}
			
			// Then add the element from second list
			if (node2 != null) {
			curr.next = node2;
			curr = curr.next;
			node2 = node2.next;
			}
		}

		// Assign the head of the new list to head pointer
		node = node.next;
    }
	
	public void populateReculsive(int max, Node head) {
		if(max > 0) {
			head.next = new Node(max);
			int leftIntegers = max - 1;
			populateReculsive(leftIntegers, head.next);
		}
	}

    public static void main(String[] args) {
		
		LinkedListShuffle listShuffle = new LinkedListShuffle();
		listShuffle.head = new Node("Head");
		listShuffle.populateReculsive(10, head);
		listShuffle.printLinkedList(head); // print original list
		listShuffle.rearrange(head); // rearrange list as per ques
		System.out.println("");
		listShuffle.printLinkedList(head); // print modified list
    }
}
