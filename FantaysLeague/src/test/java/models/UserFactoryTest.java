package models;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;

public class UserFactoryTest {
	
	private static final String TEST_TEAM = "testTeam";
	private static final String PASSWORD = "password";
	private static final String TEST_USER = "testUser";
    private static final String FILEPATH = "resources/users/";
	
	@Test
	public void testNewUser() {
		UserFactory uf = new UserFactory();
		User testUser = uf.newUser(TEST_USER, PASSWORD, TEST_TEAM);
		assertEquals("User name doesn't match (Expected : Actual) " + TEST_USER + " : " + testUser.getUserName(), TEST_USER, testUser.getUserName());
		assertEquals("Password doesn't match (Expected : Actual) " + PASSWORD + " : " + testUser.getPassword(), PASSWORD, testUser.getPassword());
		assertEquals("Team name doesn't match (Expected : Actual) " + TEST_TEAM + " : " + testUser.getTeamName(), testUser.getTeamName(), TEST_TEAM);
	}
	
	@Test
	public void testLoad() {
		User expectedUser = new User(TEST_USER, PASSWORD, TEST_TEAM);
		expectedUser.save();
		User actualUser = UserFactory.load(TEST_USER);
		assertEquals("User name doesn't match (Expected : Actual) " + expectedUser.getUserName() + " : " + actualUser.getUserName(), expectedUser.getUserName(), actualUser.getUserName());
		assertEquals("Password name doesn't match (Expected : Actual) " + expectedUser.getPassword() + " : " + actualUser.getPassword(), expectedUser.getPassword(), actualUser.getPassword());
		assertEquals("Team name doesn't match (Expected : Actual) " + expectedUser.getTeamName() + " : " + actualUser.getTeamName(), expectedUser.getTeamName(), actualUser.getTeamName());
	}
	
	@After
    public void tearDown() {
        FileUtils.deleteQuietly(new File(FILEPATH + TEST_USER));
    }
}
