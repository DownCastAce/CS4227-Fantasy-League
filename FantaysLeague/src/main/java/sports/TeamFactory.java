package sports;

import java.io.*;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class TeamFactory {

    public static AbstractTeam makeTeam(String sport, String name, User user) {
        if (sport.equalsIgnoreCase("soccer")) {
            SoccerRoster rost = new SoccerRoster("resources/players.soccer");
            double moneySpent = 0.0;
            ArrayList<AbstractPlayer> selectPlayers = new ArrayList<AbstractPlayer>();
            try {
                String teamPlayers = FileUtils.readFileToString(new File("resources/teams/" + name));
                String p[] = teamPlayers.split(",");

                ArrayList<SoccerPlayer> list = rost.getAllPlayers();
                for (int i = 0; i < list.size(); i++)
                    for (int j = 0; j < p.length; j++)
                        if (list.get(i).getID().equals(p[j])) {
                            selectPlayers.add(list.get(i));
                            moneySpent += (list.get(i).getValue());
                        }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("error reading in team from file - " + name + "\n" + e);
            }
            AbstractTeam team = new SoccerTeam(name, user, selectPlayers);
            team.decrementBudget(moneySpent);
            return team;
        }
        return null;
    }

    public static AbstractTeam makeEmptyTeam(String sport, String name, User user) {
        if (sport.equalsIgnoreCase("soccer")) {
            AbstractTeam team = new SoccerTeam(name, user);
            return team;
        }
        return null;
    }
}