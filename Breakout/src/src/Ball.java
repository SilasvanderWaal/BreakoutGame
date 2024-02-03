package src;


import java.awt.Color;
import java.awt.Graphics2D;

public class Ball extends Sprite{
	private int xSpeed = (-5 + (int)(Math.random() *10));
	private int ySpeed = -5;
	
	private int oldX;
	private int oldY;
	
	private int newX;
	private int newY;

	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void sideBorderCollsion(Border b) {
		if (xSpeed < 0) {
			this.setX(0 + Const.BORDERWIDTH);
		}else {
			this.setX((Const.WINDOWWIDTH - Const.BORDERWIDTH) - Const.BALLWIDTH);
		}
		
		xSpeed *= -1;
	}
	
	public void topBorderCollision(Border b) {
		this.setY(b.getY() + b.getHeight());
		ySpeed *= -1;
	}
	
	public void batCollision(Bat bat) {
		//Moving back the ball to previous x to see if the collision still is true
		newX = this.getX();
		this.setX(oldX);
		
		//If the collision still is true it is a top hit
		if(this.Collision(bat)) {
			this.setX(newX);
			ySpeed *= -1;
			this.setY(bat.getY() - this.getHeight());
		}else {
			//If the collision is false it is a side hit.
			if(xSpeed < 0) {
				this.setX(bat.getX() + bat.getWidth());
			}else {
				this.setX(bat.getX() - Const.BALLWIDTH);
			}
			
			xSpeed *= -1;
		}
	}
	
	public void boxCollision(Box box) {
		newX = this.getX();
		this.setX(oldX);
		
		if(this.Collision(box)) {
		//Bottom / Top collision
			if(ySpeed < 0)
				this.setY(box.getY() + box.getHeight());
			else
				this.setY(box.getY() + this.getHeight());
			
			//Reverse the Y direction
			ySpeed *= -1;
		}else {
		//Side collision, decide which side is collided by looking at the X values
			if(this.getX() > box.getX())
				this.setX(box.getX() + box.getWidth());
			else
				this.setX(box.getX() - this.getWidth());
			
			xSpeed *= -1;
		}
		
		//Increase the speed
		if(ySpeed < 20 && ySpeed > -20) {
			if(ySpeed < 0) {
				ySpeed--;
			}else{
				ySpeed++;
			}
		}
	}
	
	@Override
	public void update(Keyboard keyboard){
		oldX = this.getX();
		oldY = this.getY();
		setY(getY() + ySpeed);
		setX(getX() + xSpeed);
	}
	
	@Override 
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.red);
		graphics.fillOval(getX(), getY(), getWidth(), getHeight());
	}
}
