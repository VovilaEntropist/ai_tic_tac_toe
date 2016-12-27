package i.dont.care.search;

public class SearchResult {
	
	private NodeCollection branch;
	private SearchInfo searchInfo;
	
	public SearchResult() {
	}
	
	public SearchResult(NodeCollection branch) {
		this.branch = branch;
	}
	
	public NodeCollection getBranch() {
		return branch;
	}
	
	public void setSearchInfo(SearchInfo searchInfo) {
		this.searchInfo = searchInfo;
	}
	
	public SearchInfo getSearchInfo() {
		return searchInfo;
	}
}
