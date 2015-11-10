package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import stats.Observer;
import stats.Subject;
import stats.Stat;

import org.apache.commons.io.FileUtils;

public abstract class Team extends Observer{
	
    public abstract boolean save();

	public abstract String getTeamName();

	public abstract User getOwner();
        
	public abstract double getBudget();
	
	public abstract int getAmountOfPlayersAllowed();
}