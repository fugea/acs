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
public class GetOptions extends CwmpMessage{
	private static final long serialVersionUID = 1452457475351220634L;
	
	private String optionName;
	
	public GetOptions() {
		super("GetOptions");
	}
	
	public GetOptions(String optionName){
		this();
		this.optionName=optionName;
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("OptionName")).setValue(optionName);
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		
	}
	
	public String getOptionName() {
		return optionName;
	}

}
