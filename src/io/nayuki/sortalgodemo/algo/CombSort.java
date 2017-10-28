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
 * Sorts by scanning the array linearly, comparing and swapping
 * elements between a gap, and repeating with decreasing gaps.
 * The time complexity is in <var>O</var>(<var>n</var><sup>2</sup>) or better.
 */
public final class CombSort extends AbstractSortAlgorithm {
	
	// The singleton instance.
	public static final SortAlgorithm INSTANCE = new CombSort();
	
	
	public void sort(SortArray array) {
		// Comb sort with exponentially decreasing gaps
		int length = array.length();
		for (int step = length; step > 1; step = step * 10 / 13) {
			for (int i = 0; i + step < length; i++)
				array.compareAndSwap(i, i + step);
		}
		
		// Regular bubble sort (step = 1) to ensure correctness
		int end = length;
		while (true) {
			boolean changed = false;
			for (int i = 0; i + 1 < length; i++)
				changed |= array.compareAndSwap(i, i + 1);
			if (!changed)
				break;
			end--;
			if (end >= 0)
				array.setRange(end, length, SortArray.ElementState.DONE);
		}
		array.setRange(0, length, SortArray.ElementState.DONE);
	}
	
	
	// Private constructor.
	private CombSort() {
		super("Comb sort");
	}
	
}
