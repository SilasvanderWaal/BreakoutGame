package breakoutSpelSrc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball extends Sprite{
	private boolean isCollided = false;
	int xSpeed = 5;
	int ySpeed = 3;
	
	public boolean isCollided() {
		return isCollided;
	}

	public void setCollided(boolean isCollided) {
		this.isCollided = isCollided;
	}

	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	@Override
	public void update(Keyboard keyboard){
		
		if(getX() <= 0) 
			xSpeed *= -1;
		
		
		if(getX() >= 780) 
			xSpeed *= -1;
		
		
		if(getY() <= 0) 
			ySpeed *= -1;
		
		
		if(getY() >= 600) 
			System.exit(0);
		
		
		if(isCollided == true) {
			ySpeed *= -1;
			isCollided = false;
		}
		
		setY(getY() + ySpeed);
		setX(getX() + xSpeed);
	}
	
	@Override 
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.red);
		graphics.fillOval(getX(), getY(), getWidth(), getHeight());
	}
}
