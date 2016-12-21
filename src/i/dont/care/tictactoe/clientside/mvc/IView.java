package i.dont.care.tictactoe.clientside.mvc;

import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.utils.Index;

public interface IView {
	
	void doMove(Index position);
	
	void connect(Player player, String ip, int port);
	
	void disconnect();
	
	void startServer(int port);
	
	void stopServer();
	
	void startAIGame();

}
