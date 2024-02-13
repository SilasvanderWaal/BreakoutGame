package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;

public class GameBoard extends JComponent {
	private final int FPS = 60; 
	private Game game;
	private Keyboard keyboard;
	
	private Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\silas\\git\\BreakoutGame\\Breakout\\src\\src\\breakout_bg.png");
	
	private boolean isPaused;
	
	public GameBoard() {
		keyboard = new Keyboard(this);
		game = new Game(this);
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
				System.out.println("Game running");
			}else {
				System.out.println("Game paused");
			}
		}
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

