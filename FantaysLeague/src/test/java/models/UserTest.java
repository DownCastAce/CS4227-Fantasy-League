package models;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;

public class UserTest {

	private static final String TEST_TEAM = "testTeam";
	private static final String PASSWORD = "password";
	private static final String TEST_USER = "testUser";
    private static final String FILEPATH = "resources/users/";
	
	@Test
	public void testConstructorTwoParams() {
		IUser testUser = new User(TEST_USER, PASSWORD);
		assertTrue("User name doesn't match (Expected : Actual) " + TEST_USER + " : " + testUser.getUserName(), TEST_USER.equals(testUser.getUserName()));
		assertTrue("Password doesn't match (Expected : Actual) " + PASSWORD + " : " + testUser.getPassword(), PASSWORD.equals(testUser.getPassword()));
	}
	
	@Test
	public void testConstructorThreeParams() {
		IUser testUser = new User(TEST_USER, PASSWORD, TEST_TEAM);
		assertTrue("User name doesn't match (Expected : Actual) " + TEST_USER + " : " + testUser.getUserName(), TEST_USER.equals(testUser.getUserName()));
		assertTrue("Password doesn't match (Expected : Actual) " + PASSWORD + " : " + testUser.getPassword(), PASSWORD.equals(testUser.getPassword()));
		assertTrue("Team name doesn't match (Expected : Actual) " + TEST_TEAM + " : " + testUser.getTeamName(), TEST_TEAM.equals(testUser.getTeamName()));
	}
	
	@Test
	public void testSave() throws IOException {
		IUser testUser = new User(TEST_USER, PASSWORD, TEST_TEAM);
		assertTrue("File didn't saved as expected", testUser.save() && (new File(FILEPATH + TEST_USER).exists()));
		String[] actualResult = FileUtils.readFileToString(new File(FILEPATH + TEST_USER)).split(",");
		assertTrue("Number of strings written wrong", 3 == actualResult.length);
		assertTrue("User name doesn't match (Expected : Actual) " + TEST_USER + " : " + actualResult[0], TEST_USER.equals(actualResult[0]));
		assertTrue("Password doesn't match (Expected : Actual) " + PASSWORD + " : " + actualResult[1], PASSWORD.equals(actualResult[1]));
		assertTrue("Team name doesn't match (Expected : Actual) " + TEST_TEAM + " : " + actualResult[2], TEST_TEAM.equals(actualResult[2]));
	}
	
	@After
    public void tearDown() {
        FileUtils.deleteQuietly(new File(FILEPATH + TEST_USER));
    }
}
