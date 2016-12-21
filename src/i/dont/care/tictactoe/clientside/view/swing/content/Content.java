package i.dont.care.tictactoe.clientside.view.swing.content;

import i.dont.care.tictactoe.clientside.view.swing.ContentType;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentListener;

import javax.swing.*;
import java.awt.*;

public abstract class Content extends JPanel {
	
	protected ContentListener listener;
	protected ContentType contentType;
	
	public Content(Rectangle rectangle, ContentType contentType, ContentListener listener) {
		super();
		
		this.contentType = contentType;
		this.listener = listener;
		this.setBounds(rectangle);
	}
	
	public ContentType getContentType() {
		return contentType;
	}
}
