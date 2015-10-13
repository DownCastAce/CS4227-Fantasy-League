package sports;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class SoccerPlayerTests {

    private static final String GOALS = "goals";
    private static final String ERROR_MESSAGE = " is incorrect : ";
    private static final String SEPERATOR = " : ";
    private static final double PLAYER_VALUE = 5.5;
    private static final String PLAYER_CLUB = "Arsenal";
    private static final int PLAYER_AGE = 27;
    private static final String FORWARD = "F";
    private static final String PLAYER_NAME = "Lord Bendtner";
    private static final String PLAYER_ID = "1";

    @Test
    public void testSoccerPlayer() {
        HashMap<String, Integer> stats = new HashMap<>();
        stats.put(GOALS, 3);
        SoccerPlayer testCase = new SoccerPlayer(PLAYER_ID, PLAYER_NAME, FORWARD, PLAYER_AGE, PLAYER_CLUB, PLAYER_VALUE, stats);
        
        assertTrue("Id" + ERROR_MESSAGE + PLAYER_ID + SEPERATOR + testCase.getID(), PLAYER_ID.equals(testCase.getID()));
        assertTrue("Player name" + ERROR_MESSAGE + PLAYER_NAME + SEPERATOR + testCase.getName(), PLAYER_NAME.equals(testCase.getName()));
        assertTrue("Position" + ERROR_MESSAGE + FORWARD + SEPERATOR + testCase.getPosition(), FORWARD.equals(testCase.getPosition()));
        assertTrue("Player age" + ERROR_MESSAGE + PLAYER_AGE + SEPERATOR + testCase.getAge(), PLAYER_AGE == testCase.getAge());
        assertTrue("Player club" + ERROR_MESSAGE + PLAYER_CLUB + SEPERATOR + testCase.getRealTeam(), PLAYER_CLUB.equals(testCase.getRealTeam()));
        assertTrue("Player value" + ERROR_MESSAGE + PLAYER_VALUE + SEPERATOR + testCase.getValue(), PLAYER_VALUE == testCase.getValue());
        assertTrue("Goal amount" + ERROR_MESSAGE + 3 + SEPERATOR + stats.get(GOALS), stats.get(GOALS) == testCase.getStats().get(GOALS));
    }
}