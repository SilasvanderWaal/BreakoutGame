package src;

import java.awt.TextArea;
import java.awt.event.KeyEvent;


import javax.swing.*;

public class Program extends JFrame {
	GameBoard board;
	TextArea score;

	public Program() {
		board = new GameBoard();
		add(board);
		setResizable(false);	
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		board.start();
		
	}
	
	@Override
	protected void processKeyEvent(KeyEvent e) {
		super.processKeyEvent(e);
		board.processKeyEvent(e);
	}

	public static void main(String[] args) {
		Program program = new Program();
	}

}