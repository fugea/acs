/**
 * 
 */
package com.swg.acs.cpe;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author satriaprayoga
 *
 */
public class SimpleCpeConnector implements CpeConnector{

	private URL cpeUrl;
	
	private boolean connected=false;
	
	public SimpleCpeConnector(URL cpeUrl) {
		this.cpeUrl=cpeUrl;
	}
	
	@Override
	public void requestConnection(){
		HttpURLConnection connection=null;
		try {
			connection=(HttpURLConnection)cpeUrl.openConnection();
			connection.setReadTimeout(4000);
			int code=connection.getResponseCode();
			if(code==200 || code==204)
				connected=true;
		} catch (IOException e) {
			connected=false;
			throw new RuntimeException("failed to connect to "+cpeUrl.toString());
		}
	}
	
	public synchronized URL getCpeUrl() {
		if(!connected)
			return null;
		return cpeUrl;
	}
	
	public boolean isConnected() {
		return connected;
	}

}
