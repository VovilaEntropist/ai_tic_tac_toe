package i.dont.care.tictactoe.ai;

import i.dont.care.tictactoe.clientside.mvc.IController;
import i.dont.care.tictactoe.clientside.mvc.IView;
import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.utils.Index;

public class AiView implements IView {
	
	private IController controller;
	
	public AiView(IController controller) {
		this.controller = controller;
	}
	
	@Override
	public void doMove(Index position) {
		
	}
	
	@Override
	public void connect(Player player, String ip, int port) {
		
	}
	
	@Override
	public void disconnect() {
		
	}
	
	@Override
	public void startServer(int port) {
		
	}
	
	@Override
	public void stopServer() {
		
	}
}
