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
public class UploadResponse extends CwmpMessage{

	private static final long serialVersionUID = 4948484986577034657L;

	private String startTime;
	private String completeTime;
	private int status;
	private String cwmpId;
	public String getCwmpId(){
		return cwmpId;
	}

	public UploadResponse() {
		super("UploadResponse");
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {

	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		this.cwmpId = cwmpId;
		startTime=messageBody.getChild("StartTime").getValue();
		completeTime=messageBody.getChild("CompleteTime").getValue();
		status=Integer.parseInt(messageBody.getChild("Status").getValue());
	}

	public String getStartTime() {
		return startTime;
	}

	public String getCompleteTime() {
		return completeTime;
	}

	public int getStatus() {
		return status;
	}
	
	

}
