package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Roster;
import models.SoccerTeam;
import models.TeamFactory;
import models.User;
import views.SelectSportView;

public class SelectSportController {
	
	private SelectSportView view;
	private String choice;
	private User user;
	
	public SelectSportController(SelectSportView v, User u){
		view = v;
		user = u;
		
		view.setVisible(true);
		
		view.addNextListener(new NextListener());
	}
	
	class NextListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			choice = view.getComboBoxSelection();
			
			if(choice.equals("Soccer")){
				Roster rost = new Roster("resources/Rosters/SoccerRoster");
				TeamFactory tf = new TeamFactory();
				
				view.dispose();
				
				GoToViewTeam c = new GoToViewTeam((SoccerTeam)tf.newTeam("soccer", view.getTeamName(), user), rost);
				c.execute();
			}
		}
		
	}
	

}
