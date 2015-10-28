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
		User testUser = uf.newUser(TEST_USER, PASSWORD);
		assertTrue("User name doesn't match (Expected : Actual) " + TEST_USER + " : " + testUser.getUserName(), TEST_USER.equals(testUser.getUserName()));
		assertTrue("Password doesn't match (Expected : Actual) " + PASSWORD + " : " + testUser.getPassword(), PASSWORD.equals(testUser.getPassword()));
		assertNull("Team name doesn't match (Expected : Actual) " + null + " : " + testUser.getTeamName(), testUser.getTeamName());
	}
	
	@Test
	public void testLoad() {
		User expectedUser = new User(TEST_USER, PASSWORD, TEST_TEAM);
		expectedUser.save();
		UserFactory uf = new UserFactory();
		User actualUser = uf.load(TEST_USER);
		assertTrue("User name doesn't match (Expected : Actual) " + expectedUser.getUserName() + " : " + actualUser.getUserName(), expectedUser.getUserName().equals(actualUser.getUserName()));
		assertTrue("Password name doesn't match (Expected : Actual) " + expectedUser.getPassword() + " : " + actualUser.getPassword(), expectedUser.getPassword().equals(actualUser.getPassword()));
		assertTrue("Team name doesn't match (Expected : Actual) " + expectedUser.getTeamName() + " : " + actualUser.getTeamName(), expectedUser.getTeamName().equals(actualUser.getTeamName()));
	}
	
	@After
    public void tearDown() {
        FileUtils.deleteQuietly(new File(FILEPATH + TEST_USER));
    }
}