package i.dont.care.tictactoe.model.ai;

import i.dont.care.message.Message;
import i.dont.care.search.*;
import i.dont.care.search.interfaces.Search;
import i.dont.care.tictactoe.model.Configuration;
import i.dont.care.tictactoe.model.board.CellArray;
import i.dont.care.tictactoe.model.logic.GameState;
import i.dont.care.tictactoe.model.logic.Step;
import i.dont.care.tictactoe.model.logic.TicTacToeNode;
import i.dont.care.tictactoe.mvc.IController;
import i.dont.care.tictactoe.model.Player;
import i.dont.care.utils.Index;

import java.util.Observable;
import java.util.Observer;

public class AIProcessor implements Observer {
	
	private IController controller;
	private CellArray board;
	private Step lastStep;
	private Player player;
	private Search search;
	
	public AIProcessor(IController controller, Player player, Search search) {
		this.controller = controller;
		this.player = player;
		this.search = search;
	}
	
	public void doMove(Index position) {
		controller.doMove(player, position);
	}
	
	public void uploadInfo(String info) {
		controller.uploadInfo(info);
	}
	
	private void prepareMove(Player player) {
		if (!this.player.equals(player)) {
			return;
		}
		
		AbstractGraphNode initialNode = new TicTacToeNode(null, new GameState(board, lastStep));
		
		SearchResult searchResult = search.search(initialNode);
		NodeCollection nodes = searchResult.getBranch();
		
		if (nodes != null && nodes.size() >= 1) {
			TicTacToeNode nextNode = (TicTacToeNode) nodes.get(1);
			
			doMove(nextNode.getGameState().getLastStep().getIndex());
			//uploadInfo(searchResult.getSearchInfo().toString());
		} else {
			doMove(board.getAnyEmpty());
		}
		
		
	}

	private void updateBoard(CellArray board, Step lastStep) {
		this.board = board;
		this.lastStep = lastStep;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Message message = (Message) arg;
		
		CellArray board = (CellArray) message.getParameter(Configuration.BOARD);
		Step lastStep = (Step) message.getParameter(Configuration.STEP);
		Player player = (Player) message.getParameter(Configuration.PLAYER);
		
		int command = message.getCommand();
		switch (command) {
			case Configuration.START_OF_MOVE:
				prepareMove(player);
				break;
			case Configuration.GAME_STARTED:
				updateBoard(board, null);
				break;
			case Configuration.BOARD_CHANGED:
				updateBoard(board, lastStep);
				break;
			case Configuration.INVALID_COMMAND:
			case Configuration.GAME_ENDED:
			case Configuration.PLAYER_WIN:
			case Configuration.END_OF_MOVE:
			case Configuration.KICK_PLAYER:
			case Configuration.INVALID_MOVE:
			case Configuration.TIE:
				break;
		}
	}
}
