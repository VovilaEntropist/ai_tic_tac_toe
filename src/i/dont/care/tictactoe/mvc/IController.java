package i.dont.care.tictactoe.mvc;

import i.dont.care.tictactoe.model.Player;
import i.dont.care.utils.Index;

public interface IController {
	
	void doMove(Player player, Index position);
	
	void addPlayer(Player player);
	
	void removePlayer(Player player);
	
	void uploadInfo(String info);
}
