package controllers;

import models.SoccerTeam;
import models.User;
import views.MainMenuView;

public class FinaliseTeamCommand implements Command {

	private User user;
	private SoccerTeam team;
	
	public FinaliseTeamCommand(User user, SoccerTeam team){
		this.user = user;
		this.team =  team;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		team.save();
		MainMenuView view = new MainMenuView();
		MainMenuController controller = new MainMenuController(user,view);
	}

}
