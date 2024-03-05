package src;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Sprite {
	private int x, y, width, height;
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	public void setX(int x) { this.x = x; };
	public void setY(int y) { this.y = y; };
	public void setWidth(int width) { this.width = width; };
	public void setHeight(int height) { this.height = height; };
	public Sprite(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void update(Keyboard keyboard);
	public abstract void draw(Graphics2D graphics);

	public boolean Collision(Sprite objekt) {
		Rectangle rec1 = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		Rectangle rec2 = new Rectangle(objekt.getX(), objekt.getY(), objekt.getWidth(), objekt.getHeight());
		return rec1.intersects(rec2);
	}
}
