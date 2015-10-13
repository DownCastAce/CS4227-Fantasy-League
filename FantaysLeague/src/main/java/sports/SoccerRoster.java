package sports;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class SoccerRoster {

    private ArrayList<SoccerPlayer> allPlayers = new ArrayList<SoccerPlayer>();

    public SoccerRoster(String fileName) {
        try {
            List<String> players = FileUtils.readLines(new File(fileName));
            for (String input : players) {
                String[] player = input.split(",");
                allPlayers.add((SoccerPlayer) PlayerFactory.makePlayer("soccer", player[0], player[2], player[1], (Integer.parseInt(player[5])), player[3], (Double.parseDouble(player[4]))));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not read file : " + fileName);
        }
    }

    public ArrayList<SoccerPlayer> getAllPlayers() {
        return allPlayers;
    }

    public ArrayList<SoccerPlayer> getListOfGks() {
        ArrayList<SoccerPlayer> listOfGks = new ArrayList<SoccerPlayer>();
        for (int i = 0; i < allPlayers.size(); i++) {
            if ((allPlayers.get(i).getPosition()).equals("G"))
                listOfGks.add(allPlayers.get(i));
        }
        return listOfGks;
    }

    public ArrayList<SoccerPlayer> getListOfDs() {
        ArrayList<SoccerPlayer> listOfDs = new ArrayList<SoccerPlayer>();
        for (int i = 0; i < allPlayers.size(); i++) {
            if ((allPlayers.get(i).getPosition()).equals("D"))
                listOfDs.add(allPlayers.get(i));
        }
        return listOfDs;
    }

    public ArrayList<SoccerPlayer> getListOfMs() {
        ArrayList<SoccerPlayer> listOfMs = new ArrayList<SoccerPlayer>();
        for (int i = 0; i < allPlayers.size(); i++) {
            if ((allPlayers.get(i).getPosition()).equals("M"))
                listOfMs.add(allPlayers.get(i));
        }
        return listOfMs;
    }

    public ArrayList<SoccerPlayer> getListOfFs() {
        ArrayList<SoccerPlayer> listOfFs = new ArrayList<SoccerPlayer>();
        for (int i = 0; i < allPlayers.size(); i++) {
            if ((allPlayers.get(i).getPosition()).equals("F"))
                listOfFs.add(allPlayers.get(i));
        }
        return listOfFs;
    }
}