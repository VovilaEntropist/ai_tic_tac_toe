package i.dont.care.tictactoe.controller;

import i.dont.care.tictactoe.model.ai.AIPlayer;
import i.dont.care.tictactoe.model.ai.AIProcessor;
import i.dont.care.tictactoe.mvc.IController;
import i.dont.care.tictactoe.mvc.IModel;
import i.dont.care.tictactoe.model.Player;
import i.dont.care.utils.Index;

public class Controller implements IController {
	
	private IModel model;
	
	public Controller(IModel model) {
		this.model = model;
	}
	
	@Override
	public void doMove(Player player, Index position) {
		model.doMove(player, position);
	}
	
	@Override
	public void addPlayer(Player player) {
		if (player instanceof AIPlayer) {
			AIPlayer aiPlayer = (AIPlayer) player;
			model.addViewObserver(new AIProcessor(this, aiPlayer, aiPlayer.getSearch()));
		}
		
		model.addPlayer(player);
	}
	
	@Override
	public void removePlayer(Player player) {
		model.removePlayer(player);
	}
	
	@Override
	public void uploadInfo(String info) {
		model.uploadInfo(info);
	}
	
}
