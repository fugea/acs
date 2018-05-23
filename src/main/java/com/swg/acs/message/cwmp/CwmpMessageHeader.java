/**
 * 
 */
package com.swg.acs.message.cwmp;

import com.swg.acs.MessageHeader;

/**
 * @author satriaprayoga
 *
 */
public class CwmpMessageHeader extends CwmpArgument implements MessageHeader {
	private static final long serialVersionUID = -2405180007676437261L;

	private boolean mustUnderstand = true;

	private boolean holdRequest = false;

	private long sessionTimeOut = -1;

	public CwmpMessageHeader() {
		super();
	}

	public boolean isMustUnderstand() {
		return mustUnderstand;
	}

	public void setMustUnderstand(boolean mustUnderstand) {
		this.mustUnderstand = mustUnderstand;
	}

	public boolean isHoldRequest() {
		return holdRequest;
	}

	public void setHoldRequest(boolean holdRequest) {
		this.holdRequest = holdRequest;
	}

	public long getSessionTimeOut() {
		return sessionTimeOut;
	}

	public void setSessionTimeOut(long sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}

}
