package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import models.League;
import models.User;
import stats.CareTaker;
import views.SoccerLeagueView;

public class GoToLeagueViewCommand implements Command{
	
	private League league;
	private User user;
	private CareTaker caretaker;
	
	public GoToLeagueViewCommand(League league, User user){
		this.league = league;
		this.user = user;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		SoccerLeagueView view = new SoccerLeagueView();
		CareTaker caretaker = loadCareTaker();
		LeagueController control = new LeagueController(league,view,user,caretaker);
	}
	
	private static CareTaker loadCareTaker() {

		CareTaker caretaker = null;
		try {
			FileInputStream fileIn = new FileInputStream("resources/stats/caretaker.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			caretaker = (CareTaker) in.readObject();
			
			in.close();
			fileIn.close();
			
			return caretaker;
			
		} catch (IOException i) 
		{
			return caretaker = new CareTaker();
			
		}
		catch (ClassNotFoundException c) 
		{
			c.printStackTrace();
		}
		
		return null;
	}

}
