package src;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.TextArea;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game {
	private ArrayList<Ball> balls;
	private Bat bat;
	private ArrayList<Border> borders;
	private BoxCollection boxcollection;
	
	private int batOldX;
	public Box box;
	
	private int lives = Const.STARTLIVES;
	private int score = 0;


	public Game(GameBoard board) {
		balls = new ArrayList<Ball>();
		
		balls.add(new Ball(Const.BALLSTARTPOSITIONX, Const.BALLSTARTPOSITIONY, Const.BALLWIDTH, Const.BALLHEIGHT));
		bat = new Bat(Const.PLAYERSTARTPOSITIONX, Const.PLAYERSTARTPOSITIONY, Const.PLAYERWIDTH, Const.PLAYERHEIGHT);
		
		//Border list
		borders = new ArrayList<Border>();
		//Side border [0]
		borders.add(new Border(0, 0, Const.BORDERWIDTH, Const.WINDOWHEIGHT));
		//Top border [1]
		borders.add(new Border(0, 0, Const.WINDOWWIDTH, Const.BORDERWIDTH));
		//Side border [2]
		borders.add(new Border(Const.WINDOWWIDTH - Const.BORDERWIDTH, 0, Const.BORDERWIDTH, Const.WINDOWHEIGHT));
		
		boxcollection = new BoxCollection();
		
		}
	

	public void update(Keyboard keyboard) {
		for(Ball b : balls) {
			b.update(keyboard);
		}
		
		bat.update(keyboard);
		
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
			if(ball.Collision(borders.get(0)) || ball.Collision(borders.get(2))) {
				ball.sideBorderCollsion(borders.get(2));
			}
			//Ball collision with top border
			else if(ball.Collision(borders.get(1))) {
				ball.topBorderCollision(borders.get(1));
			}
			//Ball collision with bat
			else if(ball.Collision(bat)) {
				ball.batCollision(bat);
			}
			
			//Ball collision with box on row1
			for(int i = 0; i < boxcollection.getRow1().size(); i++) {
				ArrayList<Box> row1 = boxcollection.getRow1();
				
				if(row1.get(i).Collision(ball)) {
					ball.boxCollision(row1.get(i));
					
					if(row1.get(i).isKilled()) {
					row1.remove(i);
					score++;
					}
					
					if(row1.isEmpty()) {
						score += Const.BIGSCORE;
						
						//For future game mechanics
						//balls.add(new Ball(Const.BALLSTARTPOSITIONX, Const.BALLSTARTPOSITIONY, Const.BALLWIDTH, Const.BALLHEIGHT));
					}
				}
			}
			
			//Ball collision for boxes on row2
			for(int i = 0; i < boxcollection.getRow2().size(); i++) {
				ArrayList<Box> row2 = boxcollection.getRow2();

				if(row2.get(i).Collision(ball)) {
					ball.boxCollision(row2.get(i));
					
					if(row2.get(i).isKilled()) {
						row2.remove(i);
						score++;
					}
					
					if(row2.isEmpty()) {
						score += Const.BIGSCORE;
						
						//For future game mechanics
						//balls.add(new Ball(Const.BALLSTARTPOSITIONX, Const.BALLSTARTPOSITIONY, Const.BALLWIDTH, Const.BALLHEIGHT));
					}
				}
			}
			
			//Ball collision for boxes on row3
			for(int i = 0; i < boxcollection.getRow3().size(); i++) {
				ArrayList<Box> row3 = boxcollection.getRow3();

				if(row3.get(i).Collision(ball)) {
					ball.boxCollision(row3.get(i));
					
					if(row3.get(i).isKilled()) {
						row3.remove(i);
						score++;
					}
					
					if(row3.isEmpty()) {
						score += Const.BIGSCORE;
						
						//For future game mechanics
						//balls.add(new Ball(Const.BALLSTARTPOSITIONX, Const.BALLSTARTPOSITIONY, Const.BALLWIDTH, Const.BALLHEIGHT));
					}
				}
			}
			
			//Checking if a entire row is deleted
			if(boxcollection.getRow1().isEmpty() && boxcollection.getRow2().isEmpty() && boxcollection.getRow3().isEmpty()) {
				JOptionPane.showMessageDialog(null, "You won! Score: " + score);
				System.exit(0);
				
				//For future mechanics 
				
			}
			
			//Looping over the balls and checking if they are out of the gamefield
			for(Ball b : balls) {
				if(b.ballOut()) {
					lives--;
				}
				
				//Display game over if there are no lives left
				if(lives == 0 ) {
					JOptionPane.showMessageDialog(null, "Game over! Score: " + score);
					System.exit(0);
				}
			}
		}
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
		graphics.setFont(new Font("livesFont", Font.ITALIC, Const.FONTSIZE));
		graphics.drawString("Lives: " + Integer.toString(lives) , Const.WINDOWWIDTH / 4, Const.WINDOWHEIGHT);
		graphics.drawString("Score: "+ Integer.toString(score), 3 * (Const.WINDOWWIDTH / 4), Const.WINDOWHEIGHT);
	}
}
