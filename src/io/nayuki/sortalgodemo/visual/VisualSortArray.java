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

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import io.nayuki.sortalgodemo.core.AbstractSortArray;


/**
 * An array to be sorted. Elements can be compared and swapped, but their values cannot be accessed directly.
 */
final class VisualSortArray extends AbstractSortArray {
	
	/*---- Fields ----*/
	
	private boolean isInitialized = false;
	
	// Visual state per element: 0=active, 1=inactive, 2=comparing, 3=done
	private int[] states;
	
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
		// Check arguments and initialize arrays
		super(size);
		if (speed <= 0 || Double.isInfinite(speed) || Double.isNaN(speed))
			throw new IllegalArgumentException();
		stepsPerSecond = speed;
		stepsPerCheck = (int)Math.max(Math.min(stepsPerSecond * CHECK_INTERVAL_NS / 1e9, 1_000_000), 1);
		states = new int[size];
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
	
	/* Comparison and swapping */
	
	public int compare(int i, int j) {
		if (Thread.interrupted())
			throw new StopException();
		
		setElement(i, ElementState.COMPARING);
		setElement(j, ElementState.COMPARING);
		regulateSpeed();
		comparisonCount.setOpaque(comparisonCount.getPlain() + 1);
		
		setElement(i, ElementState.ACTIVE);
		setElement(j, ElementState.ACTIVE);
		
		return super.compare(i, j);
	}
	
	
	public void swap(int i, int j) {
		super.swap(i, j);
		if (!isInitialized)
			return;
		if (Thread.interrupted())
			throw new StopException();
		swapCount.setOpaque(swapCount.getPlain() + 1);
		setElement(i, ElementState.ACTIVE);
		setElement(j, ElementState.ACTIVE);
		regulateSpeed();
	}
	
	
	/* Array visualization */
	
	public void setElement(int index, ElementState state) {
		Objects.requireNonNull(state);
		this.states[index] = state.ordinal();
	}
	
	
	public void setRange(int start, int end, ElementState state) {
		Objects.requireNonNull(state);
		Arrays.fill(states, start, end, state.ordinal());
	}
	
	
	public int getValue(int index) {
		return values[index];
	}
	
	
	public int getState(int index) {
		return states[index];
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
		for (int i = 1; i < values.length; i++) {
			if (values[i - 1] > values[i])
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
