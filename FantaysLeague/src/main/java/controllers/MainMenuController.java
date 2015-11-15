package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.MainDriver;
import models.League;
import models.LeagueFactory;
import models.SoccerTeam;
import models.TeamFactory;
import models.User;
import views.MainMenuView;

public class MainMenuController {

	private User user;
	private MainMenuView view;
	
	public MainMenuController(User u, MainMenuView v){
		user = u;
		view = v;
		
		view.setVisible(true);
		
		view.addChatRoomListener(new ChatRoomListener());
		view.addViewLeagueListener(new ViewLeagueListener());
		view.addViewTeamListener(new ViewTeamListener());
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
	
	class ViewTeamListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			SoccerTeam team = (SoccerTeam)TeamFactory.load("soccer", user.getTeamName(), MainDriver.statListener);
						
			GoToTeamView com = new GoToTeamView(team,user);
			com.execute();
		}
		
	}
	
	class ViewLeagueListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0){
			League league = LeagueFactory.load("GlobalLeague", "soccer", MainDriver.statListener);
			
			GoToLeagueViewCommand con = new GoToLeagueViewCommand(league,user);
			con.execute();
			
			view.dispose();
		}
	}
}
