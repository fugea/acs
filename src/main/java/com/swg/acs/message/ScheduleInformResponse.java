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
public class ScheduleInformResponse extends CwmpMessage{
	private static final long serialVersionUID = 1L;
	
	public ScheduleInformResponse() {
		super("ScheduleInformResponse");
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
	}

}
