package i.dont.care.tictactoe.view.swing.content;

import i.dont.care.tictactoe.view.swing.CenterPanel;
import i.dont.care.tictactoe.view.swing.ContentType;
import i.dont.care.tictactoe.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.view.swing.content.listener.ContentListener;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends Content {
	
	private JPanel buttonPanel;
	private JButton vsPlayerBtn;
	private JButton vsAIButton;
	private JButton exitBtn;
	
	public MainMenu(JPanel parent, ContentType contentType, ContentListener listener) {
		super(parent, contentType, listener);
		init();
		initListeners();
		repaint();
	}
	
	private void init() {
		vsPlayerBtn = new JButton("Игрок против игрока");
		vsAIButton = new JButton("Игра против компьютера");
		exitBtn = new JButton("Выход");
		
		vsPlayerBtn.setEnabled(false);
		
		buttonPanel = new CenterPanel(this.getBounds(), 3, 3);
		GridLayout layout = new GridLayout(4, 1, 0, 25);
		buttonPanel.setLayout(layout);
		
		buttonPanel.add(vsPlayerBtn);
		buttonPanel.add(vsAIButton);
		buttonPanel.add(exitBtn);
		
		this.setLayout(null);
		this.add(buttonPanel);
	}
	
	private void initListeners() {
		vsPlayerBtn.addActionListener(e -> listener.handleContentEvent(this,
				ContentEvent.VsPlayerBtnClick, null));
		vsAIButton.addActionListener(e -> listener.handleContentEvent(this,
				ContentEvent.VsAIBtnClick, null));
		exitBtn.addActionListener(e -> listener.handleContentEvent(this,
				ContentEvent.ExitBtnClick, null));
	}
	
	
	
	
}
