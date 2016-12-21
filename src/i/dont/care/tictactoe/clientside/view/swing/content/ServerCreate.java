package i.dont.care.tictactoe.clientside.view.swing.content;

import i.dont.care.tictactoe.clientside.view.swing.CenterPanel;
import i.dont.care.tictactoe.clientside.view.swing.ContentType;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentListener;

import javax.swing.*;
import java.awt.*;

public class ServerCreate extends BackButtonContent {

	private JTextField portEdt;
	private JButton createBtn;
	private JPanel buttonPanel;
	
	public ServerCreate(Rectangle rectangle, ContentType contentType, ContentListener listener, Content from) {
		super(rectangle, contentType, listener, from);
		init();
	}
	
	private void init() {
		portEdt = new JTextField("6660", 25);
		createBtn = new JButton("Создать");
				
		buttonPanel = new CenterPanel(this.getBounds(), 1, 5);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		buttonPanel.add(new JLabel("Порт: "));
		buttonPanel.add(portEdt);
		buttonPanel.add(createBtn);
		
		createBtn.addActionListener(e -> {
			String portStr = portEdt.getText();
			try {
				int port = Integer.parseInt(portStr);
				listener.handleContentEvent(this,
						ContentEvent.StartServerBtnClick, port);
			} catch(NumberFormatException ex) {
				//TODO сообщение
			}
		});
		
		this.setLayout(null);
		this.add(buttonPanel);
	}
	
	
}
