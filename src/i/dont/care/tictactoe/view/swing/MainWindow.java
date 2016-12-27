package i.dont.care.tictactoe.view.swing;

import i.dont.care.message.Message;
import i.dont.care.search.AlphaBetaSearch;
import i.dont.care.search.BreadthFirstSearch;
import i.dont.care.search.DepthFirstSearch;
import i.dont.care.search.MinMaxSearch;
import i.dont.care.tictactoe.model.Configuration;
import i.dont.care.tictactoe.controller.Controller;
import i.dont.care.tictactoe.model.TicTacToe;
import i.dont.care.tictactoe.model.ai.AIPlayer;
import i.dont.care.tictactoe.model.board.Mark;
import i.dont.care.tictactoe.model.logic.TicTacToeEvaluation;
import i.dont.care.tictactoe.mvc.IController;
import i.dont.care.tictactoe.mvc.IModel;
import i.dont.care.tictactoe.mvc.IView;
import i.dont.care.tictactoe.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.view.swing.content.listener.ContentListener;
import i.dont.care.tictactoe.model.Player;
import i.dont.care.tictactoe.model.PlayerCollection;
import i.dont.care.tictactoe.model.board.CellArray;
import i.dont.care.tictactoe.view.swing.content.*;
import i.dont.care.utils.Index;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MainWindow extends JFrame implements IView, ContentListener, Observer {
	
	private IController controller;
	private ContentHolderPanel contentPanel;
	private MessagePanel messagePanel;

	private boolean enabledMoves;
	private Player player;
	private Mark aiMark;
	
	public MainWindow(IController controller) throws HeadlessException {
		this.controller = controller;
		init();
		initContent();
		validate();
	}
	
	private void init() {
		messagePanel = new MessagePanel();
		contentPanel = new ContentHolderPanel();
		
		messagePanel.setPreferredSize(new Dimension(this.getWidth(), 30));
		
		this.setLayout(new BorderLayout());
		this.add(contentPanel, BorderLayout.CENTER);
		
		this.setTitle("Крестики-нолкики");
		this.setSize(700, 500);
		//this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	private void initContent() {
		Content mainMenu =  new MainMenu(contentPanel, ContentType.MainMenu, this);
		Content playerChoice = new PlayerChoice(contentPanel, ContentType.PlayerChoice, this, ContentType.MainMenu);
		Content waitScreen = new WaitScreen(contentPanel, ContentType.WaitScreen, this);
		Content aiChoice = new AIChoice(contentPanel, ContentType.AIChoice, this);

		contentPanel.add(mainMenu);
		contentPanel.add(playerChoice);
		contentPanel.add(waitScreen);
		contentPanel.add(aiChoice);
		
		contentPanel.show(ContentType.MainMenu);
	}
	
	@Override
	public void handleContentEvent(Content content, ContentEvent event, Object[] args) {
		switch (event) {
			case VsAIBtnClick:
				contentPanel.show(ContentType.PlayerChoice);
				break;
			case ExitBtnClick:
				System.exit(0);
				break;
			case BackPressed:
				ContentType from = (ContentType) args[0];
				if (from != null) {
					contentPanel.show(from);
				}
				break;
			case VsAIStartGame:
				String name = (String) args[0];
				Mark mark = (Mark) args[1];
				aiMark = mark == Mark.Player1 ? Mark.Player2 : Mark.Player1;
				player = new Player(name, mark);
				addPlayer(player);
				
				contentPanel.show(ContentType.AIChoice);
				break;
			case DfsBtnClick:
				TicTacToeEvaluation evaluation = new TicTacToeEvaluation(aiMark, Configuration.CHAIN_TO_WIN);
				addPlayer(new AIPlayer("AI", aiMark, new DepthFirstSearch(evaluation, Configuration.DEPTH)));
				break;
			case BfsBtnClick:
				evaluation = new TicTacToeEvaluation(aiMark, Configuration.CHAIN_TO_WIN);
				addPlayer(new AIPlayer("AI", aiMark, new BreadthFirstSearch(evaluation, Configuration.DEPTH)));
				break;
			case minMaxBtnClick:
				evaluation = new TicTacToeEvaluation(aiMark, Configuration.CHAIN_TO_WIN);
				addPlayer(new AIPlayer("AI", aiMark, new MinMaxSearch(evaluation, evaluation,
						Configuration.DEPTH)));
				break;
			case alphaBettaBtnClick:
				evaluation = new TicTacToeEvaluation(aiMark, Configuration.CHAIN_TO_WIN);
				addPlayer(new AIPlayer("AI", aiMark, new AlphaBetaSearch(evaluation, evaluation,
						Configuration.DEPTH)));
				break;
			case TileCilck:
				if (enabledMoves) {
					Index index = (Index) args[0];
					doMove(player, index);
				}
				break;
			case BackMainMenu:
				contentPanel.show(ContentType.MainMenu);
				break;
			case Error:
				break;
		}
	}
	
	@Override
	public void doMove(Player player, Index position) {
		controller.doMove(player, position);
	}
	
	@Override
	public void addPlayer(Player player) {
		controller.addPlayer(player);
	}
	
	@Override
	public void removePlayer(Player player) {
		controller.removePlayer(player);
	}
		
	private void startGame(CellArray board, Player movingPlayer, PlayerCollection players) {
//		if (movingPlayer.equals(player)) {
//			messagePanel.setMessage("Ваш ход", 0);
//		} else {
//			messagePanel.setMessage("Ходит ваш противник: " + movingPlayer.getNickname(), 0);
//		}

		String xPath = Configuration.X_MARK_PATH;
		String oPath = Configuration.O_MARK_PATH;
		
		Content gameContent = new Game(contentPanel, ContentType.Game, this, xPath, oPath);
		contentPanel.add(gameContent);
		
		contentPanel.show(ContentType.Game);
	}
	
	private void endMove(Player nextPlayer) {
		messagePanel.setMessage("Ходит ваш противник: " + nextPlayer.getNickname(), 0);
		enabledMoves = false;
	}
	
	private void prepareMove(Player player) {
		if (this.player.equals(player)) {
			messagePanel.setMessage("Ваш ход", 0);
			enabledMoves = true;
		}
	}
	
	private void updateBoard(CellArray board) {
		if (contentPanel.get(ContentType.Game) == null) {
			return;
		}
		((Game) contentPanel.get(ContentType.Game)).getBoard().updateBoard(board);
		repaint();
	}
	
	private void winPlayer(Player player) {
//		messagePanel.setMessage("Победил игрок: " +  player.getNickname(), 0);
//
//		Rectangle contentBounds = new Rectangle(0, 0, contentPanel.getWidth(),
//				contentPanel.getHeight());
//		Content gameEnd = new GameEnd(contentBounds, ContentType.GameEnd, this, player);
//		contents.put(ContentType.GameEnd, gameEnd);
//		contentPanel.add(gameEnd);
//
//		contents.show(ContentType.GameEnd);
	}
	
	private void endGameTie() {
		messagePanel.setMessage("Ничья", 0);
	}
	
	private void endGame() {
		//removePlayer();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//TODO Проверки и исключения тут сыпать
		Message message = (Message) arg;
		
		//TODO Проверки на нул или ещё что-то
		CellArray board = (CellArray) message.getParameter(Configuration.BOARD);
		Player player = (Player) message.getParameter(Configuration.PLAYER);
		PlayerCollection players = (PlayerCollection) message.getParameter(Configuration.PLAYERS);
		String reason = (String) message.getParameter(Configuration.REASON);
		
		int command = message.getCommand();
		switch (command) {
			case Configuration.GAME_STARTED:
				startGame(board, player, players);
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
				//kickPlayer(player, reason);
				break;
			case Configuration.INVALID_MOVE:
				//denyMove(player);
				break;
			case Configuration.INVALID_COMMAND:
				//denyCommand();
				break;
			case Configuration.TIE:
				endGameTie();
				break;
			case Configuration.CONNECTION_ERROR:
				//removePlayer();
				System.out.println("Ошибка подключения: " + reason);
				break;
		}
	}
	
	
	public static void main(String[] args) {
		IModel model = new TicTacToe();
		IController controller = new Controller(model);
		MainWindow view = new MainWindow(controller);
		model.addViewObserver(view);
	}
	
	
}
