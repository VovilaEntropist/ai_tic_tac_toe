package i.dont.care.tictactoe.clientside.view.swing;

import i.dont.care.tictactoe.clientside.view.swing.content.Content;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentListener;
import i.dont.care.tictactoe.serverside.board.CellArray;
import i.dont.care.tictactoe.serverside.board.Mark;
import i.dont.care.utils.Index;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Board extends JPanel {
	
	public static final int BORDER = 25;
	
	private ContentListener listener;
	private Content content;
	
	private BufferedImage xImage;
	private BufferedImage oImage;
	
	private Tile[][] tiles;
	
	public Board(Rectangle rectangle, int rows, int columns, Content content, ContentListener listener,
	             BufferedImage xImage, BufferedImage oImage) {
		this.setBounds(rectangle);
		this.setLayout(null);
		this.xImage = xImage;
		this.oImage = oImage;
		this.content = content;
		this.listener = listener;
		
		init(rows, columns);
	}
	
	private void init(int rows, int columns) {
		int tileWidth = (this.getWidth() - (columns - 1) * BORDER)/ columns;
		int tileHeight = (this.getHeight() - (rows - 1) * BORDER) / rows;
		
		tiles = new Tile[rows][];
		for (int i = 0; i < rows; i++) {
			tiles[i] = new Tile[columns];
			for (int j = 0; j < columns; j++) {
				int xPos = j * (tileWidth + BORDER);
				int yPos = i * (tileHeight + BORDER);
				tiles[i][j] = new Tile(new Rectangle(xPos, yPos, tileWidth, tileHeight),
						new Index(i, j), Mark.Empty, xImage, oImage, content, listener);
				
				this.add(tiles[i][j]);
			}
		}
	}
	
	public void updateBoard(CellArray cellArray) {
		//TODO проверка на размерность
		cellArray.forEach((i, cell) -> tiles[i.row()][i.column()].setMark(cell.getMark()));
	}
	
}
