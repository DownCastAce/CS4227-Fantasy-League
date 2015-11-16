package main;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.time.Instant;
import controllers.InitialMenuController;
import views.InitialMenuView;
import stats.StatUpdateListener;
import models.Roster;

import interceptor.*;


public class MainDriver {
	
	final static String statFilepath = "resources/stats/";
	final static String rosterFilepath = "resources/Rosters/";
	final static File resFolder = new File(statFilepath);
	public static StatUpdateListener statListener = null;
	public static Instant lastUpdate;	
	
	public static void main(String [] args){
		lastUpdate = Instant.now();
		statListener = setupFileMonitor(resFolder, 2000);
		statListener.attach(Roster.getInstance(statListener));
		ServerReplyInterceptor inter1 = new TestInterceptor();
		ServerReplyInterceptor inter2 = new TestInterceptor();
		
		ServerReplyDispatcher dis = new ServerReplyDispatcher();
		dis.register(inter1);
		dis.register(inter2);
		statListener.registerServerReplyDispatcher(dis);
		
		
		InitialMenuView v = new InitialMenuView();
		InitialMenuController con = new InitialMenuController(v);

	}
	
	public static StatUpdateListener setupFileMonitor(File folder, int pollingMsecs){
		FileAlterationObserver observer = new FileAlterationObserver(folder);
		FileAlterationMonitor monitor = new FileAlterationMonitor(pollingMsecs);
		StatUpdateListener listener = new StatUpdateListener();
		
		observer.addListener(listener);
		monitor.addObserver(observer);
		try {
			monitor.start();
		} catch (Exception e) {
			System.out.println("Unexpected problem with file monitor");
			e.printStackTrace();
		}
		return listener;
	}
}
