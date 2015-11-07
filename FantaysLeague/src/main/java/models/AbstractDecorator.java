package models;

import java.util.Map;

public abstract class AbstractDecorator implements ILeague {

	protected ILeague currentLeague;
	
	
	public AbstractDecorator(ILeague newLeague) {
		this.currentLeague = newLeague;
	}
	
	@Override
	public void addTeam(Team team) {
		currentLeague.addTeam(team);
	}
		
	@Override
	public void removeTeam(Team team) {
		currentLeague.removeTeam(team);
	}

	@Override
	public boolean update(String filename) {
		currentLeague.update(filename);
    	return false;
	}

	@Override
	public boolean save() {
		currentLeague.save();
		return false;
	}

	@Override
	public User getOwner() {
		return currentLeague.getOwner();
	}

	@Override
	public Map<String, Integer> getLeagueTeams() {
		return currentLeague.getLeagueTeams();
	}

	@Override
	public String getLeagueName() {
		return currentLeague.getLeagueName();
	}

	@Override
	public String getSport() {
		return currentLeague.getSport();
	}

}
