package sports;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

public class SoccerTeam extends AbstractTeam {

	private static final String FILEPATH = "resources/teams/";
    public SoccerTeam(String teamname, User owner) {
        // Create empty team.
        super(teamname, owner);
        setAmountOfPlayersAllowed(15);
        budget = 100.0;
        positions.put("G", 2);
        positions.put("D", 5);
        positions.put("M", 5);
        positions.put("F", 3);

    }

    public SoccerTeam(String teamName, User owner, ArrayList<AbstractPlayer> players){
        // Load team from file.
        super(teamName, owner);
        setAmountOfPlayersAllowed(15);
        budget = 100.0;
        positions.put("G", 2);
        positions.put("D", 5);
        positions.put("M", 5);
        positions.put("F", 3);
        
        selectPlayers = players;

    }

    public boolean tryAddPlayer(SoccerPlayer nextPlayer) {

        boolean add;

        if (isValidTeamAddition(nextPlayer, getNumberOfPlayersAtPosition(nextPlayer.getPosition()))) 
        {
            selectPlayers.add(nextPlayer);
            add = true;
        }
        else
        {
        	add = false;
        }
       
        return add;
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

    public boolean save(String teamName) {
        boolean saved = false;

        System.out.println(this.teamName);
        if (selectPlayers.size() == 15) {
            try {
                String output = "";
                for (int i = 0; i < selectPlayers.size(); i++) {
                    if (i != 14)
                        output += selectPlayers.get(i).getID() + ",";
                    else
                        output += selectPlayers.get(i).getID();
                }
                FileUtils.write(new File(FILEPATH + teamName), output);
                saved = true;
            }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("error writing in team from file - " + teamName + "\n" + e);
            }
        }
        return saved;
    }

    @Override
    public int generateTeamPoints() {
        int totalPoints = 0;
        for(AbstractPlayer currentPlayer : selectPlayers){
            HashMap<String, Integer> currentStats = currentPlayer.getStats();
            //goals,assists,cleanSheets,yellowCards,redCards for current week
            totalPoints += currentStats.get("goals") * 4;
            totalPoints += currentStats.get("assists") * 2;
            totalPoints += currentStats.get("cleanSheets") * 3;
            totalPoints += currentStats.get("yellowCards") * -1;
            totalPoints += currentStats.get("redCards") * -3;
        }
        return totalPoints;
    }
}