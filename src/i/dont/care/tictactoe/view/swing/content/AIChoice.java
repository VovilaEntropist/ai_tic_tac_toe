package i.dont.care.tictactoe.view.swing.content;

import i.dont.care.tictactoe.view.swing.CenterPanel;
import i.dont.care.tictactoe.view.swing.ContentType;
import i.dont.care.tictactoe.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.view.swing.content.listener.ContentListener;

import javax.swing.*;
import java.awt.*;

public class AIChoice extends Content {
	
	private JPanel buttonPanel;
	private JButton dfsBtn;
	private JButton minMaxBtn;
	private JButton alphaBettaBtn;
	private JButton bfsBtn;
	
	public AIChoice(JPanel parent, ContentType contentType, ContentListener listener) {
		super(parent, contentType, listener);
		init();
		initListeners();
		repaint();
	}
	
	private void init() {
		dfsBtn = new JButton("Поиск в глубину");
		bfsBtn = new JButton("Поиск в ширину");
		minMaxBtn = new JButton("Минимаксный алгоритм");
		alphaBettaBtn = new JButton("Альфа бетта отсечение");
		
		buttonPanel = new CenterPanel(this.getBounds(), 3, 3);
		GridLayout layout = new GridLayout(4, 1, 0, 25);
		buttonPanel.setLayout(layout);
		
		buttonPanel.add(dfsBtn);
		buttonPanel.add(bfsBtn);
		buttonPanel.add(minMaxBtn);
		buttonPanel.add(alphaBettaBtn);
		
		this.setLayout(null);
		this.add(buttonPanel);
	}
	
	private void initListeners() {
		dfsBtn.addActionListener(e -> listener.handleContentEvent(this,
				ContentEvent.DfsBtnClick, null));
		bfsBtn.addActionListener(e -> listener.handleContentEvent(this,
				ContentEvent.BfsBtnClick, null));
		minMaxBtn.addActionListener(e -> listener.handleContentEvent(this,
				ContentEvent.minMaxBtnClick, null));
		alphaBettaBtn.addActionListener(e -> listener.handleContentEvent(this,
				ContentEvent.alphaBettaBtnClick, null));
	}
	
	
	
	
}
