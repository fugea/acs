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
public class DeleteObject extends CwmpMessage {

	private static final long serialVersionUID = -754615439983945263L;
	
	private String parameterKey;
	private String objectName;
	
	public DeleteObject() {
		super("DeleteObject");
	}
	
	public DeleteObject(String parameterKey,String objectName){
		this();
		this.parameterKey=parameterKey;
		this.objectName=objectName;
	}

	/* (non-Javadoc)
	 * @see com.swg.acs.Message#configureBody(com.swg.acs.MessageBody, com.swg.acs.message.cwmp.ArgumentFactory)
	 */
	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("ParameterKey")).setValue(parameterKey);
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("ObjectName")).setValue(objectName);

	}

	/* (non-Javadoc)
	 * @see com.swg.acs.Message#configureParse(com.swg.acs.MessageBody)
	 */
	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		
	}
	
	public String getObjectName() {
		return objectName;
	}
	
	public String getParameterKey() {
		return parameterKey;
	}


}
