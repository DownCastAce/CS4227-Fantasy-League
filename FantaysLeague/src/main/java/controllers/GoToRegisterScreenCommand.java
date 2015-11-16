package controllers;

import views.RegisterView;

public class GoToRegisterScreenCommand implements Command {

	public void execute() {
		// TODO Auto-generated method stub
		RegisterView v = new RegisterView();
		RegisterController rc = new RegisterController(v);
	}

}
