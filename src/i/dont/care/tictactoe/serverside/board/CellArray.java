package i.dont.care.tictactoe.serverside.board;

import i.dont.care.utils.Index;
import i.dont.care.utils.wrappers.IntWrapper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.BiConsumer;

public class CellArray implements Serializable {
	
	private Cell[][] cells;
	private char test;
	
	public CellArray(int rows, int columns) {
		initArray(rows, columns);
		test =  '2';
	}
	
	private void initArray(int rows, int columns) {
		cells = new Cell[rows][];
		for (int i = 0; i < rows; i++) {
			cells[i] = new Cell[columns];
			for (int j = 0; j < columns; j++) {
				cells[i][j] = new Cell(Mark.Empty);
			}
		}
	}
	
	
	public CellArray copy() {
		CellArray cellArray = new CellArray(getRows(), getColumns());
		cellArray.forEach((i, cell) -> cell.setMark(at(i).getMark()));
		return cellArray;
	}
	
	public void fill(CellArray someCellArray) {
		this.forEach((i, cell) -> cell.setMark(someCellArray.at(i).getMark()));
	}
	
	public boolean isValidIndex(Index i) {
		return isValidIndex(i.row(), i.column());
	}
	
	public boolean isValidIndex(int row, int collumn) {
		return row >= 0 && collumn >= 0
				&& row < getRows() && collumn < getColumns();
	}
	
	public int getRows() {
		return cells.length;
	}
	
	public int getColumns() {
		return cells[0].length;
	}
	
	public Cell at(int row, int column) {
		return cells[row][column];
	}
	
	public Cell at(Index i) {
		return at(i.row(), i.column());
	}
		
	public void set(int row, int column, Mark mark) {
		cells[row][column].setMark(mark);
	}
	
	public void set(Index index, Mark mark) {
		set(index.row(), index.column(), mark);
		test = mark.getChar();
	}
	
	public void forEach(BiConsumer<Index, Cell> c) {
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				c.accept(new Index(i, j), cells[i][j]);
			}
		}
	}
	
	public void forEachEmpty(BiConsumer<Index, Cell> c) {
		forEach((index, cell) -> {
			if (cell.getMark() == Mark.Empty) {
				c.accept(index, cell);
			}
		});
	}
	
	public int getEmptyCount() {
		IntWrapper counter = new IntWrapper();
		forEachEmpty((index, mark) -> counter.value++);
		return counter.value;
	}
	
	public boolean isEmpty() {
		return getEmptyCount() == getRows() * getColumns();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		CellArray cellArray = (CellArray) o;
		
		return Arrays.deepEquals(cells, cellArray.cells);
	}
	
	@Override
	public int hashCode() {
		return Arrays.deepHashCode(cells);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				sb.append(cells[i][j]);
			}
			sb.append("\n");
		}
		sb.append(test);
		
		return sb.toString();
	}
}
