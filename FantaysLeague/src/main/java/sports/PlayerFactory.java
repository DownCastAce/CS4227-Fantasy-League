package sports;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class PlayerFactory {

    public static AbstractPlayer makePlayer(String sport, String anID, String aName, String aPosition, int anAge, String aTeam, double aValue) {
        if (sport.equalsIgnoreCase("soccer")) {
            HashMap<String, Integer> playerStats = new HashMap<>();
            try {
                List<String> stats = FileUtils.readLines(new File("resources/stats/stats.soccer"));
                boolean foundStats = false;
                for (int i = 0; i < stats.size() && !foundStats; i++) {
                    String[] currentStats = stats.get(i).split(",");
                    if (currentStats[0].equals(anID)) { 
                        //goals,assists,cleanSheets,yellowCards,redCards
                        playerStats.put("goals", Integer.parseInt(currentStats[1]));
                        playerStats.put("assists", Integer.parseInt(currentStats[1]));
                        playerStats.put("cleanSheets", Integer.parseInt(currentStats[1]));
                        playerStats.put("yellowCards", Integer.parseInt(currentStats[1]));
                        playerStats.put("redCards", Integer.parseInt(currentStats[1]));
                        foundStats = true;
                    }
                }
                return new SoccerPlayer(anID, aName, aPosition, anAge, aTeam, aValue, playerStats);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Could not add stats to player\n" + e);
            }
        }
        return null;
    }
}
