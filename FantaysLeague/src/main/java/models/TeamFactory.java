package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

import stats.Subject;

public class TeamFactory {
	
	private static final String FILEPATH = "resources/teams/";
	
    public static Team load(String sport, String teamName, Subject listener) {
    	Team team = null;
    	if(sport.equals("soccer")){
    		ArrayList<SoccerPlayer> teamPlayers =  new ArrayList<SoccerPlayer>();
    		ArrayList<SoccerPlayer> rosterPlayers =  new ArrayList<SoccerPlayer>();

			Roster roster = Roster.getInstance(listener);
			rosterPlayers = roster.getAllPlayers();
			SoccerPlayer p;

	    	List<String> playerIdsList;
	    	
	    	//load file into List
	    	try{
				playerIdsList = FileUtils.readLines(new File(FILEPATH + teamName));
	    	}catch (IOException e) {
				e.printStackTrace();
				return null;
			}
	    	
	    	//Create user based on the first line of the list
	    	User teamOwner = UserFactory.load(playerIdsList.remove(0));
	    	double budget = Double.parseDouble(playerIdsList.remove(0));
	    	
	    	//create a list of player by iterating over ids in the list.
	    	for(String playerId: playerIdsList)	    	
	    		teamPlayers.add((SoccerPlayer)roster.getPlayer(playerId));
	    	
	    	team = new SoccerTeam(teamName, teamOwner, budget, teamPlayers, listener);
	    	listener.attach(team);
    	}

    	return team;
    }

    public static Team newTeam(String sport, String name, User user, Subject listener) {
    	Team team = null;
        if (sport.equalsIgnoreCase("soccer")) {
            team = new SoccerTeam(name, user, listener);
            team.save();
            listener.attach(team);
        }

        return team;
    }
}
