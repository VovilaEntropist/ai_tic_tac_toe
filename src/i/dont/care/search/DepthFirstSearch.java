package i.dont.care.search;

import i.dont.care.search.interfaces.DecisionChecker;
import i.dont.care.search.interfaces.GraphNode;
import i.dont.care.search.interfaces.Search;

import java.util.*;

public class DepthFirstSearch implements Search {
	
	private List<GraphNode> discoveredNodes = new ArrayList<>();
	
	public boolean isDiscovered(GraphNode current) {
		for (GraphNode node : discoveredNodes) {
			if (current.equals(node)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public GraphNode search(GraphNode initial, DecisionChecker checker) {
		if (checker.isDecision(initial)) {
			return initial;
		}
		
		Stack<GraphNode> stack = new Stack<>();
		stack.push(initial);
		
		while (!stack.isEmpty()) {
			GraphNode current = stack.pop();
			
			if (checker.isDecision(current)) {
				return current;
			}
			
			if (!isDiscovered(current)) {
				discoveredNodes.add(current);
				current.getAdjacentNodes().forEach(stack::push);
			}
		}
		
		return null;
	}
}
