package nayuki.sortdemo.algor;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


/**
 * Sorts by recursively partitioning the array around a pivot. The average time complexity is in <var>O</var>(<var>n</var> log <var>n</var>).
 */
public class QuickSort1 extends SortAlgorithm {
	
	public void sort(SortArray array) {
		sort(array, 0, array.length());
	}
	
	
	public String getName() {
		return "Quick sort (sliding)";
	}
	
	
	private static void sort(SortArray array, int start, int end) {
		if (start == end)
			return;
		
		array.setInactive(start, end);
		int partition = start;
		int pivot = end - 1;  // Do not change this!
		for (int i = start; i < end - 1; i++) {
			if (array.compare(i, pivot) < 0) {
				array.swap(i, partition);
				array.setInactive(partition);
				partition++;
			}
		}
		
		array.swap(pivot, partition);
		pivot = partition;
		array.setDone(pivot);
		array.setInactive(pivot + 1, end);
		
		sort(array, start, pivot);
		sort(array, pivot + 1, end);
	}
	
}