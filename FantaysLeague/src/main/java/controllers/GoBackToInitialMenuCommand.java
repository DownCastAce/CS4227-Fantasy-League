package controllers;

import views.InitialMenuView;

public class GoBackToInitialMenuCommand implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		InitialMenuView v = new InitialMenuView();
		InitialMenuController vc = new InitialMenuController(v);
	}

}
