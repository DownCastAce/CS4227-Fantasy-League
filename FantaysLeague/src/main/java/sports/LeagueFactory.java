package sports;

public class LeagueFactory {

    public static AbstractLeague makeLeague(String sport, String ID) {
        if (sport.equalsIgnoreCase("soccer"))
            return new SoccerLeague(ID);
        return null;
    }

    public static AbstractLeague makeLeague(String sport, String name, AbstractTeam creatorteam, User leaguecreator) {
        if (sport.equalsIgnoreCase("soccer"))
            return new SoccerLeague(name, creatorteam, leaguecreator);
        return null;
    }
}