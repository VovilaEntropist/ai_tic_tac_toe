package i.dont.care.search;

import i.dont.care.search.interfaces.GraphNode;

public class SearchResult {
	
	private GraphNode node;
	private int eval;
	private SearchInfo searchInfo;
	
	public SearchResult(GraphNode node, int eval, SearchInfo searchInfo) {
		this.node = node;
		this.eval = eval;
		this.searchInfo = searchInfo;
	}
	
	public SearchResult(GraphNode node, int eval) {
		this(node, eval, new SearchInfo());
	}
	
	public SearchResult(int eval) {
		this(null, eval);
	}
	
	
	public GraphNode getNode() {
		return node;
	}
	
	public int getEval() {
		return eval;
	}
	
	public SearchInfo getSearchInfo() {
		return searchInfo;
	}
}
