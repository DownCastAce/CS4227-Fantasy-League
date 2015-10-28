package models;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;


public class User implements IUser {
	/*
	 * Do not instantiate using the constructors
	 * Use the Factory methods in UserFactory
	 */
    private static final String FILEPATH = "resources/users/";
    private String userName, password, teamName;
    
    public User(String userName, String password) {
        this.userName = userName;
    	this.password = password;
    }
    
    public User(String userName, String password, String teamName) {
    	this.userName = userName;
    	this.password = password;
    	this.teamName = teamName;
    }
       
    @Override
    public boolean save(){
    	//return false if failure to write.
    	try{
	    	File userFile = new File(FILEPATH + this.userName);
	    	String userInfo = userName + "," + password + ",";
	    	if(teamName != null)
	    		userInfo += teamName;
	    	FileUtils.write(userFile, userInfo , false);
	    	return true;
    	}
    	catch(IOException e){
    		return false;
    	}
    }
    
    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
}