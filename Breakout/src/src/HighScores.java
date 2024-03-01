package src;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class HighScores extends DefaultListModel<String>{
	private int[] topScores; 
	private String inithials;

	public HighScores() {
		topScores = new int[Const.MAXHIGHSCORES];
	}
	
	public void update(int score){
		for(int i = 0; i < Const.MAXHIGHSCORES; i++) {
			if(topScores[i] <= score) {
			moveBackOneStep(i);
			inithials = getInithials();
			this.add(i, inithials + ": " + score);
			topScores[i] = score;
			if(this.size() > 10) {
				this.remove(10);
			}
			break;
			}
			

		}
	}
	
	private void moveBackOneStep(int i) {
		for( int j = i; j < Const.MAXHIGHSCORES - 1; j++) {
			topScores[j + 1] = topScores[j];
		}
	}
	
	private String getInithials() {
		String name = null;
		do{
		name = JOptionPane.showInputDialog("Enter your inithials");
		}while(name.length() < 3 );
		
		if(name.length() > 3) {
			name = name.substring(0, 3);
		}
		return name;
	}
}
