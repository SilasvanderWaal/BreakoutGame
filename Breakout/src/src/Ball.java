package src;


import java.awt.Color;
import java.awt.Graphics2D;

public class Ball extends Sprite{
	private int xSpeed;
	private int ySpeed;

	private int newX;

	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		//Sets the default speed, random random angle
		ySpeed = -Const.BALLSTARTSPEED;
		xSpeed = (int)(Math.random()*4) + 2;
		if(Math.random() < Const.FIFTYPROCENT) {
			xSpeed *= -1;
		}
	}


	//Changes the ball direction on a collision with a side border
	public void sideBorderCollsion() {
		if (xSpeed < 0) {
			this.setX(0 + Const.BORDERWIDTH);
		}else {
			this.setX((Const.WINDOWWIDTH - Const.BORDERWIDTH) - Const.BALLWIDTH);
		}

		xSpeed *= -1;
	}

	//Changes the ball direction on a collision with a top border
	public void topBorderCollision(Border b) {
		this.setY(b.getY() + b.getHeight());
		ySpeed *= -1;
	}

	//Changes the ball direction on a collision with the player, also detects side collisions
	public void batCollision(Bat bat) {

		//Moving back the ball to one x to see if the collision still is true
		newX = this.getX();
		interpolate();

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

	//Changes the ball direction on a collision with a box, also detects side collisions
	public void boxCollision(Box box) {

		newX = this.getX();
		//Moving the ball back one X coordinate, keep the Y.
		interpolate();

		//If the box still is in collision with the ball, it is either a top or a bottom hit
		if(this.Collision(box)) {
			if(ySpeed < 0) {
				this.setX(newX);
				this.setY(box.getY() + box.getHeight());
			}else {
				this.setX(newX);
				this.setY(box.getY() - this.getHeight());
			}
			ySpeed *= -1;
		}else {
			//If the box is not in collision any longer after interpolating its x value, the collision is a side collision
			if(this.getX() > box.getX())
				this.setX(box.getX() + box.getWidth());
			else
				this.setX(box.getX() - this.getWidth());

			xSpeed *= -1;
		}

		//Increase the speed
		if(ySpeed < Const.BALLMAXSPEED && ySpeed > -Const.BALLMAXSPEED) {
			if(ySpeed < 0) {
				ySpeed--;
			}else{
				ySpeed++;
			}
		}
	}


	@Override
	public void update(Keyboard keyboard){	
		setY(getY() + ySpeed);
		setX(getX() + xSpeed);
	}

	//Checks if the ball is out of the game field
	public boolean ballOut() {
		if( this.getY() > Const.WINDOWHEIGHT) {
			return true;
		}else {
			return false;
		}
	}

	public void interpolate() {
		this.setX(this.getX() - 1);
	}

	@Override 
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.white);
		graphics.fillOval(getX(), getY(), getWidth(), getHeight());
	}
}
