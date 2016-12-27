package i.dont.care.tictactoe.model.logic;

import i.dont.care.search.AbstractGraphNode;
import i.dont.care.tictactoe.model.board.CellArray;
import i.dont.care.tictactoe.model.board.Mark;
import i.dont.care.search.NodeCollection;
import i.dont.care.search.interfaces.IGraphNode;

public class TicTacToeNode extends AbstractGraphNode {
	
	private GameState gameState;
	
	public TicTacToeNode(AbstractGraphNode parent, GameState gameState) {
		super(parent);
		this.gameState = gameState;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	@Override
	public NodeCollection getChildNodes() {
		NodeCollection nodes = new NodeCollection();
		
		if (gameState.getLastStep() == null || gameState.getLastStep().getMark() == Mark.Empty) {
			return nodes;
		}
		
		Mark nextMark = gameState.getLastStep().getMark() == Mark.Player1 ? Mark.Player2 : Mark.Player1;
		
		gameState.getBoard().forEachEmpty((index, cell) -> {
			CellArray newBoard = gameState.getBoard().copy();
			newBoard.set(index, nextMark);
			nodes.add(new TicTacToeNode(this, new GameState(newBoard, new Step(index, nextMark))));
		});
		
		return nodes;
	}
	
	@Override
	public String toString() {
		return gameState.toString();
	}
}
