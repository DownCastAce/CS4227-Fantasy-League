package controllers;

import views.LoginView;

public class GoToLoginScreenCommand implements Command{
		
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		LoginView v = new LoginView();
		LoginController lc = new LoginController(v);
	}
	
}
