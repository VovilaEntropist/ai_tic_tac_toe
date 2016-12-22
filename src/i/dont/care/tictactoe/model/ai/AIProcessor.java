package i.dont.care.tictactoe.model.ai;

import i.dont.care.clientserver.message.Message;
import i.dont.care.search.SearchResult;
import i.dont.care.search.interfaces.GraphNode;
import i.dont.care.search.interfaces.Search;
import i.dont.care.tictactoe.model.Configuration;
import i.dont.care.tictactoe.model.board.CellArray;
import i.dont.care.tictactoe.model.logic.Step;
import i.dont.care.tictactoe.model.logic.TicTacToeChecker;
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
	
	public void doMove(Player player, Index position) {
		controller.doMove(player, position);
	}
	
	public void uploadInfo(String info) {
		controller.uploadInfo(info);
	}
	
	private void prepareMove(Player player) {
		GraphNode initialNode = new TicTacToeNode(board, lastStep);
		
		for (GraphNode node : initialNode.getAdjacentNodes()) {
			SearchResult searchResult = search.search(node, new TicTacToeChecker(player.getMark(),
					Configuration.CHAIN_TO_WIN));
			
			if (searchResult != null) {
				Step betterStep = ((TicTacToeNode) node).getLastStep();
				
				doMove(player, betterStep.getIndex());
				uploadInfo(searchResult.getSearchInfo().toString());
				return;
			}
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
			case Configuration.BOARD_CHANGED:
				updateBoard(board, lastStep);
				break;
			case Configuration.INVALID_COMMAND:
			case Configuration.GAME_ENDED:
			case Configuration.GAME_STARTED:
			case Configuration.PLAYER_WIN:
			case Configuration.END_OF_MOVE:
			case Configuration.KICK_PLAYER:
			case Configuration.INVALID_MOVE:
			case Configuration.TIE:
				break;
		}
	}
}
