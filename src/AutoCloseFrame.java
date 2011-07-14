import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


@SuppressWarnings("serial")
class AutoCloseFrame extends Frame implements WindowListener {
	
	public AutoCloseFrame() {
		super();
		addWindowListener(this);
	}
	
	
	public AutoCloseFrame(String title) {
		super(title);
		addWindowListener(this);
	}
	
	
	public void windowClosing(WindowEvent e) {
		dispose();
	}
	
	
	public void windowActivated(WindowEvent e) {}
	
	public void windowDeactivated(WindowEvent e) {}
	
	public void windowDeiconified(WindowEvent e) {}
	
	public void windowIconified(WindowEvent e) {}
	
	public void windowClosed(WindowEvent e) {}
	
	public void windowOpened(WindowEvent e) {}
	
}