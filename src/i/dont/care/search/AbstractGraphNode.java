package i.dont.care.search;

import i.dont.care.search.interfaces.IGraphNode;

public abstract class AbstractGraphNode implements IGraphNode {
	
	private AbstractGraphNode parent;
	
	public AbstractGraphNode(AbstractGraphNode parent) {
		this.parent = parent;
	}
	
	public AbstractGraphNode getParent() {
		return parent;
	}
	
	public void setParent(AbstractGraphNode parent) {
		this.parent = parent;
	}

}
