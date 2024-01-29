package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BoxList {
	private ArrayList<Box> boxlist;
	
	public BoxList() {
		boxlist = new ArrayList<Box>();
		for(int i = 0; i < 35 ; i++) {
			Box box = new Box(12 + ((int)(i / 5) * 112), 20 + (i % 5) * 50, 100, 15, Color.red);
			boxlist.add(box);
		}
	}
	
	public void draw(Graphics2D graphics) {
		for(Box box : boxlist) {
			box.draw(graphics);
		}
	}	
}
