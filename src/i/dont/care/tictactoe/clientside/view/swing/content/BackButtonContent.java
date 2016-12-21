package i.dont.care.tictactoe.clientside.view.swing.content;

import i.dont.care.tictactoe.clientside.view.swing.ContentType;
import i.dont.care.tictactoe.clientside.view.swing.ImagePanel;
import i.dont.care.tictactoe.clientside.view.swing.ImageLoader;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public abstract class BackButtonContent extends Content {
	
	protected Content from;
	protected JPanel backBtn;
	
	public BackButtonContent(Rectangle rectangle, ContentType contentType,
	                         ContentListener listener, Content from) {
		super(rectangle, contentType, listener);
		
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
						ContentEvent.BackPressed, new Object[] {from});
			}
		});

		this.setLayout(null);
		this.add(backBtn);
	}
	
	public Content getFrom() {
		return from;
	}
	
	public void setFrom(Content from) {
		this.from = from;
	}
}
