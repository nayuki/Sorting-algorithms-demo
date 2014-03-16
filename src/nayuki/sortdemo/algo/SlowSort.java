package nayuki.sortdemo.algo;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


/**
 * A pessimal "multiply and surrender" algorithm with superpolynomial time complexity.
 */
public final class SlowSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		array.setInactive(0, array.length());
		sort(array, 0, array.length(), true);
	}
	
	
	private static void sort(SortArray array, int start, int end, boolean isMain) {
		int length = end - start;
		if (length >= 2) {
			int mid = start + length / 2;
			sort(array, start, mid, false);
			sort(array, mid, end, false);
			array.setActive(start, end);
			array.compareAndSwap(mid - 1, end - 1);
			array.setInactive(start, end);
		}
		if (isMain)
			array.setDone(end - 1);
		if (length >= 2)
			sort(array, start, end - 1, isMain);
	}
	
	
	public String getName() {
		return "Slow sort";
	}
	
}
