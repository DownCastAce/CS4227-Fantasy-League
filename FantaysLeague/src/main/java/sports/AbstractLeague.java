package sports;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public abstract class AbstractLeague{
    protected static final String FILEPATH = "resources/";
    protected Date lastUpdate;
    protected String leagueId;
    protected String ownerId;
    protected String leagueName;
    protected ArrayList<AbstractTeam> leagueTeams = new ArrayList<>();
    
    //TeamId, totalPoints
    //TeamName, totalPoints - Changed
    HashMap<String, Integer> leagueTeamPoints = new HashMap<String, Integer>();
    
    public String getLeagueName() {
        return leagueName;
    }
    String getLeagueId() {
        return leagueId;
    }
    public ArrayList<AbstractTeam> getLeagueTeams() {
        return leagueTeams;
    }
    
    String getOwnerId() {
        return ownerId;
    }
    
    public int getTeamPoints(AbstractTeam team) {
        return leagueTeamPoints.get(team.getTeamName());
    }
    
    public abstract void updateTeamPoints();

    abstract void rankOrderOfTeams();
    
    public void addTeamToLeague(AbstractTeam teamToAdd) {
        if(!leagueTeamPoints.containsKey(teamToAdd.getTeamName())) {
            leagueTeams.add(teamToAdd);
            leagueTeamPoints.put(teamToAdd.getTeamName(), 0);
        }
    }
    
    void removeTeamFromLeague(AbstractTeam teamToBeRemoved) {
        for(AbstractTeam teamToRemove : leagueTeams) {
            if(teamToRemove.getTeamName().equals(teamToBeRemoved.getTeamName())) {
                leagueTeams.remove(teamToRemove);
                leagueTeamPoints.remove(teamToBeRemoved.getTeamName());
            }
        }
    }
    
    public abstract void saveLeague();
}