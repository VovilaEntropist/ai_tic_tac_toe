package i.dont.care.tictactoe.view.swing.content.listener;

import i.dont.care.tictactoe.view.swing.content.Content;

public interface ContentListener {
	
	void handleContentEvent(Content content, ContentEvent event, Object... args);
	
}
