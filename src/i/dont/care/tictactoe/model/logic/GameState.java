package i.dont.care.tictactoe.model.logic;

import i.dont.care.tictactoe.model.board.CellArray;

public class GameState {
	
	private CellArray board;
	private Step lastStep;
	
	public GameState(CellArray board, Step lastStep) {
		this.board = board;
		this.lastStep = lastStep;
	}
	
	public CellArray getBoard() {
		return board;
	}
	
	public Step getLastStep() {
		return lastStep;
	}
	
	@Override
	public String toString() {
		return board.toString() + lastStep.toString();
	}
}
