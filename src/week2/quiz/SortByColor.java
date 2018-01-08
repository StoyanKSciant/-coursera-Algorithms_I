package week2.quiz;

/***********************************************************************************************
 * Given an array of n buckets, each containing a red, white, or blue pebble, sort them by the
 * Holland flag colors order: RED/WHITE/BLUE;
 * 
 * The allowed operations are:
	- swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
	- color(i): determine the color of the pebble in bucket i.
	
 * The performance requirements are as follows:
	- At most n calls to color().
	- At most n calls to swap().
	 -Constant extra space.
 ***********************************************************************************************/



public class SortByColor {
	
    public void sort(Character[] flags) {
        
        int red = 0;					// red at start
        int blue = flags.length - 1;	// blue at end
        int i = 0;
        while (i <= blue) {
            if (flags[i] == 'r') {
                swap(flags, red++, i++);
            } else  if (flags[i] == 'b') {
                swap(flags, blue--, i);
                //we don't increase i, since after swap, the flags[i] has not been checked.
            } else {
                i ++;
            }
        }
    }
    
    public void swap(Character[] t, int i, int j) {
        Character tmp = t[i];
        t[i] = t[j];
        t[j] = tmp;
    }
    
    public static void main(String[] args) {
    	SortByColor client = new SortByColor();
    	// random color array
        Character[] t = {'b', 'r', 'w', 'w', 'r', 'b', 'r'};
        // sorted array
        client.sort(t);
        // print result
        for (char c : t) {
            System.out.print(c);
        }
        System.out.println();
    }
}
