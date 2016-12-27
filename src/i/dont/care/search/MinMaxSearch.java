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
		NodeCollection nodes = NodeUtils.formBranch(searchMinMax(node, depth, true).getNode());
		return new SearchResult();
	}
	
	private ResultWrapper searchMinMax(AbstractGraphNode node, int depth, boolean maximizingPlayer) {
		if (depth == 0 || checker.isTerminal(node)) {
			return new ResultWrapper(heuristic.evaluate(node), node);
		}
		
		int bestValue;
		if (maximizingPlayer) {
			bestValue = Integer.MIN_VALUE;
			for (AbstractGraphNode current : node.getChildNodes()) {
				int eval = searchMinMax(current, --depth, false).getEval();
				bestValue = Integer.max(bestValue, eval);
			}
			return new ResultWrapper(bestValue, node);
		} else {
			bestValue = Integer.MAX_VALUE;
			for (AbstractGraphNode current : node.getChildNodes()) {
				int eval = searchMinMax(current, --depth, true).getEval();
				bestValue = Integer.min(bestValue, eval);
			}
			return new ResultWrapper(bestValue, node);
		}
	}
	
	private class ResultWrapper {
		private int eval;
		private AbstractGraphNode node;
		
		public ResultWrapper(int eval, AbstractGraphNode node) {
			this.eval = eval;
			this.node = node;
		}
		
		public int getEval() {
			return eval;
		}
		
		public void setEval(int eval) {
			this.eval = eval;
		}
		
		public AbstractGraphNode getNode() {
			return node;
		}
		
		public void setNode(AbstractGraphNode node) {
			this.node = node;
		}
	}
}
