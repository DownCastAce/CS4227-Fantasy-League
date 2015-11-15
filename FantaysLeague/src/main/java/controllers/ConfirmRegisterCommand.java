package controllers;

import models.User;
import models.UserFactory;

public class ConfirmRegisterCommand implements Command {

	private String name;
	private String password;
	private String teamName;
	private User user;
	
	public ConfirmRegisterCommand(String n, String p, String teamname){
		name = n;
		password = p;
		teamName = teamname;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		UserFactory uf = new UserFactory();
		user = uf.newUser(name,password,teamName);
	}
	
	public User getUser(){
		return user;
	}

}
