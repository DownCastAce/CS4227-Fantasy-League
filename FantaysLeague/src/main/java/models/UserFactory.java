package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class UserFactory {
	
	public static User newUser(String userName, String password){
		User user = new User(userName, password);
		user.save();
		return user;
	}
	
	public static User load(String userName){
		try{
			String path = "resources/users/" + userName;
			BufferedReader userFile = new BufferedReader(new FileReader(path));
			String line = userFile.readLine();
			userFile.close();
			//UserInfo = {NAME, PASSWORD[, TEAM]}
			String userInfo[] = line.split(",");
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