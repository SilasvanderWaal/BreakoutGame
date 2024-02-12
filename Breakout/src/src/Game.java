package src;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.*;

import javax.swing.JOptionPane;

public class Game {
	private ArrayList<Ball> balls;
	private Bat bat;
	private ArrayList<Border> borders;
	private BoxCollection boxcollection;
	private CollisionDetection collisiondetection;
	public Box box;

	private int lives = Const.STARTLIVES;
	private int score = 0;
	
	private powerUps powerups;


	public Game(GameBoard board) {
		//Creates a list for all balls and adds the first one
		balls = new ArrayList<Ball>();
		balls.add(new Ball(Const.BALLSTARTPOSITIONX, Const.BALLSTARTPOSITIONY, Const.BALLWIDTH, Const.BALLHEIGHT));
		
		//Creates the player
		bat = new Bat(Const.PLAYERSTARTPOSITIONX, Const.PLAYERSTARTPOSITIONY, Const.PLAYERWIDTH, Const.PLAYERHEIGHT);

		//Border list
		borders = new ArrayList<Border>();
		//Side border [0]
		borders.add(new Border(0, 0, Const.BORDERWIDTH, Const.WINDOWHEIGHT));
		//Top border [1]
		borders.add(new Border(0, 0, Const.WINDOWWIDTH, Const.BORDERWIDTH));
		//Side border [2]
		borders.add(new Border(Const.WINDOWWIDTH - Const.BORDERWIDTH, 0, Const.BORDERWIDTH, Const.WINDOWHEIGHT));

		//Creates a collection with all the bricks
		boxcollection = new BoxCollection();
		
				
		powerups = new powerUps(balls, bat);
		
		//Creates the collision detection system
		collisiondetection = new CollisionDetection(balls, boxcollection, bat, this, borders, powerups);

	}


	public void update(Keyboard keyboard) {
		//Updating all the balls
		for(Ball b : balls) {
			b.update(keyboard);
		}
		
		//Updating the player
		bat.update(keyboard);
		
		//Updating the collision detection system
		collisiondetection.update(keyboard);

		//Display game over if there are no lives left
		if(lives == 0 ) {
			JOptionPane.showMessageDialog(null, "Game over! Score: " + score);
			System.exit(0);
		}
		
		powerups.update(keyboard);
	}

	public void draw(Graphics2D graphics) {
		//Display all the balls
		for(Ball b : balls) {
			b.draw(graphics);
		}

		//Display the player
		bat.draw(graphics);

		//Display all the boxes
		boxcollection.draw(graphics);

		//Display the borders
		for(Border b : borders) {
			b.draw(graphics);
		}	

		//Display the lives scoreboard
		graphics.setFont(new Font("livesFont", Font.ROMAN_BASELINE, Const.FONTSIZE));
		graphics.drawString("Lives: " + Integer.toString(lives) , Const.WINDOWWIDTH / 4, Const.WINDOWHEIGHT);
		
		//Display the player score
		graphics.drawString("Score: "+ Integer.toString(score), 2 * (Const.WINDOWWIDTH / 4), Const.WINDOWHEIGHT);
		
		powerups.draw(graphics);
	}


	public int getLives() {
		return lives;
	}


	public void setLives(int lives) {
		this.lives = lives;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
}
