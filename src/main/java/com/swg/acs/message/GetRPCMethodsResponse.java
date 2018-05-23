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
import com.swg.acs.message.struct.RPCMethodStruct;
/**
 * @author satriaprayoga
 * 
 */
public class GetRPCMethodsResponse extends CwmpMessage {
	private static final long serialVersionUID = -7537081218409233910L;

	private List<RPCMethodStruct> methods;
	private String cwmpId;
	public String getCwmpId(){
		return cwmpId;
	}

	public GetRPCMethodsResponse() {
		super("GetRPCMethodsResponse");
		methods = new ArrayList<RPCMethodStruct>();
		RPCMethodStruct rpcMethodInform = new RPCMethodStruct();
		rpcMethodInform.setMethod("Inform");
		RPCMethodStruct rpcMethodGetRPCMethods = new RPCMethodStruct();
		rpcMethodGetRPCMethods.setMethod("GetRPCMethods");
		RPCMethodStruct rpcMethodTransferComplete = new RPCMethodStruct();
		rpcMethodTransferComplete.setMethod("TransferComplete");
		methods.add(rpcMethodInform);
		methods.add(rpcMethodGetRPCMethods);
		methods.add(rpcMethodTransferComplete);
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		MessageArgument argument = bodyPart.addMessageArgument(argumentFactory
				.createMessageArgument("MethodList"));
		argument.setAttribute(SOAP_ARRAY_TYPE, XSD_STRING + "["
				+ methods.size() + "]");
		for (RPCMethodStruct s : methods) {
			argument.addMessageArgument(
					argumentFactory.createMessageArgument("string")).setValue(
					s.getMethod());
		}
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		this.cwmpId = cwmpId;
		MessageArgument argument = messageBody.getChild("MethodList");
		Iterator<MessageArgument> args = argument.childIterator();
		methods = new ArrayList<RPCMethodStruct>();
		while (args.hasNext()) {
			Argument a = args.next();
			RPCMethodStruct rpcMethod = new RPCMethodStruct();
			rpcMethod.setMethod(a.getValue());
			methods.add(rpcMethod);
		}
	}

	public List<RPCMethodStruct> getMethods() {
		return methods;
	}

}
