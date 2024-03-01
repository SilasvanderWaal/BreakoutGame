package src;

import javax.swing.DefaultListModel;

public class LatestRuns extends DefaultListModel<String>{
	int position;
	
	public LatestRuns() {
		position = 0;
	}
	
	public void addLatest(int score) {
		this.add(0, "" + score);
		
		if(this.size() > 3)
			this.remove(3);

	}
}
