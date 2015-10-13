package sports;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Test;

public class UserTests {
    
    private static final String FILEPATH = "resources/users/";
    private static final String USER_TEAM_NAME = "Arsenal";
    private static final String USER_PASSWORD = "123";
    private static final String USER_NUMBER = "0636931992";
    private static final String USER_EMAIL = "lorcan.lod@gmail.com";
    private static final String USER_ID = "1";
    private static final String USER_NAME = "lorcan";
    private static final String SEPERATOR = " : ";
    private static final String ERROR_MESSAGE = " is incorrect : ";
    
    @Test
    public void testUser() {
        User testCase = new User(USER_NAME, USER_ID, USER_EMAIL, USER_NUMBER, USER_PASSWORD, USER_TEAM_NAME);
        assertTrue("User id" + ERROR_MESSAGE + USER_ID + SEPERATOR + testCase.getId(), USER_ID.equals(testCase.getId()));
        assertTrue("User name" + ERROR_MESSAGE + USER_NAME + SEPERATOR + testCase.getUserName(), USER_NAME.equals(testCase.getUserName()));
        assertTrue("Email" + ERROR_MESSAGE + USER_EMAIL + SEPERATOR + testCase.getEmail(), USER_EMAIL.equals(testCase.getEmail()));
        assertTrue("Phone number" + ERROR_MESSAGE + USER_NUMBER + SEPERATOR + testCase.getPhoneNumber(), USER_NUMBER == testCase.getPhoneNumber());
        assertTrue("User password" + ERROR_MESSAGE + USER_PASSWORD + SEPERATOR + testCase.getPassword(), USER_PASSWORD.equals(testCase.getPassword()));
        assertTrue("Users team name" + ERROR_MESSAGE + USER_TEAM_NAME + SEPERATOR + testCase.getTeamName(), USER_TEAM_NAME.equals(testCase.getTeamName()));
    }
    
    @Test //Person who wrote method didn't catch this, there job not mine. I shall complain to them
    public void testSaveNewAccountDetails() throws IOException {
        User testCase = new User(USER_NAME, USER_ID, USER_EMAIL, USER_NUMBER, USER_PASSWORD, USER_TEAM_NAME);
        testCase.saveNewAccountDetails();
        assertTrue("File not created in : " + FILEPATH + USER_NAME, (new File(FILEPATH + USER_NAME)).exists());
        String[] userInfo = FileUtils.readFileToString(new File(FILEPATH + USER_NAME)).split(",");
        List<String> expectedUserInfo = Arrays.asList(USER_NAME,USER_EMAIL,USER_PASSWORD,USER_NUMBER, USER_TEAM_NAME);
        for(int i = 0; i < userInfo.length; i++) {
            assertTrue("File doesn't have expected information", expectedUserInfo.get(i).equals(userInfo[i]));
        }
        
    }
    
    @AfterClass
    public static void tearDown() {
        FileUtils.deleteQuietly(new File(FILEPATH + USER_NAME));
    }
}