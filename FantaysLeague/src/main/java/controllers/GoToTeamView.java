package controllers;

import models.SoccerTeam;
import models.User;
import views.SoccerTeamView;

public class GoToTeamView implements Command {

	private SoccerTeam team;
	private User user;
	
	public GoToTeamView(SoccerTeam t, User u){
		team = t;
		user = u;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		SoccerTeamView view = new SoccerTeamView();
		TeamController control = new TeamController(user,view,team);
	}

}