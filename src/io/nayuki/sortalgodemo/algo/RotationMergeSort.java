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
import io.nayuki.sortalgodemo.core.SortArray.ElementState;


/**
 * An in-place stable merge sort based on rotations.
 * The time complexity is in <var>O</var>(<var>n</var><sup>2</sup>).
 * See: https://xinok.wordpress.com/2014/08/17/in-place-merge-sort-demystified-2/
 */
public final class RotationMergeSort extends AbstractSortAlgorithm {
	
	// The singleton instance.
	public static final SortAlgorithm INSTANCE = new RotationMergeSort();
	
	
	public void sort(SortArray array) {
		sort(array, 0, array.length());
		array.setRange(0, array.length(), ElementState.DONE);
	}
	
	
	// Sorts the subrange of the array [start, end).
	private static void sort(SortArray array, int start, int end) {
		if (!(0 <= start && start <= end && end <= array.length()))
			throw new IllegalArgumentException();
		if (end - start <= 1)
			return;
		
		array.setRange(start, end, ElementState.ACTIVE);
		int mid = (start + end) / 2;
		array.setRange(mid, end, ElementState.INACTIVE);
		sort(array, start, mid);
		array.setRange(start, mid, ElementState.INACTIVE);
		sort(array, mid, end);
		merge(array, start, mid, end);
	}
	
	
	// Assuming that the subranges [start, mid) and [mid, end) are each sorted, this yields [start, end) being sorted.
	private static void merge(SortArray array, int start, int mid, int end) {
		if (!(0 <= start && start <= mid && mid <= end && end <= array.length()))
			throw new IllegalArgumentException();
		if (start == mid || mid == end)
			return;
		array.setRange(start, end, ElementState.ACTIVE);
		
		int left = mid - 1;
		int right = mid;
		while (start <= left && right < end && array.compare(left, right) > 0) {
			left--;
			right++;
		}
		
		int n = right - mid;
		for (int i = 0; i < n; i++)
			array.swap(mid - n + i, mid + i);
		
		array.setRange(mid, end, ElementState.INACTIVE);
		merge(array, start, left + 1, mid);
		array.setRange(start, mid, ElementState.INACTIVE);
		merge(array, mid, right, end);
	}
	
	
	// Private constructor.
	private RotationMergeSort() {
		super("Rotation merge sort");
	}
	
}
