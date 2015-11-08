package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.InitialMenuView;
import views.RegisterView;

public class RegisterController {
	
	private RegisterView view;
	
	public RegisterController(RegisterView v)
	{
		view = v;
		view.setVisible(true);
		
		view.addConfirmListener(new ListenForConfirm());
		view.addCancelListener(new ListenForCancel());
	}
	
	class ListenForConfirm implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			ConfirmRegisterCommand c = new ConfirmRegisterCommand(view.getUsername(),view.getPassword());
			c.execute();
			c.getUser().setTeamName(view.getTeamName());
			
			view.dispose();
		}
	}
	
	class ListenForCancel implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			GoBackToInitialMenuCommand c = new GoBackToInitialMenuCommand();
			view.dispose();
			c.execute();
		}
	}
}
