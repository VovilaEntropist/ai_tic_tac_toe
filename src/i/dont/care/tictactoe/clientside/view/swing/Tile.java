package i.dont.care.tictactoe.clientside.view.swing;

import i.dont.care.tictactoe.clientside.view.swing.content.Content;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentListener;
import i.dont.care.tictactoe.serverside.board.Mark;
import i.dont.care.utils.Index;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Tile extends JPanel {
	
	private ContentListener listener;
	private Content content;
	
	private Index index;
	private Mark mark;
	
	private BufferedImage xImage;
	private BufferedImage oImage;
	
	public Tile(Rectangle rectangle, Index index, Mark mark, BufferedImage xImage, BufferedImage oImage,
	            Content content, ContentListener listener) {
		this.setBounds(rectangle);
		this.setBackground(new Color(0, 0, 0, 0));
		
		this.index = index;
		this.mark = mark;
		
		this.content = content;
		this.listener = listener;
		
		this.xImage = xImage;
		this.oImage = oImage;
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.handleContentEvent(content, ContentEvent.TileCilck, index);
			}
		});
	}
	
	public Index getIndex() {
		return index;
	}
	
	public Mark getMark() {
		return mark;
	}
	
	public void setMark(Mark mark) {
		this.mark = mark;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.clearRect(0, 0, getWidth(), getHeight());
		switch (mark) {
			case Player1:
				g2d.drawImage(xImage, 0, 0, getWidth(), getHeight(), null);
				break;
			case Player2:
				g2d.drawImage(oImage, 0, 0, getWidth(), getHeight(), null);
				break;
		}
	}
	
}
