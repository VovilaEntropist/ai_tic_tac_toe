package i.dont.care.tictactoe.model;

import i.dont.care.message.Message;
import i.dont.care.message.MessageFactory;
import i.dont.care.tictactoe.mvc.IModel;
import i.dont.care.tictactoe.model.logic.TicTacToeChecker;
import i.dont.care.tictactoe.model.logic.Step;
import i.dont.care.tictactoe.model.logic.TicTacToeNode;
import i.dont.care.tictactoe.model.board.CellArray;
import i.dont.care.tictactoe.model.board.Mark;
import i.dont.care.utils.Index;

import java.util.Observable;
import java.util.Observer;

public class TicTacToe extends Observable implements IModel {
	
	public static final int ROW_COUNT = 3;
	public static final int COLUMN_COUNT = 3;
	private static final int PLAYER_COUNT = 2;
	private static final int CHAIN_LENGTH = 3;

	private final CellArray currentBoard;
	private Step lastStep;
	private final PlayerCollection players;
	private Player movingPlayer;
	private GameStage stage;

	public TicTacToe() {
		stage = GameStage.Wait;
		players = new PlayerCollection();
		currentBoard = new CellArray(ROW_COUNT, COLUMN_COUNT);
	}
	
	public void doMove(Player player, Index index) {
		if (!player.equals(movingPlayer)) {
			notifyView(MessageFactory.createInvalidMove(player));
			return;
		}
		
		if (stage == GameStage.Wait) {
			notifyView(MessageFactory.createInvalidMove(player));
			return;
		}
		
		if (!currentBoard.isValidIndex(index)) {
			notifyView(MessageFactory.createInvalidMove(player));
			return;
		}
		
		if (currentBoard.at(index).getMark() != Mark.Empty) {
			notifyView(MessageFactory.createInvalidMove(player));
			return;
		}
		
		Mark mark = player.getMark();

		if (lastStep != null && mark == lastStep.getMark()) {
			notifyView(MessageFactory.createInvalidMove(player));
			return;
		}
		
		lastStep = new Step(index, mark);
		Player nextPlayer = players.getNext(player);
		
		currentBoard.set(index, mark);
		notifyView(MessageFactory.createEndMove(player));
		
		notifyView(MessageFactory.createGameStateChanged(currentBoard, lastStep, nextPlayer));
		
		TicTacToeChecker checker = new TicTacToeChecker(mark, CHAIN_LENGTH);
		if (checker.isDecision(new TicTacToeNode(currentBoard, new Step(index, mark)))) {
			stage = GameStage.Win;
			notifyView(MessageFactory.createPlayerWin(player));
			notifyView(MessageFactory.createGameEnded());
		} else if (currentBoard.getEmptyCount() == 0) {
			stage = GameStage.Tie;
			notifyView(MessageFactory.createTie());
			notifyView(MessageFactory.createGameEnded());
		} else {
			movingPlayer = nextPlayer;
			notifyView(MessageFactory.createStartMove(nextPlayer));
		}
	}
	
	public void addPlayer(Player player) {
		if (players.size() >= PLAYER_COUNT) {
			notifyView(MessageFactory.createKickPlayer(player, "Игроков слишком много"));
			return;
		}
		
		if (players.isNameTaken(player)) {
			notifyView(MessageFactory.createKickPlayer(player, "Такое имя уже используется"));
			return;
		}
		
		if (players.isMarkTaken(player)) {
			notifyView(MessageFactory.createKickPlayer(player, "Такая отметка уже используется"));
			return;
		}
		
		if (stage != GameStage.Wait) {
			notifyView(MessageFactory.createKickPlayer(player, "Игра уже идёт или закончилась"));
			return;
		}
		
		players.add(player);
		
		if (players.size() == PLAYER_COUNT) {
			stage = GameStage.Active;
			movingPlayer = defineMovingPlayer();
			notifyView(MessageFactory.createGameStarted(currentBoard, movingPlayer, players));
			notifyView(MessageFactory.createStartMove(movingPlayer));
		}
	}
	
	private Player defineMovingPlayer() {
		for (Player player : players) {
			if (player.getMark() == Mark.Player1) {
				return player;
			}
		}
		
		return players.get(0);
	}
	
	public void removePlayer(Player player) {
		if (players.remove(player)) {
			notifyView(MessageFactory.createGameEnded());
		}
	}
	
	@Override
	public void uploadInfo(String info) {
		
	}
	
	@Override
	public void addViewObserver(Observer observer) {
		addObserver(observer);
	}
	
	private void notifyView(Message response) {
		setChanged();
		notifyObservers(response);
	}
	

}
