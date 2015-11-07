package models;

import java.util.ArrayList;

import stats.Subject;

public class SoccerTeam extends Team {
	
	public SoccerTeam(String teamname, User owner, Subject listener) {
        // Create empty team.		
        super(teamname, owner, listener);
        setAmountOfPlayersAllowed(15);
        budget = 100.0;
        positions.put("G", 2);
        positions.put("D", 5);
        positions.put("M", 5);
        positions.put("F", 3);

    }

    public SoccerTeam(String teamName, User owner, ArrayList<SoccerPlayer> players, Subject listener){
        // Load team from file.
        super(teamName, owner, listener);
        setAmountOfPlayersAllowed(15);
        budget = 100.0;
        positions.put("G", 2);
        positions.put("D", 5);
        positions.put("M", 5);
        positions.put("F", 3);
        
        selectPlayers.addAll(players);

    }

    public boolean tryAddPlayer(SoccerPlayer nextPlayer) {
        if (isValidTeamAddition(nextPlayer, getNumberOfPlayersAtPosition(nextPlayer.getPosition()))) 
        {
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
}
