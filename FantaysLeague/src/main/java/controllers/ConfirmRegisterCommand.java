package controllers;

import models.UserFactory;
import views.InitialMenuView;
import views.RegisterView;

public class ConfirmRegisterCommand implements Command {

	private String name;
	private String password;
	
	public ConfirmRegisterCommand(String n, String p){
		name = n;
		password = p;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		UserFactory uf = new UserFactory();
		uf.newUser(name,password);
	}

}
