package nayuki.sortdemo;

import java.awt.Canvas;
import java.awt.event.WindowEvent;


@SuppressWarnings("serial")
class SortFrame extends AutoCloseFrame {
	
	private SortThread thread;
	
	
	
	public SortFrame(Canvas canvas, SortAlgorithm algor, SortThread thread) {
		super(algor.getName());
		this.thread = thread;
		add(canvas);
		pack();
		setVisible(true);
	}
	
	
	
	public void windowClosing(WindowEvent e) {
		thread.stop1();
		super.windowClosing(e);
	}
	
}
