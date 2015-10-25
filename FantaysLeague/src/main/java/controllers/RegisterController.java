package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.InitialMenuView;
import views.RegisterView;

public class RegisterController {
	
	private RegisterView view;
	private InitialMenuView im;
	
	public RegisterController(RegisterView v, InitialMenuView i)
	{
		view = v;
		im = i;
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
			view.dispose();
			im.setVisible(true);
		}
	}
	
	class ListenForCancel implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			CancelCommand c = new CancelCommand();
			c.execute();
			view.dispose();
		}
	}
}
