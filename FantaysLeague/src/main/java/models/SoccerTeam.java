package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    protected ArrayList<SoccerPlayer> selectPlayers = new ArrayList<SoccerPlayer>();
    protected HashMap<String, Integer> positions = new HashMap<>();
    protected Subject subject;
    protected int totalPoints = 0;

	public SoccerTeam(String teamname, User owner, Subject listener) {
		// Create empty team.
		this.teamName = teamname;
		this.owner = owner;
		this.subject = listener;

		setAmountOfPlayersAllowed(15);
		this.budget = 100.0;
		this.positions.put("G", 2);
		this.positions.put("D", 5);
		this.positions.put("M", 5);
		this.positions.put("F", 3);

		// update();
	}

	public SoccerTeam(String teamname, User owner, double budget, ArrayList<SoccerPlayer> players, int points, Subject listener) {
		// Load team from file.
		this.teamName = teamname;
		this.owner = owner;

		subject = listener;
		this.budget = budget;
		this.totalPoints = points;
		setAmountOfPlayersAllowed(15);
		positions.put("G", 2);
		positions.put("D", 5);
		positions.put("M", 5);
		positions.put("F", 3);

		selectPlayers.addAll(players);

		//Placeholder to updating budget
		for (SoccerPlayer player : players) {
			this.budget -= player.getValue();
		}
		// update();

	}

	public ArrayList<SoccerPlayer> getPlayerList() {
		return selectPlayers;
	}

	@Override
	public int getTotalPoints(){
		return totalPoints;
	}
	
	public int getAmountOfPlayersAllowed() {
		return amountOfPlayersAllowed;
	}

	public void setAmountOfPlayersAllowed(int amountOfPlayersAllowed) {
		this.amountOfPlayersAllowed = amountOfPlayersAllowed;
	}

	@Override
	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public void decrementBudget(double a) {
		budget = budget - a;
	}

	@Override
	public User getOwner() {
		return this.owner;
	}

	@Override
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
	
	 public synchronized boolean save() {
	    	/*Format: filename = teamname
	    	 * ownerName
	    	 * budget
	    	 * points
	    	 * player1
	    	 * player2...
	    	 */
	    	ArrayList<String> output = new ArrayList<String>();
	    	File saveFile = new File(FILEPATH + this.teamName);
	    	
	    	output.add(owner.getUserName());
	    	output.add(Double.toString(budget));
	    	output.add(Integer.toString(totalPoints));
	    	for(Player player: selectPlayers){
	    		output.add(player.getID());
	    	}	
	    	try{
	    		AsyncWriteUtil writer = new AsyncWriteUtil(output, saveFile);
	    		writer.run();
	    		//writer.write();
	    	}
	    	catch(IOException e){
	    		return false;
	    	}
	    	return true;
	    }

		@Override
		public synchronized void update() {
			Map<Integer, List<Stat>> stats = (Map<Integer, List<Stat>>)subject.getState();
			for(Map.Entry<Integer, List<Stat>> statList : stats.entrySet()){
				for(Player player: selectPlayers)
					if(statList.getKey() == Integer.parseInt(player.getID())){
						for(Stat stat: statList.getValue())
							totalPoints += stat.getScore();
					}
			}
			save();
		}
}
