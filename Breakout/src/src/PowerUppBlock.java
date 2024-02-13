package src;

import java.awt.Color;
import java.awt.Graphics2D;

public class PowerUppBlock extends Sprite{
	private Color color;
	private String boxId;
	
	public PowerUppBlock(int x, int y, int width, int height, Color color, String boxId) {
		super(x, y, width, height);
		this.color = color;
		this.boxId = boxId;
	}

	public Color getColor() {
		return color;
	}

	public String getBoxId() {
		return boxId;
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
