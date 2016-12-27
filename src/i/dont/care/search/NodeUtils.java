package i.dont.care.search;

import java.util.Collection;
import java.util.Collections;

public class NodeUtils {
	
	private NodeUtils() {
		
	}
	
	public static NodeCollection formBranch(AbstractGraphNode finalNode) {
		NodeCollection branch = new NodeCollection();
		
		if (finalNode.getParent() == null) {
			branch.add(finalNode);
			return branch;
		}
		
		AbstractGraphNode current = finalNode;
		while (current != null) {
			branch.add(current);
			current = current.getParent();
		}
		
		Collections.reverse(branch);
		
		return branch;
	}
	
	
	
}

