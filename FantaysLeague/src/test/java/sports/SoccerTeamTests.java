package sports;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Test;

public class SoccerTeamTests {
    
    private static final String RESOURCES = "resources/";
    private static final String FILEPATH = "tests/";
    private static final String TEAM_FILEPATH = RESOURCES + "teams/";
    private static final String TEST_TEAM_NAME = "testTeam";
    private static final String TEAM_FILE = FILEPATH + TEST_TEAM_NAME;
    private static final String FOLDER_PATH = TEAM_FILEPATH + TEAM_FILE;
    private static final String TEAM_NAME = "Arsenal Fc";
    private static final String USER_ID = "12345";
    private static final String USER_NAME = "Arsene Wenger";
    
    private User testUser = new User(USER_NAME, USER_ID);
    
    @Test
    public void testSoccerTeam() {
        SoccerTeam testCase = new SoccerTeam(TEAM_NAME, testUser);
        assertTrue("User names do not match - " + USER_NAME + " : " + testCase.getOwner().getUserName(), USER_NAME.equals(testCase.getOwner().getUserName()));
        assertTrue("User ids do not match - " + USER_ID + " : " + testCase.getOwner().getId(), USER_ID.equals(testCase.getOwner().getId()));
        assertTrue("Team names do not match - " + TEAM_NAME + " : " + testCase.getTeamName(), TEAM_NAME.equals(testCase.getTeamName()));
    }

    @Test
    public void testSoccerTeamRosters() throws IOException {
        User user = new User("testUser", "1", "test.user@email.com", "0871234567", "password","testTeamUnited");
        AbstractTeam testCase = TeamFactory.makeTeam("soccer", TEAM_FILE, user);
        assertTrue("Team budget was too low - " + testCase.getBudget(), testCase.getBudget() >= 0);
        assertTrue("Team name is wrong - " + testCase.getTeamName() + " : " + TEST_TEAM_NAME, testCase.getTeamName().replace(FILEPATH, "").equalsIgnoreCase(TEST_TEAM_NAME));
    }
    
    @Test
    public void testTryAddPlayer() {
        SoccerTeam testCase = new SoccerTeam(TEAM_NAME, testUser);
        SoccerPlayer testPlayer = new SoccerPlayer("100", "Alex Oxlade-Chamberlain", "M", 21, "Arsenal", 6.2, null);
        
        assertTrue("Player not added", testCase.tryAddPlayer(testPlayer));
        
        testCase.tryAddPlayer(new SoccerPlayer("101", "Alex Song", "M", 27, "West Ham", 4.9, null));
        testCase.tryAddPlayer(new SoccerPlayer("102", "Fabian Delph", "M", 25, "Aston Villa", 4.8, null));
        testCase.tryAddPlayer(new SoccerPlayer("103", "Aiden McGeady", "M", 29, "Everton", 5.3, null));
        testCase.tryAddPlayer(new SoccerPlayer("104", "Jonjo Shelvey", "M", 23, "Swansea", 5.2, null));
        
        assertFalse("Player was added but shouldn't have been", testCase.tryAddPlayer(new SoccerPlayer("105", "testBreak", "M", 100, "Fake Team", 10, null)));
    }
    
    @Test
    public void testGetNumberOfPlayersAtPosition() {
        SoccerTeam testCase = new SoccerTeam(TEAM_NAME, testUser);
        testCase.tryAddPlayer(new SoccerPlayer("100", "Alex Oxlade-Chamberlain", "M", 21, "Arsenal", 6.2, null));
        assertTrue("Amount of midfielders isn't correct", testCase.getNumberOfPlayersAtPosition("M") == 1);
        testCase.tryAddPlayer(new SoccerPlayer("101", "Alex Song", "M", 27, "West Ham", 4.9, null));
        assertTrue("Amount of midfielders isn't correct", testCase.getNumberOfPlayersAtPosition("M") == 2);
        testCase.removePlayer("101");
        assertTrue("Amount of midfielders isn't correct", testCase.getNumberOfPlayersAtPosition("M") == 1);
    }
    
    @Test
    public void testSave() throws IOException {
        User user = new User("testUser", "1", "test.user@email.com", "0871234567", "password","testTeamUnited");
        AbstractTeam testCase = TeamFactory.makeTeam("soccer", TEAM_FILE, user);
        
        testCase.setTeamName("testSave");
        assertTrue("File testSave was not saved" ,testCase.save(testCase.getTeamName()));
        String a = FileUtils.readFileToString(new File(FOLDER_PATH));
        String b = FileUtils.readFileToString(new File(TEAM_FILEPATH + "testSave"));
        
        assertTrue("Files do not match", a.equals(b));
    }
    
    @AfterClass
    public static void tearDown() {
        FileUtils.deleteQuietly(new File("testSave.txt"));
    }
}