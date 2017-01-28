/* 
 * Sorting algorithms demo (Java)
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/sorting-algorithms-demo-java
 * 
 * (MIT License)
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * - The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 * - The Software is provided "as is", without warranty of any kind, express or
 *   implied, including but not limited to the warranties of merchantability,
 *   fitness for a particular purpose and noninfringement. In no event shall the
 *   authors or copyright holders be liable for any claim, damages or other
 *   liability, whether in an action of contract, tort or otherwise, arising from,
 *   out of or in connection with the Software or the use or other dealings in the
 *   Software.
 */

package io.nayuki.sortalgodemo.algo;

import io.nayuki.sortalgodemo.core.AbstractSortAlgorithm;
import io.nayuki.sortalgodemo.core.SortAlgorithm;
import io.nayuki.sortalgodemo.core.SortArray;


/**
 * Sorts by building a binary max-heap, then repeatedly extracting the maximum element
 * and prepending it to the sorted subarray at the end of the array.
 * The time complexity is in <var>O</var>(<var>n</var> log <var>n</var>).
 */
public final class HeapSort extends AbstractSortAlgorithm {
	
	// The singleton instance.
	public static final SortAlgorithm INSTANCE = new HeapSort();
	
	
	public void sort(SortArray array) {
		// Heapify
		int length = array.length();
		array.setRange(0, length, SortArray.ElementState.INACTIVE);
		for (int i = length - 1; i >= 0; i--)
			siftDown(array, i, length);
		
		// Extract maximum repeatedly
		for (int i = length - 1; i >= 0; i--) {
			array.swap(0, i);
			array.setElement(i, SortArray.ElementState.DONE);
			siftDown(array, 0, i);
		}
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
	
	
	// Private constructor.
	private HeapSort() {
		super("Heap sort");
	}
	
}
