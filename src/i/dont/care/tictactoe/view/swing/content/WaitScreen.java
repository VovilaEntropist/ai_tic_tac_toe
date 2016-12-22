package i.dont.care.tictactoe.view.swing.content;

import i.dont.care.tictactoe.view.swing.CenterPanel;
import i.dont.care.tictactoe.view.swing.ContentType;
import i.dont.care.tictactoe.view.swing.content.listener.ContentListener;

import javax.swing.*;
import java.awt.*;

public class WaitScreen extends Content {
	
	public WaitScreen(JPanel parent, ContentType contentType, ContentListener listener) {
		super(parent, contentType, listener);
		init();
	}
	
	private void init() {
		JLabel lbl1 = new JLabel("Подождите...");
		JPanel centerPanel = new CenterPanel(this.getBounds(), 3, 3);
		centerPanel.setLayout(new FlowLayout());
		centerPanel.add(lbl1);
		
		this.setLayout(null);
		this.add(centerPanel);
	}
	
	
}
