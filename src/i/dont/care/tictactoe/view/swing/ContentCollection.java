package i.dont.care.tictactoe.view.swing;

import i.dont.care.tictactoe.view.swing.content.Content;

import java.util.HashMap;

public class ContentCollection extends HashMap<ContentType, Content> {
	
	public Content put(Content value) {
		return super.put(value.getContentType(), value);
	}
	
	public void hideAll() {
		for (Content content : values()) {
			content.setVisible(false);
		}
	}
	
	public void show(ContentType contentType) {
		hideAll();
		this.get(contentType).setVisible(true);
	}
	
}
