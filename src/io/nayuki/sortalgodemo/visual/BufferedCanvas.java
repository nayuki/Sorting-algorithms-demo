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
import java.awt.Graphics;
import java.awt.image.BufferedImage;


@SuppressWarnings("serial")
final class BufferedCanvas extends Canvas {
	
	private BufferedImage buffer;
	private Graphics bufferg;
	
	
	public BufferedCanvas(int size) {
		setSize(size, size);
		buffer = new BufferedImage(size, size, BufferedImage.TYPE_INT_BGR);
		bufferg = buffer.getGraphics();
	}
	
	
	public Graphics getBufferGraphics() {
		return bufferg;
	}
	
	
	public void update(Graphics g) {
		paint(g);
	}
	
	
	public void paint(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}
	
}
