package nayuki.sortdemo.algor;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


/**
 * Sorts by recursively partitioning the array around a pivot. The average time complexity is in <var>O</var>(<var>n</var> log <var>n</var>).
 */
public final class QuickSort0 extends SortAlgorithm {
	
	public void sort(SortArray array) {
		sort(array, 0, array.length());
	}
	
	
	public String getName() {
		return "Quick sort (double-ended)";
	}
	
	
	private static void sort(SortArray array, int start, int end) {
		if (start == end)
			return;
		
		array.setActive(start, end);
		int left = start;
		int right = end - 1;
		int pivot = left;  // Do not change this!
		
		while (left != right) {
			// Swap the pivot, which is on the left, with something on the right that needs to go on the left
			while (array.compare(right, pivot) >= 0 && left != right) {
				array.setInactive(right);
				right--;
			}
			if (left != right) {
				array.swap(left, right);
				pivot = right;
				array.setInactive(left);
				left++;
			}
			
			// Swap the pivot, which is on the right, with something on the left that needs to go on the right
			while (array.compare(left, pivot) <= 0 && left != right) {
				array.setInactive(left);
				left++;
			}
			if (left != right) {
				array.swap(right, left);
				pivot = left;
				array.setInactive(right);
				right--;
			}
		}
		
		array.setDone(pivot);
		sort(array, start, pivot);
		sort(array, pivot + 1, end);
	}
	
}
