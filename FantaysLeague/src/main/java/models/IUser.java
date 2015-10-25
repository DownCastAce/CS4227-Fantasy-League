package models;

public interface IUser {
    String getUserName();
    String getPassword();
    
    void setUserName(String userName);
    void setPassword(String password);
    boolean save();
}