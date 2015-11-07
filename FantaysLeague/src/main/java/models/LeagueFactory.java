package models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class LeagueFactory {

	private static final String FILEPATH = "resources/leagues/";
	
	public static ILeague newLeague(User owner, String leagueName, String sport){
		ILeague result = new League(owner, leagueName, sport);
		result.save();
		return result;
	}
	
	public static ILeague load(String leagueName, String sport){
		List<String> lines;
		Date leagueDate;
		try{
			File leagueFile = new File(FILEPATH + leagueName);
			lines = FileUtils.readLines(leagueFile);
			leagueDate = new Date((leagueFile).lastModified());
		}catch(IOException e){
			return null;
		}
		
		User owner = UserFactory.load(lines.remove(0));
		
		Map<String, Integer> teamPoints = new HashMap<>();
		for(String line : lines) {
			String[] teamData = line.split(",");
			teamPoints.put(teamData[0], Integer.parseInt(teamData[1]));
		}
		return new League(owner, leagueName, sport, leagueDate, teamPoints);
	}
}
