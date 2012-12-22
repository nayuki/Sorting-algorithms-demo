package nayuki.sortdemo;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


@SuppressWarnings("serial")
final class SortFrame extends Frame {
	
	private SortThread thread;
	
	
	
	public SortFrame(Canvas canvas, SortAlgorithm algo, SortThread thread) {
		super(algo.getName());
		this.thread = thread;
		add(canvas);
		setResizable(false);
		pack();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				SortFrame.this.thread.requestStop();
				dispose();
			}
		});
		
		Rectangle rect = getGraphicsConfiguration().getBounds();
		setLocation((rect.width - getWidth()) / 8, (rect.height - getHeight()) / 8);
		setVisible(true);
	}
	
}
