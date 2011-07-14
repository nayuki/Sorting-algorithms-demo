package nayuki.sortdemo.algor;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


/**
 * Sorts by swapping the first adjacent inversion and going back to the beginning, until the array is sorted. The time complexity is in <var>O</var>(<var>n</var><sup>3</sup>). Insertion sort is the optimized form.
 */
public class StupidSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		array.setInactive(0, array.length());
		for (int i = 1; i < array.length(); i++) {
			if (array.compareAndSwap(i - 1, i))
				i = 0;
		}
		array.setDone(0, array.length());
	}
	
	
	public String getName() {
		return "Stupid sort";
	}
	
}
