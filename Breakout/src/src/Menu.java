package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener{
	
	private GameBoard gb;
	private String gameStatus;
	private String playButton;

	private JButton buttonLeft;
	private JButton buttonRight;
	
	private JPanel buttons;
	
	private JPanel north;
	private JPanel east;
	private JPanel west;
	private JPanel center;
	
	private JLabel header;
	
	private  JList<Integer> latestRuns;
	private  JLabel latestRunsHeader;
	private static LatestRuns latestRunsList;

	private  JLabel scoreboardHeader;
	private  JList<Integer> scoreboard;
	private static HighScores highscoresList;

    public Menu(GameBoard gb, String gameStatus) {
        this.gb = gb;
        this.setVisible(false);
        this.gameStatus = gameStatus;
        
        if(this.gameStatus == "Game paused")
        	this.playButton = "Resume";
        
        if(this.gameStatus == "Game over")
        	this.playButton = "Retry";
        
        this.setOpaque(true);
        this.setLayout(new BorderLayout());
        
        initComponents();
    }
                       
    private void initComponents() {    	
    	//North
    	north = new JPanel();
    	header = new JLabel(gameStatus);
    	
    	north.add(header);
    	header.setHorizontalAlignment(JLabel.CENTER);
    	header.setFont(new Font("Georgia", 0, 36));
    	
    	add(north, BorderLayout.NORTH);
    	
    	//EAST
    	east = new JPanel();
    	highscoresList = new HighScores();
    	scoreboardHeader = new JLabel("Highscores");
    	scoreboard = new JList<Integer>(highscoresList);
    	
    	east.add(scoreboardHeader);
    	east.add(scoreboard);
    	
    	add(east, BorderLayout.EAST);
    	east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
    	
    	//WEST
    	west = new JPanel();
    	latestRunsList = new LatestRuns();
    	latestRuns = new JList<Integer>(latestRunsList);
    	latestRunsHeader = new JLabel("Latest runs");
    	
    	west.add(latestRunsHeader);
    	west.add(latestRuns);
    	
    	add(west, BorderLayout.WEST);
    	west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));

    	
    	
    	//CENTER
    	buttonLeft = new JButton(playButton);
    	buttonRight = new JButton("Exit");
    	buttons = new JPanel();
    	center = new JPanel(new GridBagLayout());
    	
    	buttonLeft.addActionListener(this);
        if(this.gameStatus == "Game paused")
        	buttonLeft.setActionCommand("resume");
        
        if(this.gameStatus == "Game over")
        	buttonLeft.setActionCommand("restart");
    	
    	buttonRight.addActionListener(this);
    	buttonRight.setActionCommand("exit");
    
    	buttons.add(buttonLeft);
    	buttons.add(buttonRight);
    	
    	center.add(buttons);
    	
    	add(center, BorderLayout.CENTER);
    	
    	
    	//DEBUGING
 
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch (command) {
		case "resume":
			gb.hideMenu();
			break;
		case "exit":
			System.exit(0);
			break;
		case "restart":
			gb.restart();
			break;
		default:
			break;
		}
	}               
}
