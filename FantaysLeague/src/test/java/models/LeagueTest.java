package models;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.MainDriver;

public class LeagueTest {
	private static final String TEST_OWNER = "testUser";
	private static final String TEST_ROSTER = "testRoster";
	private static final String SPORT_SOCCER = "soccer";
	private static final String TEST_LEAGUE_NAME = "testLeague";
	private static final String TEST_TEAM = "testTeam";
	private static final String TEST_PASSWORD = "testPassword";
	private static final String LEAGUE_FILEPATH = "resources/leagues/";
	private static final String TEAM_FILEPATH = "resources/teams/";
	private static final String ROSTER_FILEPATH = "resources/Rosters/";
	private static final String OWNER_FILEPATH = "resources/users/";
	private static final User TEST_USER = new User(TEST_OWNER, TEST_PASSWORD, TEST_TEAM);
	private static final File TEST_LEAGUE_FILE = new File(LEAGUE_FILEPATH + TEST_LEAGUE_NAME);
	private static final File TEST_TEAM_FILE = new File(TEAM_FILEPATH + TEST_TEAM);
	private static final File TEST_ROSTER_FILE = new File(ROSTER_FILEPATH + TEST_ROSTER);
	private static final File TEST_OWNER_FILE = new File(OWNER_FILEPATH + TEST_OWNER);

	@Before
	public void setUp() throws Exception {
		FileUtils.deleteQuietly(TEST_LEAGUE_FILE);
		FileUtils.deleteQuietly(TEST_ROSTER_FILE);
		FileUtils.deleteQuietly(TEST_TEAM_FILE);
		FileUtils.deleteQuietly(TEST_OWNER_FILE);
		FileUtils.write(TEST_LEAGUE_FILE, TEST_OWNER + "\n" + TEST_TEAM + ",101");
		FileUtils.write(TEST_ROSTER_FILE, "1,G,Hart,6.1,20\n2,D,Courtois,5.9,23\n3,D,de Gea,5.9,22\n4,M,Forster,5.6,27\n5,F,Lloris,5.6,23");
		FileUtils.write(TEST_TEAM_FILE, TEST_OWNER + "\n1\n2\n3\n4\n5");
		FileUtils.write(TEST_OWNER_FILE, TEST_OWNER + ",123," + TEST_TEAM);
	}
	
	@After
	public void tearDown() {
		FileUtils.deleteQuietly(TEST_LEAGUE_FILE);
		FileUtils.deleteQuietly(TEST_ROSTER_FILE);
		FileUtils.deleteQuietly(TEST_TEAM_FILE);
		FileUtils.deleteQuietly(TEST_OWNER_FILE);
	}
	
	@Test
	public void testContructorThreeParams() {
		long testDate = Instant.now().getEpochSecond();
		SoccerTeam testTeam = new SoccerTeam(TEST_TEAM, TEST_USER, null);
		League testLeague = new League(TEST_USER, TEST_LEAGUE_NAME, testTeam, SPORT_SOCCER);
		assertEquals("Owner name doesn't match (Expected : Actual) " + TEST_OWNER + " : " + testLeague.getOwner(), TEST_USER, testLeague.getOwner());
		assertEquals("League name doesn't match (Expected : Actual) " + TEST_LEAGUE_NAME + " : " + testLeague.getLeagueName(), TEST_LEAGUE_NAME, testLeague.getLeagueName());
		assertEquals("Sport doesn't match (Expected : Actual) " + SPORT_SOCCER + " : " + testLeague.getSport(), SPORT_SOCCER, testLeague.getSport());
		assertTrue("Sport doesn't match (Expected : Actual) " + testDate + " : " + testLeague.getLastUpdate(), Math.abs((testDate-testLeague.getLastUpdate().getEpochSecond())) < 1000);
		//assertTrue("Team name doesn't match (Expected : Actual) 0 : " + testLeague.getLeagueTeams().get(TEST_TEAM), 0 == testLeague.getLeagueTeams().get(TEST_TEAM));
	}
	
	@Test
	public void testConstructorFiveParams() {
		MainDriver.lastUpdate = Instant.now();
		long testDate = Instant.now().getEpochSecond();
		SoccerTeam testTeam = new SoccerTeam(TEST_TEAM, TEST_USER, null);
		Map<SoccerTeam, Integer> testTeams = new HashMap<>();
		testTeams.put(testTeam, 101);
		League testLeague = new League(TEST_USER, TEST_LEAGUE_NAME, SPORT_SOCCER, testDate, testTeams);
		assertEquals("Owner name doesn't match (Expected : Actual) " + TEST_USER + " : " + testLeague.getOwner(), TEST_USER, testLeague.getOwner());
		assertEquals("League name doesn't match (Expected : Actual) " + TEST_LEAGUE_NAME + " : " + testLeague.getLeagueName(), TEST_LEAGUE_NAME, testLeague.getLeagueName());
		assertEquals("Sport doesn't match (Expected : Actual) " + SPORT_SOCCER + " : " + testLeague.getSport(), SPORT_SOCCER, testLeague.getSport());
		assertTrue("Sport doesn't match (Expected : Actual) " + testDate + " : " + testLeague.getLastUpdate(), Math.abs((testDate-testLeague.getLastUpdate().getEpochSecond())) < 1000);
		//assertTrue("Team name doesn't match (Expected : Actual) 101 : " + testLeague.getLeagueTeams().get(TEST_TEAM), 101 == testLeague.getLeagueTeams().get(TEST_TEAM));
	}
	
	@Test
	public void testSave() throws IOException {
		MainDriver.lastUpdate = Instant.now();
		long testDate = Instant.now().getEpochSecond();
		SoccerTeam testTeam = new SoccerTeam(TEST_TEAM, TEST_USER, null);
		Map<SoccerTeam, Integer> testTeams = new HashMap<>();
		testTeams.put(testTeam, 101);
		League testLeague = new League(TEST_USER, TEST_LEAGUE_NAME, SPORT_SOCCER, testDate, testTeams);
		testLeague.save();
		assertTrue("File didn't save as expected TEST_LEAGUE_FILE.exists()", TEST_LEAGUE_FILE.exists());
		List<String> actualResult = FileUtils.readLines(TEST_LEAGUE_FILE);
		assertTrue("Amount of lines doesn't match (Expected : Actual) 2 : " + actualResult.size(), 2 == actualResult.size());
		assertEquals("Owner doesn't match (Expected : Actual) " + TEST_OWNER + " : " + actualResult.get(0), TEST_OWNER, actualResult.get(0));
		assertEquals("Team and score doesn't match (Expected : Actual) " + TEST_TEAM + ",101 : " + actualResult.get(1), TEST_TEAM + ",101", actualResult.get(1));
	}
}
