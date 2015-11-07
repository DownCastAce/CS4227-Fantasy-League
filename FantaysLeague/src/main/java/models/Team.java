package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import stats.Observer;
import stats.Subject;
import stats.Stat;

import org.apache.commons.io.FileUtils;

public class Team extends Observer{
	private static final String FILEPATH = "resources/teams/";
    protected User owner;
    protected String teamName;
    protected double budget;
    private int amountOfPlayersAllowed;
    protected ArrayList<Player> selectPlayers;
    protected HashMap<String, Integer> positions = new HashMap<>();
    protected Subject subject;
    protected int totalPoints;

    public Team(String teamname, User owner, Subject subject) {
        teamName = teamname;
        this.owner = owner;

        //calling update here should get the current value for points from the subject
        update();
    }

    public int getAmountOfPlayersAllowed() {
        return amountOfPlayersAllowed;
    }

    public void setAmountOfPlayersAllowed(int amountOfPlayersAllowed) {
        this.amountOfPlayersAllowed = amountOfPlayersAllowed;
    }

    public ArrayList<Player> getPlayerList() {
        return selectPlayers;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void decrementBudget(double a) {
        budget = budget - a;
    }

    public User getOwner() {
        return this.owner;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
    public void incrementBudget(String a) {
        for (int i = 0; i < selectPlayers.size(); i++)
            if (selectPlayers.get(i).getID().equals(a))
                budget = budget + selectPlayers.get(i).getValue();
    }

    public void removePlayer(String p) {
        for (int i = 0; i < selectPlayers.size(); i++)
            if (selectPlayers.get(i).getID().equals(p))
                selectPlayers.remove(i);
    }

    public boolean checkBudgetAmount(double amount) {
        if (budget - amount >= 0)
            return true;
        return false;
    }
    
    
    public boolean save() {
    	/*Format: filename = teamname
    	 * 
    	 * 
    	 * ownerName
    	 * player1
    	 * player2...
    	 */
    	ArrayList<String> output = new ArrayList<String>();
    	File saveFile = new File(FILEPATH + this.teamName);
    	
    	output.add(teamName);
    	output.add(owner.getUserName());
    	for(Player player: selectPlayers){
    		output.add(player.getID());
    	}	
    	try{
    		FileUtils.writeLines(saveFile, output);
    	}
    	catch(IOException e){
    		return false;
    	}
    	return true;
    }

	@Override
	public synchronized void update() {
		Map<Integer, Stat> stats = (Map<Integer, Stat>)subject.getState();
		for(Map.Entry<Integer, Stat> stat : stats.entrySet()){
			for(Player player: selectPlayers)
				if(stat.getKey() == Integer.parseInt(player.getID())){
					totalPoints += stat.getValue().getScore();
				}
		}
		save();
	}
        
}
