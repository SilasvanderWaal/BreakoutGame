package src;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class HighScores extends DefaultListModel<String>{
	private int[] topScores; 
	private String initials;

	public HighScores() {
		topScores = new int[Const.MAXHIGHSCORES];
	}
	
	public void update(int score, Menu pauseMenu){
		for(int i = 0; i < Const.MAXHIGHSCORES; i++) {
			if(topScores[i] <= score) {
			moveBackOneStep(i);
			initials = getInithials();
			this.add(i, initials + ": " + score);
			topScores[i] = score;
			if(this.size() > Const.MAXHIGHSCORES) {
				this.remove(Const.MAXHIGHSCORES);
			}
			pauseMenu.addLatestRun(score, pauseMenu, initials);
			break;
			}
		}
	}
	
	public void update(int score, Menu pauseMenu, String initials){
		for(int i = 0; i < Const.MAXHIGHSCORES; i++) {
			if(topScores[i] <= score) {
			moveBackOneStep(i);
			this.add(i, initials + ": " + score);
			topScores[i] = score;
			if(this.size() > Const.MAXHIGHSCORES) {
				this.remove(Const.MAXHIGHSCORES);
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
		}while(name.length() < Const.MAXPLAYERINITHIALS );
		
		if(name.length() > Const.MAXPLAYERINITHIALS) {
			name = name.substring(0, Const.MAXPLAYERINITHIALS);
		}
		return name;
	}
}
