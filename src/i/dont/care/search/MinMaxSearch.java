package i.dont.care.search;

import i.dont.care.search.interfaces.*;

public class MinMaxSearch implements Search {
	
	private TerminalNodeChecker checker;
	private HeuristicEvaluation heuristic;
	private int depth;
	
	public MinMaxSearch(TerminalNodeChecker checker, HeuristicEvaluation heuristic, int depth) {
		this.checker = checker;
		this.heuristic = heuristic;
		this.depth = depth;
	}
	
	@Override
	public SearchResult search(GraphNode node) {
		int eval = searchMinMax(node, depth, true);
		return new SearchResult(eval);
	}
	
	private int searchMinMax(GraphNode node, int depth, boolean maximizingPlayer) {
		if (depth == 0 || checker.isTerminal(node)) {
			return heuristic.evaluate(node);
		}
		
		int bestValue;
		if (maximizingPlayer) {
			bestValue = Integer.MIN_VALUE;
			for (GraphNode current : node.getChildNodes()) {
				int eval = searchMinMax(current, --depth, false);
				bestValue = Integer.max(bestValue, eval);
			}
			return bestValue;
		} else {
			bestValue = Integer.MAX_VALUE;
			for (GraphNode current : node.getChildNodes()) {
				int eval = searchMinMax(current, --depth, true);
				bestValue = Integer.min(bestValue, eval);
			}
			return bestValue;
		}
	}
	
}
