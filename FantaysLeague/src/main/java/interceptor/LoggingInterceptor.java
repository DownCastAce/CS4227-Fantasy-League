package interceptor;

import java.util.Date;

public class LoggingInterceptor implements ServerReplyInterceptor {

	@Override
	public void preRemoteReply(ServerReplyContext context) {
		// TODO Auto-generated method stub
		System.out.println("Stats updated at time - " + context.getStartTime());
	}

	@Override
	public void postRemoteReply(ServerReplyContext context) {
		// TODO Auto-generated method stub
		Date endTime = new Date();
		long diff = endTime.getTime() - context.getStartTime().getTime();

		System.out.println("Post - Took " + diff + " miliseconds to complete.");
	}

}
