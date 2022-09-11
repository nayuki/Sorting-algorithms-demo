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

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Objects;


@SuppressWarnings("serial")
final class SortCanvas extends Canvas {
	
	/*---- Fields ----*/
	
	private VisualSortArray array;
	private final int scale;
	private BufferedImage buffer;
	private Graphics bufGfx;
	private Thread worker;
	
	
	
	/*---- Constructor ----*/
	
	public SortCanvas(VisualSortArray array, int scale, double frameRateHz) {
		this.array = Objects.requireNonNull(array);
		if (scale <= 0)
			throw new IllegalArgumentException();
		this.scale = scale;
		if (frameRateHz <= 0)
			throw new IllegalArgumentException();
		
		int size = Math.multiplyExact(array.length(), scale);
		buffer = new BufferedImage(size, size, BufferedImage.TYPE_INT_BGR);
		bufGfx = buffer.getGraphics();
		this.setSize(size, size);
		
		worker = new Thread(() -> {
			try {
				while (!array.isDone()) {
					SortCanvas.this.repaint();
					Thread.sleep((int)(1000 / frameRateHz));
				}
				SortCanvas.this.repaint();
			} catch (InterruptedException e) {}
		});
		worker.start();
	}
	
	
	
	/*---- Methods ----*/
	
	// Called by the AWT event loop, not by user code.
	@Override public void update(Graphics g) {
		bufGfx.setColor(BACKGROUND_COLOR);
		bufGfx.fillRect(0, 0, array.length() * scale, array.length() * scale);
		for (int i = 0; i < array.length(); i++) {
			bufGfx.setColor(STATE_COLORS[array.getState(i).ordinal()]);
			int val = array.getValue(i);
			if (scale == 1)
				bufGfx.drawLine(0, i, val, i);
			else  // scale > 1
				bufGfx.fillRect(0, i * scale, (val + 1) * scale, scale);
		}
		paint(g);
	}
	
	
	// Called by the AWT event loop, not by user code.
	@Override public void paint(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}
	
	
	public void stop() {
		worker.interrupt();
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
