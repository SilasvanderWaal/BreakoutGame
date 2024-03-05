package src;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CollisionDetection {

	private BoxCollection boxcollection;
	private Bat bat;
	private Game game;
	private ArrayList<Border> borders;
	private ArrayList<Ball> balls;
	private powerUps powerups;

	private int batOldX;

	public CollisionDetection(ArrayList<Ball> balls, BoxCollection boxcollection, Bat bat, Game game, ArrayList<Border> borders, powerUps powerups) {
		this.balls = balls;
		this.boxcollection = boxcollection;
		this.bat = bat;
		this.game = game;
		this.borders = borders;
		this.powerups = powerups;
	}

	public void update(Keyboard keyboard) {
		batCollisionWithBorder(keyboard);

		for(int j = 0; j < balls.size(); j++) {
			Ball ball = balls.get(j);
			ballCollistionWithGamefield(keyboard, ball);
			row1Collision(keyboard, ball);
			row2Collision(keyboard, ball);
			row3Collision(keyboard, ball);
			ballOutOfField(keyboard);
			allRowsEmpty(keyboard);
		}
	}

	public void batCollisionWithBorder(Keyboard keyboard) {
		//Bat collision with border
		if(!bat.Collision(borders.get(0)) && !bat.Collision(borders.get(2))) {
			batOldX = bat.getX();
			bat.move(keyboard);
		}else {

			if(bat.getX() > Const.WINDOWWIDTH / 2) {
				bat.setX((Const.WINDOWWIDTH - Const.BORDERWIDTH) - bat.getWidth());
			}else {
				bat.setX(batOldX);
			}
		}
	}

	public void ballCollistionWithGamefield(Keyboard keyboard, Ball ball) {
		//Ball collision with side borders
		if(ball.Collision(borders.get(0)) || ball.Collision(borders.get(2))) 
			ball.sideBorderCollsion(borders.get(2));

		//Ball collision with top border
		else if(ball.Collision(borders.get(1))) 
			ball.topBorderCollision(borders.get(1));

		//Ball collision with bat
		else if(ball.Collision(bat)) 
			ball.batCollision(bat);
	}

	public void row1Collision(Keyboard keyboard, Ball ball) {
		//Ball collision with box on row1
		for(int i = 0; i < boxcollection.getRow1().size(); i++) {
			ArrayList<Box> row1 = boxcollection.getRow1();
			Box box = row1.get(i);

			if(row1.get(i).Collision(ball)) {
				ball.boxCollision(box);

				if(box.isKilled()) {
					if(Math.random() < Const.TENPROCENT) {
						powerups.getBlocks().add(new PowerUppBlock(box.getX() + (Const.BOXWIDTH / 2), box.getY() + Const.BOXHEIGHT + Const.POWERUPPBLOCKHEIGHT, Const.POWERUPPBLOCKWIDTH, Const.POWERUPPBLOCKHEIGHT, Color.orange, "IncreaseScore20"));
					}
					row1.remove(i);
					game.setScore(game.getScore() + box.getPoints());
				}

				if(row1.isEmpty())
					game.setScore(game.getScore() + Const.BIGSCORE);
			}
		}
	}

	public void row2Collision(Keyboard keyboard, Ball ball) {
		//Ball collision for boxes on row2
		for(int i = 0; i < boxcollection.getRow2().size(); i++) {
			ArrayList<Box> row2 = boxcollection.getRow2();
			Box box = row2.get(i);

			if(box.Collision(ball)) {
				ball.boxCollision(box);

				if(box.isKilled()) {
					if(Math.random() < Const.THIRTHYPROCENT) {
						powerups.getBlocks().add(new PowerUppBlock(box.getX() + (Const.BOXWIDTH / 2), box.getY() + Const.BOXHEIGHT + Const.POWERUPPBLOCKHEIGHT, Const.POWERUPPBLOCKWIDTH, Const.POWERUPPBLOCKHEIGHT, Color.green, "IncreaseScore10"));
					}
					row2.remove(i);
					game.setScore(game.getScore() + box.getPoints());
				}

				if(row2.isEmpty()) 
					game.setScore(game.getScore() + Const.BIGSCORE);
			}
		}
	}

	public void row3Collision(Keyboard kayboard, Ball ball) {
		//Ball collision for boxes on row3
		for(int i = 0; i < boxcollection.getRow3().size(); i++) {
			ArrayList<Box> row3 = boxcollection.getRow3();
			Box box = row3.get(i);

			if(row3.get(i).Collision(ball)) {
				ball.boxCollision(box);

				if(box.isKilled()) {

					game.setScore(game.getScore() + box.getPoints());

					if(Math.random() < Const.FIFTYPROCENT) {
						powerups.getBlocks().add(new PowerUppBlock(box.getX() + (Const.BOXWIDTH / 2), box.getY() + Const.BOXHEIGHT + Const.POWERUPPBLOCKHEIGHT, Const.POWERUPPBLOCKWIDTH, Const.POWERUPPBLOCKHEIGHT, Color.blue, "IncreaseBatSize"));
					}

					row3.remove(i);
				}

				if(row3.isEmpty()) 
					game.setScore(game.getScore() + Const.BIGSCORE);
			}
		}
	}

	public void ballOutOfField(Keyboard keyboard) {
		//Looping over the balls and checking if they are out of the gamefield
		for(int i = 0; i < balls.size(); i++) {
			Ball b = balls.get(i);
			if(b.ballOut()) {
				game.setLives(game.getLives() - 1);
				balls.remove(b);
				balls.add(new Ball(Const.BALLSTARTPOSITIONX, Const.BALLSTARTPOSITIONY, Const.BALLWIDTH, Const.BALLHEIGHT));
			}
		}
	}

	public void allRowsEmpty(Keyboard keyboard) {
		//Checking if all rows are empty
		if(boxcollection.getRow1().isEmpty() && boxcollection.getRow2().isEmpty() && boxcollection.getRow3().isEmpty()) {
			JOptionPane.showMessageDialog(null, "You won! Score: " + game.getScore());
			game.setLives(0);
		}
	}
}
