package i.dont.care.tictactoe.model.logic;

import i.dont.care.tictactoe.model.board.Mark;
import i.dont.care.utils.Index;

public class Step {
	
	private Index index;
	private Mark mark;
	
	public Step(Index index, Mark mark) {
		this.index = index;
		this.mark = mark;
	}
	
	public Index getIndex() {
		return index;
	}
	
	public Mark getMark() {
		return mark;
	}
}
