package i.dont.care.clientserver.message;

import i.dont.care.tictactoe.Configuration;
import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.tictactoe.serverside.PlayerCollection;
import i.dont.care.tictactoe.serverside.board.CellArray;
import i.dont.care.utils.Index;

public class MessageFactory {
	
	public static Message createGameStarted(CellArray board, Player movingPlayer, PlayerCollection players) {
		Message message = new Message(Configuration.GAME_STARTED);
		message.addParameter(Configuration.BOARD, board);
		message.addParameter(Configuration.PLAYER, movingPlayer);
		message.addParameter(Configuration.PLAYERS, players);
		return message;
	}
	
	public static Message createEndMove(Player player) {
		Message message = new Message(Configuration.END_OF_MOVE);
		message.addParameter(Configuration.PLAYER, player);
		return message;
	}
	
	public static Message createStartMove(Player player) {
		Message message = new Message(Configuration.START_OF_MOVE);
		message.addParameter(Configuration.PLAYER, player);
		return message;
	}
	
	public static Message createGameStateChanged(CellArray board, Player movingPlayer) {
		Message message = new Message(Configuration.BOARD_CHANGED);
		message.addParameter(Configuration.BOARD, board);
		message.addParameter(Configuration.PLAYER, movingPlayer);
		return message;
	}
	
	public static Message createTie() {
		Message message = new Message(Configuration.TIE);
		return message;
	}
	
	public static Message createPlayerWin(Player player) {
		Message message = new Message(Configuration.PLAYER_WIN);
		message.addParameter(Configuration.PLAYER, player);
		return message;
	}
	
	public static Message createGameEnded() {
		Message message = new Message(Configuration.GAME_ENDED);
		return message;
	}
	
	public static Message createKickPlayer(Player player, String reason) {
		Message message = new Message(Configuration.KICK_PLAYER);
		message.addParameter(Configuration.PLAYER, player);
		message.addParameter(Configuration.REASON, reason);
		return message;
	}
	
	public static Message createInvalidMove(Player player) {
		Message message = new Message(Configuration.INVALID_MOVE);
		message.addParameter(Configuration.PLAYER, player);
		return message;
	}
	
	public static Message createMove(Player player, Index position) {
		Message message = new Message(Configuration.MOVE);
		message.addParameter(Configuration.PLAYER, player);
		message.addParameter(Configuration.INDEX, position);
		return message;
	}
	
	public static Message createConnectPlayer(Player player) {
		Message message = new Message(Configuration.CONNECT);
		message.addParameter(Configuration.PLAYER, player);
		return message;
	}
	
	public static Message createDisconnectPlayer(Player player) {
		Message message = new Message(Configuration.DISCONNECT);
		message.addParameter(Configuration.PLAYER, player);
		return message;
	}
	
	public static Message createGetGameState(Player player) {
		Message message = new Message(Configuration.GET_STATE);
		message.addParameter(Configuration.PLAYER, player);
		return message;
	}
	
	public static Message createInvalidCommand(int command) {
		Message message = new Message(Configuration.INVALID_COMMAND);
		message.addParameter(Configuration.COMMAND, command);
		return message;
	}
	
	public static Message createConnectionError(String text) {
		Message message = new Message(Configuration.CONNECTION_ERROR);
		message.addParameter(Configuration.REASON, text);
		return message;
	}
	
	public static Message createGameStop() {
		Message message = new Message(Configuration.GAME_STOP);
		return message;
	}
}
