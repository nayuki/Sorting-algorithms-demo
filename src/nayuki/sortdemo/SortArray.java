package nayuki.sortdemo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


/**
 * An array to be sorted. Elements can be compared and swapped, but their values cannot be accessed directly.
 */
public final class SortArray {
	
	private static Random random = new Random();
	
	
	// Data
	private int[] values;
	
	// Control
	private volatile boolean stop;
	
	// Graphics
	private int scale;
	private int delay;
	private BufferedCanvas canvas;
	private Graphics graphics;
	
	// Statistics
	private volatile int comparisonCount;
	private volatile int swapCount;
	
	// Colors
	private Color activeColor     = new Color(0x294099);  // Blue
	private Color inactiveColor   = new Color(0x959EBF);  // Gray
	private Color comparingColor  = new Color(0xD4BA0D);  // Yellow
	private Color doneColor       = new Color(0x25963D);  // Green
	private Color backgroundColor = new Color(0xFFFFFF);  // White
	
	
	
	public SortArray(int size, int scale, int delay) {
		// Initialize in order
		values = new int[size];
		for (int i = 0; i < values.length; i++)
			values[i] = i + 1;
		
		// Knuth shuffle
		for (int i = values.length - 1; i >= 0; i--) {
			int j = random.nextInt(i + 1);
			int temp = values[i];
			values[i] = values[j];
			values[j] = temp;
		}
		
		comparisonCount = 0;
		swapCount = 0;
		stop = false;
		
		this.scale = scale;
		this.delay = delay;
		canvas = new BufferedCanvas(size * scale);
		graphics = canvas.getBufferGraphics();
		
		redrawRange(0, values.length, activeColor);
	}
	
	
	/* Field getters */
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public int length() {
		return values.length;
	}
	
	
	/* Comparison and swapping */
	
	// Compares the values at the two given array indices.
	// Returns a negative number if array[i] < array[j], zero if array[i] == array[j], or a positive number if array[i] > array[j].
	// Do not assume that this returns only -1, 0, or 1.
	public synchronized int compare(int i, int j) {
		if (stop)
			throw new StopException();
		
		redrawElement(i, comparingColor);
		redrawElement(j, comparingColor);
		canvas.repaint();
		sleep(delay);
		
		redrawElement(i, activeColor);
		redrawElement(j, activeColor);
		// No repaint here
		
		comparisonCount++;
		return compareInts(values[i], values[j]);
	}
	
	
	// Swaps the values at the two given array indices.
	public synchronized void swap(int i, int j) {
		if (stop)
			throw new StopException();
		
		int temp = values[i];
		values[i] = values[j];
		values[j] = temp;
		swapCount++;
		
		redrawElement(i, activeColor);
		redrawElement(j, activeColor);
		canvas.repaint();
		sleep(delay);
	}
	
	
	// Compares the values at the two given array indices, swaps if and only if array[i] > array[j], and returns whether a swap occurred.
	public synchronized boolean compareAndSwap(int i, int j) {
		if (compare(j, i) < 0) {
			swap(i, j);
			return true;
		} else
			return false;
	}
	
	
	/* Control */
	
	public void stop() {
		stop = true;
	}
	
	
	/* Array visualization */
	
	public synchronized void setActive  (int index) { redrawElement(index, activeColor  ); canvas.repaint(); }
	public synchronized void setInactive(int index) { redrawElement(index, inactiveColor); canvas.repaint(); }
	public synchronized void setDone    (int index) { redrawElement(index, doneColor    ); canvas.repaint(); }
	
	public synchronized void setActive  (int start, int end) { redrawRange(start, end, activeColor  ); canvas.repaint(); }
	public synchronized void setInactive(int start, int end) { redrawRange(start, end, inactiveColor); canvas.repaint(); }
	public synchronized void setDone    (int start, int end) { redrawRange(start, end, doneColor    ); canvas.repaint(); }
	
	
	/* After sorting */
	
	public int getComparisonCount() {
		return comparisonCount;
	}
	
	public int getSwapCount() {
		return swapCount;
	}
	
	
	// Checks if the array is sorted. Returns silently if so, throws AssertionError if not.
	public synchronized void assertSorted() {
		for (int i = 1; i < values.length; i++) {
			if (values[i - 1] > values[i])
				throw new AssertionError();
		}
	}
	
	
	/* Canvas drawing. These methods do not call repaint()! */
	
	private synchronized void redrawElement(int index, Color color) {
		graphics.setColor(backgroundColor);
		if (scale == 1) graphics.drawLine(values[index], index, values.length, index);
		else graphics.fillRect(values[index] * scale, index * scale, values.length * scale, scale);
		
		graphics.setColor(color);
		if (scale == 1) graphics.drawLine(0, index, values[index] - 1, index);
		else graphics.fillRect(0, index * scale, values[index] * scale, scale);
	}
	
	
	private synchronized void redrawRange(int start, int end, Color color) {
		graphics.setColor(backgroundColor);
		graphics.fillRect(0, start * scale, values.length * scale, (end - start) * scale);
		graphics.setColor(color);
		if (scale == 1) {
			for (int i = start; i < end; i++)
				graphics.drawLine(0, i, values[i] - 1, i);
		} else {
			for (int i = start; i < end; i++)
				graphics.fillRect(0, i * scale, values[i] * scale, scale);
		}
	}
	
	
	/* Easy methods */
	
	private static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new StopException();
		}
	}
	
	
	private static int compareInts(int x, int y) {
		if (x < y)
			return -1;
		else if (x > y)
			return 1;
		else
			return 0;
	}
	
}
