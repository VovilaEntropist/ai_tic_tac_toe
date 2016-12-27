package i.dont.care.tictactoe.model.board;

import i.dont.care.tictactoe.model.Player;

import java.io.Serializable;

public enum Mark implements Serializable {
	Empty('_'),
	Player1('X'),
	Player2('0');
	
	private char value;
	
	Mark(char value) {
		this.value = value;
	}
	
	public char getChar() {
		return value;
	}
	
	public Mark getNext() {
		Mark[] marks = Mark.values();
		for (int i = 0; i < marks.length; i++) {
			if (this.equals(marks[i])) {
				return  i == marks.length - 1 ? marks[i] : marks[i + 1];
			}
		}

		return marks[0];
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
