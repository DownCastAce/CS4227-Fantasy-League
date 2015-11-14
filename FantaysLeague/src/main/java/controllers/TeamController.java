package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;

import main.MainDriver;
import models.Player;
import models.Roster;
import models.SoccerPlayer;
import models.SoccerTeam;
import models.Team;
import models.User;
import views.SoccerTeamView;

public class TeamController {
	private SoccerTeamView view;
	private SoccerTeam team;
	private Roster roster;
	private User user;

	public TeamController(User u, SoccerTeamView v, SoccerTeam t) {
		view = v;
		team = t;
		user = u;
		roster = Roster.getInstance(MainDriver.statListener);
	
		view.addPlayerAdditionListener(new AddPlayerListener());
		view.addFilterListener(new FilterListener());
		view.addRemovePlayerListener(new RemovePlayerListener());
		view.addFinaliseButtonListener(new FinaliseTeamListener());
		view.addReturnListener(new ReturnListener());
		view.setVisible(true);

		if (team.getPlayerList().size() != 0) {
			view.updateGoalKeeperTable(team.getPlayerList());
			view.updateDefenderTable(team.getPlayerList());
			view.updateMidfielderTable(team.getPlayerList());
			view.updateForwardsTable(team.getPlayerList());
			// view.setBudgetTextField(team.getBudget());
		}
	}

	class AddPlayerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			ArrayList<SoccerPlayer> selectedTypePlayers;

			if ((view.getComboBoxSelection()).equals("Goalkeepers")) {
				selectedTypePlayers = roster.getPlayersAtPosition("G");
				if (team.tryAddPlayer(selectedTypePlayers.get(view.getAvailablePlayersSelection())) == true) {			
					view.updateGoalKeeperTable(team.getPlayerList());
					team.decrementBudget(selectedTypePlayers.get(view.getAvailablePlayersSelection()).getValue());
					view.setBudgetTextField(team.getBudget());
				}
			}

			else if ((view.getComboBoxSelection()).equals("Defenders")) {
				selectedTypePlayers = roster.getPlayersAtPosition("D");
				if (team.tryAddPlayer(selectedTypePlayers.get(view.getAvailablePlayersSelection())) == true) {
					view.updateDefenderTable(team.getPlayerList());
					team.decrementBudget(selectedTypePlayers.get(view.getAvailablePlayersSelection()).getValue());
					view.setBudgetTextField(team.getBudget());
				}
			}

			else if ((view.getComboBoxSelection()).equals("Midfielders")) {
				selectedTypePlayers = roster.getPlayersAtPosition("M");
				if (team.tryAddPlayer(selectedTypePlayers.get(view.getAvailablePlayersSelection())) == true) {
					view.updateMidfielderTable(team.getPlayerList());
					team.decrementBudget(selectedTypePlayers.get(view.getAvailablePlayersSelection()).getValue());
					view.setBudgetTextField(team.getBudget());
				}
			}

			else {
				selectedTypePlayers = roster.getPlayersAtPosition("F");
				if (team.tryAddPlayer(selectedTypePlayers.get(view.getAvailablePlayersSelection())) == true) {
					view.updateForwardsTable(team.getPlayerList());
					team.decrementBudget(selectedTypePlayers.get(view.getAvailablePlayersSelection()).getValue());
					view.setBudgetTextField(team.getBudget());
				}
			}
		}
	}

	class FilterListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<SoccerPlayer> selectedTypePlayers;
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
	
	class FinaliseTeamListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			FinaliseTeamCommand com = new FinaliseTeamCommand(user, team);
			com.execute();
			
			view.dispose();
		}
		
	}
	
	class ReturnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			GoBackToMainMenuCommand com = new GoBackToMainMenuCommand(user);
			com.execute();
			view.dispose();
		}
		
	}
}
