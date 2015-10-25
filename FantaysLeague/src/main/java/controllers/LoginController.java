package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.IUser;
import views.InitialMenuView;
import views.LoginView;

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
			
			if(user == null){
				JOptionPane.showMessageDialog(null, "Error", "Invalid Login, Try Again!", 0);
			}
			
			else{
				System.out.println("Logged in as: " + user.getUserName());
			}
		}
		
	}
	
	class ListenForCancel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CancelCommand c = new CancelCommand();
			c.execute();
			view.dispose();
		}
		
	}

}
