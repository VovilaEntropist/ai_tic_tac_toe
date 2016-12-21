package i.dont.care.tictactoe.clientside.view.swing.content;

import i.dont.care.tictactoe.clientside.view.swing.ContentType;
import i.dont.care.tictactoe.clientside.view.swing.ImageLoader;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class BackgroundContent extends Content {
	
	private BufferedImage background;
	
	public BackgroundContent(Rectangle rectangle, ContentType contentType, ContentListener listener) {
		this(rectangle, contentType, listener, ImageLoader.load(new File("src/images/background.jpg")));
	}
	
	public BackgroundContent(Rectangle rectangle, ContentType contentType, ContentListener listener,
	                         BufferedImage background) {
		super(rectangle, contentType, listener);
		this.background = background;
	}
	
	public BufferedImage getBackgroundImage() {
		return background;
	}
	
	public void setBackgroundImage(BufferedImage background) {
		this.background = background;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);
	}
}
