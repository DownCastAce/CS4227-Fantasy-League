package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class TeamFactory {
	
	private static final String FILEPATH = "resources/teams/";
	
    public static Team load(String sport, String name, String rosterName) {
    	Team team = null;
    	if(sport.equals("soccer")){
    		ArrayList<SoccerPlayer> players =  new ArrayList<SoccerPlayer>();
	    	Roster roster = new Roster(rosterName);
	    	List<String> lines;
	    	
	    	//load file into List
	    	try{
				lines = FileUtils.readLines(new File(FILEPATH + name));
	    	}catch (IOException e) {
				e.printStackTrace();
				return null;
			}
	    	//Create user based on the first line of the list
	    	User owner = UserFactory.load(lines.get(0));
	    	lines.remove(0);
	    	
	    	//create a list of player by iterating over ids in the list.
	    	for(String line: lines)
	    		players.add(roster.getPlayer(line));
	    	team = new SoccerTeam(name, owner, players);
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
