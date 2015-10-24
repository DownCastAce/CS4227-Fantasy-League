package sports;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SoccerRosterTests {

    private static final String SEPERATOR = " : ";
    private static final String ERROR_MESSAGE = " count is incorrect : ";
    private static final int NUM_FORWARDS = 8;
    private static final int NUM_MIDFIELDERS = 14;
    private static final int NUM_DEFENDERS = 11;
    private static final int NUM_PLAYERS = 47;
    private static final int NUM_GOALKEEPERS = 14;
    private static final String ROSTER_FILEPATH = "resources/players.soccer";

    @Test
    public void testSoccerRoster() {
        SoccerRoster testCase = new SoccerRoster(ROSTER_FILEPATH);
        assertTrue("Roster" + ERROR_MESSAGE + NUM_PLAYERS + SEPERATOR + testCase.getAllPlayers().size(), testCase.getAllPlayers().size() == NUM_PLAYERS);
    }
    
    @Test
    public void testGetListOfGks() {
        SoccerRoster testCase = new SoccerRoster(ROSTER_FILEPATH);
        ArrayList<SoccerPlayer> listOfGks = testCase.getListOfGks();
        assertTrue("Goalkeepers" + ERROR_MESSAGE + NUM_GOALKEEPERS + SEPERATOR + listOfGks.size(), listOfGks.size() == NUM_GOALKEEPERS);
    }
    
    @Test
    public void testGetListOfDs() {
        SoccerRoster testCase = new SoccerRoster(ROSTER_FILEPATH);
        ArrayList<SoccerPlayer> listOfDs = testCase.getListOfDs();
        assertTrue("Defenders" + ERROR_MESSAGE + NUM_DEFENDERS + SEPERATOR + listOfDs.size(), listOfDs.size() == NUM_DEFENDERS);
    }
    
    @Test
    public void testGetListOfMs() {
        SoccerRoster testCase = new SoccerRoster(ROSTER_FILEPATH);
        ArrayList<SoccerPlayer> listOfMs = testCase.getListOfMs();
        assertTrue("Midfielders" + ERROR_MESSAGE + NUM_MIDFIELDERS + SEPERATOR + listOfMs.size(), listOfMs.size() == NUM_MIDFIELDERS);
    }
    
    @Test
    public void testGetListOfFs() {
        SoccerRoster testCase = new SoccerRoster(ROSTER_FILEPATH);
        ArrayList<SoccerPlayer> listOfFs = testCase.getListOfFs();
        assertTrue("Forwards" + ERROR_MESSAGE + NUM_FORWARDS + SEPERATOR + listOfFs.size(), listOfFs.size() == NUM_FORWARDS);
    }
}