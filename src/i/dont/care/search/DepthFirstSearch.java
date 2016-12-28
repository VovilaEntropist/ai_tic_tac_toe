package i.dont.care.search;

import i.dont.care.search.interfaces.*;

import java.util.*;

public class DepthFirstSearch implements Search {
	
	private List<AbstractGraphNode> discoveredNodes = new ArrayList<>();
	
	private SolutionChecker checker;
	private TerminalNodeChecker terminalChecker;
	private int depth;
	
	public DepthFirstSearch(SolutionChecker checker, TerminalNodeChecker terminalChecker, int depth) {
		this.checker = checker;
		this.terminalChecker = terminalChecker;
		this.depth = depth;
	}
	
	public boolean isDiscovered(AbstractGraphNode current) {
		for (AbstractGraphNode node : discoveredNodes) {
			if (current.equals(node)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public SearchResult search(AbstractGraphNode initial) {
		long startTime = System.currentTimeMillis();
		int maxDepth = 1;
		int totalNodes = 1;
		
		if (checker.isSolution(initial)) {
			return new SearchResult(NodeUtils.formBranch(initial),
					new SearchInfo(maxDepth, 1, totalNodes, System.currentTimeMillis() - startTime));
		}
		
		Stack<AbstractGraphNode> stack = new Stack<>();
		stack.push(initial);
		
		while (!stack.isEmpty() && depth-- >= 0) {
			AbstractGraphNode current = stack.pop();
			
			if (terminalChecker.isTerminal(current)) {
				maxDepth = Integer.max(maxDepth, NodeUtils.formBranch(current).size());
			}
			
			if (checker.isSolution(current)) {
				NodeCollection branch = NodeUtils.formBranch(current);
				return new SearchResult(branch, new SearchInfo(maxDepth, branch.size(),
						totalNodes, System.currentTimeMillis() - startTime));
			}
			
			if (!isDiscovered(current)) {
				discoveredNodes.add(current);
				NodeCollection nodes = current.getChildNodes();
				totalNodes += nodes.size();
				nodes.forEach(stack::push);
			}
		}
		
		return new SearchResult(new SearchInfo(maxDepth, 0, totalNodes,
				System.currentTimeMillis() - startTime));
	}
	

}
