package sports;

import java.util.HashMap;

public abstract class AbstractPlayer {
    protected String name;
    protected String ID;
    protected String position;
    protected int age;
    protected String realTeam;
    protected boolean playable;
    protected double value;
    protected HashMap<String, Integer> stats;

    public AbstractPlayer(String anID, String aName, String aPosition, int anAge, String aTeam, double aValue, HashMap<String,Integer> playerStats) {
        this.ID = anID;
        this.name = aName;
        this.position = aPosition;
        this.age = anAge;
        this.realTeam = aTeam;
        this.value = aValue;
        this.stats = playerStats;
    }

    public abstract HashMap<String, Integer> getThisWeeksStats();

    public abstract HashMap<String, Integer> getSeasonStats();

    /*
     * !!!!GETTERS AND SETTERS!!!!
     */
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public String getRealTeam() {
        return realTeam;
    }

    public boolean isPlayable() {
        return playable;
    }

    public double getValue() {
        return value;
    }

    public HashMap<String, Integer> getStats() {
        return stats;
    }

    public void setCanPlay(boolean canPlay) {
        this.playable = canPlay;
    }

    public void setValue(int value) {
        this.value = value;
    }

}