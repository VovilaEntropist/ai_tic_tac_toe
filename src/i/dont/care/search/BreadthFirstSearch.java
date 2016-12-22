package i.dont.care.search;

import i.dont.care.search.interfaces.GraphNode;
import i.dont.care.search.interfaces.Search;
import i.dont.care.search.interfaces.SolutionChecker;

import java.util.*;

public class BreadthFirstSearch implements Search {
	
	private List<GraphNode> discoveredNodes = new ArrayList<>();
	
	private SolutionChecker checker;
	private int depth;
	
	public BreadthFirstSearch(SolutionChecker checker, int depth) {
		this.checker = checker;
		this.depth = depth;
	}
	
	public boolean isDiscovered(GraphNode current) {
		for (GraphNode node : discoveredNodes) {
			if (current.equals(node)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public SearchResult search(GraphNode initial) {
		if (checker.isSolution(initial)) {
			return new SearchResult(initial, 1);
		}
		
		List<GraphNode> queue = new LinkedList<>();
		queue.add(initial);
		
		while (!queue.isEmpty() && depth-- >= 0) {
			GraphNode current = queue.get(0);
			queue.remove(0);
			
			if (checker.isSolution(current)) {
				return new SearchResult(current, 1);
			}
			
			if (!isDiscovered(current)) {
				discoveredNodes.add(current);
				current.getChildNodes().forEach(queue::add);
			}
		}
		
		return new SearchResult(-1);
	}
}
