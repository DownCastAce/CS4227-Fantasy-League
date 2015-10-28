package controllers;

import models.IUser;
import views.MainMenuView;

public class GoBackToMainMenuCommand implements Command {

	private IUser user;
	
	public GoBackToMainMenuCommand(IUser user){
		this.user = user;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		MainMenuView main = new MainMenuView();
		MainMenuController control = new MainMenuController(user,main);
	}

}
