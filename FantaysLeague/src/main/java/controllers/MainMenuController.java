package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.IUser;
import views.MainMenuView;

public class MainMenuController {

	private IUser user;
	private MainMenuView view;
	
	public MainMenuController(IUser u, MainMenuView v){
		user = u;
		view = v;
		
		view.setVisible(true);
		
		view.addChatRoomListener(new ChatRoomListener());
	}
	
	class ChatRoomListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			GoToChatRoomCommand c = new GoToChatRoomCommand(user, view);
			view.dispose();
			c.execute();
		}
		
	}
}
