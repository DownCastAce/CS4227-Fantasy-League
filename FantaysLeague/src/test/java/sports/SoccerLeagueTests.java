package sports;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SoccerLeagueTests {

    private static final String FILE_PATH = "tests/sports/";
    private static final int AMOUNT_OF_TEAMS = 4;
    private SoccerTeam testSoccerTeam = new SoccerTeam("Arsenal", new User("Arsene Wenger", "1")) ;
    
    @Test
    public void testSoccerLeague() {
        SoccerLeague testCase = new SoccerLeague("Premier League", testSoccerTeam, new User("Arsene Wenger", "123456789"));
        ArrayList<AbstractTeam> teamsToCheck = testCase.getLeagueTeams();
        assertTrue("League Names do not match - " + testCase.getLeagueName() + " : " + "Premier League", testCase.getLeagueName().equals("Premier League"));
        assertTrue("Team Names do not match - " + teamsToCheck.get(0).getTeamName() + " : " + "Arsenal", teamsToCheck.get(0).getTeamName().equals("Arsenal"));
        assertTrue("User Names do not match - " + testCase.getOwnerId() + " : " + "123456789", testCase.getOwnerId().equals("123456789"));
    }
    
    @Test
    public void testRankOrderOfTeams() {
        SoccerLeague testCase = new SoccerLeague(FILE_PATH + "testLeagueFile");
        testCase.rankOrderOfTeams();
        String[] teamsCompare = {"Arsenal","Chelsea","Liverpool","Manchester United"};
        ArrayList<AbstractTeam> teamsToCheck = testCase.getLeagueTeams();
        for(int i = 0; i < teamsCompare.length; i++) {
        assertTrue("Teams Names do not match - " + teamsToCheck.get(i).getTeamName() + " : " + teamsCompare[i], teamsToCheck.get(i).getTeamName().equals(teamsCompare[i]));
        }
    }
    
    @Test
    public void testRemoveTeamFromLeague() {
        SoccerLeague testCase = new SoccerLeague(FILE_PATH + "testLeagueFile");
        testCase.rankOrderOfTeams();
        ArrayList<AbstractTeam> teamsToCheck = testCase.getLeagueTeams();
        assertTrue("The amount of teams does not match - " + AMOUNT_OF_TEAMS + " : " + teamsToCheck.size(), AMOUNT_OF_TEAMS == teamsToCheck.size());
        testCase.removeTeamFromLeague(new SoccerTeam("Liverpool", new User("Brendan Rogers", "123")));
        teamsToCheck = testCase.getLeagueTeams();
        assertTrue("The amount of teams does not match - " + (AMOUNT_OF_TEAMS-1) + " : " + teamsToCheck.size(), (AMOUNT_OF_TEAMS-1) == teamsToCheck.size());
    }
}