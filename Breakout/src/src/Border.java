package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Border extends Sprite{

	public Border(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void update(Keyboard keyboard) {
		
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.green);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
}
