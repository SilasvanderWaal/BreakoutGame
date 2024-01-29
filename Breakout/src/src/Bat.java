package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bat extends Sprite{
	public Bat(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	@Override
	public void update(Keyboard keyboard) {
		if(this.getX() > 0 && this.getX() < 700) {
			if(keyboard.isKeyDown(Key.Left)) {
				setX(getX() - 3);
				if(keyboard.isKeyDown(Key.Left) && keyboard.isKeyDown(Key.Space)){
					setX(getX() - 5);
				}
			}
		
			if(keyboard.isKeyDown(Key.Right)) {
				setX(getX() + 3);
				if(keyboard.isKeyDown(Key.Right) && keyboard.isKeyDown(Key.Space)){
					setX(getX() + 5);
				}
			}
		}else {
			if(this.getX() <= 0) {
				setX(getX() + 1);
			}
			
			if(this.getX() >= 700){
				setX(getX() - 1);
			}
		}
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.cyan);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
