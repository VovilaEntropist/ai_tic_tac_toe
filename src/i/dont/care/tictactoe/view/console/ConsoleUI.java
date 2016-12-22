package i.dont.care.tictactoe.view.console;

import i.dont.care.clientserver.message.Message;
import i.dont.care.tictactoe.Configuration;
import i.dont.care.tictactoe.clientside.mvc.IController;
import i.dont.care.tictactoe.clientside.mvc.IView;
import i.dont.care.tictactoe.mvc.IController;
import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.tictactoe.serverside.board.CellArray;
import i.dont.care.utils.Index;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


public class ConsoleUI implements IView, Observer {
	
	private IController controller;
	private Scanner scanner;
	
	public ConsoleUI(IController controller) {
		this.controller = controller;
		this.scanner = new Scanner(System.in);
	}
	
	public void printName() {
		System.out.println("Введите имя\n");
		String name = scanner.nextLine();
		System.out.println("Выберите фигуру\n");
		String mark = scanner.nextLine();
		
		//addPlayer(new Player(name, mark.charAt(0) == 'X' ? Mark.Player1 : Mark.Player2,  false), "localhost", 6660);
	}
	
	private void printBoard(CellArray board) {
		System.out.println(board + "\n");
	}
	
	private Index getIndexByNumPad(int num) {
		int i = (9 - num) / 3;
		int j = 2 - ((9 - num) % 3);
		return new Index(i, j);
	}
	
	@Override
	public void doMove(Index index) {
		controller.doMove(index);
	}
	
	@Override
	public void connect(Player player, String ip, int port) {
		controller.addPlayer(player, ip, port);
	}
	
	@Override
	public void removePlayer() {
		controller.removePlayer();
	}
	
	@Override
	public void startServer(int port) {
		controller.startServer(port);
	}
	
	@Override
	public void stopServer() {
		controller.stopServer();
	}
	
	
	public void startGame(CellArray board) {
		System.out.println("Игра началась\n");
		//printBoard(board);
	}

	public void prepareMove(Player player) {
		System.out.println("Выбите ход (1-9): ");
		int num = scanner.nextInt();
		doMove(getIndexByNumPad(num));
	}
	
	public void endMove(Player player) {
		System.out.println("Ход принят" + "\n");
	}
	
	public void updateBoard(CellArray board) {
		printBoard(board);
	}
	
	public void winPlayer(Player winner) {
		System.out.println("Выиграл " + winner.getNickname()+  "\n");
	}
	
	public void endGame() {
		System.out.println("Игра закончена\n");
		removePlayer();
	}
	
	public void kickPlayer(Player player, String reason) {
		System.out.println("Отказ доступа: " + reason + "\n");
	}
	
	public void denyMove(Player player) {
		System.out.println("Ошибка хода");
		//prepareMove(player);
	}
	
	private void denyCommand() {
		System.out.println("Неверная комманда\n");
	}
	
	private void endGameTie() {
		System.out.println("Ничья\n");
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//TODO Проверки и исключения тут сыпать
		Message message = (Message) arg;
		
		//TODO Проверки на нул или ещё что-то
		CellArray board = (CellArray) message.getParameter(Configuration.BOARD);
		Player player = (Player) message.getParameter(Configuration.PLAYER);
		String reason = (String) message.getParameter(Configuration.REASON);
		/*
		int command = message.getCommand();
		switch (command) {
			case Configuration.GAME_STARTED:
				startGame(board);
				break;
			case Configuration.END_OF_MOVE:
				endMove(player);
				break;
			case Configuration.START_OF_MOVE:
				prepareMove(player);
				break;
			case Configuration.BOARD_CHANGED:
				updateBoard(board);
				break;
			case Configuration.PLAYER_WIN:
				winPlayer(player);
				break;
			case Configuration.GAME_ENDED:
				endGame();
				break;
			case Configuration.KICK_PLAYER:
				kickPlayer(player, reason);
				break;
			case Configuration.INVALID_MOVE:
				denyMove(player);
				break;
			case Configuration.INVALID_COMMAND:
				denyCommand();
				break;
			case Configuration.TIE:
				endGameTie();
				break;
			case Configuration.CONNECTION_ERROR:
				//removePlayer();
				System.out.println("Ошибка подключения: " + reason);
				break;
		}*/
	}
	
	
}
