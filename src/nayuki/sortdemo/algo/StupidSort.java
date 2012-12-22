package nayuki.sortdemo.algo;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


/**
 * Sorts by swapping the first adjacent inversion and going back to the beginning, until the array is sorted. The time complexity is in <var>O</var>(<var>n</var><sup>3</sup>). Insertion sort is the optimized form.
 */
public final class StupidSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		array.setInactive(0, array.length());
		int i = 0;
		while (i < array.length() - 1) {
			if (array.compareAndSwap(i, i + 1))
				i = 0;
			else
				i++;
		}
		array.setDone(0, array.length());
	}
	
	
	public String getName() {
		return "Stupid sort";
	}
	
}
