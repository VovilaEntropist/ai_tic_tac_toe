package i.dont.care.tictactoe.model;

import i.dont.care.tictactoe.model.board.Mark;

import java.io.Serializable;

public class Player implements Serializable {
	private String nickname;
	private Mark mark;

	public Player(String nickname, Mark mark) {
		this.nickname = nickname;
		this.mark = mark;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public Mark getMark() {
		return mark;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Player player = (Player) o;
		
		if (nickname != null ? !nickname.equals(player.nickname) : player.nickname != null) return false;
		return mark == player.mark;
	}
	
	@Override
	public int hashCode() {
		int result = nickname != null ? nickname.hashCode() : 0;
		result = 31 * result + (mark != null ? mark.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("Player: %s, %s", nickname, String.valueOf(mark.getChar()));
	}
}
