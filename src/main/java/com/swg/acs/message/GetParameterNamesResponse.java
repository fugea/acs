/**
 * 
 */
package com.swg.acs.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.swg.acs.MessageArgument;
import com.swg.acs.MessageBody;
import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessage;
import com.swg.acs.message.struct.ParameterInfoStruct;
/**
 * @author satriaprayoga
 * 
 */
public class GetParameterNamesResponse extends CwmpMessage {

	private static final long serialVersionUID = 1916066322555290513L;

	private List<ParameterInfoStruct> parameterInfoStructs;
	private String cwmpId;
	public String getCwmpId(){
		return cwmpId;
	}

	public GetParameterNamesResponse() {
		super("GetParameterNamesResponse");
		parameterInfoStructs = new ArrayList<ParameterInfoStruct>();
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {

	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		this.cwmpId = cwmpId;
		MessageArgument argument = messageBody.getChild("ParameterList");
		Iterator<MessageArgument> iterator = argument.childIterator();
		while (iterator.hasNext()) {
			MessageArgument pis = iterator.next();
			MessageArgument nameArg = pis.getChild("Name");
			String name = nameArg.getValue();
			MessageArgument writableArg = pis.getChild("Writable");
			boolean writable = false;
			if (writableArg.getValue().trim().equals("true")) {
				writable = true;
			}
			ParameterInfoStruct infoStruct = new ParameterInfoStruct(name, writable);
			parameterInfoStructs.add(infoStruct);
		}
	}

	public List<ParameterInfoStruct> getParameterInfoStructs() {
		return parameterInfoStructs;
	}

}
