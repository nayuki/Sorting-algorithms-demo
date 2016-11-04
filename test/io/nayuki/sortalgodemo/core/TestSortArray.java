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

package io.nayuki.sortalgodemo.core;

import org.junit.Assert;
import io.nayuki.sortalgodemo.core.SortArray;


// An array for test purposes, not supporting graphics.
final class TestSortArray extends SortArray {
	
	/* Initialization */
	
	public TestSortArray(int size) {
		super(size);
	}
	
	public void reverse() {
		for (int i = 0; i < values.length / 2; i++)
			swap(i, values.length - 1 - i);
	}
	
	
	/* Test */
	
	public void assertSorted() {
		for (int i = 0; i < values.length - 1; i++)
			Assert.assertTrue(values[i] <= values[i + 1]);
	}
	
	
	/* Ignore graphics visualization calls */
	
	public void setActive(int index) {}
	public void setInactive(int index) {}
	public void setDone(int index) {}
	
	public void setActive(int start, int end) {}
	public void setInactive(int start, int end) {}
	public void setDone(int start, int end) {}
	
}
