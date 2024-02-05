package src;

import java.awt.*;

public class Bat extends Sprite{
	public Bat(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	//Move the bat sideways, keys + space = boost
	public void move(Keyboard keyboard) {
		if(keyboard.isKeyDown(Key.Left)) {
			setX(getX() -Const.PLAYERBASESPEED);
			if(keyboard.isKeyDown(Key.Space)) {
				setX(getX() - Const.PLAYERBOOSTSPEED);
			}
		}

		if(keyboard.isKeyDown(Key.Right)) {
			setX(getX() + Const.PLAYERBASESPEED);
			if(keyboard.isKeyDown(Key.Space)) {
				setX(getX() + Const.PLAYERBOOSTSPEED);
			}
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
		graphics.setColor(Color.red);
		graphics.drawString("SvdW", getX() + (Const.PLAYERWIDTH/2), getY() + Const.PLAYERHEIGHT);
	}
}
