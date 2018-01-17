/***************************************************************************
 * Nuts and bolts: 
 * A disorganized carpenter has a mixed pile of n nuts and n bolts. The goal
 * is to find the corresponding pairs of nuts and bolts. Each nut fits 
 * exactly one bolt and each bolt fits exactly one nut. By fitting a nut and
 * a bolt together, the carpenter can see which one is bigger (but the 
 * carpenter cannot compare two nuts or two bolts directly). Design an 
 * algorithm for the problem that uses nlogn compares (probabilistically).
 * 
 * Hint: modify the quicksort partitioning part of quicksort.
 ***************************************************************************/

package week3.quiz;

public class NutsAndBolts {

	// Driver method
	public static void main(String[] args) {
		// Nuts and bolts are represented as array of characters
		char nuts[] = { '@', '#', '$', '%', '^', '&' };
		char bolts[] = { '$', '%', '&', '^', '@', '#' };

		// Method based on quick sort which matches nuts and bolts
		matchPairs(nuts, bolts, 0, 5);

		System.out.println("Matched nuts and bolts are : ");
		printArray(nuts);
		printArray(bolts);
	}

	// Method to print the array
	private static void printArray(char[] arr) {
		for (char ch : arr) {
			System.out.print(ch + " ");
		}
		System.out.print("n");
	}

	// Method which works just like quick sort
	private static void matchPairs(char[] nuts, char[] bolts, int low, int high) {
		if (low < high) {
			// Choose last character of bolts array for nuts partition.
			int pivot = partition(nuts, low, high, bolts[high]);

			// Now using the partition of nuts choose that for bolts
			// partition.
			partition(bolts, low, high, nuts[pivot]);

			// Recur for [low...pivot-1] & [pivot+1...high] for nuts and
			// bolts array.
			matchPairs(nuts, bolts, low, pivot - 1);
			matchPairs(nuts, bolts, pivot + 1, high);
		}
	}

	// Similar to standard partition method. Here we pass the pivot element
	// too instead of choosing it inside the method.
	private static int partition(char[] arr, int low, int high, char pivot) {
		int i = low;
		char temp1, temp2;
		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				temp1 = arr[i];
				arr[i] = arr[j];
				arr[j] = temp1;
				i++;
			} else if (arr[j] == pivot) {
				temp1 = arr[j];
				arr[j] = arr[high];
				arr[high] = temp1;
				j--;
			}
		}
		temp2 = arr[i];
		arr[i] = arr[high];
		arr[high] = temp2;

		// Return the partition index of an array based on the pivot
		// element of other array.
		return i;
	}
}