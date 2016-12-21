package i.dont.care.tictactoe.clientside.view.swing;

import i.dont.care.tictactoe.clientside.view.swing.content.Content;

import java.util.HashMap;

public class ContentCollection extends HashMap<ContentType, Content> {
	
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
