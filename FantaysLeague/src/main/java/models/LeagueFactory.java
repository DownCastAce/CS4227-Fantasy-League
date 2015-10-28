package models;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class LeagueFactory {

	private static final String FILEPATH = "resources/leagues";
	
	public static League newLeague(String name, User owner, String sport, int win, int loss, int draw){
		League result = new League(owner, name, sport, win, loss, draw);
		result.save();
		return result;
	}
	
	public static League load(String name, String rosterName, String sport){
		List<String> lines;
		Roster roster = new Roster(rosterName);
		try{
		lines = FileUtils.readLines(new File(FILEPATH + "name"));
		}catch(IOException e){
			return null;
		}
		
		User owner = UserFactory.load(lines.get(0));
		lines.remove(0);

		String resultPoints[] = lines.get(0).split(",");
		int win = Integer.parseInt(resultPoints[0]);
		int loss = Integer.parseInt(resultPoints[1]);
		int draw = Integer.parseInt(resultPoints[2]);
		ArrayList<Team> teams = new ArrayList<Team>();
		for(String line : lines)
			teams.add(TeamFactory.load("soccer", rosterName));
		return new League(owner, name, sport, win, loss, draw);
	}
}
