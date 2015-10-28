package models;

import java.util.ArrayList;

public interface IRoster {

	public ArrayList getAllPlayers();
	
	public ArrayList getPlayersAtPosition(String position);
	
	public Player getPlayer(String id);
	
}
