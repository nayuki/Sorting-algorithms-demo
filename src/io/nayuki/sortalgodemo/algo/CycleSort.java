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
 * Sorts by finding the final resting place of each element, swapping, and following
 * cycles. The time complexity is in <var>O</var>(<var>n</var><sup>2</sup>).
 */
public final class CycleSort extends AbstractSortAlgorithm {
	
	// The singleton instance.
	public static final SortAlgorithm INSTANCE = new CycleSort();
	
	
	public void sort(SortArray array) {
		int length = array.length();
		boolean[] done = new boolean[length];
		for (int i = 0; i < length; i++) {
			while (true) {
				int target = 0;
				for (int j = 0; j < length; j++) {
					if (array.compare(j, i) < 0)
						target++;
					if (done[i])
						array.setElement(i, SortArray.ElementState.DONE);
					if (done[j])
						array.setElement(j, SortArray.ElementState.DONE);
				}
				done[target] = true;
				if (target == i)
					break;
				array.swap(i, target);
				array.setElement(target, SortArray.ElementState.DONE);
			}
		}
	}
	
	
	// Private constructor.
	private CycleSort() {
		super("Cycle sort");
	}
	
}
