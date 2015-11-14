package interceptor;

import java.util.List;
import java.util.ArrayList;

public class ServerReplyDispatcher implements ServerReplyInterceptor{
	List<ServerReplyInterceptor> interceptors = new ArrayList<ServerReplyInterceptor>();
		
	public void register(ServerReplyInterceptor interceptor){
		interceptors.add(interceptor);
	}

	@Override
	public void preRemoteReply(ServerReplyContext context) {
		for(ServerReplyInterceptor interceptor: interceptors){
			interceptor.preRemoteReply(context);
		}
	}

	@Override
	public void postRemoteReply(ServerReplyContext context) {
		for(ServerReplyInterceptor interceptor: interceptors){
			interceptor.postRemoteReply(context);
		}
	}

}
