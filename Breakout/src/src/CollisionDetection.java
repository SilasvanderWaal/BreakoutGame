package src;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CollisionDetection {

	private BoxCollection boxcollection;
	private Bat bat;
	private Game game;
	private ArrayList<Border> borders;
	private ArrayList<Ball> balls;

	private int batOldX;

	public CollisionDetection(ArrayList<Ball> balls, BoxCollection boxcollection, Bat bat, Game game, ArrayList<Border> borders) {
		this.balls = balls;
		this.boxcollection = boxcollection;
		this.bat = bat;
		this.game = game;
		this.borders = borders;
	}

	public void update(Keyboard keyboard) {
		//Bat collision with border
		if(!bat.Collision(borders.get(0)) && !bat.Collision(borders.get(2))) {
			batOldX = bat.getX();
			bat.move(keyboard);
		}else {
			bat.setX(batOldX);
		}

		for(int j = 0; j < balls.size(); j++) {
			Ball ball = balls.get(j);
			//Ball collision with side borders
			if(ball.Collision(borders.get(0)) || ball.Collision(borders.get(2))) 
				ball.sideBorderCollsion(borders.get(2));

			//Ball collision with top border
			else if(ball.Collision(borders.get(1))) 
				ball.topBorderCollision(borders.get(1));

			//Ball collision with bat
			else if(ball.Collision(bat)) 
				ball.batCollision(bat);


			//Ball collision with box on row1
			for(int i = 0; i < boxcollection.getRow1().size(); i++) {
				ArrayList<Box> row1 = boxcollection.getRow1();

				if(row1.get(i).Collision(ball)) {
					ball.boxCollision(row1.get(i));

					if(row1.get(i).isKilled()) {
						row1.remove(i);
						game.setScore(game.getScore() + 1);
					}

					if(row1.isEmpty())
						game.setScore(game.getScore() + Const.BIGSCORE);
				}
			}

			//Ball collision for boxes on row2
			for(int i = 0; i < boxcollection.getRow2().size(); i++) {
				ArrayList<Box> row2 = boxcollection.getRow2();

				if(row2.get(i).Collision(ball)) {
					ball.boxCollision(row2.get(i));

					if(row2.get(i).isKilled()) {
						row2.remove(i);
						game.setScore(game.getScore() + 1);
					}

					if(row2.isEmpty()) 
						game.setScore(game.getScore() + Const.BIGSCORE);
				}
			}

			//Ball collision for boxes on row3
			for(int i = 0; i < boxcollection.getRow3().size(); i++) {
				ArrayList<Box> row3 = boxcollection.getRow3();

				if(row3.get(i).Collision(ball)) {
					ball.boxCollision(row3.get(i));

					if(row3.get(i).isKilled()) {
						row3.remove(i);
						game.setScore(game.getScore() + 1);;
					}

					if(row3.isEmpty()) 
						game.setScore(game.getScore() + Const.BIGSCORE);
				}
			}

			//Checking if a entire row is deleted
			if(boxcollection.getRow1().isEmpty() && boxcollection.getRow2().isEmpty() && boxcollection.getRow3().isEmpty()) {
				JOptionPane.showMessageDialog(null, "You won! Score: " + game.getScore());
				System.exit(0);
			}

			//Looping over the balls and checking if they are out of the gamefield
			for(Ball b : balls) {
				if(b.ballOut()) {
					game.setLives(game.getLives() - 1);
				}
			}
		}
	}
}
