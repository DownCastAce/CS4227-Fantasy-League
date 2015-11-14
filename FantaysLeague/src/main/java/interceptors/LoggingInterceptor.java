package interceptors;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import models.User;

public class LoggingInterceptor {
	
	private static SimpleDateFormat time;
	private static SimpleDateFormat date;
	final private static String FILEPATH = "resources/chatlogs/";
	
	public static void LogMessage(User user, String msg){
		time =  new SimpleDateFormat("HH:mm:ss");
		date = new SimpleDateFormat("dd-MM-yyyy");
		
		String sDate = date.format(new Date());
		String sTime = time.format(new Date());
		
		try{
			
			File userFile = new File(FILEPATH + "Chat Log - " + sDate);
			String output = sDate + " " + sTime + " " + user.getUserName() + ": " + msg;
			
			FileUtils.write(userFile, output , true);
		}
		catch(IOException e){
			
		}
		
	}

}
