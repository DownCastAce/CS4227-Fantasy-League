package views;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import models.League;
import models.SoccerTeam;
import java.awt.Color;


public class SoccerLeagueView extends JFrame {

	private JPanel contentPane;
	private JPanel viewPanel;
	private JTable leagueTableView;
	private JScrollPane scroll;

	 private JButton btnReturnToMainMenu;

	    public SoccerLeagueView() {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        viewPanel = new JPanel();
	        viewPanel.setBackground(Color.WHITE);
	        this.setSize(800, 600);
	        setLocationRelativeTo(null);

	       scroll = new JScrollPane();
	       viewPanel.add(scroll);
	        
	        btnReturnToMainMenu = new JButton("Return to main menu");
	        btnReturnToMainMenu.setBounds(400, 300, 100, 50);
	        viewPanel.add(btnReturnToMainMenu);
	        getContentPane().add(viewPanel);
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
	    		Arrays.sort(leagueTable, new Comparator<String []>() {
	    			@Override
	    			public int compare(final String[] entry1, final String[] entry2) {
	    				return (entry2[3].compareTo(entry1[3]));
	    			}
				});
	    		
	    	  	leagueTableView = new JTable(leagueTable, leagueheaders);
	    	  	scroll.setViewportView(leagueTableView);
	    	  
	    }

}
