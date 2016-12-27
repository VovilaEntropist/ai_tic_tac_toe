package i.dont.care.search.interfaces;

import i.dont.care.search.AbstractGraphNode;
import i.dont.care.search.SearchResult;

public interface Search {
	
	SearchResult search(AbstractGraphNode node);
	
}
