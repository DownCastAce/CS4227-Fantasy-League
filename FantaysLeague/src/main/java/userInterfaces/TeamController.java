package userInterfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import sports.AbstractTeam;
import sports.SoccerPlayer;
import sports.SoccerRoster;
import sports.SoccerTeam;
import sports.User;

public class TeamController {

    private PickTeamView p_view;
    private TeamNameView t_view;
    private SoccerTeam team;
    private SoccerRoster roster;
    private MainMenuView mainView;

    public TeamController(TeamNameView view, SoccerTeam team, SoccerRoster rost) {
        this.t_view = view;
        this.team = team;
        this.roster = rost;
        //this.t_view.addSubmitListenerEdit(new submitTeamNameListener());

    }
    
    public TeamController(PickTeamView view, AbstractTeam team, SoccerRoster rost, MainMenuView mainView) {
        this.p_view = view;
        this.team = (SoccerTeam) team;
        this.roster = rost;
        this.mainView = mainView;
        this.mainView.setVisible(false);
        this.team.generateTeamPoints();
        this.p_view.setVisible(true);
        this.p_view.addReturnListener(new ListenForReturn());
        this.p_view.addFilterListener(new FilterListener());
        this.p_view.addPlayerAdditionListener(new PlayerAdditionListener());
        this.p_view.addRemovePlayerListener(new PlayerRemoveListener());
        this.p_view.finaliseButtonListener(new FinaliseTeam());

        if (team.getPlayerList().size() != 0) {
            view.updateGoalKeeperTable(team.getPlayerList());
            view.updateDefenderTable(team.getPlayerList());
            view.updateMidfielderTable(team.getPlayerList());
            view.updateForwardsTable(team.getPlayerList());
            view.setBudgetTextField(team.getBudget());
        }
    }

    private class ListenForReturn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            p_view.dispose();
            mainView.setVisible(true);
        }
    }
    
    class FilterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<SoccerPlayer> selectedTypePlayers;
            if ((p_view.getComboBoxSelection()).equals("Goalkeepers")) {
                selectedTypePlayers = roster.getListOfGks();
                p_view.updateAvailablePlayersPane(selectedTypePlayers);
            }

            else if ((p_view.getComboBoxSelection()).equals("Defenders")) {
                selectedTypePlayers = roster.getListOfDs();
                p_view.updateAvailablePlayersPane(selectedTypePlayers);
            }

            else if ((p_view.getComboBoxSelection()).equals("Midfielders")) {
                selectedTypePlayers = roster.getListOfMs();
                p_view.updateAvailablePlayersPane(selectedTypePlayers);
            }

            else {
                selectedTypePlayers = roster.getListOfFs();
                p_view.updateAvailablePlayersPane(selectedTypePlayers);
            }
        }
    }

    class PlayerAdditionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<SoccerPlayer> selectedTypePlayers;
            if ((p_view.getComboBoxSelection()).equals("Goalkeepers")) {
                selectedTypePlayers = roster.getListOfGks();
                if (team.tryAddPlayer(selectedTypePlayers.get(p_view.getAvailablePlayersSelection())) == true) 
                {
                	p_view.updateGoalKeeperTable(team.getPlayerList());
                    team.decrementBudget(selectedTypePlayers.get(p_view.getAvailablePlayersSelection()).getValue());
                    p_view.setBudgetTextField(team.getBudget());
                }
            }

            else if ((p_view.getComboBoxSelection()).equals("Defenders")) 
            {
                selectedTypePlayers = roster.getListOfDs();
                if (team.tryAddPlayer(selectedTypePlayers.get(p_view.getAvailablePlayersSelection())) == true) 
                {
                	p_view.updateDefenderTable(team.getPlayerList());
                    team.decrementBudget(selectedTypePlayers.get(
                    p_view.getAvailablePlayersSelection()).getValue());
                    p_view.setBudgetTextField(team.getBudget());
                }
            }

            else if ((p_view.getComboBoxSelection()).equals("Midfielders")) 
            {
                selectedTypePlayers = roster.getListOfMs();
                if (team.tryAddPlayer(selectedTypePlayers.get(p_view.getAvailablePlayersSelection())) == true) 
                {
                	p_view.updateMidfielderTable(team.getPlayerList());
                    team.decrementBudget(selectedTypePlayers.get(
                            p_view.getAvailablePlayersSelection()).getValue());
                    p_view.setBudgetTextField(team.getBudget());
                }
            }

            else {
                selectedTypePlayers = roster.getListOfFs();
                if (team.tryAddPlayer(selectedTypePlayers.get(p_view.getAvailablePlayersSelection())) == true) {
                	p_view.updateForwardsTable(team.getPlayerList());
                    team.decrementBudget(selectedTypePlayers.get(
                            p_view.getAvailablePlayersSelection()).getValue());
                    p_view.setBudgetTextField(team.getBudget());
                }
            }
        }
    }

    class PlayerRemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (p_view.getGksSelection() != -1) {
                team.incrementBudget(p_view.gkSelectionString());
                p_view.setBudgetTextField(team.getBudget());
                team.removePlayer(p_view.gkSelectionString());
                p_view.updateGoalKeeperTable(team.getPlayerList());
            }

            else if (p_view.getDsSelection() != -1) {
                team.incrementBudget(p_view.dSelectionString());
                p_view.setBudgetTextField(team.getBudget());
                team.removePlayer(p_view.dSelectionString());
                p_view.updateDefenderTable(team.getPlayerList());
            }

            else if (p_view.getMsSelection() != -1) {
                team.incrementBudget(p_view.mSelectionString());
                p_view.setBudgetTextField(team.getBudget());
                team.removePlayer(p_view.mSelectionString());
                p_view.updateMidfielderTable(team.getPlayerList());
            }

            else if (p_view.getFsSelection() != -1) {
                team.incrementBudget(p_view.fSelectionString());
                p_view.setBudgetTextField(team.getBudget());
                team.removePlayer(p_view.fSelectionString());
                p_view.updateForwardsTable(team.getPlayerList());
            }
        }
    }

    class FinaliseTeam implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (team.save(team.getTeamName()) == true) {
                p_view.dispose();
                mainView.setVisible(true);
            }
            
        }
    }
    
    /*class submitTeamNameListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
    	{
    		if(StringUtils.isNotBlank(t_view.getTeamName()))
    		{
    			if(!new File("resources/teams/" + t_view.getTeamName()).exists())
    			{
    				SoccerRoster rost = new SoccerRoster("resources/players.txt");
    				PickTeamView view = new PickTeamView());
    				User user = new User();
    				user.setEmail("tomnolan96@hotmail.com");
    				user.setPhoneNumber("0876495996");
    				user.setPassword("password");
    				user.setUserName("Derpp");
    				try {
						user.saveNewAccountDetails();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				SoccerTeam newTeam = new SoccerTeam(t_view.getTeamName(),user);
    				new TeamController(view,newTeam,rost);
    				view.setVisible(true);
    				t_view.dispose();
    			}
    		}
    		
    		else
    			t_view.displayErrorMessage("Team Name Already Exsists!");
    	}  	
    }*/
}