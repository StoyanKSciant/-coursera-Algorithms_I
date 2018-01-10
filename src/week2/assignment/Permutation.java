package week2.assignment;

/**************************************************************************************
 * Write a client program Permutation.java that:
 * 1. Takes an integer k as a command-line argument;
 * 2. Reads in a sequence of strings from standard input using StdIn.readString();
 * 3. Prints exactly k of them, uniformly at random. Print each item from the sequence 
 * at most once.
 * 
 * Command-line input:
 * You may assume that 0 ≤ k ≤ n, where n is the number of string on standard input.
 * 
 * Performance requirements:
 * The running time of Permutation must be linear in the size of the input. You may use
 * only a constant amount of memory plus either one Deque or RandomizedQueue object of
 * maximum size at most n. (For an extra challenge, use only one Deque or 
 * RandomizedQueue object of maximum size at most .
 **************************************************************************************/

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	private String elements[];
	private RandomizedQueue<String> q;

	private void runPerm(int k, String input) {

		if (k == 0 || input == null || input.isEmpty()) {
			throw new IllegalArgumentException("wrong input");
		}

		elements = input.split(" ");
		q = new RandomizedQueue<String>();
		
		for (int i = 0; i < elements.length; i++) {
			q.enqueue(elements[i]); // populate randomized queue
		}
		for (int j = 0; j < k; j++) {
			// with dequeue we make sure that items get printed only once
			System.out.println(q.dequeue()); // print k times random elements
		}
	}

	public static void main(String[] args) {

		// takes an integer k as a command-line argument
		int k = Integer.parseInt(args[0]);
		// reads in a sequence of strings from standard input
		String input = StdIn.readString();

		Permutation perm = new Permutation();
		perm.runPerm(k, input);
	}
}