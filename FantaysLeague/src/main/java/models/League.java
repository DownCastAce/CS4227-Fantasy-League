package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class League {

    protected Date lastUpdate;
    protected User owner;
    //How can I test this is working correctly
    protected Map<String, Integer> leagueTeams = new HashMap<>();
    protected String leagueName;
    protected String sport;
	protected File saveFile;
    
    public League(User owner, String leagueName, String sport){
    	//creating empty league
    	this.owner = owner;
    	this.lastUpdate = new Date();
    	this.leagueName = leagueName;
    	this.sport = sport;
    	//Add the owners team.
    	leagueTeams.put(owner.getTeamName(), 0);
    	this.saveFile = new File("resources/leagues/" + leagueName);
    }
    //
    public League(User owner, String leagueName, String sport, Date lastUpdate, Map<String, Integer> leagueTeams){
    	//Loading league
    	this.sport = sport;
    	this.owner = owner;
    	this.lastUpdate = lastUpdate;
    	this.leagueName = leagueName;
    	this.lastUpdate = lastUpdate;
    	//Add all the league team.
    	this.leagueTeams = leagueTeams;
    	this.saveFile = new File("resources/leagues/" + leagueName);
    }
    
    public void addTeam(Team team){
    	leagueTeams.put(team.getTeamName(), 0);
    }
    
    public void removeTeam(Team team){
    	leagueTeams.remove(team.getTeamName());
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
    	 * team1 , points
    	 * team2 , points
    	 */
    	ArrayList<String> output = new ArrayList<String>();
    	for(Map.Entry<String, Integer> entry: leagueTeams.entrySet()){
    		output.add(entry.getKey() + "," + entry.getValue());
    	}
    	
    	try {
    		FileUtils.write(saveFile, owner.getUserName() + "\n");
    		FileUtils.writeLines(saveFile, output, true);
    	} catch(IOException e) {
    		return false;
    	}
    	return true;
    }  
    
    public Date getLastUpdate() {
		return lastUpdate;
	}
    
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public Map<String, Integer> getLeagueTeams() {
		return leagueTeams;
	}
	
	public void setLeagueTeams(Map<String, Integer> leagueTeams) {
		this.leagueTeams = leagueTeams;
	}
	
	public String getLeagueName() {
		return leagueName;
	}
	
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	
	public String getSport() {
		return sport;
	}
	
	public void setSport(String sport) {
		this.sport = sport;
	}
}
