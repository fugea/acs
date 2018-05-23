/**
 * 
 */
package com.swg.acs.message.soap;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import com.swg.acs.Message;

/**
 * Interface untuk parsing {@link SOAPMessage}
 * @author satriaprayoga
 *
 */
public interface MessageParser {

	/**
	 * Method untuk parsing {@link SOAPMessage} dan dikonversi ke object {@link Message}
	 * @param soapMessage
	 * @return
	 * @throws SOAPException
	 */
	public Message parse(SOAPMessage soapMessage) throws SOAPException;
}
