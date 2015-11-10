package controllers;

import models.IUser;
import models.User;
import views.MainMenuView;

public class GoBackToMainMenuCommand implements Command {

	private User user;
	
	public GoBackToMainMenuCommand(User user){
		this.user = user;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		MainMenuView main = new MainMenuView();
		MainMenuController control = new MainMenuController(user,main);
	}

}
