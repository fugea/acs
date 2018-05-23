/**
 * 
 */
package com.swg.acs.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.swg.acs.Argument;
import com.swg.acs.MessageArgument;
import com.swg.acs.MessageBody;
import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessage;

/**
 * @author satriaprayoga
 *
 */
public class GetParameterValues extends CwmpMessage{

	private static final long serialVersionUID = -909174078440244591L;
	
	private List<String> parameterNames;
	private String cwmpId ;
	public String getCwmpId(){
		return cwmpId;
	}

	public GetParameterValues() {
		super("GetParameterValues");
		parameterNames=new ArrayList<String>();
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		MessageArgument paramArg=bodyPart.addMessageArgument(argumentFactory.createMessageArgument("ParameterNames"));
		paramArg.setAttribute(SOAP_ARRAY_TYPE, "xsd:string["+parameterNames.size()+"]");
		for(String s:parameterNames){
			Argument param=paramArg.addMessageArgument(argumentFactory.createMessageArgument("string"));
			param.setValue(s);
		}
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		this.cwmpId = cwmpId;
		MessageArgument paramArg=messageBody.getChild("ParameterNames");
		Iterator<MessageArgument> params=paramArg.childIterator();
		parameterNames=new ArrayList<String>();
		while(params.hasNext()){
			MessageArgument arg=params.next();
			parameterNames.add(arg.getValue());
		}
	}
	
	public List<String> getParameterNames() {
		return parameterNames;
	}
	
	public void addParameterNames(String parameterName){
		parameterNames.add(parameterName);
	}

}
