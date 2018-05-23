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
public class SetParameterAttributesResponse extends CwmpMessage{
	private static final long serialVersionUID = 242138233469359501L;
	
	public SetParameterAttributesResponse() {
		super("SetParameterAttributesResponse");
	}
	


	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		// TODO Auto-generated method stub
		
	}

}
