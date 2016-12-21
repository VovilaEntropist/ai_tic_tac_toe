package i.dont.care.tictactoe.serverside;

import i.dont.care.clientserver.RequestProcessor;
import i.dont.care.clientserver.message.*;
import i.dont.care.tictactoe.Configuration;
import i.dont.care.tictactoe.GameStage;
import i.dont.care.tictactoe.serverside.logic.TicTacToeChecker;
import i.dont.care.tictactoe.serverside.logic.Step;
import i.dont.care.tictactoe.serverside.logic.TicTacToeNode;
import i.dont.care.tictactoe.serverside.board.CellArray;
import i.dont.care.tictactoe.serverside.board.Mark;
import i.dont.care.utils.Index;

import java.util.HashSet;
import java.util.Observable;

public class TicTacToe extends Observable implements RequestProcessor {
	
	public static final int ROW_COUNT = 3;
	public static final int COLUMN_COUNT = 3;
	private static final int PLAYER_COUNT = 2;
	private static final int CHAIN_LENGTH = 3;

	private final CellArray currentBoard;
	private Step lastStep;
	private final PlayerCollection players;
	private Player movingPlayer;
	
	private GameStage stage;
	
	private PublicMessageCollection publicMessages;
	
	public TicTacToe() {
		stage = GameStage.Wait;
		players = new PlayerCollection();
		publicMessages = new PublicMessageCollection();
		currentBoard = new CellArray(ROW_COUNT, COLUMN_COUNT);
	}
	
	public MessageCollection doMove(Player player, Index index) {
		System.out.println("doMove " + player + " to " + index);
		
		MessageCollection response = new MessageCollection();
		
		if (!player.equals(movingPlayer)) {
			response.add(MessageFactory.createInvalidMove(player));
			return response;
		}
		
		if (stage == GameStage.Wait) {
			response.add(MessageFactory.createInvalidMove(player));
			return response;
		}
		
		if (!currentBoard.isValidIndex(index)) {
			response.add(MessageFactory.createInvalidMove(player));
			return response;
		}
		
		if (currentBoard.at(index).getMark() != Mark.Empty) {
			response.add(MessageFactory.createInvalidMove(player));
			return response;
		}
		
		Mark mark = player.getMark();

		if (lastStep != null && mark == lastStep.getMark()) {
			response.add(MessageFactory.createInvalidMove(player));
			return response;
		}
		
		lastStep = new Step(index, mark);
		Player nextPlayer = players.getNext(player);
		
		currentBoard.set(index, mark);
		response.add(MessageFactory.createEndMove(nextPlayer));
		
		response.add(MessageFactory.createGameStateChanged(currentBoard, nextPlayer));
		
		TicTacToeChecker checker = new TicTacToeChecker(mark, CHAIN_LENGTH);
		if (checker.isDecision(new TicTacToeNode(currentBoard, new Step(index, mark)))) {
			stage = GameStage.Win;
			//response.add(MessageFactory.createPlayerWin(player));
			//response.add(MessageFactory.createGameEnded());
		} else if (currentBoard.getEmptyCount() == 0) {
			stage = GameStage.Tie;
			//response.add(MessageFactory.createTie());
			//response.add(MessageFactory.createGameEnded());
		} else {
			movingPlayer = nextPlayer;
			//response.add(MessageFactory.createStartMove(nextPlayer));
		}
		
		return response;
	}
	
	
	public MessageCollection addPlayer(Player player) {
		System.out.println("connect " + player);
		
		MessageCollection response = new MessageCollection();
		
		if (players.size() >= PLAYER_COUNT) {
			response.add(MessageFactory.createKickPlayer(player, "Игроков слишком много"));
			return response;
		}
		
		if (players.isNameTaken(player)) {
			response.add(MessageFactory.createKickPlayer(player, "Такое имя уже используется"));
			return response;
		}
		
		if (players.isMarkTaken(player)) {
			response.add(MessageFactory.createKickPlayer(player, "Такая отметка уже используется"));
			return response;
		}
		
		if (stage != GameStage.Wait) {
			response.add(MessageFactory.createKickPlayer(player, "Игра уже идёт или закончилась"));
			return response;
		}
		
		players.add(player);
		
		if (players.size() == PLAYER_COUNT) {
			stage = GameStage.Active;
			movingPlayer = defineMovingPlayer();
			
			publicMessages.add(new PublicMessage(MessageFactory.createGameStarted(currentBoard, movingPlayer, players),
					new HashSet<>(players)));
		}
		
		return response;
	}
	
	private Player defineMovingPlayer() {
		for (Player player : players) {
			if (player.getMark() == Mark.Player1) {
				return player;
			}
		}
		
		return players.get(0);
	}
	
	public MessageCollection removePlayer(Player player) {
		System.out.println("disconnect " + player);
		
		MessageCollection response = new MessageCollection();
		
		if (players.remove(player)) {
			publicMessages.add(new PublicMessage(MessageFactory.createGameEnded(), new HashSet<>(players)));
		}
		
		return response;
	}
	
	private MessageCollection getState(Player player) {
		System.out.println("getState " + player);
		
		MessageCollection response = new MessageCollection();
		
		response.add(MessageFactory.createGameStateChanged(currentBoard, movingPlayer));
		response.addAll(publicMessages.grabMessageCollection(player));
		
		
		switch (stage) {
			case Win:
				response.add(MessageFactory.createPlayerWin(movingPlayer));
				response.add(MessageFactory.createGameEnded());
				break;
			case Tie:
				response.add(MessageFactory.createTie());
				response.add(MessageFactory.createGameEnded());
				break;
		}
		
		return response;
	}

	@Override
	public synchronized MessageCollection handleRequest(Message request) {
		int command = request.getCommand();
		
		MessageCollection response;
		
		switch (command) {
			case Configuration.CONNECT:
				response = addPlayer((Player) request.getParameter(Configuration.PLAYER));
				break;
			case Configuration.DISCONNECT:
				response = removePlayer((Player) request.getParameter(Configuration.PLAYER));
				break;
			case Configuration.MOVE:
				response = doMove((Player) request.getParameter(Configuration.PLAYER),
						(Index) request.getParameter(Configuration.INDEX));
				break;
			case Configuration.GET_STATE:
				response = getState((Player) request.getParameter(Configuration.PLAYER));
				break;
			default:
				response = new MessageCollection(MessageFactory.createInvalidCommand(command));
				break;
		}

		return response;
	}
}
