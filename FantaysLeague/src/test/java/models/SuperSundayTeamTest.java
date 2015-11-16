package models;

import static org.junit.Assert.*;

import org.junit.Test;

public class SuperSundayTeamTest {
	private static final String TEST_OWNER = "testUser";
	private static final String TEST_TEAM = "testTeam";
	private static final String TEST_PASSWORD = "testPassword";
	private static final User TEST_USER = new User(TEST_OWNER, TEST_PASSWORD, TEST_TEAM);

	@Test
	public void testBonusTotalPoints() {
		Team testTeam = new SuperSoccerTeam(new SoccerTeam(TEST_TEAM, TEST_USER, null));
		assertTrue("Team bonus points for super sunday weren't added (expected, actual) 0 : " + testTeam.getTotalPoints(), testTeam.getTotalPoints() == 0);
	}
}