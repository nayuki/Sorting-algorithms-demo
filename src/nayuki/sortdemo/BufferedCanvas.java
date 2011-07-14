package nayuki.sortdemo;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


@SuppressWarnings("serial")
class BufferedCanvas extends Canvas {
	
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