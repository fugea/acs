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
public class AddObject extends CwmpMessage{

	private static final long serialVersionUID = -5162063931057599791L;
	
	private String parameterKey;
	private String objectName;
	
	public AddObject() {
		super("AddObject");
	}
	
	public AddObject(String parameterKey,String objectName) {
		this();
		this.parameterKey=parameterKey;
		this.objectName=objectName;
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("ParameterKey")).setValue(parameterKey);
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("ObjectName")).setValue(objectName);
	}

	@Override
	protected void configureParse(MessageBody messageBody,String id) {
		
	}
	
	public String getObjectName() {
		return objectName;
	}
	
	public String getParameterKey() {
		return parameterKey;
	}

	
}
