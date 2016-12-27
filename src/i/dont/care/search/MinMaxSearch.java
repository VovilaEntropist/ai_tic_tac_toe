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
	public SearchResult search(AbstractGraphNode node) {
		int bestEval = Integer.MIN_VALUE;
		AbstractGraphNode bestMove = null;
		for (AbstractGraphNode current : node.getChildNodes()) {
			int eval = searchMinMax(current, depth, false);
			if (eval > bestEval) {
				bestEval = eval;
				bestMove = current;
			}
		}
		
		NodeCollection nodes = NodeUtils.formBranch(bestMove);
		return new SearchResult(nodes);
	}
	
	private int searchMinMax(AbstractGraphNode node, int depth, boolean maximizingPlayer) {
		if (depth == 0 || checker.isTerminal(node)) {
			return heuristic.evaluate(node);
		}
		
		int bestValue;
		if (maximizingPlayer) {
			bestValue = Integer.MIN_VALUE;
			for (AbstractGraphNode current : node.getChildNodes()) {
				int eval = searchMinMax(current, --depth, false);
				bestValue = Integer.max(bestValue, eval);
			}
			return bestValue;
		} else {
			bestValue = Integer.MAX_VALUE;
			for (AbstractGraphNode current : node.getChildNodes()) {
				int eval = searchMinMax(current, --depth, true);
				bestValue = Integer.min(bestValue, eval);
			}
			return bestValue;
		}
	}
	
}
