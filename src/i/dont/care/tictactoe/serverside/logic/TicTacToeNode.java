package i.dont.care.tictactoe.serverside.logic;

import i.dont.care.tictactoe.serverside.board.CellArray;
import i.dont.care.tictactoe.serverside.board.Mark;
import i.dont.care.search.NodeCollection;
import i.dont.care.search.interfaces.GraphNode;

public class TicTacToeNode implements GraphNode {
	
	private CellArray board;
	private Step lastStep;
	
	public TicTacToeNode(CellArray board, Step lastStep) {
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
	public NodeCollection getAdjacentNodes() {
		if (lastStep.getMark() == Mark.Empty) {
			//TODO Кинуть эксепшн
			return null;
		}
		
		NodeCollection nodes = new NodeCollection();
		Mark nextMark = lastStep.getMark() == Mark.Player1 ? Mark.Player2 : Mark.Player1;
		
		board.forEachEmpty((index, cell) -> {
			CellArray newBoard = board.copy();
			newBoard.set(index, nextMark);
			nodes.add(new TicTacToeNode(newBoard, new Step(index, nextMark)));
		});
		
		return nodes;
	}
}
