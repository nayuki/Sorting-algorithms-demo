package nayuki.sortdemo;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


@SuppressWarnings("serial")
final class SortFrame extends Frame {
	
	private SortThread thread;
	
	
	
	public SortFrame(String name, Component maincomp, SortThread thread) {
		super(name);
		this.thread = thread;
		add(maincomp);
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
