package controllers;

import java.util.ArrayList;

import javax.swing.JTable;

import models.Player;
import models.Roster;
import models.Team;
import views.TeamView;

public class TeamController{
	private TeamView view;
	private Team team;
	private Roster roster;
	
	public TeamController(TeamView v, Team t, Roster r){
		view = v;
		team = t;
		roster = r;
		
		view.setVisible(true);
		
		if (team.getPlayerList().size() != 0) {
            view.updateGoalKeeperTable(team.getPlayerList());
            view.updateDefenderTable(team.getPlayerList());
            view.updateMidfielderTable(team.getPlayerList());
            view.updateForwardsTable(team.getPlayerList());
            //view.setBudgetTextField(team.getBudget());
        }
	}
}
