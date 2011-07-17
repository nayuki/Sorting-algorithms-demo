package nayuki.sortdemo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;


/**
 * An array to be sorted. Elements can be compared and swapped, but their values cannot be accessed directly.
 */
final class SortArrayImpl implements SortArray {
	
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
	
	
	
	public SortArrayImpl(int size, int scale, int delay) {
		// Initialize in order
		values = new int[size];
		for (int i = 0; i < values.length; i++)
			values[i] = i;
		// Permute randomly
		Utils.shuffle(values);
		
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
	
	public int compare(int i, int j) {
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
		return Utils.compare(values[i], values[j]);
	}
	
	
	public void swap(int i, int j) {
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
	
	
	public boolean compareAndSwap(int i, int j) {
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
	
	public void setActive  (int index) { redrawElement(index, activeColor  ); canvas.repaint(); }
	public void setInactive(int index) { redrawElement(index, inactiveColor); canvas.repaint(); }
	public void setDone    (int index) { redrawElement(index, doneColor    ); canvas.repaint(); }
	
	public void setActive  (int start, int end) { redrawRange(start, end, activeColor  ); canvas.repaint(); }
	public void setInactive(int start, int end) { redrawRange(start, end, inactiveColor); canvas.repaint(); }
	public void setDone    (int start, int end) { redrawRange(start, end, doneColor    ); canvas.repaint(); }
	
	
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
	}
	
	
	/* Canvas drawing. These methods do not call repaint()! */
	
	private void redrawElement(int index, Color color) {
		graphics.setColor(backgroundColor);
		if (scale == 1) graphics.drawLine(values[index] + 1, index, values.length, index);
		else graphics.fillRect((values[index] + 1) * scale, index * scale, values.length * scale, scale);
		
		graphics.setColor(color);
		if (scale == 1) graphics.drawLine(0, index, values[index], index);
		else graphics.fillRect(0, index * scale, (values[index] + 1) * scale, scale);
	}
	
	
	private void redrawRange(int start, int end, Color color) {
		graphics.setColor(backgroundColor);
		graphics.fillRect(0, start * scale, values.length * scale, (end - start) * scale);
		graphics.setColor(color);
		if (scale == 1) {
			for (int i = start; i < end; i++)
				graphics.drawLine(0, i, values[i], i);
		} else {
			for (int i = start; i < end; i++)
				graphics.fillRect(0, i * scale, (values[i] + 1) * scale, scale);
		}
	}
	
	
	/* Easy methods */
	
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new StopException();
		}
	}
	
}
