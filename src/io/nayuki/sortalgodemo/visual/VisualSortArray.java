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

package io.nayuki.sortalgodemo.visual;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import io.nayuki.sortalgodemo.core.SortArray;


/**
 * An array to be sorted. Elements can be compared and swapped, but their values cannot be accessed directly.
 */
final class VisualSortArray implements SortArray {
	
	/*---- Fields ----*/
	
	private boolean isInitialized = false;
	
	private AtomicIntegerArray values;
	private AtomicReferenceArray<ElementState> states;
	private volatile boolean isDone;
	
	// Statistics
	private AtomicLong comparisonCount = new AtomicLong();
	private AtomicLong swapCount = new AtomicLong();
	
	// Speed regulation
	private final double stepsPerSecond;
	private final int stepsPerCheck;
	private int stepsRemaining;
	private long prevCheckTimeNs;
	
	private static final long CHECK_INTERVAL_NS = 10_000_000;
	private static final long BUSY_WAIT_THRESHOLD_NS = 1_000_000;
	
	
	
	/*---- Constructors ----*/
	
	public VisualSortArray(int size, double speed) {
		if (speed <= 0 || Double.isInfinite(speed) || Double.isNaN(speed))
			throw new IllegalArgumentException();
		stepsPerSecond = speed;
		stepsPerCheck = (int)Math.max(Math.min(stepsPerSecond * CHECK_INTERVAL_NS / 1e9, 1_000_000), 1);
		
		if (size <= 0)
			throw new IllegalArgumentException();
		values = new AtomicIntegerArray(size);
		for (int i = 0; i < values.length(); i++)
			values.set(i, i);
		
		states = new AtomicReferenceArray<>(size);
		setRange(0, size, ElementState.ACTIVE);
	}
	
	
	public void finishInitialization() {
		if (isInitialized)
			throw new IllegalStateException();
		isInitialized = true;
		
		isDone = false;
		comparisonCount.set(0);
		swapCount.set(0);
		
		stepsRemaining = stepsPerCheck;
		prevCheckTimeNs = System.nanoTime();
	}
	
	
	
	/*---- Methods ----*/
	
	@Override public int length() {
		return values.length();
	}
	
	
	/* Comparison and swapping */
	
	@Override public int compare(int i, int j) {
		if (!isInitialized)
			throw new IllegalStateException();
		
		setElementInternal(i, ElementState.COMPARING);
		setElementInternal(j, ElementState.COMPARING);
		regulateSpeed();
		
		setElementInternal(i, ElementState.ACTIVE);
		setElementInternal(j, ElementState.ACTIVE);
		comparisonCount.setOpaque(comparisonCount.getPlain() + 1);
		return Integer.compare(values.getPlain(i), values.getPlain(j));
	}
	
	
	@Override public void swap(int i, int j) {
		int x = values.getPlain(i);
		int y = values.getPlain(j);
		values.setOpaque(i, y);
		values.setOpaque(j, x);
		if (!isInitialized)
			return;
		
		setElementInternal(i, ElementState.ACTIVE);
		setElementInternal(j, ElementState.ACTIVE);
		swapCount.setOpaque(swapCount.getPlain() + 1);
		regulateSpeed();
	}
	
	
	@Override public boolean compareAndSwap(int i, int j) {
		if (!isInitialized)
			throw new IllegalStateException();
		
		setElementInternal(i, ElementState.COMPARING);
		setElementInternal(j, ElementState.COMPARING);
		regulateSpeed();
		
		setElementInternal(i, ElementState.ACTIVE);
		setElementInternal(j, ElementState.ACTIVE);
		comparisonCount.setOpaque(comparisonCount.getPlain() + 1);
		int x = values.getPlain(i);
		int y = values.getPlain(j);
		if (x > y) {
			values.setOpaque(i, y);
			values.setOpaque(j, x);
			swapCount.setOpaque(swapCount.getPlain() + 1);
			regulateSpeed();
			return true;
		} else
			return false;
	}
	
	
	/* Array visualization */
	
	@Override public void setElement(int index, ElementState state) {
		setElementInternal(index, Objects.requireNonNull(state));
	}
	
	
	@Override public void setRange(int start, int end, ElementState state) {
		Objects.requireNonNull(state);
		for (int i = start; i < end; i++)
			setElementInternal(i, state);
	}
	
	
	private void setElementInternal(int index, ElementState state) {
		states.setOpaque(index, state);
	}
	
	
	public int getValue(int index) {
		return values.getOpaque(index);
	}
	
	
	public ElementState getState(int index) {
		return states.getOpaque(index);
	}
	
	
	/* After sorting */
	
	public long getComparisonCount() {
		return comparisonCount.getOpaque();
	}
	
	
	public long getSwapCount() {
		return swapCount.getOpaque();
	}
	
	
	// Checks if the array is sorted. Returns silently if so, throws AssertionError if not.
	public void finishSorting() {
		if (isDone)
			throw new IllegalStateException();
		for (int i = 1; i < values.length(); i++) {
			if (values.getPlain(i - 1) > values.getPlain(i))
				throw new AssertionError();
		}
		isDone = true;
	}
	
	
	public boolean isDone() {
		return isDone;
	}
	
	
	private void regulateSpeed() {
		stepsRemaining--;
		if (stepsRemaining > 0)
			return;
		
		if (Thread.interrupted())
			throw new StopException();
		
		long elapsedTime = System.nanoTime() - prevCheckTimeNs;
		double targetTime = stepsPerCheck * 1e9 / stepsPerSecond;
		if (elapsedTime < targetTime) {
			double toSleepNs = targetTime - elapsedTime - BUSY_WAIT_THRESHOLD_NS;
			if (toSleepNs > 0) {
				try {
					Thread.sleep((long)(toSleepNs / 1e6));
				} catch (InterruptedException e) {
					throw new StopException();
				}
			}
			while (System.nanoTime() - prevCheckTimeNs < targetTime);  // Busy-wait
		}
		
		stepsRemaining = stepsPerCheck;
		prevCheckTimeNs = System.nanoTime();
	}
	
}
