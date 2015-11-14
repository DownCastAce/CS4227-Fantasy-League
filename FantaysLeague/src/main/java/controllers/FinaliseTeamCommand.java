package controllers;

import main.MainDriver;
import models.League;
import models.LeagueFactory;
import models.SoccerTeam;
import models.User;
import views.MainMenuView;

public class FinaliseTeamCommand implements Command {

	private User user;
	private SoccerTeam team;
	private League league;
	
	public FinaliseTeamCommand(User user, SoccerTeam team){
		this.user = user;
		this.team =  team;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		team.save();
		
		league = LeagueFactory.load("GlobalLeague", "soccer", MainDriver.statListener);
		System.out.println("League Name: " + league.getLeagueName());
		
		league.addTeam(team);
		league.save();
		
		MainMenuView view = new MainMenuView();
		MainMenuController controller = new MainMenuController(user,view);
	}

}
