package models;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class UserFactory {
	
	private static final String USERS_FILEPATH = "resources/users/";

	public User newUser(String userName, String password){
		User user = new User(userName, password);
		user.save();
		return user;
	}
	
	public User load(String userName){
		try{
			//UserInfo = {NAME, PASSWORD, TEAM}
			String[] userInfo = FileUtils.readFileToString(new File(USERS_FILEPATH + userName)).split(",");
			if(userInfo.length == 2)
				//No team created
				return new User(userInfo[0], userInfo[1]);
			//team already created
			return new User(userInfo[0], userInfo[1], userInfo[2]);
		}
		catch(IOException e){
			System.out.println("File does not exist");
		}
		return null;
	}
}