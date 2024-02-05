package src;

import java.awt.Color;
import java.awt.Graphics2D;

public class Box extends Sprite{
	private Color color;
	private int lives;
	
	public Box(int x, int y, int width, int height, int lives){
		super(x, y , width, height);
		this.lives = lives;
	}
	
	@Override
	public void update(Keyboard keyboard) {
	}
	
	@Override 
	public void draw(Graphics2D graphics) {
		//Set the right color after the lives of he box
		switch (lives) {
		case 1:
			this.color = Color.red;
			break;
		case 2:
			this.color = Color.yellow;
			break;
		case 3:
			this.color = Color.green;
			break;
		default:
			break;
		}
		
		graphics.setColor(color);
		
		graphics.fillRect(getX(), getY(), getWidth(),getHeight());
	}
	
	public boolean isKilled() {
		if(1 >= this.lives) 
		{
			return true;
		}else {
			lives--;
			return false;
		}
	}
}
