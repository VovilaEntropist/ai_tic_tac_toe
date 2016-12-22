package i.dont.care.tictactoe.view.swing;

import i.dont.care.clientserver.message.Message;
import i.dont.care.tictactoe.model.Configuration;
import i.dont.care.tictactoe.controller.Controller;
import i.dont.care.tictactoe.model.TicTacToe;
import i.dont.care.tictactoe.mvc.IController;
import i.dont.care.tictactoe.mvc.IModel;
import i.dont.care.tictactoe.mvc.IView;
import i.dont.care.tictactoe.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.view.swing.content.listener.ContentListener;
import i.dont.care.tictactoe.model.Player;
import i.dont.care.tictactoe.model.PlayerCollection;
import i.dont.care.tictactoe.model.board.CellArray;
import i.dont.care.tictactoe.model.board.Mark;
import i.dont.care.tictactoe.view.swing.content.*;
import i.dont.care.utils.Index;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MainWindow extends JFrame implements IView, ContentListener, Observer {
	
	private IController controller;
	private ContentCollection contents;
	private JPanel contentPanel;
	private MessagePanel messagePanel;
	
	private Player player;
	
	private String serverIp;
	private int serverPort;

	private boolean enabledMoves;
	
	
	public MainWindow(IController controller) throws HeadlessException {
		this.controller = controller;
		contents = new ContentCollection();
		init();
		initContent();
		validate();
	}
	
	private void init() {
		messagePanel = new MessagePanel();
		contentPanel = new JPanel();
		
		messagePanel.setPreferredSize(new Dimension(this.getWidth(), 30));
		
		this.setLayout(new BorderLayout());
		this.add(messagePanel, BorderLayout.NORTH);
		this.add(contentPanel, BorderLayout.CENTER);
		
		this.setTitle("Крестики-нолкики (STAR WARS EDITION)");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
//		this.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosed(WindowEvent e) {
//				removePlayer();
//			}
//		});
	}
	
	private void initContent() {
		Rectangle contentBounds = new Rectangle(0, 0, contentPanel.getWidth(),
				contentPanel.getHeight());
		Content mainMenu =  new MainMenu(contentBounds, ContentType.MainMenu, this);
		Content playerChoice = new PlayerChoice(contentBounds, ContentType.PlayerChoice,
				this, mainMenu);
		Content serverCreate = new ServerCreate(contentBounds, ContentType.ServerCreate,
				this, mainMenu);
		Content serverConnect = new ServerConnect(contentBounds, ContentType.ServerConnect,
				this, mainMenu);
		Content waitScreen = new WaitScreen(contentBounds, ContentType.WaitScreen, this);
		
		contents.put(ContentType.MainMenu, mainMenu);
		contents.put(ContentType.ServerCreate, serverCreate);
		contents.put(ContentType.ServerConnect, serverConnect);
		contents.put(ContentType.PlayerChoice, playerChoice);
		contents.put(ContentType.WaitScreen, waitScreen);
		
		contents.show(ContentType.MainMenu);
		
		contentPanel.setLayout(null);
		contentPanel.add(mainMenu);
		contentPanel.add(serverCreate);
		contentPanel.add(serverConnect);
		contentPanel.add(playerChoice);
		contentPanel.add(waitScreen);
	}
	
	@Override
	public void handleContentEvent(Content content, ContentEvent event, Object[] args) {
		switch (event) {
			case ServerBtnClick:
				contents.show(ContentType.ServerCreate);
				break;
			case ConnectBtnClick:
				contents.show(ContentType.ServerConnect);
				break;
			case AiBtnClick:
				
				break;
			case ExitBtnClick:
				break;
			case BackPressed:
				Content from = (Content) args[0];
				if (from != null) {
					contents.show(from.getContentType());
				}
				break;
			case TileCilck:
				if (enabledMoves) {
					Index index = (Index) args[0];
					//doMove(index);
				}
				break;
			case StartServerBtnClick:
				this.serverPort = (int) args[0];
				((BackButtonContent) contents.get(ContentType.PlayerChoice))
						.setFrom(contents.get(ContentType.ServerCreate));
				contents.show(ContentType.PlayerChoice);
				break;
			case ConnectServerBtnClick:
				this.serverIp = (String) args[0];
				this.serverPort = (int) args[1];
				
				((BackButtonContent) contents.get(ContentType.PlayerChoice))
						.setFrom(contents.get(ContentType.ServerConnect));
				contents.show(ContentType.PlayerChoice);
				break;
			case StartServer:
				//this.player = new Player((String) args[0], (Mark) args[1], (String) args[2], false);
				
				contents.show(ContentType.WaitScreen);
				
				//startServer(serverPort);
				//connect(this.player, serverIp, serverPort);
				break;
			case ChoiceNickName:
				break;
			case ConnectServer:
				//this.player = new Player((String) args[0], (Mark) args[1], (String) args[2], false);
				
				contents.show(ContentType.WaitScreen);
				
				//connect(this.player, serverIp, serverPort);
				break;
			case Error:
				break;
			case BackMainMenu:
				contents.show(ContentType.MainMenu);
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
		if (movingPlayer.equals(player)) {
			messagePanel.setMessage("Ваш ход", 0);
		} else {
			messagePanel.setMessage("Ходит ваш противник: " + movingPlayer.getNickname(), 0);
		}
		
		
		String xPath;
		String oPath;

		if (player.getMark() == Mark.Player1) {
			xPath = player.getImagePath();
			oPath = players.getNext(player).getImagePath();
		} else {
			xPath = players.getNext(player).getImagePath();
			oPath = player.getImagePath();
		}

		Rectangle contentBounds = new Rectangle(0, 0, contentPanel.getWidth(),
			contentPanel.getHeight());
		Content gameContent = new Game(contentBounds, ContentType.Game, this, xPath, oPath);
		contents.put(ContentType.Game, gameContent);
		contentPanel.add(gameContent);
		
		contents.show(ContentType.Game);
	}
	
	private void endMove(Player nextPlayer) {
		messagePanel.setMessage("Ходит ваш противник: " + nextPlayer.getNickname(), 0);
		enabledMoves = false;
	}
	
	private void prepareMove(Player player) {
		messagePanel.setMessage("Ваш ход", 0);
		enabledMoves = true;
	}
	
	private void updateBoard(CellArray board) {
		if (contents.get(ContentType.Game) == null) {
			return;
		}
		((Game) contents.get(ContentType.Game)).getBoard().updateBoard(board);
		repaint();
	}
	
	private void winPlayer(Player player) {
		messagePanel.setMessage("Победил игрок: " +  player.getNickname(), 0);
		
		Rectangle contentBounds = new Rectangle(0, 0, contentPanel.getWidth(),
				contentPanel.getHeight());
		Content gameEnd = new GameEnd(contentBounds, ContentType.GameEnd, this, player);
		contents.put(ContentType.GameEnd, gameEnd);
		contentPanel.add(gameEnd);
		
		contents.show(ContentType.GameEnd);
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
