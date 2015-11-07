package models;

import java.util.Map;

public interface ILeague {
	public void addTeam(Team team);
    
    public void removeTeam(Team team);
    
    public boolean update(String filename);
    
    public boolean save();  

	public User getOwner();

	public Map<String, Integer> getLeagueTeams();

	public String getLeagueName();

	public String getSport();

}
