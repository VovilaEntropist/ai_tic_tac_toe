package i.dont.care.tictactoe.serverside;

import java.util.ArrayList;

public class PlayerCollection extends ArrayList<Player> {
	
	public boolean isNameTaken(Player somePlayer) {
		for (Player player : this) {
			if (player.getNickname().equals(somePlayer.getNickname())) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isMarkTaken(Player somePlayer) {
		for (Player player : this) {
			if (player.getMark().equals(somePlayer.getMark())) {
				return true;
			}
		}
		
		return false;
	}
	
	public Player getNext(Player previous) {
		for (int i = 0; i < this.size(); i++) {
			if (previous.equals(this.get(i))) {
				return  i == this.size() - 1 ? this.get(0) : this.get(i + 1);
			}
		}
		
		//TODO Возможно тут лучше бахнуть эксепшн
		return null;
	}
	
	
}
