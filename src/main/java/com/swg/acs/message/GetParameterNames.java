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
public class GetParameterNames extends CwmpMessage {

	private static final long serialVersionUID = 3281203752819168379L;

	private String parameterPath = ".";

	private boolean nextLevel;

	public GetParameterNames(String parameterPath, boolean nextLevel) {
		super("GetParameterNames");
		this.parameterPath = parameterPath;
		this.nextLevel = nextLevel;
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		bodyPart.addMessageArgument(
				argumentFactory.createMessageArgument("ParameterPath"))
				.setValue(parameterPath);
		bodyPart.addMessageArgument(
				argumentFactory.createMessageArgument("NextLevel")).setValue(
				nextLevel ? "1" : "0");

	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {

	}

	public String getParameterPath() {
		return parameterPath;
	}

	public boolean isNextLevel() {
		return nextLevel;
	}

}
