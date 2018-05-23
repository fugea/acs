/**
 * 
 */
package com.swg.acs.message;

import com.swg.acs.MessageArgument;
import com.swg.acs.MessageBody;
import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessage;

/**
 * @author satriaprayoga
 *
 */
public class TransferComplete extends CwmpMessage{

	private static final long serialVersionUID = -6696457564086003088L;
	
	private String commandKey;
	private String startTime;
	private String completeTime;
	private String faultCode;
	private String faultString;
	private String cwmpId;
	public String getCwmpId(){
		return cwmpId;
	}
	public TransferComplete() {
		super("TransferComplete");
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		this.cwmpId = cwmpId;
		commandKey=messageBody.getChild("CommandKey").getValue();
		startTime=messageBody.getChild("StartTime").getValue();
		completeTime=messageBody.getChild("CompleteTime").getValue();
		MessageArgument faultArg=messageBody.getChild("FaultStruct");
		if(faultArg!=null){
			faultCode=faultArg.getChild("FaultCode").getValue();
			faultString=faultArg.getChild("FaultString").getValue();
		}else{
			faultCode="0";
			faultString=null;
		}
	}

	public String getCommandKey() {
		return commandKey;
	}

	public void setCommandKey(String commandKey) {
		this.commandKey = commandKey;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	public String getFaultString() {
		return faultString;
	}

	public void setFaultString(String faultString) {
		this.faultString = faultString;
	}
	
	

}
