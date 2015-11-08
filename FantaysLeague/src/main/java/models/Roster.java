package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Roster implements IRoster{

    private ArrayList<Player> allPlayers = new ArrayList<Player>();

    public Roster(String fileName) {
        try {
            List<String> players = FileUtils.readLines(new File(fileName));
            for (String input : players) {
                String[] player = input.split(",");
                allPlayers.add(new Player(player[0], player[2], player[1], Double.parseDouble(player[4])));
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
}