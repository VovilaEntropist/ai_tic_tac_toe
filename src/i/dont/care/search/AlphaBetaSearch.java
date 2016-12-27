package i.dont.care.search;

import i.dont.care.search.interfaces.HeuristicEvaluation;
import i.dont.care.search.interfaces.Search;
import i.dont.care.search.interfaces.TerminalNodeChecker;

public class AlphaBetaSearch implements Search {
	
	private TerminalNodeChecker checker;
	private HeuristicEvaluation heuristic;
	private int depth;
	
	public AlphaBetaSearch(TerminalNodeChecker checker, HeuristicEvaluation heuristic, int depth) {
		this.checker = checker;
		this.heuristic = heuristic;
		this.depth = depth;
	}
	
	@Override
	public SearchResult search(AbstractGraphNode node) {
		int bestEval = Integer.MIN_VALUE;
		AbstractGraphNode bestMove = null;
		for (AbstractGraphNode current : node.getChildNodes()) {
			int eval = searchAlphaBeta(current, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
			if (eval > bestEval) {
				bestEval = eval;
				bestMove = current;
			}
		}
		
		NodeCollection nodes = NodeUtils.formBranch(bestMove);
		return new SearchResult(nodes);
	}
	
	private int searchAlphaBeta(AbstractGraphNode node, int depth, int alpha, int beta,
	                            boolean maximizingPlayer) {
		if (depth == 0 || checker.isTerminal(node)) {
			return heuristic.evaluate(node);
		}
		
		int bestValue;
		if (maximizingPlayer) {
			bestValue = Integer.MIN_VALUE;
			for (AbstractGraphNode current : node.getChildNodes()) {
				int eval = searchAlphaBeta(current, --depth, alpha, beta, false);
				bestValue = Integer.max(bestValue, eval);
				alpha = Integer.max(alpha, bestValue);
				if (beta <= alpha) {
					break;
				}
			}
			return bestValue;
		} else {
			bestValue = Integer.MAX_VALUE;
			for (AbstractGraphNode current : node.getChildNodes()) {
				int eval = searchAlphaBeta(current, --depth, alpha, beta, true);
				bestValue = Integer.min(bestValue, eval);
				beta = Integer.min(beta, bestValue);
				if (beta <= alpha) {
					break;
				}
			}
			return bestValue;
		}
	}
	
}
