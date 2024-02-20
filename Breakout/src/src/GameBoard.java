package src;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoard extends JComponent {
	private final int FPS = 60; 
	private Game game;
	private Keyboard keyboard;
	
	private File imgFile = new File("breakout_bg.png");
	private String imgFilePath = System.getProperty("user.dir") + "/src/" + "/src/" + imgFile.getPath();
	private Image img = Toolkit.getDefaultToolkit().getImage(imgFilePath);
	
	private boolean isPaused = false;
	
	private Program program;
	
	private Menu menuPanel;
	
	public GameBoard() {
		keyboard = new Keyboard(this);
		game = new Game(this);
		menuPanel = new Menu(this);
		this.setLayout(new BorderLayout());
		add(menuPanel, BorderLayout.CENTER);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		Toolkit.getDefaultToolkit().sync();
		super.paintComponent(arg0);
		Graphics2D graphics = (Graphics2D)arg0;
		graphics.setColor(Color.black);
		graphics.drawImage(img, 0, 0, null);
		setVisible(true);
		game.draw(graphics);
	}

	@Override
	protected void processKeyEvent(KeyEvent e) {
		super.processKeyEvent(e);
		if (e.getID() == KeyEvent.KEY_PRESSED)
			keyboard.processKeyEvent(e.getKeyCode(), true);
		else if (e.getID() == KeyEvent.KEY_RELEASED)
			keyboard.processKeyEvent(e.getKeyCode(), false);
	}

	public void start() {
		while(true) {
			if(isPaused == false) {
				game.update(keyboard);
				try {
					Thread.sleep(1000 / FPS); //Throttle thread
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				this.repaint();
			
			}else {
				System.out.println("Game paused");
				
			}
		}
	}
	
	public void showMenu() {
		pause();
		menuPanel.setVisible(true);
	}
	
	public void hideMenu() {
		menuPanel.setVisible(false);
		this.grabFocus();
		resume();
	}
	
	public void pause() {
		isPaused = true;
	}
	
	public void resume() {
		isPaused = false;
	}
	
	public boolean isPaused() {
		return isPaused;
	}
}