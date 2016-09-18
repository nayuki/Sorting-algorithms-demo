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

package nayuki.sortdemo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;


/**
 * An array to be sorted. Elements can be compared and swapped, but their values cannot be accessed directly.
 */
final class VisualSortArray extends SortArray {
	
	// Visual state per element: 0=active, 1=inactive, 2=comparing, 3=done
	private int[] state;
	
	// Control
	private volatile boolean isStopRequested;
	
	// Graphics
	private int scale;
	private BufferedCanvas canvas;
	private Graphics graphics;
	
	// Statistics
	private volatile int comparisonCount;
	private volatile int swapCount;
	
	// Speed regulation
	private static double MAX_FPS = 60;
	private int stepsToExecute;
	private int stepsSinceRepaint;
	private int delay;
	private boolean enableLazyDrawing;
	
	// Colors
	private static Color[] COLORS = {
		new Color(0x294099),  // Active: Blue
		new Color(0x959EBF),  // Inactive: Gray
		new Color(0xD4BA0D),  // Comparing: Yellow
		new Color(0x25963D),  // Done: Green
	};
	private static Color BACKGROUND_COLOR = new Color(0xFFFFFF);  // White
	
	
	
	public VisualSortArray(int size, int scale, double speed) {
		// Initialize in order, then permute randomly
		super(size);
		if (speed <= 0)
			throw new IllegalArgumentException("Speed must be positive");
		shuffle();
		state = new int[size];
		
		comparisonCount = 0;
		swapCount = 0;
		isStopRequested = false;
		
		delay = (int)Math.round(1000 / Math.min(speed, MAX_FPS));
		stepsToExecute = (int)Math.round(Math.max(speed / MAX_FPS, 1));
		stepsSinceRepaint = 0;
		enableLazyDrawing = stepsToExecute > size;
		
		this.scale = scale;
		canvas = new BufferedCanvas(size * scale);
		graphics = canvas.getBufferGraphics();
		redraw(0, values.length, true);
	}
	
	
	/* Initialization getters */
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	
	/* Comparison and swapping */
	
	public int compare(int i, int j) {
		if (isStopRequested)
			throw new StopException();
		
		comparisonCount++;
		setIndex(i, 2);
		setIndex(j, 2);
		requestRepaint();
		
		// No repaint here
		setActive(i);
		setActive(j);
		
		return super.compare(i, j);
	}
	
	
	public void swap(int i, int j) {
		if (isStopRequested)
			throw new StopException();
		super.swap(i, j);
		if (state != null) {  // If outside the constructor
			swapCount++;
			setActive(i);
			setActive(j);
			requestRepaint();
		}
	}
	
	
	/* Control */
	
	public void requestStop() {
		isStopRequested = true;
	}
	
	
	/* Array visualization */
	
	public void setActive  (int index) { setIndex(index, 0); }
	public void setInactive(int index) { setIndex(index, 1); }
	public void setDone    (int index) { setIndex(index, 3); }
	
	public void setActive  (int start, int end) { setRange(start, end, 0); }
	public void setInactive(int start, int end) { setRange(start, end, 1); }
	public void setDone    (int start, int end) { setRange(start, end, 3); }
	
	private void setIndex(int index, int st) {
		state[index] = st;
		redraw(index, index + 1, false);
	}
	
	private void setRange(int start, int end, int st) {
		Arrays.fill(state, start, end, st);
		redraw(start, end, false);
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
		redraw(0, values.length, true);
		canvas.repaint();
	}
	
	
	/* Canvas drawing. The method does not call repaint()! */
	
	private void redraw(int start, int end, boolean force) {
		if (!force && enableLazyDrawing)
			return;  // Wait for lazy full drawing instead
		graphics.setColor(BACKGROUND_COLOR);
		graphics.fillRect(0, start * scale, values.length * scale, (end - start) * scale);
		if (scale == 1) {
			for (int i = start; i < end; i++) {
				graphics.setColor(COLORS[state[i]]);
				graphics.drawLine(0, i, values[i], i);
			}
		} else {
			for (int i = start; i < end; i++) {
				graphics.setColor(COLORS[state[i]]);
				graphics.fillRect(0, i * scale, (values[i] + 1) * scale, scale);
			}
		}
	}
	
	
	/* Speed regulation */
	
	private void requestRepaint() {
		stepsSinceRepaint++;
		if (stepsSinceRepaint >= stepsToExecute) {
			if (enableLazyDrawing)  // Lazy full drawing
				redraw(0, values.length, true);
			canvas.repaint();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				throw new StopException();
			}
			stepsSinceRepaint = 0;
		}
	}
	
}
