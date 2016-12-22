package i.dont.care.tictactoe.model;

import i.dont.care.tictactoe.model.board.Mark;

import java.io.Serializable;

public class Player implements Serializable {
	private String nickname;
	private Mark mark;
	private String imagePath;

	public Player(String nickname, Mark mark, String imagePath) {
		this.nickname = nickname;
		this.mark = mark;
		this.imagePath = imagePath;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public Mark getMark() {
		return mark;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Player player = (Player) o;
		
		if (nickname != null ? !nickname.equals(player.nickname) : player.nickname != null) return false;
		if (mark != player.mark) return false;
		return imagePath != null ? imagePath.equals(player.imagePath) : player.imagePath == null;
	}
	
	@Override
	public int hashCode() {
		int result = nickname != null ? nickname.hashCode() : 0;
		result = 31 * result + (mark != null ? mark.hashCode() : 0);
		result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("Player: %s, %s", nickname, String.valueOf(mark.getChar()));
	}
}
