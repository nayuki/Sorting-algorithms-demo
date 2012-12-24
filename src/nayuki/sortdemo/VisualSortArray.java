package nayuki.sortdemo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;


/**
 * An array to be sorted. Elements can be compared and swapped, but their values cannot be accessed directly.
 */
final class VisualSortArray extends AbstractSortArray {
	
	// Visual state per element: 0=active, 1=inactive, 2=comparing, 3=done
	private int[] state;
	
	// Control
	private volatile boolean isStopRequested;
	
	// Graphics
	private int scale;
	private int delay;
	private BufferedCanvas canvas;
	private Graphics graphics;
	
	// Statistics
	private volatile int comparisonCount;
	private volatile int swapCount;
	
	// Colors
	private static Color[] COLORS = {
		new Color(0x294099),  // Active: Blue
		new Color(0x959EBF),  // Inactive: Gray
		new Color(0xD4BA0D),  // Comparing: Yellow
		new Color(0x25963D),  // Done: Green
	};
	private static Color BACKGROUND_COLOR = new Color(0xFFFFFF);  // White
	
	
	
	public VisualSortArray(int size, int scale, int delay) {
		// Initialize in order, then permute randomly
		super(size);
		Utils.shuffle(values);
		state = new int[size];
		
		comparisonCount = 0;
		swapCount = 0;
		isStopRequested = false;
		
		this.scale = scale;
		this.delay = delay;
		canvas = new BufferedCanvas(size * scale);
		graphics = canvas.getBufferGraphics();
		redraw(0, values.length);
	}
	
	
	/* Initialization getters */
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	
	/* Comparison and swapping */
	
	public int compare(int i, int j) {
		if (isStopRequested)
			throw new StopException();
		
		// Do repaint here
		setRange(i, i + 1, 2);
		setRange(j, j + 1, 2);
		sleep(delay);
		
		// No repaint here
		state[i] = 0;
		state[j] = 0;
		redraw(i, i + 1);
		redraw(j, j + 1);
		
		comparisonCount++;
		return super.compare(i, j);
	}
	
	
	public void swap(int i, int j) {
		if (isStopRequested)
			throw new StopException();
		
		super.swap(i, j);
		swapCount++;
		
		setActive(i);
		setActive(j);
		sleep(delay);
	}
	
	
	/* Control */
	
	public void requestStop() {
		isStopRequested = true;
	}
	
	
	/* Array visualization */
	
	public void setActive  (int index) { setRange(index, index + 1, 0); }
	public void setInactive(int index) { setRange(index, index + 1, 1); }
	public void setDone    (int index) { setRange(index, index + 1, 3); }
	
	public void setActive  (int start, int end) { setRange(start, end, 0); }
	public void setInactive(int start, int end) { setRange(start, end, 1); }
	public void setDone    (int start, int end) { setRange(start, end, 3); }
	
	private void setRange(int start, int end, int st) {
		for (int i = start; i < end; i++)
			state[i] = st;
		redraw(start, end);
		canvas.repaint();
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
	}
	
	
	/* Canvas drawing. The method does not call repaint()! */
	
	private void redraw(int start, int end) {
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
	
	
	/* Utilities */
	
	private static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new StopException();
		}
	}
	
}
