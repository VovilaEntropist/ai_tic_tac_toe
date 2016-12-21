package i.dont.care.tictactoe.clientside.view.swing.content;

import i.dont.care.tictactoe.clientside.view.swing.CenterPanel;
import i.dont.care.tictactoe.clientside.view.swing.ContentType;
import i.dont.care.tictactoe.clientside.view.swing.ImageLoader;
import i.dont.care.tictactoe.clientside.view.swing.ImagePanel;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentListener;
import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.tictactoe.serverside.board.Mark;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameEnd extends Content {
	
	private static final String IMP_LOGO_PATH = "src/images/imp_logo.png";
	private static final String REBEL_LOGO_PATH = "src/images/rebel_logo.png";
	private static final String SABERS_PATH = "src/images/sabers.png";
	private Player winner;
	
	private JButton backBtn;
	private ImagePanel logoPanel;
	
	
	public GameEnd(Rectangle rectangle, ContentType contentType, ContentListener listener, Player winner) {
		super(rectangle, contentType, listener);
		this.winner = winner;
		init();
	}
	
	private void init() {
		backBtn = new JButton("Вернуться в меню");
		
		String path;
		if (winner.getMark() == Mark.Player1) {
			path = REBEL_LOGO_PATH;
		} else if (winner.getMark() == Mark.Player1){
			path = IMP_LOGO_PATH;
		} else {
			path = SABERS_PATH;
		}
		
		logoPanel = new ImagePanel(new Rectangle(0, 0, 200, 200), ImageLoader.load(new File(path)));
		logoPanel.setPreferredSize(new Dimension(200, 200));
		
		backBtn.addActionListener(e -> listener.handleContentEvent(this, ContentEvent.BackMainMenu));
		
		JPanel centerPanel = new CenterPanel(this.getBounds(), 3, 2);
		
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		centerPanel.add(new JLabel("Победил игрок " + winner.getNickname()));
		centerPanel.add(logoPanel);
		centerPanel.add(backBtn);
		
		this.setLayout(null);
		this.add(centerPanel);
	}
}
