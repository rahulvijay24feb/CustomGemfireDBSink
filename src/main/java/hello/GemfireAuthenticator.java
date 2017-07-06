package hello;

import java.util.Properties;

import com.gemstone.gemfire.LogWriter;
import com.gemstone.gemfire.distributed.DistributedMember;
import com.gemstone.gemfire.security.AuthInitialize;
import com.gemstone.gemfire.security.AuthenticationFailedException;

public class GemfireAuthenticator implements AuthInitialize {

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	public static GemfireAuthenticator create(){
		return new GemfireAuthenticator();
	}

	@Override
	public Properties getCredentials(Properties arg0, DistributedMember arg1,
			boolean arg2) throws AuthenticationFailedException {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		prop.put("security-username", "cluster_operator");
		prop.put("security-password", "XXXXXX");
		prop.put("security-client-auth-init", "hello.GemfireAuthenticator");
		return prop;
	}

	@Override
	public void init(LogWriter arg0, LogWriter arg1)
			throws AuthenticationFailedException {
		// TODO Auto-generated method stub
		
	}

}
