package models;

public interface IUser {
    String getUserName();
    String getPassword();
    String getTeamName();
    
    void setTeamName(String teamName);
    void setUserName(String userName);
    void setPassword(String password);
    boolean save();
}