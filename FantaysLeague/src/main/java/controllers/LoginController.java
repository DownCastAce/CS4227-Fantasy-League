package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.IUser;
import views.InitialMenuView;
import views.LoginView;
import views.MainMenuView;

public class LoginController {
	
	private LoginView view;
	private IUser user;
	
	public LoginController(LoginView v){
		view = v;		
		view.setVisible(true);
		
		System.out.println(view.getUsername());
		view.addConfirmListener(new ListenForConfirm());
		view.addCancelListener(new ListenForCancel());
	}
	
	class ListenForConfirm implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ConfirmLoginCommand c = new ConfirmLoginCommand(view.getUsername());
			c.execute();
			user = c.getUser();
			
			MainMenuView mainview = new MainMenuView();
			MainMenuController mainmenu = new MainMenuController(user,mainview);
			
			view.dispose();
		}
		
	}
	
	class ListenForCancel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			GoBackToInitialMenuCommand c = new GoBackToInitialMenuCommand();
			c.execute();
			view.dispose();
		}
		
	}

}
