package models;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import stats.Observer;
import stats.Stat;

public class Roster extends Observer implements IRoster{

    private ArrayList<Player> allPlayers = new ArrayList<Player>();
    private Map<Integer, ArrayList<Stat>> stats = new HashMap<Integer, ArrayList<Stat>>();

    public Roster(String fileName) {
        try {
            List<String> players = FileUtils.readLines(new File(fileName));
            for (String input : players) {
                String[] player = input.split(",");
                allPlayers.add(new Player(player[0], player[1], player[2], Double.parseDouble(player[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not read file : " + fileName);
        }
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }
    
    public ArrayList<Player> getPlayersAtPosition(String position){
    	ArrayList<Player> result = new ArrayList<Player>();
    	for(Player player: allPlayers){
    		if(player.getPosition().equals(position))
    			result.add(player);
    	}
    	return result;
    }
    
    public Player getPlayer(String id){
    	for(Player p: allPlayers){
    		if(p.getID().equals(id))
    			return p;
    	}
    	return null;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}