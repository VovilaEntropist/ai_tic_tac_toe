package i.dont.care.tictactoe.view.swing.content;

import i.dont.care.tictactoe.view.swing.ContentType;
import i.dont.care.tictactoe.view.swing.ImagePanel;
import i.dont.care.tictactoe.view.swing.ImageLoader;
import i.dont.care.tictactoe.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.view.swing.content.listener.ContentListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public abstract class BackButtonContent extends Content {
	
	protected ContentType from;
	protected JPanel backBtn;
	
	public BackButtonContent(JPanel parent, ContentType contentType, ContentListener listener, ContentType from) {
		super(parent, contentType, listener);
		this.from = from;
		init();
	}
	
	private void init() {
		backBtn = new ImagePanel(new Rectangle(20, 20, 50, 50),
				ImageLoader.load(new File("src/images/back_btn_icon.png")));
		
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.handleContentEvent(BackButtonContent.this,
						ContentEvent.BackPressed, from);
			}
		});

		this.setLayout(null);
		this.add(backBtn);
	}
	
	public ContentType getFrom() {
		return from;
	}
	
	public void setFrom(ContentType from) {
		this.from = from;
	}
	
	public void showBackButton(boolean show) {
		backBtn.setVisible(show);
	}
}
