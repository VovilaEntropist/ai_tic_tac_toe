package i.dont.care.search;

import i.dont.care.search.interfaces.IGraphNode;
import i.dont.care.search.interfaces.Search;
import i.dont.care.search.interfaces.SolutionChecker;
import i.dont.care.search.interfaces.TerminalNodeChecker;

import java.util.*;

public class BreadthFirstSearch implements Search {
	
	private List<IGraphNode> discoveredNodes = new ArrayList<>();
	
	private SolutionChecker checker;
	private TerminalNodeChecker terminalChecker;
	private int depth;
	
	public BreadthFirstSearch(SolutionChecker checker, TerminalNodeChecker terminalChecker, int depth) {
		this.checker = checker;
		this.terminalChecker = terminalChecker;
		this.depth = depth;
	}
	
	public boolean isDiscovered(IGraphNode current) {
		for (IGraphNode node : discoveredNodes) {
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
		
		List<AbstractGraphNode> queue = new LinkedList<>();
		queue.add(initial);
		
		while (!queue.isEmpty()) {
			AbstractGraphNode current = queue.get(0);
			queue.remove(0);
			
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
				nodes.forEach(queue::add);
			}
		}
		
		return new SearchResult(new SearchInfo(maxDepth, 0, totalNodes,
				System.currentTimeMillis() - startTime));
	}
}
