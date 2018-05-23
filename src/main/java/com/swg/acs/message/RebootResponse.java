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
public class RebootResponse extends CwmpMessage{

	private static final long serialVersionUID = 5248453031470727160L;
	private String cwmpId;
	public String getCwmpId(){
		return cwmpId;
	}
	public RebootResponse() {
		super("RebootResponse");
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		this.cwmpId = cwmpId;
	}

}
