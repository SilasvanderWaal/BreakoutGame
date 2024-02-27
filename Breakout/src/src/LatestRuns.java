package src;

import javax.swing.DefaultListModel;

public class LatestRuns extends DefaultListModel<Integer>{
	int position;
	
	public LatestRuns() {
		position = 0;
		this.add(0, 4);
	}
	
	public void addLatest(int score, String inithials) {
		this.add(position++, 0);
	}
}
