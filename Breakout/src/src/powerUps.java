package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class powerUps {
	private Bat bat;
	private ArrayList<Ball> balls;
	private ArrayList<PowerUppBlock> blocks;
	private Game game;

	public powerUps(ArrayList<Ball> balls, Bat bat, Game game) {
		this.bat = bat;
		this.balls = balls;
		blocks = new ArrayList<PowerUppBlock>();
		this.game = game;
	}

	public void update(Keyboard keyboard){
		for(int i = 0; i < blocks.size(); i++) {
			PowerUppBlock block = blocks.get(i);

			block.update(keyboard);

			if(blocks.get(i).getY() > Const.WINDOWHEIGHT) {
				blocks.remove(i);
			}

			if(block.Collision(bat)) {
				switch (block.getBoxId()) {
				case "IncreaseScore10":	increaseScore(10); break;
				case "IncreaseScore20": increaseScore(20); break;
				case "IncreaseBatSize": increaseBatSize(); break;
				default:
					break;
				}

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

	public void increaseScore(int ammount) {
		game.setScore(game.getScore() + ammount);
	}

	public ArrayList<PowerUppBlock> getBlocks() {
		return blocks;
	}
}
