package i.dont.care.tictactoe.clientside.view.swing.content;

import i.dont.care.tictactoe.clientside.view.swing.Board;
import i.dont.care.tictactoe.clientside.view.swing.ContentType;
import i.dont.care.tictactoe.clientside.view.swing.ImageLoader;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentListener;

import java.awt.*;
import java.io.File;

public class Game extends Content {
	
	private Board board;
	
	private String xImagePath;
	private String oImagePath;
	
	public Game(Rectangle rectangle, ContentType contentType, ContentListener listener,
	            String xImagePath, String oImagePath) {
		super(rectangle, contentType, listener);
		this.xImagePath = xImagePath;
		this.oImagePath = oImagePath;
		init();
	}
	
	private void init() {
		board = new Board(this.getBounds(), 3, 3, this, listener,
				ImageLoader.load(new File(xImagePath)),
				ImageLoader.load(new File(oImagePath)));
		
		this.setLayout(null);
		this.add(board);
	}
	
	public Board getBoard() {
		return board;
	}
}
