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

	 private JButton btnReturnToMainMenu;

	    public SoccerLeagueView(League currentLeague) {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        viewPanel = new JPanel();
	        this.setSize(800, 600);
	        setLocationRelativeTo(null);
	        this.setTitle(currentLeague.getLeagueName());

	        String[] leagueheaders = { "Position", "Team Name", "Manager", "Total Points" };

	        JTable information = new JTable(getLeagueTable(currentLeague),leagueheaders);

	        JScrollPane scroll = new JScrollPane(information);
	        viewPanel.add(scroll);
	        
	        btnReturnToMainMenu = new JButton("Return to main menu");
	        btnReturnToMainMenu.setBounds(400, 300, 100, 50);
	        viewPanel.add(btnReturnToMainMenu);
	        this.add(viewPanel);
	    }

	    private String[][] getLeagueTable(League currentLeague) {
	    	CareTaker leagueHistory = currentLeague.getCareTaker();
	        StatMomento statsMomento = leagueHistory.get(leagueHistory.size()-1);
	        Map<String, Integer> stats = statsMomento.getState();
	        String[][] leagueTable = new String[stats.size()][4];
	        int i = 1;

	        
	        for(int i = 0;i < c
	        {
	        	leagueTable[i-1][0] = ""+ (i);
	            leagueTable[i-1][1] = t.getTeamName();
	            leagueTable[i-1][2] = t.getOwner().getUserName();
	            leagueTable[i-1][3] = Integer.toString(t.getTotalPoints());
	            i++;
	        }
	        
	        return leagueTable;

	    }
	    
	    public void addReturnListener(ActionListener listenForReturn)
	    {
	        btnReturnToMainMenu.addActionListener(listenForReturn);
	    }

}
