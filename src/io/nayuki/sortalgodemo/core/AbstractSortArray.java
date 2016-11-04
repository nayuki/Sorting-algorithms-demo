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

import java.util.Random;


/**
 * An array that can be sorted and have the sorting process visualized.
 */
public abstract class AbstractSortArray implements SortArray {
	
	protected int[] values;
	
	
	
	public AbstractSortArray(int size) {
		// Initialize in order: [0, 1, 2, ..., size-1]
		values = new int[size];
		for (int i = 0; i < values.length; i++)
			values[i] = i;
	}
	
	
	
	/* Field getters */
	
	public int length() {
		return values.length;
	}
	
	
	/* Comparison and swapping */
	
	// Compares the values at the two given array indices.
	// Returns a negative number if array[i] < array[j], zero if array[i] == array[j], or a positive number if array[i] > array[j].
	// Do not assume that this returns only -1, 0, or 1.
	public int compare(int i, int j) {
		if (values[i] < values[j])
			return -1;
		else if (values[i] > values[j])
			return 1;
		else
			return 0;
	}
	
	
	// Swaps the values at the two given array indices.
	public void swap(int i, int j) {
		int temp = values[i];
		values[i] = values[j];
		values[j] = temp;
	}
	
	
	// Compares the values at the two given array indices, swaps if and only if array[i] > array[j], and returns whether a swap occurred.
	public boolean compareAndSwap(int i, int j) {
		if (compare(j, i) < 0) {
			swap(i, j);
			return true;
		} else
			return false;
	}
	
	
	public void shuffle() {
		// Fisher-Yates / Knuth shuffle
		for (int i = length() - 1; i >= 0; i--)
			swap(i, random.nextInt(i + 1));
	}
	
	
	/* Sorting progress visualization */
	
	public abstract void setActive(int index);
	public abstract void setInactive(int index);
	public abstract void setDone(int index);
	
	public abstract void setActive(int start, int end);
	public abstract void setInactive(int start, int end);
	public abstract void setDone(int start, int end);
	
	
	public static final Random random = new Random();
	
}
