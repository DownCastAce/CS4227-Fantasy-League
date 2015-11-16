package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import models.League;
import models.SoccerTeam;
import models.User;
import stats.CareTaker;
import stats.StatMomento;
import views.SoccerLeagueView;

public class LeagueController {

	private League league;
	private SoccerLeagueView view;
	private User user;
	private CareTaker caretaker;

	public LeagueController(League league, SoccerLeagueView view, User user, CareTaker caretaker) {
		this.league = league;
		this.view = view;
		this.user = user;
		this.caretaker = caretaker;
		view.addReturnListener(new ListenForReturn());

		view.setVisible(true);

		view.updateLeagueTable(league.getLeagueTeams());
	}

	private void saveState() {

		if(league.saveToMomento() != null)
		{
			caretaker.add(league.saveToMomento());
			saveCareTaker();
			league.setUpdated(false);
		}
	}

	private void saveCareTaker() {
		try {
			FileOutputStream fileOut = new FileOutputStream("resources/stats/caretaker.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(caretaker);
			out.close();
			fileOut.close();
			System.out.println("Saved New CareTaker");

		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	class ListenForReturn implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			saveState();

			GoBackToMainMenuCommand command = new GoBackToMainMenuCommand(user);
			saveState();
			command.execute();
			view.dispose();
		}
	}

}
