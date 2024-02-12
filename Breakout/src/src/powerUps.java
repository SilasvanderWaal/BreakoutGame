package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class powerUps {
	private Bat bat;
	private ArrayList<Ball> balls;
	private ArrayList<PowerUppBlock> blocks;
	
	public powerUps(ArrayList<Ball> balls, Bat bat) {
		this.bat = bat;
		this.balls = balls;
		blocks = new ArrayList<PowerUppBlock>();
	}
	
	public void update(Keyboard keyboard){
		for(int i = 0; i < blocks.size(); i++) {
			PowerUppBlock block = blocks.get(i);
			
			block.update(keyboard);
			
			if(blocks.get(i).getY() > Const.WINDOWHEIGHT) {
				blocks.remove(i);
			}
				
			if(block.Collision(bat)) {
					increaseBatSize();
					blocks.remove(i);
			}
		}
	}
	
	public void draw(Graphics2D graphics) {	
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).draw(graphics);
		}
	}
	
	public void increaseBatSize() {
		bat.setWidth(bat.getWidth() + Const.POWERUPPSIZEINCREASER);
	}

	public ArrayList<PowerUppBlock> getBlocks() {
		return blocks;
	}
}
