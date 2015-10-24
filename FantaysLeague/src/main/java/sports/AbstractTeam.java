package sports;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

public abstract class AbstractTeam {
	private static final String FILEPATH = "resources/teams/";
    protected User owner;
    protected String teamName;
    protected double budget;
    private int amountOfPlayersAllowed;
    protected ArrayList<AbstractPlayer> selectPlayers = new ArrayList<>();
    protected HashMap<String, Integer> positions = new HashMap<>();

    public AbstractTeam(String teamname, User owner) {
        teamName = teamname;
        this.owner = owner;
    }

    public User getOwner() {
        return this.owner;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getAmountOfPlayersAllowed() {
        return amountOfPlayersAllowed;
    }

    public void setAmountOfPlayersAllowed(int amountOfPlayersAllowed) {
        this.amountOfPlayersAllowed = amountOfPlayersAllowed;
    }

    public ArrayList<AbstractPlayer> getPlayerList() {
        return selectPlayers;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void decrementBudget(double a) {
        budget = budget - a;
    }

    public void incrementBudget(String a) {
        for (int i = 0; i < selectPlayers.size(); i++)
            if (selectPlayers.get(i).getID().equals(a))
                budget = budget + selectPlayers.get(i).getValue();
        
    }

    public void removePlayer(String p) {
        for (int i = 0; i < selectPlayers.size(); i++)
            if (selectPlayers.get(i).getID().equals(p))
                selectPlayers.remove(i);
    }

    public boolean checkBudgetAmount(double amount) {
        if (budget - amount >= 0)
            return true;
        return false;
    }
    
    public abstract int generateTeamPoints();
    
    public boolean save(String teamName) {
        boolean saved = false;

        System.out.println(this.teamName);
        if (selectPlayers.size() == 15) {
            try {
                String output = "";
                for (int i = 0; i < selectPlayers.size(); i++) {
                    if (i != 14)
                        output += selectPlayers.get(i).getID() + ",";
                    else
                        output += selectPlayers.get(i).getID();
                }
                FileUtils.write(new File(FILEPATH + teamName), output);
                saved = true;
            }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("error writing in team from file - " + teamName + "\n" + e);
            }
        }
        return saved;
    }
}