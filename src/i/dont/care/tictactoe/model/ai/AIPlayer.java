package i.dont.care.tictactoe.model.ai;

import i.dont.care.search.interfaces.Search;
import i.dont.care.tictactoe.model.Player;
import i.dont.care.tictactoe.model.board.Mark;

public class AIPlayer extends Player {
	
	private Search search;
	
	public AIPlayer(String nickname, Mark mark, String imagePath, Search search) {
		super(nickname, mark, imagePath);
		this.search = search;
	}
	
	public Search getSearch() {
		return search;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		
		AIPlayer aiPlayer = (AIPlayer) o;
		
		return search != null ? search.equals(aiPlayer.search) : aiPlayer.search == null;
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (search != null ? search.hashCode() : 0);
		return result;
	}
}
