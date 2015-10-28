package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class League {

    protected Date lastUpdate;
    protected User owner;
    protected Map<Team, Integer> leagueTeams;
    protected String leagueName;
    protected String sport;
    
    protected File SAVEFILE = new File("resources/leagues/" + leagueName);
    
    public League(User owner, String leagueName, String sport, String roster){
    	//creating empty league
    	this.owner = owner;
    	this.lastUpdate = new Date();
    	this.leagueName = leagueName;
    	//Add the owners team.
    	leagueTeams.put(TeamFactory.load(sport, owner.getTeamName(), roster), 0);
    }
    //
    public League(User owner, String leagueName, String sport, Date lastUpdate, Map<Team, Integer> leagueTeams){
    	//Loading league
    	this.sport = sport;
    	this.owner = owner;
    	this.lastUpdate = lastUpdate;
    	this.leagueName = leagueName;
    	this.lastUpdate = lastUpdate;
    	//Add all the league team.
    	this.leagueTeams = leagueTeams;
    }
    
    public void addTeam(Team team){
    	leagueTeams.put(team, 0);
    }
    
    public void removeTeam(Team team){
    	leagueTeams.remove(team);
    }
    
    public boolean update(String filename){
    	//Update file format yet to be set
    	lastUpdate = new Date();
    	return false;
    }
    public boolean save(){
    	/* 
    	 * Save File Format
    	 * ---------
    	 * OwnerName
    	 * win,loss,draw
    	 * team1
    	 * team2
    	 */
    	ArrayList<String> output = new ArrayList<String>();
    	for(Map.Entry<Team, Integer> entry: leagueTeams.entrySet()){
    		output.add(entry.getKey() + "," + entry.getValue());
    	}
    	
    	try {
    		FileUtils.write(SAVEFILE, owner.getUserName());
    		FileUtils.writeLines(SAVEFILE, output);
    	} catch(IOException e) {
    		return false;
    	}
    	return true;
    }    
}
