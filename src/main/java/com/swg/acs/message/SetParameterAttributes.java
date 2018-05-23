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
import com.swg.acs.message.struct.SetParameterAttributeStruct;

/**
 * @author satriaprayoga
 *
 */
public class SetParameterAttributes extends CwmpMessage{

	private static final long serialVersionUID = -1788299451507503606L;
	
	private List<SetParameterAttributeStruct> parameterAttributeStructs;
	
	public SetParameterAttributes() {
		super("SetParameterAttributes");
		parameterAttributeStructs=new ArrayList<SetParameterAttributeStruct>();
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		MessageArgument argument=bodyPart.addMessageArgument(argumentFactory.createMessageArgument("ParameterList"));
		argument.setAttribute(SOAP_ARRAY_TYPE, "cwmp:SetParameterAttributesStruct["+parameterAttributeStructs.size()+"]");
		for(SetParameterAttributeStruct struct:parameterAttributeStructs){
			MessageArgument structArg=argument.addMessageArgument(argumentFactory.createMessageArgument("SetParameterAttributesStruct"));
			structArg.addMessageArgument(argumentFactory.createMessageArgument("Name")).setValue(struct.getName());
			structArg.addMessageArgument(argumentFactory.createMessageArgument("NotificationChange")).setValue(struct.isNotificationChange()?"1":"0");
			structArg.addMessageArgument(argumentFactory.createMessageArgument("Notification")).setValue(String.valueOf(struct.getNotification()));
			structArg.addMessageArgument(argumentFactory.createMessageArgument("AccessListChange")).setValue(struct.isAccessListChange()?"1":"0");
			MessageArgument acl=argument.addMessageArgument(argumentFactory.createMessageArgument("AccessList"));
			List<String> acls=struct.getAccessList();
			acl.setAttribute(XSD_STRING, "xsd:string["+acls.size()+"]");
			for(String s:acls){
				acl.addMessageArgument(argumentFactory.createMessageArgument("string")).setValue(s);
				acl.setAttribute(XSI_TYPE, XSD_STRING);
			}
		}
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
	}
	
	public void addParameterAttributeStruct(SetParameterAttributeStruct struct){
		parameterAttributeStructs.add(struct);
	}

}
