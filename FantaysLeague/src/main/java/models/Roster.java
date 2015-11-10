package models;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import stats.Observer;
import stats.Stat;
import stats.Subject;

public class Roster extends Observer implements IRoster{
	
	private static volatile Roster instance; 
	private final String filename = "resources/Rosters/SoccerRoster";
    private ArrayList<SoccerPlayer> allPlayers = new ArrayList<SoccerPlayer>();
    private Map<Integer, Stat> currentStats = new HashMap<Integer, Stat>();

    private Roster(Subject statListener) {
    	subject = statListener;
        try {
            List<String> players = FileUtils.readLines(new File(filename));
            for (String input : players) {
                String[] player = input.split(",");
                allPlayers.add(new SoccerPlayer(player[0], player[2], player[1], Double.parseDouble(player[4])));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not read file : " + filename);
        }
    }

    public ArrayList<SoccerPlayer> getAllPlayers() {
        return allPlayers;
    }
    
    public ArrayList<SoccerPlayer> getPlayersAtPosition(String position){
    	ArrayList<SoccerPlayer> result = new ArrayList<SoccerPlayer>();
    	for(SoccerPlayer player: allPlayers){
    		if(player.getPosition().equals(position))
    			result.add(player);
    	}
    	return result;
    }
    
    public SoccerPlayer getPlayer(String id){
    	for(SoccerPlayer p: allPlayers){
    		if(p.getID().equals(id))
    		{
    			return p;
    		}
    	}
    	System.out.println("Player Doesn't Exsist.");
    	return null;
    }
    
    public static synchronized Roster getInstance(Subject subject){
    	if(instance == null){
    		instance = new Roster(subject);
    		subject.attach(instance);
    	}
    	return instance;
    }
    public static synchronized Roster getInstance() throws Exception{
    	if(instance == null){
    		throw new Exception("Subject must be passed on first creation ");
    	}
    	return instance;
    }


	@Override
	public synchronized void update() {
		currentStats = (Map<Integer, Stat>)subject.getState();
	}
	
	public Map<Integer, Stat> getStats(){
		return currentStats;
	}
}