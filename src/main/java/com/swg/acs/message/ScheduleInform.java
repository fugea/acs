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
public class ScheduleInform extends CwmpMessage{
	private static final long serialVersionUID = 2947602738711470518L;
	
	private String commandKey;
	private int delaySeconds;

	public ScheduleInform() {
		super("ScheduleInform");
	}
	
	public ScheduleInform(String commandKey,int delaySeconds) {
		this();
		this.commandKey=commandKey;
		this.delaySeconds=delaySeconds;
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("CommandKey")).setValue(commandKey);
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("DelaySeconds")).setValue(String.valueOf(delaySeconds));
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		
	}

	public String getCommandKey() {
		return commandKey;
	}

	public int getDelaySeconds() {
		return delaySeconds;
	}
	
	

}
