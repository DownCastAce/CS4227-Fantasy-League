package models;

import stats.Observer;

public abstract class Team extends Observer{
	
    public abstract boolean save();

	public abstract String getTeamName();

	public abstract User getOwner();
        
	public abstract double getBudget();
	
	public abstract int getAmountOfPlayersAllowed();
}
