package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Box extends Sprite{
	private Color color;
	
	public Box(int x, int y, int width, int height, Color color){
		super(x, y , width, height);
		this.color = color;
	}
	
	@Override
	public void update(Keyboard keyboard) {
		
	}
	
	@Override 
	public void draw(Graphics2D graphics) {
		graphics.setColor(color);
		graphics.fillRect(getX(), getY(), getWidth(),getHeight());
	}
}
