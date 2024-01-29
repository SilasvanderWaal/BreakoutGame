package breakoutSpelSrc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BoxList {
	private ArrayList<Box> boxlist;
	
	public BoxList() {
		boxlist = new ArrayList<Box>();
		for(int i = 0; i < 20 ; i++) {
			Box box = new Box(0 + ((i % 5) * 100), (i % 5) * 100, 100, 20, Color.red);
			boxlist.add(box);
		}
	}
	
	public void draw(Graphics2D graphics) {
		for(int i = 0; i < 20; i++ ) {
			boxlist.get(i).draw(graphics);
		}
	}	
}
