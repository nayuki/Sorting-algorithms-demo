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
 * <p>By design, array elements can only be compared and swapped. It is
 * forbidden to peek at or copy element values. These restrictions ensure
 * that all implemented sorting algorithms are in-place comparison sorts.</p>
 */
public interface SortArray {
	
	/**
	 * The length of this array, which is a non-negative, unchanging value.
	 * @return the length of this array
	 */
	public int length();
	
	
	/**
	 * Compares the values of this array at the two specified indexes.
	 * Returns a negative number if array[i] &lt; array[j], zero if array[i] == array[j], or a positive number
	 * if array[i] > array[j]. Do not assume that this returns only &minus;1, 0, or 1.
	 * This follows the behavior of {@link java.lang.Comparable#compareTo(Object) Comparable.compareTo(T)}.
	 * @param i an index for comparing
	 * @param j an index for comparing
	 * @return a negative number, zero, or a positive number depending on how array[i] compares to array[j]
	 * @throws IndexOutOfBoundsException if not (0 &le; {@code i}, {@code j} &lt; {@code length()})
	 */
	public int compare(int i, int j);
	
	
	/**
	 * Swaps the values of this array at the two specified indexes.
	 * The two indexes can be the same.
	 * @param i an index for swapping
	 * @param j an index for swapping
	 * @throws IndexOutOfBoundsException if not (0 &le; {@code i}, {@code j} &lt; {@code length()})
	 */
	public void swap(int i, int j);
	
	
	/**
	 * Compares the values of this array at the two given indexes, then swaps
	 * if and only if array[i] > array[j], and returns whether a swap occurred.
	 * This is equivalent to:
	 * <pre>if (array.compare(i, j) > 0) {
	 *    array.swap(i, j);
	 *    return true;
	 *} else
	 *    return false;
	 *}</pre>
	 * @param i an index for comparing and swapping
	 * @param j an index for comparing and swapping
	 * @return whether initially array[i] > array[j], which causes them to be swapped
	 * @throws IndexOutOfBoundsException if not (0 &le; {@code i}, {@code j} &lt; {@code length()})
	 */
	public default boolean compareAndSwap(int i, int j) {
		if (compare(i, j) > 0) {
			swap(i, j);
			return true;
		} else
			return false;
	}
	
	
	/**
	 * Randomizes the order of all elements in this array.
	 */
	public default void shuffle() {
		// Durstenfeld shuffle algorithm
		for (int i = length() - 1; i > 0; i--)
			swap(i, random.nextInt(i + 1));
	}
	
	
	/**
	 * Sets the element at the specified index to the given state.
	 * This may trigger a visual change or do nothing.
	 * @param index the index to set
	 * @param state the element state (not {@code null})
	 * @throws NullPointerException if {@code state} is {@code null}
	 * @throws IndexOutOfBoundsException if not (0 &le; {@code index} &lt; {@code length()})
	 */
	public void setElement(int index, ElementState state);
	
	
	/**
	 * Sets all elements in the specified range [{@code start}, {@code end}) to the specified state.
	 * This may trigger a visual change or do nothing.
	 * @param start the start index of the range (inclusive)
	 * @param end the end index of the range (exclusive)
	 * @param state the element state (not {@code null})
	 * @throws NullPointerException if {@code state} is {@code null}
	 * @throws IndexOutOfBoundsException if not (0 &le; {@code start} &le; {@code end} &lt; {@code length()})
	 */
	public void setRange(int start, int end, ElementState state);
	
	
	
	public static final Random random = new Random();
	
	
	
	/**
	 * All the possible states that each array element is in.
	 */
	public enum ElementState {
		ACTIVE, INACTIVE, COMPARING, DONE;
	}
	
}
