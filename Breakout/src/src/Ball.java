package src;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball extends Sprite{
	private int xSpeed = -3;
	private int ySpeed = -5;

	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void sideBorderCollsion() {
		xSpeed *= -1;
	}
	
	public void topBorderCollision() {
		ySpeed *= -1;
	}
	
	public void playerCollision() {
		ySpeed *= -1;
	}
	
	public void batCollision() {
		ySpeed *= -1;
	}
	
	public void boxCollision() {
		ySpeed *= -1;
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
