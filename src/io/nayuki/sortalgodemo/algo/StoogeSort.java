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
 * A silly but simple, recursive sorting algorithm.
 * The time complexity is in <var>O</var>(<var>n</var><sup>2.71</sup>).
 */
public final class StoogeSort extends AbstractSortAlgorithm {
	
	// The singleton instance.
	public static final SortAlgorithm INSTANCE = new StoogeSort();
	
	
	public void sort(SortArray array) {
		sort(array, 0, array.length());
		array.setRange(0, array.length(), SortArray.ElementState.DONE);
	}
	
	
	private static void sort(SortArray array, int start, int end) {
		array.setRange(start, end, SortArray.ElementState.ACTIVE);
		array.compareAndSwap(start, end - 1);
		array.setRange(start, end, SortArray.ElementState.INACTIVE);
		int length = end - start;
		if (length < 3)
			return;
		
		int third = length / 3;
		sort(array, start, end - third);
		sort(array, start + third, end);
		sort(array, start, end - third);
	}
	
	
	// Private constructor.
	private StoogeSort() {
		super("Stooge sort");
	}
	
}
