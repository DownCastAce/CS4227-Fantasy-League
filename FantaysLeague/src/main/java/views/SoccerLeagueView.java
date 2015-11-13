package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import models.League;
import models.SoccerTeam;
import models.Team;
import stats.CareTaker;
import stats.StatMomento;

public class SoccerLeagueView extends JFrame {

	private JPanel contentPane;
	private JPanel viewPanel;
	private JTable leagueTableView;
	private JScrollPane scroll;

	 private JButton btnReturnToMainMenu;

	    public SoccerLeagueView() {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        viewPanel = new JPanel();
	        this.setSize(800, 600);
	        setLocationRelativeTo(null);

	       scroll = new JScrollPane();
	       viewPanel.add(scroll);
	        
	        btnReturnToMainMenu = new JButton("Return to main menu");
	        btnReturnToMainMenu.setBounds(400, 300, 100, 50);
	        viewPanel.add(btnReturnToMainMenu);
	        this.add(viewPanel);
	    }

	    public void setTitle(League currentLeague){
	    	this.setTitle(currentLeague.getLeagueName());
	    }
	    
	    
	    public void addReturnListener(ActionListener listenForReturn)
	    {
	        btnReturnToMainMenu.addActionListener(listenForReturn);
	    }
	    
	    public void updateLeagueTable(Map<SoccerTeam,Integer> leagueTeams){
	    	String leagueTable[][] = new String[leagueTeams.size()][4];
	    	String[] leagueheaders = { "Position", "Team Name", "Manager", "Total Points" };
	    	int i = 1;

	    	for(Map.Entry<SoccerTeam, Integer> t : leagueTeams.entrySet()){
	    		leagueTable[i-1][0] = ""+ (i);
	            leagueTable[i-1][1] = t.getKey().getTeamName();
	            leagueTable[i-1][2] = t.getKey().getOwner().getUserName();
	            leagueTable[i-1][3] = Integer.toString(t.getValue());
	            i++;
	    	}
	    	  leagueTableView = new JTable(leagueTable, leagueheaders);
	    	  scroll.setViewportView(leagueTableView);
	    	  
	    }

}
