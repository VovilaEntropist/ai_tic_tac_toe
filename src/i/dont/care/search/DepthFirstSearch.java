package i.dont.care.search;

import i.dont.care.search.interfaces.*;

import java.util.*;

public class DepthFirstSearch implements Search {
	
	private List<GraphNode> discoveredNodes = new ArrayList<>();
	
	private SolutionChecker checker;
	private int depth;
	
	public DepthFirstSearch(SolutionChecker checker, int depth) {
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
		
		Stack<GraphNode> stack = new Stack<>();
		stack.push(initial);
		
		while (!stack.isEmpty() && depth-- >= 0) {
			GraphNode current = stack.pop();
			
			if (checker.isSolution(current)) {
				return new SearchResult(current, 1);
			}
			
			if (!isDiscovered(current)) {
				discoveredNodes.add(current);
				current.getChildNodes().forEach(stack::push);
			}
		}
		
		return new SearchResult(-1);
	}
}
