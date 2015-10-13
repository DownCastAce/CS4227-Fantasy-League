package sports;

import java.util.HashMap;

/**
 *
 * @author Peter
 */

public class SoccerPlayer extends AbstractPlayer{

    public SoccerPlayer(String anID, String aName, String aPosition, int anAge, String aTeam, double aValue, HashMap<String, Integer> playerStats) {
        super(anID, aName, aPosition, anAge, aTeam, aValue, playerStats);
    }
    
    public HashMap<String, Integer> getThisWeeksStats() {

        return new HashMap<String, Integer>();
    }

    public HashMap<String, Integer> getSeasonStats() {

        return new HashMap<String, Integer>();
    }
}