package nayuki.sortdemo.algor;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


/**
 * Sorts by building a binary max-heap, then repeatedly extracting the maximum element and prepending it to the sorted subarray at the end of the array. The time complexity is in <var>O</var>(<var>n</var> log <var>n</var>).
 */
public class HeapSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		// Heapify
		for (int i = array.length() - 1; i >= 0; i--)
			siftDown(array, i, array.length());
		
		// Extract maximum repeatedly
		for (int i = array.length() - 1; i >= 0; i--) {
			array.swap(0, i);
			array.setDone(i);
			siftDown(array, 0, i);
		}
	}
	
	
	public String getName() {
		return "Heap sort";
	}
	
	
	private static void siftDown(SortArray array, int node, int end) {
		while (node * 2 + 1 < end) {
			int child = node * 2 + 1;
			if (child + 1 < end && array.compare(child + 1, child) > 0)
				child++;
			if (!array.compareAndSwap(child, node))
				break;
			node = child;
		}
	}
	
}
