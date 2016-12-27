package i.dont.care.utils;

import java.io.Serializable;

public class Index implements Serializable {
	
	private int row;
	private int column;
	
	public Index(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int row() {
		return row;
	}
	
	public int column() {
		return column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	@Override
	public String toString() {
		return String.format("[%d, %d]", row, column);
	}
}
