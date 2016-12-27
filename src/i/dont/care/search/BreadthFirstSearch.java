package i.dont.care.search;

import i.dont.care.search.interfaces.IGraphNode;
import i.dont.care.search.interfaces.Search;
import i.dont.care.search.interfaces.SolutionChecker;

import java.util.*;

public class BreadthFirstSearch implements Search {
	
	private List<IGraphNode> discoveredNodes = new ArrayList<>();
	
	private SolutionChecker checker;
	private int depth;
	
	public BreadthFirstSearch(SolutionChecker checker, int depth) {
		this.checker = checker;
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
		if (checker.isSolution(initial)) {
			return new SearchResult(NodeUtils.formBranch(initial));
		}
		
		List<AbstractGraphNode> queue = new LinkedList<>();
		queue.add(initial);
		
		while (!queue.isEmpty()) {
			AbstractGraphNode current = queue.get(0);
			queue.remove(0);
			
			if (checker.isSolution(current)) {
				return new SearchResult(NodeUtils.formBranch(current));
			}
			
			if (!isDiscovered(current)) {
				discoveredNodes.add(current);
				current.getChildNodes().forEach(queue::add);
			}
		}
		
		return new SearchResult();
	}
}
