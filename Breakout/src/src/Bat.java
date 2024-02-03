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
	
	public void move(Keyboard keyboard) {
		if(keyboard.isKeyDown(Key.Left)) {
			setX(getX() - 7);
		}
	
		if(keyboard.isKeyDown(Key.Right)) {
			setX(getX() + 7);
		}
	}
	
	@Override
	public void update(Keyboard keyboard) {
		if(keyboard.isKeyDown(Key.Up)) {
			this.setWidth(Const.WINDOWWIDTH);
		}else {
			this.setWidth(Const.PLAYERWIDTH);
		}
	}
		
	
	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.blue);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
