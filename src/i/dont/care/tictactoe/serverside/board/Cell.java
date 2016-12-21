package i.dont.care.tictactoe.serverside.board;

import java.io.Serializable;

public class Cell implements Serializable {
	
	private Mark mark;
	
//	public Cell() {
//		this.mark = Mark.Empty;
//	}
	
	public Cell(Mark mark) {
		this.mark = mark;
	}
	
	public Mark getMark() {
		return mark;
	}
	
	public void setMark(Mark mark) {
		this.mark = mark;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Cell cell = (Cell) o;
		
		return mark == cell.mark;
	}
	
	@Override
	public int hashCode() {
		return mark != null ? mark.hashCode() : 0;
	}
	
	@Override
	public String toString() {
		return String.valueOf(mark.getChar());
	}
}
