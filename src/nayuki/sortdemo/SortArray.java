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
	
	
	private int scale;
	private int delay;
	private BufferedCanvas canvas;
	private Graphics graphics;
	
	private int[] values;
	private int comparisons;
	private int swaps;
	private volatile boolean stop;
	
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
		for (int i = 0; i < values.length; i++) {
			int j = i + random.nextInt(values.length - i);
			int temp = values[i];
			values[i] = values[j];
			values[j] = temp;
		}
		
		comparisons = 0;
		swaps = 0;
		stop = false;
		
		this.scale = scale;
		this.delay = delay;
		canvas = new BufferedCanvas(size * scale);
		graphics = canvas.getBufferGraphics();
		
		redraw(0, values.length, activeColor);
	}
	
	
	public synchronized Canvas getCanvas() {
		return canvas;
	}
	
	
	public synchronized int length() {
		return values.length;
	}
	
	
	public synchronized int getComparisons() {
		return comparisons;
	}
	
	
	public synchronized int getSwaps() {
		return swaps;
	}
	
	
	public synchronized int compare(int i, int j) {
		if (stop)
			throw new StopException();
		
		redraw(i, comparingColor);
		redraw(j, comparingColor);
		canvas.repaint();
		sleep(delay);
		redraw(i, activeColor);
		redraw(j, activeColor);
		comparisons++;
		if (values[i] < values[j])
			return -1;
		else if (values[i] > values[j])
			return 1;
		else
			return 0;
	}
	
	
	public synchronized void swap(int i, int j) {
		if (stop)
			throw new StopException();
		
		sleep(delay);
		swaps++;
		int temp = values[i];
		values[i] = values[j];
		values[j] = temp;
		redraw(i, activeColor);
		redraw(j, activeColor);
		canvas.repaint();
	}
	
	
	public synchronized boolean compareAndSwap(int i, int j) {
		if (compare(j, i) < 0) {
			swap(i, j);
			return true;
		} else
			return false;
	}
	
	
	public synchronized boolean isSorted() {
		for (int i = 1; i < values.length; i++) {
			if (values[i - 1] > values[i])
				return false;
		}
		return true;
	}
	
	
	public void stop() {
		stop = true;
	}
	
	
	
	public synchronized void setActive(int index) {
		setActive(index, index + 1);
	}
	
	
	public synchronized void setActive(int start, int end) {
		redraw(start, end, activeColor);
		canvas.repaint();
	}
	
	
	public synchronized void setInactive(int index) {
		setInactive(index, index + 1);
	}
	
	
	public synchronized void setInactive(int start, int end) {
		redraw(start, end, inactiveColor);
		canvas.repaint();
	}
	
	
	public synchronized void setDone(int index) {
		setDone(index, index + 1);
	}
	
	
	public synchronized void setDone(int start, int end) {
		redraw(start, end, doneColor);
		canvas.repaint();
	}
	
	
	private synchronized void redraw(int index, Color color) {
		graphics.setColor(backgroundColor);
		if (scale == 1)
			graphics.drawLine(0, index, values.length, index);
		else
			graphics.fillRect(0, index * scale, values.length * scale, scale);
		
		graphics.setColor(color);
		if (scale == 1)
			graphics.drawLine(0, index, values[index] - 1, index);
		else
			graphics.fillRect(0, index * scale, values[index] * scale, scale);
	}
	
	
	private synchronized void redraw(int start, int end, Color color) {
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
	
	
	protected static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new StopException();
		}
	}
	
}
