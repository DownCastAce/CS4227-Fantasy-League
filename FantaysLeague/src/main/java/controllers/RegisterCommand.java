package controllers;

import views.InitialMenuView;
import views.RegisterView;

public class RegisterCommand implements Command {
	
	private InitialMenuView view;
	
	public RegisterCommand(InitialMenuView v){
		view = v;
	}
	public void execute() {
		// TODO Auto-generated method stub
		RegisterView v = new RegisterView();
		RegisterController rc = new RegisterController(v,view);
	}

}
