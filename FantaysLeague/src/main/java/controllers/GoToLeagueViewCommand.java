package controllers;

import models.League;
import models.User;
import views.SoccerLeagueView;

public class GoToLeagueViewCommand implements Command{
	
	private League league;
	private User user;
	
	public GoToLeagueViewCommand(League league, User user){
		this.league = league;
		this.user = user;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		SoccerLeagueView view = new SoccerLeagueView(league);
		LeagueController control = new LeagueController(league, view,user);
	}

}
