package i.dont.care.tictactoe;

//TODO Временное решение. В дальнейшем сделать через файл ресурсов (XML, JSON, Properties или что-то вроде того)
public class Configuration {
	
	public static final int GAME_STARTED = 0;
	public static final int END_OF_MOVE = 1;
	public static final int START_OF_MOVE = 2;
	public static final int BOARD_CHANGED = 3;
	public static final int PLAYER_WIN = 4;
	public static final int GAME_ENDED = 5;
	public static final int KICK_PLAYER = 6;
	public static final int INVALID_MOVE = 7;
	public static final int INVALID_COMMAND = 8;
	public static final int TIE = 9;
	
	public static final int MOVE = 100;
	public static final int CONNECT = 101;
	public static final int DISCONNECT = 102;
	public static final int GET_STATE = 103;
	
	public static final String BOARD = "board";
	public static final String PLAYER = "player";
	public static final String REASON = "reason";
	public static final String INDEX = "index";
	
	public static final String COMMAND = "command";
	public static final int CONNECTION_ERROR = -1;
	public static final int GAME_STOP = -2;
	public static final String ENEMY_IMAGE = "enemy_image";
	public static final String PLAYERS = "player_collection";
}
