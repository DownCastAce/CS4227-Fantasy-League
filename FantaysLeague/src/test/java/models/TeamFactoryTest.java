package models;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import stats.StatUpdateListener;
import stats.Subject;

public class TeamFactoryTest {
	private static final String TEST_TEAM = "testTeam";
	private static final String TEST_LEAGUE_NAME = "testLeague";
	private static final String TEST_OWNER = "testUser";
	private static final String SPORT_SOCCER = "soccer";
	private static final String TEST_PASSWORD = "testPassword";
	private static final String LEAGUE_FILEPATH = "resources/leagues/";
	private static final String OWNER_FILEPATH = "resources/users/";
	private static final String TEAM_FILEPATH = "resources/teams/";
	private static final User TEST_USER = new User(TEST_OWNER, TEST_PASSWORD, TEST_TEAM);
	private static final File TEST_LEAGUE_FILE = new File(LEAGUE_FILEPATH + TEST_LEAGUE_NAME);
	private static final File TEST_OWNER_FILE = new File(OWNER_FILEPATH + TEST_OWNER);
	private static final File TEST_TEAM_FILE = new File(TEAM_FILEPATH + TEST_TEAM);
	private final static String statFilepath = "resources/users/";
	private final static File RES_FOLDER = new File(statFilepath);
	private static Subject TEST_SUBJECT = null;
	
	
	@Before
	public void setUp() throws Exception {
		FileUtils.deleteQuietly(TEST_LEAGUE_FILE);
		FileUtils.deleteQuietly(TEST_OWNER_FILE);
		FileUtils.deleteQuietly(TEST_TEAM_FILE);
		FileUtils.write(TEST_LEAGUE_FILE, TEST_OWNER + "\n" + TEST_TEAM + ",101");
		FileUtils.write(TEST_OWNER_FILE, TEST_OWNER + ",123," + TEST_TEAM);
		FileUtils.write(TEST_TEAM_FILE, TEST_OWNER + "\n100\n1\n2\n3\n4\n5");
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
	
	@After
	public void tearDown() {
		FileUtils.deleteQuietly(TEST_LEAGUE_FILE);
		FileUtils.deleteQuietly(TEST_OWNER_FILE);
		FileUtils.deleteQuietly(TEST_TEAM_FILE);
	}
	
	@Test
	public void testNewTeam() {
		Team testTeam = TeamFactory.newTeam(SPORT_SOCCER, TEST_TEAM, TEST_USER, TEST_SUBJECT);
		assertEquals("Owner name doesn't match (Expected : Actual) " + TEST_OWNER + " : " + testTeam.getOwner(), TEST_USER, testTeam.getOwner());
		assertEquals("Team name doesn't match (Expected : Actual) " + TEST_TEAM + " : " + testTeam.getTeamName(), TEST_TEAM, testTeam.getTeamName());
		assertTrue("Budget doesn't match (Expected : Actual) 100 : " + testTeam.getBudget(), 100 == testTeam.getBudget());
		assertTrue("Amount of players allowed doesn't match (Expected : Actual) 15 : " + testTeam.getAmountOfPlayersAllowed(), 15 == testTeam.getAmountOfPlayersAllowed());
	}

	
	@Test
	public void testLoadTeam() {
		Team testTeam = TeamFactory.load(SPORT_SOCCER, TEST_TEAM, TEST_SUBJECT);
		assertEquals("User name doesn't match (Expected : Actual) " + TEST_OWNER + " : " + testTeam.getOwner().getUserName(), TEST_OWNER, testTeam.getOwner().getUserName());
		assertEquals("Team name doesn't match (Expected : Actual) " + TEST_TEAM + " : " + testTeam.getTeamName(), TEST_TEAM, testTeam.getTeamName());
		assertTrue("Budget doesn't match (Expected : Actual) 70.9 : " + testTeam.getBudget(), 70.9 == testTeam.getBudget());
		assertTrue("Amount of players allowed doesn't match (Expected : Actual) 15 : " + testTeam.getAmountOfPlayersAllowed(), 15 == testTeam.getAmountOfPlayersAllowed());
		
	}
}
