package interceptor;

public interface ServerReplyInterceptor {
	
	public void preRemoteReply(ServerReplyContext context);
	public void postRemoteReply(ServerReplyContext context);
}
