/**
 * 
 */
package com.swg.acs.cpe;

import java.net.URL;

/**
 * @author satriaprayoga
 *
 */
public interface CpeConnector {

	void requestConnection();
	
	URL getCpeUrl();
}
