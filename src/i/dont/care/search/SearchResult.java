package i.dont.care.search;

public class SearchResult {
	
	private NodeCollection branch;
	private SearchInfo searchInfo;
	
	public SearchResult(SearchInfo searchInfo) {
		this.searchInfo = searchInfo;
	}
	
	public SearchResult(NodeCollection branch, SearchInfo searchInfo) {
		this.branch = branch;
		this.searchInfo = searchInfo;
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
