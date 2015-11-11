package controllers;

import models.IUser;
import models.User;
import views.ChatRoomView;
import views.MainMenuView;

public class GoToChatRoomCommand implements Command {

	private User user;
	private MainMenuView view;
	
	public GoToChatRoomCommand(User u, MainMenuView v)
	{
		user = u;
		view = v;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ChatRoomView chatview = new ChatRoomView();
		ChatRoomController chatcontrol = new ChatRoomController(user, chatview);
		chatcontrol.start();
	}

}
