package src;

import java.awt.Color;
import java.awt.Graphics2D;

public class PowerUppBlock extends Sprite{
	private Color color;
	
	public PowerUppBlock(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
		// TODO Auto-generated constructor stub
	}

	public Color getColor() {
		return color;
	}

	@Override
	public void update(Keyboard keyboard) {
		// TODO Auto-generated method stub
		this.setY(this.getY() + Const.BALLSTARTSPEED); 
	}

	@Override
	public void draw(Graphics2D graphics) {
		// TODO Auto-generated method stub
		graphics.setColor(color);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
