package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class BoxCollection {
	
	private ArrayList<Box> row1;
	private ArrayList<Box> row2;
	private ArrayList<Box> row3;
	
	public BoxCollection() {
		row1 = new ArrayList<Box>();
		row2 = new ArrayList<Box>();
		row3 = new ArrayList<Box>();

		for(int i = 0; i < Const.BOXESEACHROW; i++) {
			row1.add(new Box(i *( Const.BOXWIDTH + Const.BOXGAPX) + Const.BORDERWIDTH + Const.BOXGAPX, Const.BORDERWIDTH + Const.BOXGAPY, (Const.WINDOWWIDTH - ((Const.BOXESEACHROW + 1) * Const.BOXGAPX)) / Const.BOXESEACHROW, Const.BOXHEIGHT, 1));
			row2.add(new Box(i *( Const.BOXWIDTH + Const.BOXGAPX) + Const.BORDERWIDTH + Const.BOXGAPX, Const.BORDERWIDTH + Const.BOXHEIGHT +  2 * Const.BOXGAPY, (Const.WINDOWWIDTH - ((Const.BOXESEACHROW + 1) * Const.BOXGAPX)) / Const.BOXESEACHROW, Const.BOXHEIGHT, 2));
			row3.add(new Box(i *( Const.BOXWIDTH + Const.BOXGAPX) + Const.BORDERWIDTH + Const.BOXGAPX,  2 * Const.BOXHEIGHT +  4 * Const.BOXGAPY, (Const.WINDOWWIDTH - ((Const.BOXESEACHROW + 1) * Const.BOXGAPX)) / Const.BOXESEACHROW, Const.BOXHEIGHT, 3));
		}
	}
	
	public void update(Keyboard keyboard) {

	}
	
	public void draw(Graphics2D graphics) {
		//Displaying the boxes
		for(int i = 0; i < row1.size(); i++) {
			row1.get(i).draw(graphics);
		}
		
		for(int i = 0; i < row2.size(); i++) {
			row2.get(i).draw(graphics);
		}
		
		for(int i = 0; i < row3.size(); i++) {
			row3.get(i).draw(graphics);
		}
	}

	public ArrayList<Box> getRow1() {
		return row1;
	}

	public void setRow1(ArrayList<Box> row1) {
		this.row1 = row1;
	}

	public ArrayList<Box> getRow2() {
		return row2;
	}

	public void setRow2(ArrayList<Box> row2) {
		this.row2 = row2;
	}

	public ArrayList<Box> getRow3() {
		return row3;
	}

	public void setRow3(ArrayList<Box> row3) {
		this.row3 = row3;
	}
	
	
}
