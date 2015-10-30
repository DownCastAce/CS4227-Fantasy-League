package models;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LeagueFactoryTest {
	private static final String TEST_TEAM = "testTeam";
	private static final String TEST_LEAGUE_NAME = "testLeague";
	private static final String TEST_OWNER = "testUser";
	private static final String SPORT_SOCCER = "soccer";
	private static final String TEST_PASSWORD = "testPassword";
	private static final String LEAGUE_FILEPATH = "resources/leagues/";
	private static final String OWNER_FILEPATH = "resources/users/";
	private static final User TEST_USER = new User(TEST_OWNER, TEST_PASSWORD, TEST_TEAM);
	private static final File TEST_LEAGUE_FILE = new File(LEAGUE_FILEPATH + TEST_LEAGUE_NAME);
	private static final File TEST_OWNER_FILE = new File(OWNER_FILEPATH + TEST_OWNER);
	
	@Before
	public void setUp() throws Exception {
		FileUtils.deleteQuietly(TEST_LEAGUE_FILE);
		FileUtils.deleteQuietly(TEST_OWNER_FILE);
		FileUtils.write(TEST_LEAGUE_FILE, TEST_OWNER + "\n" + TEST_TEAM + ",101");
		FileUtils.write(TEST_OWNER_FILE, TEST_OWNER + ",123," + TEST_TEAM);
	}
	
	@After
	public void tearDown() {
		FileUtils.deleteQuietly(TEST_LEAGUE_FILE);
		FileUtils.deleteQuietly(TEST_OWNER_FILE);
	}
	
	@Test
	public void testNewLeague() {
		FileUtils.deleteQuietly(TEST_LEAGUE_FILE);
		Date testDate = new Date();
		League testLeague = LeagueFactory.newLeague(TEST_USER, TEST_LEAGUE_NAME, SPORT_SOCCER);
		assertTrue("User name doesn't match (Expected : Actual) " + TEST_OWNER + " : " + testLeague.getOwner(), TEST_USER.equals(testLeague.getOwner()));
		assertTrue("League name doesn't match (Expected : Actual) " + TEST_LEAGUE_NAME + " : " + testLeague.getLeagueName(), TEST_LEAGUE_NAME.equals(testLeague.getLeagueName()));
		assertTrue("Sport doesn't match (Expected : Actual) " + SPORT_SOCCER + " : " + testLeague.getSport(), SPORT_SOCCER.equals(testLeague.getSport()));
		assertTrue("Sport doesn't match (Expected : Actual) " + testDate.getTime() + " : " + testLeague.getLastUpdate().getTime(), Math.abs((testDate.getTime()-testLeague.getLastUpdate().getTime())) < 1000);
		assertTrue("Team name doesn't match (Expected : Actual) 0 : " + testLeague.getLeagueTeams().get(TEST_TEAM), 0 == testLeague.getLeagueTeams().get(TEST_TEAM));
	}
	
	@Test
	public void testLoadLeague() {
		Date testDate = new Date();
		League testLeague = LeagueFactory.load(TEST_LEAGUE_NAME, SPORT_SOCCER);
		assertTrue("Owner name doesn't match (Expected : Actual) " + TEST_OWNER + " : " + testLeague.getOwner().getUserName(), TEST_OWNER.equals(testLeague.getOwner().getUserName()));
		assertTrue("League name doesn't match (Expected : Actual) " + TEST_LEAGUE_NAME + " : " + testLeague.getLeagueName(), TEST_LEAGUE_NAME.equals(testLeague.getLeagueName()));
		assertTrue("Sport doesn't match (Expected : Actual) " + SPORT_SOCCER + " : " + testLeague.getSport(), SPORT_SOCCER.equals(testLeague.getSport()));
		assertTrue("Sport doesn't match (Expected : Actual) " + testDate.getTime() + " : " + testLeague.getLastUpdate().getTime(), Math.abs((testDate.getTime()-testLeague.getLastUpdate().getTime())) < 1000);
		assertTrue("Team name doesn't match (Expected : Actual) 101 : " + testLeague.getLeagueTeams().get(TEST_TEAM), 101 == testLeague.getLeagueTeams().get(TEST_TEAM));
	}
}
