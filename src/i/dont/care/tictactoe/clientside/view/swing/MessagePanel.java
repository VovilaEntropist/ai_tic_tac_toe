package i.dont.care.tictactoe.clientside.view.swing;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
	
	private JLabel messageLbl;
	
	private Color defaultTextColor;
	
	public MessagePanel() {
		super();
		init();
	}
	
	private void init() {
		messageLbl = new JLabel();
		defaultTextColor = messageLbl.getForeground();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(messageLbl);
	}
	
	public void setError(String message, int time) {
		messageLbl.setForeground(Color.RED);
		messageLbl.setText(message);
		setDefaultAfter(time);
	}
	
	public void setMessage(String message, int time) {
		messageLbl.setForeground(defaultTextColor);
		messageLbl.setText(message);
		setDefaultAfter(time);
	}
	
	private void setDefaultAfter(int time) {
		if (time == 0) {
			return;
		}
		
//		new Timer(time, e -> {
//			messageLbl.setText("");
//			messageLbl.setForeground(defaultTextColor);
//			repaint();
//		}).start();
	}
}
