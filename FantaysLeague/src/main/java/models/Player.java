package models;

public class Player{
    protected String name;
    protected String ID;
    protected String position;
    protected boolean playable;
    protected double value;

    public Player(String anID, String aName, String aPosition, double aValue) {
        this.ID = anID;
        this.name = aName;
        this.position = aPosition;
        this.value = aValue;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public boolean isPlayable() {
        return playable;
    }

    public double getValue() {
        return value;
    }

    public void setCanPlay(boolean canPlay) {
        this.playable = canPlay;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
