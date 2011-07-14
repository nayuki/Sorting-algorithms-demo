package nayuki.sortdemo;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


@SuppressWarnings("serial")
final class SortFrame extends Frame {
	
	private SortThread thread;
	
	
	
	public SortFrame(Canvas canvas, SortAlgorithm algor, SortThread thread) {
		super(algor.getName());
		this.thread = thread;
		add(canvas);
		pack();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				SortFrame.this.thread.stop1();
				dispose();
			}
		});
		setVisible(true);
	}
	
}
