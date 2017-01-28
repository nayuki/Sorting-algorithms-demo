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
 * Sorts by recursively partitioning the array around a pivot.
 * The average time complexity is in <var>O</var>(<var>n</var> log <var>n</var>).
 */
public final class QuickSortDoubleEnded extends AbstractSortAlgorithm {
	
	// The singleton instance.
	public static final SortAlgorithm INSTANCE = new QuickSortDoubleEnded();
	
	
	public void sort(SortArray array) {
		sort(array, 0, array.length());
	}
	
	
	private static void sort(SortArray array, int start, int end) {
		if (start == end)
			return;
		
		array.setRange(start, end, SortArray.ElementState.ACTIVE);
		int left = start;
		int right = end - 1;
		int pivot = left;  // Do not change this!
		
		while (left != right) {
			// Swap the pivot, which is on the left, with something on the right that needs to go on the left
			while (array.compare(right, pivot) >= 0 && left != right) {
				array.setElement(right, SortArray.ElementState.INACTIVE);
				right--;
			}
			if (left != right) {
				array.swap(left, right);
				pivot = right;
				array.setElement(left, SortArray.ElementState.INACTIVE);
				left++;
			}
			
			// Swap the pivot, which is on the right, with something on the left that needs to go on the right
			while (array.compare(left, pivot) <= 0 && left != right) {
				array.setElement(left, SortArray.ElementState.INACTIVE);
				left++;
			}
			if (left != right) {
				array.swap(right, left);
				pivot = left;
				array.setElement(right, SortArray.ElementState.INACTIVE);
				right--;
			}
		}
		
		array.setElement(pivot, SortArray.ElementState.DONE);
		sort(array, start, pivot);
		sort(array, pivot + 1, end);
	}
	
	
	// Private constructor.
	private QuickSortDoubleEnded() {
		super("Quick sort (double-ended)");
	}
	
}
