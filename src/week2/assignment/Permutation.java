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

package week2.assignment;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {

		// takes an integer k as a command-line argument
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<>();

		while (!StdIn.isEmpty()) {
			// reads in a sequence of strings from standard input
			rq.enqueue(StdIn.readString());
		}

		for (int i = 0; i < k; i++) {
			StdOut.println(rq.dequeue());
		}
	}
}