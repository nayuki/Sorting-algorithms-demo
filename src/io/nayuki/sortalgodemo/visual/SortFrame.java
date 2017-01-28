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

import java.awt.Component;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


@SuppressWarnings("serial")
final class SortFrame extends Frame {
	
	/*---- Fields ----*/
	
	private SortThread thread;
	
	
	/*---- Constructors ----*/
	
	public SortFrame(String name, Component maincomp, SortThread thread) {
		// Initialize
		super(name);
		this.thread = thread;
		
		// Do component layout
		add(maincomp);
		setResizable(false);
		pack();
		
		// Set window closing action
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				SortFrame.this.thread.interrupt();
				dispose();
			}
		});
		
		// Set window position and show
		Rectangle rect = getGraphicsConfiguration().getBounds();
		setLocation((rect.width - getWidth()) / 8, (rect.height - getHeight()) / 8);
		setVisible(true);
	}
	
}
