package controllers;

import views.InitialMenuView;
import views.LoginView;

public class LoginCommand implements Command{
		
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		LoginView v = new LoginView();
		LoginController lc = new LoginController(v);
	}
	
}
