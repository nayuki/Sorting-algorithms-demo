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

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Objects;
import io.nayuki.sortalgodemo.core.AbstractSortArray;


/**
 * An array to be sorted. Elements can be compared and swapped, but their values cannot be accessed directly.
 */
final class VisualSortArray extends AbstractSortArray {
	
	/*---- Fields ----*/
	
	// Visual state per element: 0=active, 1=inactive, 2=comparing, 3=done
	private int[] state;
	
	// Graphics
	private final int scale;
	public final BufferedCanvas canvas;
	private final Graphics graphics;
	
	// Statistics
	private volatile int comparisonCount;
	private volatile int swapCount;
	
	// Speed regulation
	private static double MAX_FPS = 60;
	private int stepsToExecute;
	private int stepsSinceRepaint;
	private int delay;
	private final boolean drawIncrementally;
	
	
	
	/*---- Constructors ----*/
	
	public VisualSortArray(int size, int scale, double speed) {
		// Check arguments and initialize arrays
		super(size);
		if (scale <= 0 || speed <= 0 || Double.isInfinite(speed) || Double.isNaN(speed))
			throw new IllegalArgumentException();
		shuffle();
		state = new int[size];
		
		// Initialize various numbers
		comparisonCount = 0;
		swapCount = 0;
		delay = (int)Math.round(1000 / Math.min(speed, MAX_FPS));
		stepsToExecute = (int)Math.round(Math.max(speed / MAX_FPS, 1));
		stepsSinceRepaint = 0;
		drawIncrementally = stepsToExecute <= size;
		
		// Initialize graphics
		this.scale = scale;
		canvas = new BufferedCanvas(size * scale);
		graphics = canvas.getBufferGraphics();
		redraw(0, values.length);
	}
	
	
	
	/*---- Methods ----*/
	
	/* Comparison and swapping */
	
	public int compare(int i, int j) {
		if (Thread.interrupted())
			throw new StopException();
		
		comparisonCount++;
		setElement(i, ElementState.COMPARING);
		setElement(j, ElementState.COMPARING);
		requestRepaint();
		
		// No repaint here
		setElement(i, ElementState.ACTIVE);
		setElement(j, ElementState.ACTIVE);
		
		return super.compare(i, j);
	}
	
	
	public void swap(int i, int j) {
		if (Thread.interrupted())
			throw new StopException();
		super.swap(i, j);
		if (state != null) {  // If outside the constructor
			swapCount++;
			setElement(i, ElementState.ACTIVE);
			setElement(j, ElementState.ACTIVE);
			requestRepaint();
		}
	}
	
	
	/* Array visualization */
	
	public void setElement(int index, ElementState state) {
		Objects.requireNonNull(state);
		this.state[index] = state.ordinal();
		if (drawIncrementally)
			redraw(index, index + 1);
	}
	
	
	public void setRange(int start, int end, ElementState state) {
		Objects.requireNonNull(state);
		Arrays.fill(this.state, start, end, state.ordinal());
		if (drawIncrementally)
			redraw(start, end);
	}
	
	
	/* After sorting */
	
	public int getComparisonCount() {
		return comparisonCount;
	}
	
	public int getSwapCount() {
		return swapCount;
	}
	
	
	// Checks if the array is sorted. Returns silently if so, throws AssertionError if not.
	public void assertSorted() {
		for (int i = 1; i < values.length; i++) {
			if (values[i - 1] > values[i])
				throw new AssertionError();
		}
		redraw(0, values.length);
		canvas.repaint();
	}
	
	
	// Redraws the given range of indices to the off-screen buffer image.
	// Note that this does not call repaint() on the canvas element.
	private void redraw(int start, int end) {
		graphics.setColor(BACKGROUND_COLOR);
		graphics.fillRect(0, start * scale, values.length * scale, (end - start) * scale);
		for (int i = start; i < end; i++) {
			graphics.setColor(STATE_COLORS[state[i]]);
			if (scale == 1)
				graphics.drawLine(0, i, values[i], i);
			else  // scale > 1
				graphics.fillRect(0, i * scale, (values[i] + 1) * scale, scale);
		}
	}
	
	
	/* Speed regulation */
	
	private void requestRepaint() {
		stepsSinceRepaint++;
		if (stepsSinceRepaint >= stepsToExecute) {
			if (!drawIncrementally)
				redraw(0, values.length);
			canvas.repaint();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				throw new StopException();
			}
			stepsSinceRepaint = 0;
		}
	}
	
	
	
	/*---- Color constants ----*/
	
	private static Color[] STATE_COLORS = {
		new Color(0x294099),  // Active: Blue
		new Color(0x959EBF),  // Inactive: Gray
		new Color(0xD4BA0D),  // Comparing: Yellow
		new Color(0x25963D),  // Done: Green
	};
	
	private static Color BACKGROUND_COLOR = new Color(0xFFFFFF);  // White
	
}
