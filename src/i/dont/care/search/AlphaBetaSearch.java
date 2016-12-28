package i.dont.care.search;

import i.dont.care.search.interfaces.HeuristicEvaluation;
import i.dont.care.search.interfaces.Search;
import i.dont.care.search.interfaces.SolutionChecker;
import i.dont.care.search.interfaces.TerminalNodeChecker;

public class AlphaBetaSearch implements Search {
	
	private SolutionChecker solutionChecker;
	private TerminalNodeChecker checker;
	private HeuristicEvaluation heuristic;
	private int depth;
	
	private long startTime;
	private int maxDepth;
	private int minPath;
	private int totalNodes;
	
	public AlphaBetaSearch(SolutionChecker solutionChecker, TerminalNodeChecker checker,
	                       HeuristicEvaluation heuristic, int depth) {
		this.solutionChecker = solutionChecker;
		this.checker = checker;
		this.heuristic = heuristic;
		this.depth = depth;
	}
	
	@Override
	public SearchResult search(AbstractGraphNode node) {
		startTime = System.currentTimeMillis();
		maxDepth = 1;
		minPath = 0;
		totalNodes = 1;
		
		int bestEval = Integer.MIN_VALUE;
		AbstractGraphNode bestMove = null;
		for (AbstractGraphNode current : node.getChildNodes()) {
			int eval = searchAlphaBeta(current, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
			if (eval > bestEval) {
				bestEval = eval;
				bestMove = current;
			}
		}
		
		if (bestMove != null) {
			NodeCollection nodes = NodeUtils.formBranch(bestMove);
			return new SearchResult(nodes, new SearchInfo(maxDepth, minPath, totalNodes,
					System.currentTimeMillis() - startTime));
		}
		
		return new SearchResult(new SearchInfo(maxDepth, minPath, totalNodes,
				System.currentTimeMillis() - startTime));
	}
	
	private int searchAlphaBeta(AbstractGraphNode node, int depth, int alpha, int beta,
	                            boolean maximizingPlayer) {
		if (solutionChecker.isSolution(node)) {
			minPath = Integer.min(minPath, NodeUtils.formBranch(node).size());
		}
		
		if (depth == 0 || checker.isTerminal(node)) {
			maxDepth = Integer.max(maxDepth, NodeUtils.formBranch(node).size());
			return heuristic.evaluate(node);
		}
		
		int bestValue;
		if (maximizingPlayer) {
			bestValue = Integer.MIN_VALUE;
			NodeCollection nodes = node.getChildNodes();
			for (AbstractGraphNode current : node.getChildNodes()) {
				totalNodes += nodes.size();
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
			NodeCollection nodes = node.getChildNodes();
			for (AbstractGraphNode current : node.getChildNodes()) {
				totalNodes += nodes.size();
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
