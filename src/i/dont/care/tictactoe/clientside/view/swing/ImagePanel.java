package i.dont.care.tictactoe.clientside.view.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
	
	private BufferedImage image;
	
	public ImagePanel(Rectangle bounds, BufferedImage image) {
		super();
		
		this.setBounds(bounds);
		this.setBackground(new Color(0, 0, 0, 0));
		
		this.image = image;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
	public BufferedImage getImage() {
		return image;
	}
}
