package i.dont.care.tictactoe.clientside.mvc;

import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.tictactoe.serverside.board.CellArray;
import i.dont.care.utils.Index;

import java.util.Observer;

public interface IModel {
	
	void doMove(Index position);
	
	void addPlayer(Player player, String ip, int port);
	
	void removePlayer();
	
	void startServer(int port);
	
	void stopServer();
	
	void startAIGame();
	
	default void notifyGameStarted(CellArray cellArray) {
		
	}
	
	default void notifyPlayerGoes(Player targetPlayer) {
		
	}
	
	default void notifyBoardChanged(CellArray cellArray) {
		
	}
	
	default void notifyPlayerEndsOfMove(Player targetPlayer) {
		
	}
	
	default void notifyInvalidMove(Player targetPlayer) {
		
	}
	
	default void notifyPlayerKicked(Player targetPlayer, String reason) {
		
	}
	
	default void notifyPlayerWin(Player winner) {
		
	}
	
	default void notifyTie() {
		
	}
	
	default void notifyGameEnded() {
		
	}
	
	void addViewObserver(Observer observer);
	
}
