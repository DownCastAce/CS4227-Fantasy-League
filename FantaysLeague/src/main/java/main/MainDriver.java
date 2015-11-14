package main;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import controllers.InitialMenuController;
import controllers.LeagueController;
import views.InitialMenuView;
import views.SoccerLeagueView;
import stats.StatUpdateListener;
import stats.Subject;
import models.League;
import models.LeagueFactory;
import models.Roster;
import models.SoccerTeam;

public class MainDriver {
	
	final static String statFilepath = "resources/users/";
	final static String rosterFilepath = "resources/Rosters/";
	final static File resFolder = new File(statFilepath);
	public static Subject statListener = null;
	
	public static void main(String [] args){
		
		statListener = setupFileMonitor(resFolder, 5000);
		statListener.attach(Roster.getInstance(statListener));
		
		InitialMenuView v = new InitialMenuView();
		InitialMenuController con = new InitialMenuController(v);
	}
	
	public static Subject setupFileMonitor(File folder, int pollingMsecs){
		FileAlterationObserver observer = new FileAlterationObserver(folder);
		FileAlterationMonitor monitor = new FileAlterationMonitor(pollingMsecs);
		StatUpdateListener listener = new StatUpdateListener();
		
		observer.addListener(listener);
		monitor.addObserver(observer);
		try {
			monitor.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unexpected problem with file monitor");
			e.printStackTrace();
		}
		return listener;
	}
}