package models;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.Before;
import org.junit.Test;

import stats.StatUpdateListener;
import stats.Subject;

public class SoccerTeamTest {
	
	private static final int TEST_TEAM_BUDGET = 100;
	private static final String TEST_TEAM = "testTeam";
	private static final String TEST_LEAGUE_NAME = "testLeague";
	private static final String TEST_OWNER = "testUser";
	private static final String SPORT_SOCCER = "soccer";
	private static final String TEST_PASSWORD = "testPassword";
	private final static String statFilepath = "resources/users/";
	private static final User TEST_USER = new User(TEST_OWNER, TEST_PASSWORD, TEST_TEAM);
	private static Subject TEST_SUBJECT = null;
	private final static File RES_FOLDER = new File(statFilepath);

	@Before
	public void setUp() throws Exception {
		FileAlterationObserver observer = new FileAlterationObserver(RES_FOLDER);
		FileAlterationMonitor monitor = new FileAlterationMonitor(5000);
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
		TEST_SUBJECT = listener;
	}
	
	@Test
	public void testThreeConstructor() {
		Team testTeam = new SoccerTeam(TEST_TEAM, TEST_USER, TEST_SUBJECT);
		assertEquals("Owner name doesn't match (Expected : Actual) " + TEST_OWNER + " : " + testTeam.getOwner(), TEST_USER, testTeam.getOwner());
		assertEquals("Team name doesn't match (Expected : Actual) " + TEST_TEAM + " : " + testTeam.getTeamName(), TEST_TEAM, testTeam.getTeamName());
		assertTrue("Budget doesn't match (Expected : Actual) " + TEST_TEAM_BUDGET + " : " + testTeam.getBudget(), TEST_TEAM_BUDGET == testTeam.getBudget());
		assertTrue("Amount of players allowed doesn't match (Expected : Actual) 15 : " + testTeam.getAmountOfPlayersAllowed(), 15 == testTeam.getAmountOfPlayersAllowed());
	}
	
	@Test
	public void testFourConstructor() {
		ArrayList<SoccerPlayer> testTeamsList = new ArrayList<>();
		testTeamsList.add(new SoccerPlayer("1", "DownCastAce", "D", 8.5));
		Team testTeam = new SoccerTeam(TEST_TEAM, TEST_USER, testTeamsList, TEST_SUBJECT);
		assertTrue("Team budget not doesn't match Expected : Actual) " + (TEST_TEAM_BUDGET-8.5) + " : " + testTeam.getBudget(), testTeam.getBudget() == (TEST_TEAM_BUDGET - 8.5));
		assertEquals("Owner name doesn't match (Expected : Actual) " + TEST_OWNER + " : " + testTeam.getOwner(), TEST_USER, testTeam.getOwner());
		assertEquals("Team name doesn't match (Expected : Actual) " + TEST_TEAM + " : " + testTeam.getTeamName(), TEST_TEAM, testTeam.getTeamName());
		assertTrue("Amount of players allowed doesn't match (Expected : Actual) 15 : " + testTeam.getAmountOfPlayersAllowed(), 15 == testTeam.getAmountOfPlayersAllowed());
	}
}