package models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

public class SoccerTeamTest {
	
	private static final int TEST_TEAM_POINTS = 100;
	private static final int TEST_TEAM_BUDGET = 100;
	private static final String TEST_TEAM = "testTeam";
	private static final String TEST_OWNER = "testUser";
	private static final String TEST_PASSWORD = "testPassword";
	private static final User TEST_USER = new User(TEST_OWNER, TEST_PASSWORD, TEST_TEAM);
	
	@Test
	public void testThreeConstructor() {
		Team testTeam = new SoccerTeam(TEST_TEAM, TEST_USER, null);
		assertEquals("Owner name doesn't match (Expected : Actual) " + TEST_OWNER + " : " + testTeam.getOwner(), TEST_USER, testTeam.getOwner());
		assertEquals("Team name doesn't match (Expected : Actual) " + TEST_TEAM + " : " + testTeam.getTeamName(), TEST_TEAM, testTeam.getTeamName());
		assertTrue("Budget doesn't match (Expected : Actual) " + TEST_TEAM_BUDGET + " : " + testTeam.getBudget(), TEST_TEAM_BUDGET == testTeam.getBudget());
		assertTrue("Amount of players allowed doesn't match (Expected : Actual) 15 : " + testTeam.getAmountOfPlayersAllowed(), 15 == testTeam.getAmountOfPlayersAllowed());
	}
	
	@Test
	public void testFiveConstructor() {
		ArrayList<SoccerPlayer> testTeamsList = new ArrayList<>();
		testTeamsList.add(new SoccerPlayer("1", "DownCastAce", "D", 8.5));
		Team testTeam = new SoccerTeam(TEST_TEAM, TEST_USER, TEST_TEAM_BUDGET, testTeamsList, TEST_TEAM_POINTS, null);
		assertEquals("Owner name doesn't match (Expected : Actual) " + TEST_OWNER + " : " + testTeam.getOwner(), TEST_USER, testTeam.getOwner());
		assertEquals("Team name doesn't match (Expected : Actual) " + TEST_TEAM + " : " + testTeam.getTeamName(), TEST_TEAM, testTeam.getTeamName());
		assertTrue("Team points doesn't match (Expected : Actual) " + TEST_TEAM_POINTS + " : " + testTeam.getTotalPoints(), TEST_TEAM_POINTS == testTeam.getTotalPoints());
		assertTrue("Amount of players allowed doesn't match (Expected : Actual) 15 : " + testTeam.getAmountOfPlayersAllowed(), 15 == testTeam.getAmountOfPlayersAllowed());
	}
}