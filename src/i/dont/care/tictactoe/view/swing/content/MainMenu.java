package i.dont.care.tictactoe.view.swing.content;

import i.dont.care.tictactoe.view.swing.CenterPanel;
import i.dont.care.tictactoe.view.swing.ContentType;
import i.dont.care.tictactoe.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.view.swing.content.listener.ContentListener;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends Content {
	
	private JPanel buttonPanel;
	private JButton serverBtn;
	private JButton clientBtn;
	private JButton aiBtn;
	private JButton exitBtn;
	
	public MainMenu(Rectangle rectangle, ContentType contentType, ContentListener listener) {
		super(rectangle, contentType, listener);
		init();
		initListeners();
		repaint();
	}
	
	private void init() {
		serverBtn = new JButton("Создать игру");
		clientBtn = new JButton("Присоединиться к игре");
		aiBtn = new JButton("Игра с компьютером (Скоро)");
		exitBtn = new JButton("Выход");
		
		aiBtn.setEnabled(false);
		
		buttonPanel = new CenterPanel(this.getBounds(), 3, 3);
		GridLayout layout = new GridLayout(4, 1, 0, 25);
		buttonPanel.setLayout(layout);
		
		buttonPanel.add(serverBtn);
		buttonPanel.add(clientBtn);
		buttonPanel.add(aiBtn);
		buttonPanel.add(exitBtn);
		
		this.setLayout(null);
		this.add(buttonPanel);
	}
	
	private void initListeners() {
		serverBtn.addActionListener(e -> listener.handleContentEvent(this,
				ContentEvent.ServerBtnClick, null));
		clientBtn.addActionListener(e -> listener.handleContentEvent(this,
				ContentEvent.ConnectBtnClick, null));
		aiBtn.addActionListener(e -> listener.handleContentEvent(this,
				ContentEvent.AiBtnClick, null));
		exitBtn.addActionListener(e -> listener.handleContentEvent(this,
				ContentEvent.ExitBtnClick, null));
	}
	
	
	
	
}
