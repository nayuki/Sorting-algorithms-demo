package nayuki.sortdemo.algo;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;
import nayuki.sortdemo.Utils;


/**
 * Sorts by randomly selecting two elements and swapping them if they are in inverted order, until the array is sorted. The time complexity is in <var>O</var>(<var>n</var><sup>3</sup> log <var>n</var>).
 */
public final class BozoSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		while (!isSorted(array)) {
			int i = Utils.random.nextInt(array.length());
			int j = Utils.random.nextInt(array.length());
			array.compareAndSwap(Math.min(i, j), Math.max(i, j));
		}
		array.setDone(0, array.length());
	}
	
	
	private boolean isSorted(SortArray array) {
		for (int i = 0; i < array.length() - 1; i++) {
			if (array.compare(i, i + 1) > 0)
				return false;
		}
		return true;
	}
	
	
	public String getName() {
		return "Bozo sort";
	}
	
}
