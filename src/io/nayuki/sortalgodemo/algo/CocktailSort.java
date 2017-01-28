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


public final class CocktailSort extends AbstractSortAlgorithm {
	
	// The singleton instance.
	public static final SortAlgorithm INSTANCE = new CocktailSort();
	
	
	public void sort(SortArray array) {
		int left = 0;
		int right = array.length();
		int i = left;
		while (left < right) {
			// Scan right
			for (; i + 1 < right; i++)
				array.compareAndSwap(i, i + 1);
			array.setElement(i, SortArray.ElementState.DONE);
			right--;
			i--;
			if (left == right)
				break;
			
			// Scan left
			for (; i > left; i--)
				array.compareAndSwap(i - 1, i);
			array.setElement(i, SortArray.ElementState.DONE);
			left++;
			i++;
		}
	}
	
	
	// Private constructor.
	private CocktailSort() {
		super("Cocktail sort");
	}
	
}
