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

package nayuki.sortdemo.algo;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


public final class QuasiPancakeSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		for (int i = 1; i < array.length(); i++) {
			int j = i;
			while (j >= 1 && array.compare(j - 1, i) > 0)
				j--;
			flip(array, j, i);
			flip(array, j, i + 1);
		}
		array.setDone(0, array.length());
	}
	
	
	private static void flip(SortArray array, int start, int end) {
		int half = (end - start) / 2;
		for (int i = 0; i < half; i++)
			array.swap(start + i, end - 1 - i);
	}
	
	
	public String getName() {
		return "Quasi-pancake sort";
	}
	
}
