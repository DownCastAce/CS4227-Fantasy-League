package models;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import main.MainDriver;
import stats.CareTaker;
import stats.StatMomento;
import stats.Subject;

public class League {

    protected Instant lastUpdate;
    protected User owner;
    protected Map<SoccerTeam, Integer> leagueTeams = new HashMap<>();
    protected CareTaker caretaker = new CareTaker();
    protected String leagueName;
    protected String sport;
    
    protected File SAVEFILE;

    public League(User owner, String leagueName, SoccerTeam ownerTeam, String sport){
    	//creating empty league
    	this.owner = owner;
    	this.lastUpdate = Instant.now();
    	this.leagueName = leagueName;
    	this.sport = sport;
    	//Add the owners team.
    	leagueTeams.put(ownerTeam, 0);
    	saveState();

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
    	update();
    	saveState();
    }
    
    private void saveState(){
    	Map<String, Integer> state = new HashMap<String, Integer>();
    	for(Map.Entry<SoccerTeam, Integer> entry : leagueTeams.entrySet()){
    		state.put(entry.getKey().getTeamName(), entry.getValue());
    	}
    	caretaker.add(new StatMomento(state));
    }
    
    public CareTaker getCareTaker(){
    	return caretaker;
    }

    public String getLeagueName(){
    	return leagueName;
    }
    
    public Map<SoccerTeam, Integer> getLeagueTeams(){
    	
    	return leagueTeams;
    }
    
    public void addTeam(SoccerTeam team){
    	leagueTeams.put(team, 0);
    }
    
    public void removeTeam(Team team){
    	leagueTeams.remove(team);
    }
    
    public void update(){
    	if(MainDriver.lastUpdate.isAfter(this.lastUpdate)){
	    	for(Map.Entry<SoccerTeam, Integer> entry : leagueTeams.entrySet()){
	    		leagueTeams.put(entry.getKey(), entry.getValue() + entry.getKey().getTotalPoints());
	    	}
	    	this.lastUpdate = Instant.now();
	    	saveState();
    	}
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
    	SAVEFILE = new File("resources/leagues/" + leagueName);
    	ArrayList<String> output = new ArrayList<String>();
    	for(Map.Entry<SoccerTeam, Integer> entry: leagueTeams.entrySet()){
    		output.add(entry.getKey().getTeamName() + "," + entry.getValue());
    	}
    	System.out.println("PATH: " + SAVEFILE);
    	try {
    		FileUtils.write(SAVEFILE, owner.getUserName());
    		FileUtils.write(SAVEFILE, "\n",true);
    		FileUtils.writeLines(SAVEFILE, output,true);
    	} catch(IOException e) {
    		return false;
    	}
    	return true;
    }    
}
