package i.dont.care.tictactoe.serverside.logic;

import i.dont.care.tictactoe.serverside.board.CellArray;
import i.dont.care.tictactoe.serverside.board.Mark;
import i.dont.care.search.interfaces.DecisionChecker;
import i.dont.care.search.interfaces.GraphNode;

public class TicTacToeChecker implements DecisionChecker {
	
	private Mark winMark;
	private int chainLength;
	
	public TicTacToeChecker(Mark winMark, int chainLength) {
		this.winMark = winMark;
		this.chainLength = chainLength;
	}
	
	@Override
	public boolean isDecision(GraphNode graphNode) {
		//TODO замутить проверку и кидать эксепшн
		TicTacToeNode node = (TicTacToeNode) graphNode;
		
		return isDecisionOnDirect(node, Direction.Horizontally)
				|| isDecisionOnDirect(node, Direction.Vertically)
				|| isDecisionOnDirect(node, Direction.MainDiagonal)
				|| isDecisionOnDirect(node, Direction.AntiDiagonal);
	}
	
	private boolean isDecisionOnDirect(TicTacToeNode node, Direction direction) {
		CellArray board = node.getBoard();
		Step step = node.getLastStep();
		
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
			
			counter = checkAt(i, j, board) ? counter + 1 : 0;
			
			if (counter >= chainLength) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkAt(int row, int column, CellArray board) {
		if (!board.isValidIndex(row, column)) {
			return false;
		}
		
		return board.at(row, column).getMark() == winMark;
	}
	
	private enum Direction {
		Horizontally,
		Vertically,
		MainDiagonal,
		AntiDiagonal
	}
}
