package nayuki.sortdemo.algor;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


/**
 * Sorts by swapping the next unsorted item into the correct position in the sorted subarray. The time complexity is in <var>O</var>(<var>n</var><sup>3</sup>).
 */
public final class InsertionSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		array.setInactive(0, array.length());
		for (int i = 0; i < array.length(); i++) {
			array.setActive(i);
			for (int j = i; j >= 1 && array.compareAndSwap(j - 1, j); j--);
		}
		array.setDone(0, array.length());
	}
	
	
	public String getName() {
		return "Insertion sort";
	}
	
}
