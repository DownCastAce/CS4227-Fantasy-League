package userInterfaces;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sports.AbstractLeague;
import sports.AbstractTeam;

public class LeagueView extends JFrame {

    /**
     * Create the frame.
     */
    private JPanel viewPanel;
    private JButton btnReturnToMainMenu;

    public LeagueView(AbstractLeague currentLeague) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewPanel = new JPanel();

        this.setSize(800, 600);
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

    private String[][] getLeagueTable(AbstractLeague currentLeague) {
        ArrayList<AbstractTeam> leagueTeams = currentLeague.getLeagueTeams();
        String[][] leagueTable = new String[leagueTeams.size()][4];
        for(int i =0; i < leagueTeams.size(); i++) {
            leagueTable[i][0] = ""+(i+1);
            leagueTable[i][1] = leagueTeams.get(i).getTeamName();
            leagueTable[i][2] = leagueTeams.get(i).getOwner().getUserName();
            leagueTable[i][3] = currentLeague.getTeamPoints(leagueTeams.get(i)) + "";
        }
        
        return leagueTable;

    }
    
    public void addReturnListener(ActionListener listenForReturn)
    {
        btnReturnToMainMenu.addActionListener(listenForReturn);
    }
}