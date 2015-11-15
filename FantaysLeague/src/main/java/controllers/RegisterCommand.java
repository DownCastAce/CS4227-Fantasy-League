package controllers;

import views.RegisterView;

public class RegisterCommand implements Command {

	public void execute() {
		// TODO Auto-generated method stub
		RegisterView v = new RegisterView();
		RegisterController rc = new RegisterController(v);
	}

}
