package breakoutSpelSrc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

public class Game {
	private Ball ball;
	private Bat bat;
	private BoxList boxlist;
	private Box box;
	
	public Game(GameBoard board) {
		ball = new Ball(400, 300, 20, 20);
		bat = new Bat(400, 550, 100, 10);
		boxlist = new BoxList();
	}
	
	private boolean Collision() {
		return bat.getBounds().intersects(ball.getBounds());
	}

	public void update(Keyboard keyboard) {
		ball.update(keyboard);
		bat.update(keyboard);
		if(Collision()) {
			ball.setY(bat.getY() - ball.getHeight());
			ball.setCollided(true);
		}
	}

	public void draw(Graphics2D graphics) {
		ball.draw(graphics);
		bat.draw(graphics);
		boxlist.draw(graphics);
		
	}
}
