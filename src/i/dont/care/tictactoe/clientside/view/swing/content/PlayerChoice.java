package i.dont.care.tictactoe.clientside.view.swing.content;

import i.dont.care.tictactoe.clientside.view.swing.CenterPanel;
import i.dont.care.tictactoe.clientside.view.swing.ContentType;
import i.dont.care.tictactoe.clientside.view.swing.ImageLoader;
import i.dont.care.tictactoe.clientside.view.swing.ImagePanel;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentListener;
import i.dont.care.tictactoe.serverside.board.Mark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class PlayerChoice extends BackButtonContent {
	
	private static final String LUKE_MARK_PATH = "src/images/luke.png";
	private static final String OBI_MARK_PATH = "src/images/obi.png";
	private static final String YODA_MARK_PATH = "src/images/yoda.png";
	private static final String DART_MARK_PATH = "src/images/dart.png";
	private static final String IMP_MARK_PATH = "src/images/imp.png";
	private static final String FET_MARK_PATH = "src/images/fet.png";
	
	private JTextField nickNameEdt;
	
	private ImagePanel lukMark;
	private ImagePanel obiMark;
	private ImagePanel yodaMark;
	private ImagePanel dartMark;
	private ImagePanel impMark;
	private ImagePanel fetMark;
	private ImagePanel sabersImg;
	
	
	public PlayerChoice(Rectangle rectangle, ContentType contentType, ContentListener listener, Content from) {
		super(rectangle, contentType, listener, from);
		init();
		initListeners();
	}
	
	private void init() {
		JLabel lbl1 = new JLabel("Назовите себя, затем выберите сторону и кликните на персонажа");
		nickNameEdt = new JTextField();
		
		nickNameEdt.setPreferredSize(new Dimension(200, 25));

		initImagePanels();
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.add(lukMark);
		bottomPanel.add(obiMark);
		bottomPanel.add(yodaMark);
		bottomPanel.add(sabersImg);
		bottomPanel.add(dartMark);
		bottomPanel.add(impMark);
		bottomPanel.add(fetMark);
		
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
		lukMark = new ImagePanel(new Rectangle(0, 0, 50, 50), ImageLoader.load(new File(LUKE_MARK_PATH)));
		obiMark = new ImagePanel(new Rectangle(0, 0, 50, 50), ImageLoader.load(new File(OBI_MARK_PATH)));
		yodaMark = new ImagePanel(new Rectangle(0, 0, 50, 50), ImageLoader.load(new File(YODA_MARK_PATH)));
		dartMark  = new ImagePanel(new Rectangle(0, 0, 50, 50), ImageLoader.load(new File(DART_MARK_PATH)));
		impMark = new ImagePanel(new Rectangle(0, 0, 50, 50), ImageLoader.load(new File(IMP_MARK_PATH)));
		fetMark = new ImagePanel(new Rectangle(0, 0, 50, 50), ImageLoader.load(new File(FET_MARK_PATH)));
		sabersImg = new ImagePanel(new Rectangle(0, 0, 50, 50), ImageLoader.load(new File("src/images/sabers.png")));
		
		lukMark.setPreferredSize(new Dimension(70, 70));
		obiMark.setPreferredSize(new Dimension(70, 70));
		yodaMark.setPreferredSize(new Dimension(70, 70));
		dartMark.setPreferredSize(new Dimension(70, 70));
		impMark.setPreferredSize(new Dimension(70, 70));
		fetMark.setPreferredSize(new Dimension(70, 70));
		sabersImg.setPreferredSize(new Dimension(70, 70));
	}
	
	private void initListeners() {
		lukMark.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.handleContentEvent(PlayerChoice.this, defineContentEvent(), nickNameEdt.getText(),
						Mark.Player1, LUKE_MARK_PATH);
			}
		});
		
		obiMark.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.handleContentEvent(PlayerChoice.this, defineContentEvent(), nickNameEdt.getText(),
						Mark.Player1, OBI_MARK_PATH);
			}
		});
		
		yodaMark.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.handleContentEvent(PlayerChoice.this, defineContentEvent(), nickNameEdt.getText(),
						Mark.Player1, YODA_MARK_PATH);
			}
		});
		
		dartMark.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						listener.handleContentEvent(PlayerChoice.this, defineContentEvent(), nickNameEdt.getText(),
								Mark.Player2, DART_MARK_PATH);
			}
		});
		
		fetMark.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.handleContentEvent(PlayerChoice.this, defineContentEvent(), nickNameEdt.getText(),
						Mark.Player2, FET_MARK_PATH);
			}
		});
		
		impMark.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.handleContentEvent(PlayerChoice.this, defineContentEvent(), nickNameEdt.getText(),
						Mark.Player2, IMP_MARK_PATH);
			}
		});
	}
	
	private ContentEvent defineContentEvent() {
		ContentEvent event = ContentEvent.Error;
		if (from instanceof ServerCreate) {
			event = ContentEvent.StartServer;
		} else if (from instanceof ServerConnect) {
			event = ContentEvent.ConnectServer;
		}
		
		return event;
	}
}
