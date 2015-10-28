package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Roster implements IRoster{

    private ArrayList<SoccerPlayer> allPlayers = new ArrayList<SoccerPlayer>();

    public Roster(String fileName) {
        try {
            List<String> players = FileUtils.readLines(new File(fileName));
            for (String input : players) {
                String[] player = input.split(",");
                allPlayers.add(new SoccerPlayer(player[0], player[1], player[2], Double.parseDouble(player[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not read file : " + fileName);
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
    			return p;
    	}
    	return null;
    }
}