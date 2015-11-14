package interceptor;

public class TestInterceptor implements ServerReplyInterceptor{
		
	@Override
	public void preRemoteReply(ServerReplyContext context) {
		System.out.println("Pre-Update");
	}

	@Override
	public void postRemoteReply(ServerReplyContext context) {
		System.out.println("Post-Update");		
	}

}
