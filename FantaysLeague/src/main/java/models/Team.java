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
	
	public abstract String getTeamName();
	
    public abstract boolean save();
        
}
