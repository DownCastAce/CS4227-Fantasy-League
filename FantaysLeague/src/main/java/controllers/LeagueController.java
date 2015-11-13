package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.League;
import models.User;
import views.SoccerLeagueView;

public class LeagueController {
	
	private League league;
	private SoccerLeagueView view;
	private User user;
	
	public LeagueController(League league, SoccerLeagueView view, User user){
		this.league = league;
		this.view = view;
		this.user = user;
		
		view.addReturnListener(new ListenForReturn());
		
		view.setVisible(true);
	}

	class ListenForReturn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			GoBackToMainMenuCommand command = new GoBackToMainMenuCommand(user);
			command.execute();
			view.dispose();
		}
	}

}
