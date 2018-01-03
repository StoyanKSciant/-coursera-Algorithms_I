package week1.questions;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Design an algorithm for the 3-SUM problem that takes time proportional to
 * O(n^2) in the worst case. You may assume that you can sort the N integers in
 * time proportional to n^2 or better.
 */

public class ThreeSum {

	public ArrayList<Integer> data;

	public void createArray() {
		// create array and sort array
		data = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextInt()) {
			data.add(scanner.nextInt());
		}
		Collections.sort(data);
	}

	public void calculate3SumCombinations() {
		for (int i = 0; i < data.size() - 2; ++i) {
			int j = i + 1;
			int k = data.size() - 1;
			
			while(j < k) {
				int sum = data.get(i) + data.get(j) + data.get(k);
				
				if(sum == 0) {
					String result = "array[" +i+"]:" +data.get(i)+ " + array[" +j+ 
							"]:" +data.get(j)+ " + array[" +k+ "]:" +data.get(k) + " = 0";
					System.out.println(result);
				}
				if(sum >= 0) {
					--k;
				} else {
					++j;
				}
			}
		}
	}

	public static void main(String[] args) {
		ThreeSum ts = new ThreeSum();
		ts.createArray();
		ts.calculate3SumCombinations();
	}
}
