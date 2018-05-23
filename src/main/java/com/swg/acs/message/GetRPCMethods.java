/**
 * 
 */
package com.swg.acs.message;

import com.swg.acs.MessageBody;
import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessage;

/**
 * @author satriaprayoga
 *
 */
public class GetRPCMethods extends CwmpMessage{

	private static final long serialVersionUID = -295221626818208195L;
	
	public GetRPCMethods() {
		super("GetRPCMethods");
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		
	}

}
