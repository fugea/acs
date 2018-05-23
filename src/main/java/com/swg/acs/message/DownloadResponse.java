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
public class DownloadResponse extends CwmpMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String startTime;
	private String completeTime;
	private int status;
	private String cwmpId;
	
	public DownloadResponse() {
		super("DownloadResponse");
	}

	/* (non-Javadoc)
	 * @see com.swg.acs.Message#configureBody(com.swg.acs.MessageBody, com.swg.acs.message.cwmp.ArgumentFactory)
	 */
	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
	}

	/* (non-Javadoc)
	 * @see com.swg.acs.Message#configureParse(com.swg.acs.MessageBody)
	 */
	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		startTime=messageBody.getChild("StartTime").getValue();
		completeTime=messageBody.getChild("CompleteTime").getValue();
		status=Integer.parseInt(messageBody.getChild("Status").getValue());
		this.cwmpId = cwmpId;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getCwmpId(){
		return cwmpId;
	}
	public String getCompleteTime() {
		return completeTime;
	}

	public int getStatus() {
		return status;
	}

	
}
