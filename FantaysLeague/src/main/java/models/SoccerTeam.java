package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import stats.Stat;
import stats.Subject;

public class SoccerTeam extends Team {

	private static final String FILEPATH = "resources/teams/";
    protected User owner;
    protected String teamName;
    protected double budget;
    private int amountOfPlayersAllowed;
    protected ArrayList<SoccerPlayer> selectPlayers;
    protected HashMap<String, Integer> positions = new HashMap<>();
    protected Subject subject;
    protected int totalPoints;

	public SoccerTeam(String teamname, User owner, Subject listener) {
		// Create empty team.
		teamName = teamname;
		this.owner = owner;
		subject = listener;
		selectPlayers = new ArrayList<SoccerPlayer>();

		setAmountOfPlayersAllowed(15);
		budget = 100.0;
		positions.put("G", 2);
		positions.put("D", 5);
		positions.put("M", 5);
		positions.put("F", 3);

		// update();
	}

	public SoccerTeam(String teamname, User owner, ArrayList<SoccerPlayer> players, Subject listener) {
		// Load team from file.
		teamName = teamname;
		this.owner = owner;
		subject = listener;
		selectPlayers = new ArrayList<SoccerPlayer>();
		
		setAmountOfPlayersAllowed(15);
		budget = 100.0;
		positions.put("G", 2);
		positions.put("D", 5);
		positions.put("M", 5);
		positions.put("F", 3);

		selectPlayers.addAll(players);

		// update();

	}

	public ArrayList<SoccerPlayer> getPlayerList() {
		return selectPlayers;
	}

	public int getTotalPoints(){
		return totalPoints;
	}
	
	public int getAmountOfPlayersAllowed() {
		return amountOfPlayersAllowed;
	}

	public void setAmountOfPlayersAllowed(int amountOfPlayersAllowed) {
		this.amountOfPlayersAllowed = amountOfPlayersAllowed;
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

	public boolean tryAddPlayer(SoccerPlayer nextPlayer) {
		if (isValidTeamAddition(nextPlayer, getNumberOfPlayersAtPosition(nextPlayer.getPosition()))) {
			selectPlayers.add(nextPlayer);
			return true;
		}
		return false;
	}

	public int getNumberOfPlayersAtPosition(String position) {
		int count = 0;
		for (int c = 0; c < selectPlayers.size(); c++)
			if ((selectPlayers.get(c).getPosition()).equals(position))
				count++;
		return count;
	}

	public boolean isValidTeamAddition(SoccerPlayer player, int count) {
		boolean nextStage = true;

		for (int i = 0; i < selectPlayers.size(); i++)
			if (player.getID().equals(selectPlayers.get(i).getID()))
				nextStage = false;

		if (checkBudgetAmount(player.getValue()) == false)
			nextStage = false;

		if (nextStage == true) {
			if (count < positions.get(player.getPosition())) {
				return true;
			} else
				return false;
		}
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
