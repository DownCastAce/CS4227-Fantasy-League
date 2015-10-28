package models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class LeagueFactory {

	private static final String FILEPATH = "resources/leagues";
	
	public static League newLeague(User owner, String name, String sport, String roaster){
		League result = new League(owner, name, sport, roaster);
		result.save();
		return result;
	}
	
	public static League load(String leagueName, String rosterName, String sport){
		List<String> lines;
		Date leagueDate;
		try{
			lines = FileUtils.readLines(new File(FILEPATH + leagueName));
			leagueDate = new Date((new File(FILEPATH + leagueName)).lastModified());
		}catch(IOException e){
			return null;
		}
		
		User owner = UserFactory.load(lines.remove(0));
		
		Map<Team, Integer> teamPoints = new HashMap<>();
		for(String line : lines) {
			String[] teamData = line.split(",");
			teamPoints.put(TeamFactory.load("soccer", teamData[0], rosterName), Integer.parseInt(teamData[1]));
		}
		return new League(owner, leagueName, sport, leagueDate, teamPoints);
	}
}
