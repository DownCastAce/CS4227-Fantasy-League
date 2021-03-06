package models;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;

import main.MainDriver;
import stats.CareTaker;
import stats.StatMomento;

public class League {

    protected Instant lastUpdate;
    protected User owner;
    protected Map<SoccerTeam, Integer> leagueTeams = new HashMap<>();
    protected String leagueName;
    protected String sport;
    protected boolean updated;
	protected File SAVEFILE;

    public League(User owner, String leagueName, SoccerTeam ownerTeam, String sport){
    	//creating empty league
    	this.owner = owner;
    	this.lastUpdate = Instant.now();
    	this.leagueName = leagueName;
    	this.sport = sport;
    	//Add the owners team.
    	leagueTeams.put(ownerTeam, 0);

    }
    //
    public League(User owner, String leagueName, String sport, long lastUpdate, Map<SoccerTeam, Integer> leagueTeams){
    	//Loading league
    	this.sport = sport;
    	this.owner = owner;

    	this.lastUpdate = Instant.ofEpochSecond(lastUpdate);
    	this.leagueName = leagueName;
    	//Add all the league team.
    	this.leagueTeams = leagueTeams;
    	updated = true;
    	update();
    }
    
    public String getLeagueName(){
    	return leagueName;
    }
    
    public Map<SoccerTeam, Integer> getLeagueTeams(){
    	
    	return leagueTeams;
    }
    public boolean isUpdated() {
		return updated;
	}
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
    public void addTeam(SoccerTeam team){
    	leagueTeams.put(team, 0);
    }
    
    public void removeTeam(Team team){
    	leagueTeams.remove(team);
    }
    
    public StatMomento saveToMomento(){
    	if (isUpdated() == true) {
			Map<String, Integer> state = new HashMap<String, Integer>();
			for (Map.Entry<SoccerTeam, Integer> entry : leagueTeams.entrySet()) {
				state.put(entry.getKey().getTeamName(), entry.getValue());
			}
			
			return new StatMomento(state);
    	}
    	
    	return null;
    }
    
    public void update(){
    	if(MainDriver.lastUpdate.isAfter(this.lastUpdate)){
	    	for(Map.Entry<SoccerTeam, Integer> entry : leagueTeams.entrySet()){
	    		leagueTeams.put(entry.getKey(), entry.getValue() + entry.getKey().getTotalPoints());
	    	}
	    	updated = true;
	    	this.lastUpdate = Instant.now();
    	}
    }

    public synchronized boolean save(){
    	/* 
    	 * Save File Format
    	 * ---------
    	 * OwnerName
    	 * win,loss,draw
    	 * team1
    	 * team2
    	 */
    	SAVEFILE = new File("resources/leagues/" + leagueName);
    	ArrayList<String> output = new ArrayList<String>();

    	output.add(owner.getUserName());
    	for(Map.Entry<SoccerTeam, Integer> entry: leagueTeams.entrySet()){
    		output.add(entry.getKey().getTeamName() + "," + entry.getValue());
    	}
    	try {
    		AsyncWriteUtil writer = new AsyncWriteUtil(output, SAVEFILE);
    		writer.run();
    	} catch(IOException e) {
    		return false;
    	}
    	return true;
    }
    
    public User getOwner() {
		return owner;
	}
    
    public String getSport() {
		return sport;
	}
    
	public Instant getLastUpdate() {
		return lastUpdate;
	}
}
