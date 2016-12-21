package i.dont.care.tictactoe.clientside.controller;

import i.dont.care.tictactoe.clientside.mvc.IController;
import i.dont.care.tictactoe.clientside.mvc.IModel;
import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.utils.Index;

public class Controller implements IController {
	
	private IModel model;
	
	public Controller(IModel model) {
		this.model = model;
	}
	
	@Override
	public void doMove(Index position) {
		model.doMove(position);
	}
	
	@Override
	public void addPlayer(Player player, String ip, int port) {
		model.addPlayer(player, ip, port);
	}
	
	@Override
	public void removePlayer() {
		model.removePlayer();
	}
	
	@Override
	public void startServer(int port) {
		model.startServer(port);
	}
	
	@Override
	public void stopServer() {
		model.stopServer();
	}
	
	@Override
	public void startAIGame() {
		model.startAIGame();
	}
}
