package i.dont.care.search;

import i.dont.care.search.interfaces.*;

import java.util.*;

public class DepthFirstSearch implements Search {
	
	private List<AbstractGraphNode> discoveredNodes = new ArrayList<>();
	
	private SolutionChecker checker;
	private int depth;
	
	public DepthFirstSearch(SolutionChecker checker, int depth) {
		this.checker = checker;
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
		if (checker.isSolution(initial)) {
			return new SearchResult(NodeUtils.formBranch(initial));
		}
		
		Stack<AbstractGraphNode> stack = new Stack<>();
		stack.push(initial);
		
		while (!stack.isEmpty() && depth-- >= 0) {
			AbstractGraphNode current = stack.pop();
			
			if (checker.isSolution(current)) {
				return new SearchResult(NodeUtils.formBranch(current));
			}
			
			if (!isDiscovered(current)) {
				discoveredNodes.add(current);
				current.getChildNodes().forEach(stack::push);
			}
		}
		
		return new SearchResult();
	}
	

}
