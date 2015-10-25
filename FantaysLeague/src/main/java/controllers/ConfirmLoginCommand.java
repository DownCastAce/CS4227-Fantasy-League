package controllers;

import models.User;
import models.UserFactory;

public class ConfirmLoginCommand implements Command{

	private String username;
	private String password;
	private User user;
	
	public ConfirmLoginCommand(String u){
		username = u;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		UserFactory uf = new UserFactory();
		user = uf.load(username);
		
	}
	
	public User getUser(){
		return user;
	}
}
