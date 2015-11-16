package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.MainDriver;
import models.SoccerTeam;
import models.TeamFactory;
import models.User;
import views.RegisterView;

public class RegisterController {
	
	private RegisterView view;
	private User user;
	private SoccerTeam team;
	
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
			ConfirmRegisterCommand c = new ConfirmRegisterCommand(view.getUsername(),view.getPassword(), view.getTeamName());
			c.execute();
			user = c.getUser();
			user.setTeamName(view.getTeamName());
		
			team = (SoccerTeam) TeamFactory.newTeam("soccer", user.getTeamName(), user, MainDriver.statListener);
			
			GoToTeamViewCommand command = new GoToTeamViewCommand(team, user);
			command.execute();
					
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
