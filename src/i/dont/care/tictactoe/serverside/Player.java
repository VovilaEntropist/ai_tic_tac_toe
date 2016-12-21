package i.dont.care.tictactoe.serverside;

import i.dont.care.tictactoe.serverside.board.Mark;

import java.io.Serializable;

public class Player implements Serializable {
	private String nickname;
	private Mark mark;
	private String imagePath;
	private boolean ai;
	
	public Player(String nickname, Mark mark, String imagePath, boolean ai) {
		this.nickname = nickname;
		this.mark = mark;
		this.imagePath = imagePath;
		this.ai = ai;
	}
	
//	public Player(String nickname, Mark mark, boolean ai) {
//		this.nickname = nickname;
//		this.mark = mark;
//		this.ai = ai;
//	}
	
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
	
	public boolean isAi() {
		return ai;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Player player = (Player) o;
		
		if (ai != player.ai) return false;
		if (nickname != null ? !nickname.equals(player.nickname) : player.nickname != null) return false;
		return mark == player.mark;
	}
	
	@Override
	public int hashCode() {
		int result = nickname != null ? nickname.hashCode() : 0;
		result = 31 * result + (mark != null ? mark.hashCode() : 0);
		result = 31 * result + (ai ? 1 : 0);
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("Player: %s, %s", nickname, String.valueOf(mark.getChar()));
	}
}
