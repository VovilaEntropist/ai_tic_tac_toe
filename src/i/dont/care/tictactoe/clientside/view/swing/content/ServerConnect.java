package i.dont.care.tictactoe.clientside.view.swing.content;

import i.dont.care.tictactoe.clientside.view.swing.CenterPanel;
import i.dont.care.tictactoe.clientside.view.swing.ContentType;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentListener;

import javax.swing.*;
import java.awt.*;

public class ServerConnect extends BackButtonContent {
	
	private JTextField ipEdt;
	private JTextField portEdt;
	private JButton connectBtn;
	
	public ServerConnect(Rectangle rectangle, ContentType contentType,
	                     ContentListener listener, Content from) {
		super(rectangle, contentType, listener, from);
		init();
	}
		
	private void init() {
		ipEdt = new JTextField("localhost", 25);
		portEdt = new JTextField("6660", 10);
		connectBtn = new JButton("Присоединиться");
		
		JPanel centerPanel = new CenterPanel(this.getBounds(), 1, 5);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		centerPanel.add(new JLabel("IP: "));
		centerPanel.add(ipEdt);
		centerPanel.add(new JLabel("Порт: "));
		centerPanel.add(portEdt);
		centerPanel.add(connectBtn);
		
		connectBtn.addActionListener(e -> {
			String portStr = portEdt.getText();
			String ip = ipEdt.getText();
			
			try {
				int port = Integer.parseInt(portStr);
				listener.handleContentEvent(this, ContentEvent.ConnectServerBtnClick, ip, port);
			} catch(NumberFormatException ex) {
				//TODO сообщение
			}
		});
		
		this.setLayout(null);
		this.add(centerPanel);
	}
	
}
