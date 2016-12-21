package i.dont.care.tictactoe.clientside.model;

import i.dont.care.clientserver.Client;
import i.dont.care.clientserver.Server;
import i.dont.care.clientserver.message.Message;
import i.dont.care.clientserver.message.MessageCollection;
import i.dont.care.clientserver.message.MessageFactory;
import i.dont.care.tictactoe.Configuration;
import i.dont.care.tictactoe.clientside.mvc.IModel;
import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.tictactoe.serverside.TicTacToe;
import i.dont.care.tictactoe.serverside.board.CellArray;
import i.dont.care.utils.Index;

import java.util.Observable;
import java.util.Observer;

public class Model extends Observable implements IModel {
	
	private Player player;
	private CellArray board;
	private Player movingPlayer;
	private boolean currentPlayerGoes;
	private boolean exit = false;
	private Client client;
	
	private Server server;
	
	public void start() {
		new Thread(() -> {
			while (!exit) {
				checkGame();
				
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void checkGame() {
		handleResponse(client.request(MessageFactory.createGetGameState(player)));
	}
	
	@Override
	public void doMove(Index position) {
		handleResponse(client.request(MessageFactory.createMove(player, position)));
	}
	
	@Override
	public void addPlayer(Player player, String ip, int port) {
		this.player = player;
		this.client = new Client(ip, port);
		
		handleResponse(client.connect(player));
		start();
	}
	
	@Override
	public void removePlayer() {
		handleResponse(client.disconect(player));
		exit = true;
	}
	
	@Override
	public void startServer(int port) {
		server = new Server(new TicTacToe(), port);
		server.start();
	}
	
	@Override
	public void stopServer() {
		if (server != null) {
			server.stopServer();
		}
	}
	
	@Override
	public void startAIGame() {
		
	}
	
	@Override
	public void addViewObserver(Observer observer) {
		addObserver(observer);
	}
	
	private void handleResponse(MessageCollection response) {
		for (Message message : response) {
			
			switch (message.getCommand()) {
				case Configuration.BOARD_CHANGED:
					CellArray board = (CellArray) message.getParameter(Configuration.BOARD);
					Player movingPlayer = (Player) message.getParameter(Configuration.PLAYER);
					
					if (movingPlayer != null) {
						if (player.equals(movingPlayer)) {
							if (!currentPlayerGoes) {
								currentPlayerGoes = true;
								setChanged();
								notifyObservers(MessageFactory.createStartMove(player));
							}
						} else {
							currentPlayerGoes = false;
						}
						
						boolean movingPlayerChanged = !movingPlayer.equals(this.movingPlayer);
						boolean boardChanged = !board.equals(this.board);
						
						if (movingPlayerChanged) {
							this.movingPlayer = movingPlayer;
						}
						
						if (boardChanged) {
							this.board = board.copy();
						}
						
						if (boardChanged) {
							setChanged();
							notifyObservers(message);
						}
						
					}
					
					break;
				case Configuration.INVALID_MOVE:
					if (currentPlayerGoes) {
						setChanged();
						notifyObservers(MessageFactory.createStartMove(player));
					}
					setChanged();
					notifyObservers(message);
					break;
				case Configuration.END_OF_MOVE:
					currentPlayerGoes = false;
					setChanged();
					notifyObservers(message);
					break;
				case Configuration.CONNECTION_ERROR:
				case Configuration.GAME_ENDED:
					if (server != null) {
						server.stopServer();
					}
					
					exit = true;
				default:
					setChanged();
					notifyObservers(message);
			}
		}
	}
	
}
