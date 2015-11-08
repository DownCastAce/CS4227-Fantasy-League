package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;

import models.Player;
import models.Roster;
import models.SoccerPlayer;
import models.SoccerTeam;
import models.Team;
import views.TeamView;

public class TeamController{
	private TeamView view;
	private SoccerTeam team;
	private Roster roster;
	
	public TeamController(TeamView v, SoccerTeam t, Roster r){
		view = v;
		team = t;
		roster = r;
		
		view.addPlayerAdditionListener(new AddPlayerListener());
		view.addFilterListener(new FilterListener());
		view.addRemovePlayerListener(new RemovePlayerListener());
		view.setVisible(true);
		
		if (team.getPlayerList().size() != 0) {
            view.updateGoalKeeperTable(team.getPlayerList());
            view.updateDefenderTable(team.getPlayerList());
            view.updateMidfielderTable(team.getPlayerList());
            view.updateForwardsTable(team.getPlayerList());
            //view.setBudgetTextField(team.getBudget());
        }
	}
	
	class AddPlayerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			ArrayList<Player> selectedTypePlayers;
			
			if ((view.getComboBoxSelection()).equals("Goalkeepers")) {
                selectedTypePlayers = roster.getPlayersAtPosition("G");
                if (team.tryAddPlayer(selectedTypePlayers.get(view.getAvailablePlayersSelection())) == true) 
                {
                	view.updateGoalKeeperTable(team.getPlayerList());
                    team.decrementBudget(selectedTypePlayers.get(view.getAvailablePlayersSelection()).getValue());
                    view.setBudgetTextField(team.getBudget());
                }
            }

            else if ((view.getComboBoxSelection()).equals("Defenders")) 
            {
                selectedTypePlayers = roster.getPlayersAtPosition("D");
                if (team.tryAddPlayer(selectedTypePlayers.get(view.getAvailablePlayersSelection())) == true) 
                {
                	view.updateDefenderTable(team.getPlayerList());
                    team.decrementBudget(selectedTypePlayers.get(view.getAvailablePlayersSelection()).getValue());
                    view.setBudgetTextField(team.getBudget());
                }
            }

            else if ((view.getComboBoxSelection()).equals("Midfielders")) 
            {
                selectedTypePlayers = roster.getPlayersAtPosition("M");
                if (team.tryAddPlayer(selectedTypePlayers.get(view.getAvailablePlayersSelection())) == true) 
                {
                	view.updateMidfielderTable(team.getPlayerList());
                    team.decrementBudget(selectedTypePlayers.get(view.getAvailablePlayersSelection()).getValue());
                    view.setBudgetTextField(team.getBudget());
                }
            }

            else {
                selectedTypePlayers = roster.getPlayersAtPosition("F");
                if (team.tryAddPlayer(selectedTypePlayers.get(view.getAvailablePlayersSelection())) == true) 
                {
                	view.updateForwardsTable(team.getPlayerList());
                    team.decrementBudget(selectedTypePlayers.get(view.getAvailablePlayersSelection()).getValue());
                    view.setBudgetTextField(team.getBudget());
                }
            }
        }
    }
	
	 class FilterListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            ArrayList<Player> selectedTypePlayers;
	            if ((view.getComboBoxSelection()).equals("Goalkeepers")) {
	                selectedTypePlayers = roster.getPlayersAtPosition("G");
	                view.updateAvailablePlayersPane(selectedTypePlayers);
	            }

	            else if ((view.getComboBoxSelection()).equals("Defenders")) {
	                selectedTypePlayers = roster.getPlayersAtPosition("D");
	                view.updateAvailablePlayersPane(selectedTypePlayers);
	            }

	            else if ((view.getComboBoxSelection()).equals("Midfielders")) {
	                selectedTypePlayers = roster.getPlayersAtPosition("M");
	                view.updateAvailablePlayersPane(selectedTypePlayers);
	            }

	            else {
	                selectedTypePlayers = roster.getPlayersAtPosition("F");
	                view.updateAvailablePlayersPane(selectedTypePlayers);
	            }
	        }
	    }
	 class RemovePlayerListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            if (view.getGksSelection() != -1) {
	                team.incrementBudget(view.gkSelectionString());
	                view.setBudgetTextField(team.getBudget());
	                team.removePlayer(view.gkSelectionString());
	                view.updateGoalKeeperTable(team.getPlayerList());
	            }

	            else if (view.getDsSelection() != -1) {
	                team.incrementBudget(view.dSelectionString());
	                view.setBudgetTextField(team.getBudget());
	                team.removePlayer(view.dSelectionString());
	                view.updateDefenderTable(team.getPlayerList());
	            }

	            else if (view.getMsSelection() != -1) {
	                team.incrementBudget(view.mSelectionString());
	                view.setBudgetTextField(team.getBudget());
	                team.removePlayer(view.mSelectionString());
	                view.updateMidfielderTable(team.getPlayerList());
	            }

	            else if (view.getFsSelection() != -1) {
	                team.incrementBudget(view.fSelectionString());
	                view.setBudgetTextField(team.getBudget());
	                team.removePlayer(view.fSelectionString());
	                view.updateForwardsTable(team.getPlayerList());
	            }
	        }
	    }
}
