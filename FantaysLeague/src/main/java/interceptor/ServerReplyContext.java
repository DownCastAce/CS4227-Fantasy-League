package interceptor;

import java.util.Date;

public class ServerReplyContext {
	
	private Date startTime;
	
	public ServerReplyContext(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getStartTime(){
		return startTime;
	}
}
