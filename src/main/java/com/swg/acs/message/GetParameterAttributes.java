package com.swg.acs.message;

import java.util.ArrayList;
import java.util.List;

import com.swg.acs.Argument;
import com.swg.acs.MessageArgument;
import com.swg.acs.MessageBody;
import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessage;

/**
 * 
 * @author satriaprayoga
 *
 */
public class GetParameterAttributes extends CwmpMessage{
	
	private static final long serialVersionUID = 4932974910608969152L;
	
	private List<String> parameterNames;
	
	public GetParameterAttributes() {
		super("GetParameterAttributes");
		parameterNames=new ArrayList<String>();
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		MessageArgument paramArg=bodyPart.addMessageArgument(argumentFactory.createMessageArgument("ParameterNames"));
		paramArg.setAttribute(SOAP_ARRAY_TYPE, "xsd:string["+parameterNames.size()+"]");
		for(String s:parameterNames){
			Argument param=paramArg.addMessageArgument(bodyPart.addMessageArgument(argumentFactory.createMessageArgument("string")));
			param.setValue(s);
		}
		
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		
	}
	
	public List<String> getParameterNames() {
		return parameterNames;
	}
	
	public void addParameterName(String param){
		parameterNames.add(param);
	}

}
