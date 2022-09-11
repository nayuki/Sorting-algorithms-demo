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

import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import io.nayuki.sortalgodemo.core.SortAlgorithm;
import io.nayuki.sortalgodemo.core.SortArray;


@SuppressWarnings("serial")
final class SortFrame extends Frame {
	
	public SortFrame(VisualSortArray array, SortAlgorithm algorithm, int scale) {
		// Do component layout
		super(algorithm.getName());
		var canvas = new SortCanvas(array, scale, FRAME_RATE_HZ);
		this.add(canvas);
		this.setResizable(false);
		this.pack();
		
		var worker = new Thread(() -> {
			// Wait and sort
			try {
				Thread.sleep(START_DELAY_MS);
				algorithm.sort(array);
				array.setRange(0, array.length(), SortArray.ElementState.DONE);
			} catch (StopException|InterruptedException e) {
				canvas.stop();
				return;
			}
			
			// Check and print
			String msg = String.format("%s: %d elements, %d comparisons, %d swaps",
				algorithm.getName(), array.length(), array.getComparisonCount(), array.getSwapCount());
			try {
				array.finishSorting();
			} catch (AssertionError e) {
				msg = algorithm.getName() + ": Sorting failed";
			}
			synchronized(System.err) {
				System.err.println(msg);
			}
		});
		worker.start();
		
		// Set window closing action
		this.addWindowListener(new WindowAdapter() {
			@Override public void windowClosing(WindowEvent e) {
				worker.interrupt();
				SortFrame.this.dispose();
			}
		});
		
		// Set window position and show
		Rectangle rect = getGraphicsConfiguration().getBounds();
		this.setLocation(
			(rect.width - this.getWidth()) / 8,
			(rect.height - this.getHeight()) / 8);
		this.setVisible(true);
	}
	
	
	private static final int START_DELAY_MS = 1000;
	private static final double FRAME_RATE_HZ = 60;
	
}
