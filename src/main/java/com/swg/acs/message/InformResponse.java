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
public class InformResponse extends CwmpMessage {

	private static final long serialVersionUID = 1511751735849616564L;
	
	private int maxEnvelopes;
	
	public InformResponse() {
		super("InformResponse");
	}
	
	public void setMaxEnvelopes(int maxEnvelopes) {
		this.maxEnvelopes = maxEnvelopes;
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("MaxEnvelopes")).setValue(String.valueOf(maxEnvelopes));
	}

	
	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
	}

}
