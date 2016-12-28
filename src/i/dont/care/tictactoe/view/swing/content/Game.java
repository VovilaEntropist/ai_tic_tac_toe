package i.dont.care.tictactoe.view.swing.content;

import i.dont.care.tictactoe.view.swing.Board;
import i.dont.care.tictactoe.view.swing.ContentType;
import i.dont.care.tictactoe.view.swing.ImageLoader;
import i.dont.care.tictactoe.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.view.swing.content.listener.ContentListener;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Game extends Content {
	
	private Board board;
	private JTextArea infoPanel;
	private JButton backBtn;
	
	private String xImagePath;
	private String oImagePath;
	private ContentType from;
	
	public Game(JPanel parent, ContentType contentType, ContentListener listener,
	            String xImagePath, String oImagePath, ContentType from) {
		super(parent, contentType, listener);
		this.xImagePath = xImagePath;
		this.oImagePath = oImagePath;
		this.from = from;
		init();
	}
	
	private void init() {
		backBtn = new JButton("Вернуться в меню");
		backBtn.setEnabled(false);
		
		infoPanel = new JTextArea(1, 30);
		infoPanel.setLineWrap(true);
		infoPanel.setEditable(false);
		board = new Board(this.getBounds(), 3, 3, this, listener,
				ImageLoader.load(new File(xImagePath)),
				ImageLoader.load(new File(oImagePath)));
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		
		leftPanel.add(new JScrollPane(infoPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		leftPanel.add(backBtn, BorderLayout.SOUTH);
		
		this.setLayout(new BorderLayout());
		this.add(board, BorderLayout.CENTER);
		this.add(leftPanel, BorderLayout.EAST);
		
		backBtn.addActionListener(e -> listener.handleContentEvent(this,
				ContentEvent.BackMainMenu, null));
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void showInfo(String info) {
		infoPanel.append(info);
	}
	
	public void showBackButton(boolean show) {
		backBtn.setEnabled(show);
	}
	
}
