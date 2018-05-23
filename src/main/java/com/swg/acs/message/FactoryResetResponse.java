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
public class FactoryResetResponse extends CwmpMessage{
	private static final long serialVersionUID = 3783139474406392517L;
	private String cwmpId;
	
	public FactoryResetResponse() {
		super("FactoryResetResponse");
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		this.cwmpId = cwmpId;
	}
	public String getCwmpId(){
		return cwmpId;
	}
}
