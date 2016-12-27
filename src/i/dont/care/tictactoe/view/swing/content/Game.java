package i.dont.care.tictactoe.view.swing.content;

import i.dont.care.tictactoe.view.swing.Board;
import i.dont.care.tictactoe.view.swing.ContentType;
import i.dont.care.tictactoe.view.swing.ImageLoader;
import i.dont.care.tictactoe.view.swing.content.listener.ContentListener;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Game extends Content {
	
	private Board board;
	private JTextArea infoPanel;
	
	private String xImagePath;
	private String oImagePath;
	
	public Game(JPanel parent, ContentType contentType, ContentListener listener,
	            String xImagePath, String oImagePath) {
		super(parent, contentType, listener);
		this.xImagePath = xImagePath;
		this.oImagePath = oImagePath;
		init();
	}
	
	private void init() {
		infoPanel = new JTextArea(100, 25);
		infoPanel.setLineWrap(true);
		board = new Board(this.getBounds(), 3, 3, this, listener,
				ImageLoader.load(new File(xImagePath)),
				ImageLoader.load(new File(oImagePath)));
		
		this.setLayout(new BorderLayout());
		this.add(board, BorderLayout.CENTER);
		this.add(new JScrollPane(infoPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.EAST);
	}
	
	public Board getBoard() {
		return board;
	}
}
