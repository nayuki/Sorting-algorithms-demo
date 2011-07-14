package nayuki.sortdemo.algor;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


/**
 * Sorts by scanning forward and swapping inverted adjacent elements. The time complexity is in <var>O</var>(<var>n</var><sup>3</sup>).
 */
public final class BubbleSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		for (int i = array.length(); i >= 1; i--) {
			for (int j = 0; j < i - 1; j++) {
				array.compareAndSwap(j, j + 1);
			}
			array.setDone(i - 1);
		}
	}
	
	
	public String getName() {
		return "Bubble sort";
	}
	
}
