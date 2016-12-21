package i.dont.care.tictactoe.clientside.view.swing;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
	
	public CenterPanel(Rectangle parentBounds, int widthPart, int heightPart) {
		super();
		
		this.setBackground(new Color(0, 0, 0, 0));
		int panelWidth = (int) parentBounds.getWidth() / widthPart;
		int panelHeight = (int) parentBounds.getHeight() / heightPart;
		int panelX = (int) parentBounds.getWidth() / 2 - panelWidth / 2;
		int panelY = (int) parentBounds.getHeight() / 2 - panelHeight / 2;
		setBounds(panelX, panelY, panelWidth, panelHeight);
	}
}
