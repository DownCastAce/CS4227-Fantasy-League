package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class TeamFactory {
	
	private static final String FILEPATH = "resources/teams/";
	
    public static Team load(String sport, String teamName, String rosterName) {
    	Team team = null;
    	if(sport.equals("soccer")){
    		ArrayList<SoccerPlayer> teamPlayers =  new ArrayList<SoccerPlayer>();
	    	Roster roster = new Roster(rosterName);
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
	    	
	    	//create a list of player by iterating over ids in the list.
	    	for(String playerId: playerIdsList)
	    		teamPlayers.add((SoccerPlayer)roster.getPlayer(playerId));
	    	team = new SoccerTeam(teamName, teamOwner, teamPlayers);
    	}
    	return team;
    }

    public static Team newTeam(String sport, String name, User user) {
    	Team team = null;
        if (sport.equalsIgnoreCase("soccer")) {
            team = new SoccerTeam(name, user);
            team.save();
            return team;
        }
        return team;
    }
}
