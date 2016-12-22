package i.dont.care.search;

import i.dont.care.search.interfaces.GraphNode;

public class SearchResult {
	
	private GraphNode node;
	private SearchInfo searchInfo;
	
	public SearchResult(GraphNode node, SearchInfo searchInfo) {
		this.node = node;
		this.searchInfo = searchInfo;
	}
	
	public SearchResult(GraphNode node) {
		this(node, new SearchInfo());
	}
	
	public GraphNode getNode() {
		return node;
	}
	
	public SearchInfo getSearchInfo() {
		return searchInfo;
	}
}
