package com.swg.acs.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.swg.acs.MessageArgument;
import com.swg.acs.MessageBody;
import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessage;
import com.swg.acs.message.struct.ParameterAttributeStruct;

public class GetParameterAttributesResponse extends CwmpMessage{

	private static final long serialVersionUID = 1433457408934635358L;
	
	private List<ParameterAttributeStruct> parameterAttributeStructs;
	private String cwmpId;
	public String getCwmpId(){
		return cwmpId;
	}
	
	public GetParameterAttributesResponse() {
		super("GetParameterAttributeResponse");
		parameterAttributeStructs=new ArrayList<ParameterAttributeStruct>();
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		this.cwmpId =cwmpId;
		MessageArgument argument=messageBody.getChild("ParameterList");
		Iterator<MessageArgument> iterator=argument.childIterator();
		while(iterator.hasNext()){
			MessageArgument pa=iterator.next();
			String name=pa.getChild("Name").getValue();
			int notification=Integer.parseInt(pa.getChild("Notification").getValue());
			ParameterAttributeStruct struct=new ParameterAttributeStruct();
			struct.setName(name);
			struct.setNotification(notification);
			MessageArgument acl=pa.getChild("AccessList");
			Iterator<MessageArgument> iterator2=acl.childIterator();
			while(iterator2.hasNext()){
				String sacl=iterator2.next().getValue();
				struct.addAccessList(sacl);
			}
		}
	}
	
	public List<ParameterAttributeStruct> getParameterAttributeStructs() {
		return parameterAttributeStructs;
	}

}
