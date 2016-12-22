package i.dont.care.tictactoe.mvc;

import i.dont.care.tictactoe.model.Player;
import i.dont.care.utils.Index;

import java.util.Observer;

public interface IModel {
	
	void doMove(Player player, Index position);
	
	void addPlayer(Player player);
	
	void removePlayer(Player player);
	
	void addViewObserver(Observer observer);
	
	void uploadInfo(String info);
}
