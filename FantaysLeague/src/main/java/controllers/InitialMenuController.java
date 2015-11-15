package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.InitialMenuView;

public class InitialMenuController {
	
	private InitialMenuView view;
	
	public InitialMenuController(InitialMenuView v){
		view = v;
		view.setVisible(true);
		view.addRegisterListener(new ListenForRegister());
		view.addLoginListener(new ListenForLogin());
	}
	
	class ListenForRegister implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			RegisterCommand c = new RegisterCommand();
			c.execute();
			view.dispose();
		}
	}
	
	class ListenForLogin implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			LoginCommand c = new LoginCommand();
			c.execute();
			view.dispose();
		}
		
	}
}
