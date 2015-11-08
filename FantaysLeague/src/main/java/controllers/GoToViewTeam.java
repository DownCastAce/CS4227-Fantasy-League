package controllers;

import models.Roster;
import models.SoccerTeam;
import views.TeamView;

public class GoToViewTeam implements Command {

	private SoccerTeam team;
	private Roster roster;
	
	public GoToViewTeam(SoccerTeam t, Roster rost){
		team = t;
		roster = rost;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		TeamView view = new TeamView();
		TeamController control = new TeamController(view,team,roster);
	}

}
