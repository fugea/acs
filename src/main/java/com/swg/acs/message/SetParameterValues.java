/**
 * 
 */
package com.swg.acs.message;

import java.util.ArrayList;
import java.util.List;

import com.swg.acs.MessageArgument;
import com.swg.acs.MessageBody;
import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessage;
import com.swg.acs.message.struct.ParameterValueStruct;

/**
 * @author satriaprayoga
 *
 */
public class SetParameterValues extends CwmpMessage{
	private static final long serialVersionUID = -5095995628910787559L;
	
	private String parameterKey = "";
	private List<ParameterValueStruct> parameterValueStructs;

	public SetParameterValues() {
		super("SetParameterValues");
		parameterValueStructs=new ArrayList<ParameterValueStruct>();
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		MessageArgument argument=bodyPart.addMessageArgument(argumentFactory.createMessageArgument("ParameterList"));
		argument.setAttribute(SOAP_ARRAY_TYPE, "cwmp:ParameterValueStruct["+parameterValueStructs.size()+"]");
		for(ParameterValueStruct pvs:parameterValueStructs){
			MessageArgument arg=argument.addMessageArgument(argumentFactory.createMessageArgument("ParameterValueStruct"));
			arg.addMessageArgument(argumentFactory.createMessageArgument("Name")).setValue(pvs.getName());
			arg.addMessageArgument(argumentFactory.createMessageArgument("Value")).setValue(pvs.getValue());
		}
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("ParameterKey")).setValue(parameterKey);
		
	}
	
	public void setParameterKey(String parameterKey){
        if (parameterKey == null) {
            parameterKey = "";
        }
        this.parameterKey = parameterKey;
	}

	public void addParameterValueStruct(ParameterValueStruct struct){
		parameterValueStructs.add(struct);
	}

}
