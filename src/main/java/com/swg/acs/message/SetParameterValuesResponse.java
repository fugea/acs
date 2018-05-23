package com.swg.acs.message;

import com.swg.acs.MessageBody;
import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessage;

/**
 * 
 * @author satriaprayoga
 *
 */
public class SetParameterValuesResponse extends CwmpMessage{
	private static final long serialVersionUID = 5645504722332986246L;
	
	private int status;
	
	private String cwmpId ;
	
	public SetParameterValuesResponse() {
		super("SetParameterValuesResponse");
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		
	}

	@Override
	protected void configureParse(MessageBody messageBody, String id) {
		status=Integer.valueOf(messageBody.getChild("Status").getValue());
		this.cwmpId = id;
	}
	
	public String getCwmpId(){
		return cwmpId;
	}
	
	public int getStatus() {
		return status;
	}

}
