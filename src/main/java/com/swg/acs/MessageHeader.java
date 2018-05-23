/**
 * 
 */
package com.swg.acs;

/**
 * Interface untuk header dari SOAP Message. Element-element apa saja
 *  yang ada di SOAP header ini bisa dilihat di TR-069 Amandement
 * @author satriaprayoga
 *
 */
public interface MessageHeader extends MessageArgument{

	boolean isMustUnderstand();
	void setMustUnderstand(boolean mustUnderstand);
	long getSessionTimeOut();
	void setSessionTimeOut(long sessionTimeOut);
	boolean isHoldRequest();
	void setHoldRequest(boolean holdRequest);
}
