package i.dont.care.tictactoe.model.logic;

import i.dont.care.search.AbstractGraphNode;
import i.dont.care.search.interfaces.IGraphNode;
import i.dont.care.search.interfaces.HeuristicEvaluation;
import i.dont.care.search.interfaces.SolutionChecker;
import i.dont.care.search.interfaces.TerminalNodeChecker;
import i.dont.care.tictactoe.model.board.CellArray;
import i.dont.care.tictactoe.model.board.Mark;

public class TicTacToeEvaluation implements TerminalNodeChecker, HeuristicEvaluation, SolutionChecker {
	
	public static final int WIN = 5;
	public static final int LOSE = -5;
	public static final int DRAW = -2;
	public static final int NONE = 0;
	
	private Mark winMark;
	private int chainLength;
	
	public TicTacToeEvaluation(Mark winMark, int chainLength) {
		this.winMark = winMark;
		this.chainLength = chainLength;
	}
	
	@Override
	public boolean isTerminal(AbstractGraphNode node) {
		int eval = evaluate(node);
		return eval != NONE;
	}
	
	@Override
	public boolean isSolution(AbstractGraphNode node) {
		int eval = evaluate(node);
		return eval == WIN;
	}
	
	@Override
	public int evaluate(AbstractGraphNode graphNode) {
		//TODO замутить проверку и кидать эксепшн
		TicTacToeNode node = (TicTacToeNode) graphNode;
		GameState gameState = node.getGameState();
		
		if (isDecisionOnDirect(gameState, TicTacToeEvaluation.Direction.Horizontally)
				|| isDecisionOnDirect(gameState, TicTacToeEvaluation.Direction.Vertically)
				|| isDecisionOnDirect(gameState, TicTacToeEvaluation.Direction.MainDiagonal)
				|| isDecisionOnDirect(gameState, TicTacToeEvaluation.Direction.AntiDiagonal)) {
			return winMark == gameState.getLastStep().getMark() ? WIN : LOSE;
		}
		
		if (gameState.getBoard().getEmptyCount() == 0) {
			return DRAW;
		}
		
		return NONE;
	}
	
	private boolean isDecisionOnDirect(GameState gameState, TicTacToeEvaluation.Direction direction) {
		CellArray board = gameState.getBoard();
		Step step = gameState.getLastStep();
		
		int counter = 0;
		for (int dx = -chainLength + 1; dx < chainLength; dx++) {
			int i = step.getIndex().row();
			int j = step.getIndex().column();
			
			switch (direction) {
				case Horizontally:
					j += dx;
					break;
				case Vertically:
					i += dx;
					break;
				case MainDiagonal:
					i -= dx;
					j += dx;
					break;
				case AntiDiagonal:
					i += dx;
					j += dx;
					break;
			}
			
			counter = checkAt(i, j, board, step.getMark()) ? counter + 1 : 0;
			
			if (counter >= chainLength) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkAt(int row, int column, CellArray board, Mark checkMark) {
		if (!board.isValidIndex(row, column)) {
			return false;
		}
		
		return board.at(row, column).getMark() == checkMark;
	}
	
	private enum Direction {
		Horizontally,
		Vertically,
		MainDiagonal,
		AntiDiagonal
	}
}
