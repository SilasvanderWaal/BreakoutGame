package src;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameBoard extends JComponent {
	private final int FPS = 60; 
	private Game game;
	private Keyboard keyboard;

	private File imgFile = new File("breakout_bg.png");
	private String imgFilePath = System.getProperty("user.dir") + "/src/" + "/src/" + imgFile.getPath();
	private Image img = Toolkit.getDefaultToolkit().getImage(imgFilePath);

	private boolean isPaused = false;
	private boolean isDead = false;

	private JPanel menuHolder;
	CardLayout card;

	private Menu pauseMenu;
	private Menu deathMenu;

	public GameBoard() {
		keyboard = new Keyboard(this);
		game = new Game(this);

		this.setLayout(new BorderLayout());
		card = new CardLayout();

		menuHolder = new JPanel(card);
		menuHolder.setVisible(false);

		pauseMenu = new Menu(this, "Game paused");
		deathMenu = new Menu(this, "Game over");

		menuHolder.add(pauseMenu, "pause");
		menuHolder.add(deathMenu, "death");

		add(menuHolder, BorderLayout.CENTER);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Const.WINDOWWIDTH, Const.WINDOWHEIGHT);
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
			if(!isPaused && !isDead) {
				game.update(keyboard);
				try {
					Thread.sleep(1000 / FPS); //Throttle thread
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				this.repaint();

				if(game.getLives() == 0) {
					this.death();
					isDead = true;
				}

			}else if(isPaused == true){
				System.out.println("Game paused");
			}
		}
	}

	public void restart() {
		game = null;
		game = new Game(this);
		isDead = false;
		hideDeathMenu();
	}

	public void showMenu() {
		pause();
		menuHolder.setVisible(true);
		card.show(menuHolder, "pause");
	}

	public void hideMenu() {
		menuHolder.setVisible(false);
		this.grabFocus();
		resume();
	}

	public void death() {
		pause();
		deathMenu.addLatestRun(game.getScore(), pauseMenu);
		showDeathMenu();
	}

	public void showDeathMenu() {
		menuHolder.setVisible(true);
		card.show(menuHolder, "death");
	}

	public void hideDeathMenu(){
		menuHolder.setVisible(false);
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

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
}