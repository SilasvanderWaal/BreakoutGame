package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

public class Game {
	private Ball ball;
	private Bat bat;
	private ArrayList<Border> borders;
	private BoxCollection boxcollection;
	
	private int batOldX;
	public Box box;


	public Game(GameBoard board) {
		ball = new Ball(Const.BALLSTARTPOSITIONX, Const.BALLSTARTPOSITIONY, Const.BALLWIDTH, Const.BALLHEIGHT);
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
		
		box = new Box(0, 2 * Const.BOXHEIGHT +  4 * Const.BOXGAPY, Const.WINDOWWIDTH, 10, Color.PINK);
	}
	

	public void update(Keyboard keyboard) {
		ball.update(keyboard);
		bat.update(keyboard);
		
		//Bat collision with border
		if(!bat.Collision(borders.get(0)) && !bat.Collision(borders.get(2))) {
			batOldX = bat.getX();
			bat.move(keyboard);
		}else {
			bat.setX(batOldX);
		}
		
		//Ball collision with side borders
		if(ball.Collision(borders.get(0)) || ball.Collision(borders.get(2))) {
			ball.sideBorderCollsion();
		}
		//Ball collision with top border
		else if(ball.Collision(borders.get(1))) {
			ball.topBorderCollision();
		}
		//Ball collision with bat
		else if(ball.Collision(bat)) {
			ball.batCollision();
		}
		
		//Ball collision with box on row1
		for(int i = 0; i < boxcollection.getRow3().size(); i++) {
			if(boxcollection.getRow3().get(i).Collision(ball)) {
				ball.boxCollision();
				boxcollection.getRow3().remove(i);
			}
		}
		
		
	}

	public void draw(Graphics2D graphics) {
		ball.draw(graphics);
		bat.draw(graphics);
		
		boxcollection.draw(graphics);
		
		for(Border b : borders) {
			b.draw(graphics);
		}
		
		// box.draw(graphics);
		
		
	}
}
