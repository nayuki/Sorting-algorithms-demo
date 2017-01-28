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


/**
 * An array that can be sorted and have the sorting process visualized.
 * <p>By design, array elements can only be compared and swapped. It is forbidden to peek
 * at element values, or to move them to and from auxiliary storage. These restrictions
 * ensure that all implemented sorting algorithms are in-place comparison sorts.</p>
 */
public interface SortArray {
	
	// Must be a non-negative, unchanging value.
	public int length();
	
	
	// Compares the values at the two given array indices. Returns a negative number
	// if array[i] < array[j], zero if array[i] == array[j], or a positive number
	// if array[i] > array[j]. Do not assume that this returns only -1, 0, or 1.
	public int compare(int i, int j);
	
	
	// Swaps the values at the two given array indices.
	public void swap(int i, int j);
	
	
	// Compares the values at the two given array indices, swaps if and
	// only if array[i] > array[j], and returns whether a swap occurred.
	public boolean compareAndSwap(int i, int j);
	
	
	// Randomizes the order of all elements in this array.
	public void shuffle();
	
	
	// Sets the element at the given index to the given state.
	public void setElement(int index, ElementState state);
	
	// Sets all elements in the given range [start, end) to the given state.
	public void setRange(int start, int end, ElementState state);
	
	
	
	// The set of all possible states that each array element is in.
	public enum ElementState {
		ACTIVE, INACTIVE, COMPARING, DONE;
	}
	
}
