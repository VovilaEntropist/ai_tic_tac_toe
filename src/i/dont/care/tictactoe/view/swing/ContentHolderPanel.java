package i.dont.care.tictactoe.view.swing;

import i.dont.care.tictactoe.view.swing.content.Content;

import javax.swing.*;
import java.awt.*;

public class ContentHolderPanel extends JPanel {
	
	private ContentCollection contents;
	
	public ContentHolderPanel() {
		this.setLayout(null);
		contents = new ContentCollection();
	}
	
	@Override
	public Component add(Component comp) {
		if (comp instanceof Content) {
			Rectangle contentBounds = new Rectangle(0, 0, this.getWidth(),
					this.getHeight());
			comp.setBounds(contentBounds);
			contents.put((Content) comp);
		}
		
		return super.add(comp);
	}
	
	public Content get(Object key) {
		return contents.get(key);
	}
	
	public void hideAll() {
		contents.hideAll();
	}
	
	public void show(ContentType contentType) {
		contents.show(contentType);
	}
}
