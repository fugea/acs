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
public class TransferCompleteResponse extends CwmpMessage{

	private static final long serialVersionUID = -5617584079717915222L;

	public TransferCompleteResponse() {
		super("TransferCompleteResponse");
	}
	
	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
	
	}

}
