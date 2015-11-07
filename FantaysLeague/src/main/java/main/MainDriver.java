package main;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.io.IOException;

import controllers.InitialMenuController;
import views.InitialMenuView;
import stats.StatUpdateListener;
import stats.Subject;
import models.Roster;

public class MainDriver {
	
	final static String statFilepath = "resources/users/";
	final static String rosterFilepath = "resources/Rosters/";
	final static File resFolder = new File(statFilepath);
	
	public static void main(String [] args){

		Subject subject = setupFileMonitor(resFolder, 5000);
		subject.attach(Roster.getInstance(subject));
		
		//InitialMenuView v = new InitialMenuView();
		//InitialMenuController con = new InitialMenuController(v);
	
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
