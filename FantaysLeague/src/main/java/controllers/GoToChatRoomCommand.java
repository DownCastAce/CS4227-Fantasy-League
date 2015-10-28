package controllers;

import models.IUser;
import views.ChatRoomView;
import views.MainMenuView;

public class GoToChatRoomCommand implements Command {

	private IUser user;
	private MainMenuView view;
	
	public GoToChatRoomCommand(IUser u, MainMenuView v)
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
