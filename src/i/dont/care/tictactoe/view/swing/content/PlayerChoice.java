package i.dont.care.tictactoe.view.swing.content;

import i.dont.care.tictactoe.model.Configuration;
import i.dont.care.tictactoe.view.swing.CenterPanel;
import i.dont.care.tictactoe.view.swing.ContentType;
import i.dont.care.tictactoe.view.swing.ImageLoader;
import i.dont.care.tictactoe.view.swing.ImagePanel;
import i.dont.care.tictactoe.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.view.swing.content.listener.ContentListener;
import i.dont.care.tictactoe.model.board.Mark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class PlayerChoice extends BackButtonContent {
	
	private JTextField nickNameEdt;
	
	private ImagePanel xMark;
	private ImagePanel oMark;
	
	public PlayerChoice(JPanel parent, ContentType contentType, ContentListener listener, ContentType from) {
		super(parent, contentType, listener, from);
		init();
		initListeners();
	}
	
	private void init() {
		JLabel lbl1 = new JLabel("Выберите имя и сторону");
		nickNameEdt = new JTextField();
		nickNameEdt.setPreferredSize(new Dimension(200, 25));

		initImagePanels();
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.add(xMark);
		bottomPanel.add(oMark);

		JPanel centerPanel = new CenterPanel(this.getBounds(), 1, 3);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(new JLabel("Имя: "));
		panel.add(nickNameEdt);
		
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel2.add(lbl1);
		
		centerPanel.add(panel2);
		centerPanel.add(panel);
		centerPanel.add(bottomPanel);
		
		this.add(centerPanel);
	}
	
	private void initImagePanels() {
		xMark = new ImagePanel(new Rectangle(0, 0, 50, 50),
				ImageLoader.load(new File(Configuration.X_MARK_PATH)));
		oMark = new ImagePanel(new Rectangle(0, 0, 50, 50),
				ImageLoader.load(new File(Configuration.O_MARK_PATH)));
		
		xMark.setPreferredSize(new Dimension(70, 70));
		oMark.setPreferredSize(new Dimension(70, 70));
	}
	
	private void initListeners() {
		xMark.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.handleContentEvent(PlayerChoice.this, ContentEvent.VsAIStartGame, nickNameEdt.getText(),
						Mark.Player1, Configuration.X_MARK_PATH);
			}
		});
		
		oMark.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.handleContentEvent(PlayerChoice.this, ContentEvent.VsAIStartGame, nickNameEdt.getText(),
						Mark.Player2, Configuration.O_MARK_PATH);
			}
		});
	}
}
