package src;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball extends Sprite{
	private int xSpeed = -(3 + (int)(Math.random()) *3);
	private int ySpeed = -6;

	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void sideBorderCollsion() {
		xSpeed *= -1;
	}
	
	public void topBorderCollision() {
		ySpeed *= -1;
	}
	
	public void batCollision(Bat bat) {
		ySpeed *= -1;
		this.setY(bat.getY() - bat.getHeight());
	}
	
	public void boxCollision(Box box) {
		
		ySpeed *= -1;
		
		if(ySpeed < 20 && ySpeed > -20) {
			if(ySpeed < 0) {
				ySpeed--;
			}else{
				ySpeed++;
			}
		}
		
		this.setY(box.getY() + box.getHeight());
	}
	
	@Override
	public void update(Keyboard keyboard){
		setY(getY() + ySpeed);
		setX(getX() + xSpeed);
	}
	
	@Override 
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.red);
		graphics.fillOval(getX(), getY(), getWidth(), getHeight());
	}
}
