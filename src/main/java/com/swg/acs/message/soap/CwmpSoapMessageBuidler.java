/**
 *
 */
package com.swg.acs.message.soap;

import java.util.Iterator;
import java.util.Map;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;

import com.swg.acs.Argument;
import com.swg.acs.Message;
import com.swg.acs.MessageArgument;
import com.swg.acs.MessageBody;
import com.swg.acs.MessageEnvelope;
import com.swg.acs.MessageHeader;

/**
 * @author satriaprayoga
 */
final class CwmpSoapMessageBuidler extends CwmpMessageBuilder {

	@Override
	protected void buildSoapEnvelope(SOAPEnvelope envelope,
			MessageEnvelope messageEnvelope) throws SOAPException {

		Map<String, String> envMap = messageEnvelope.getNamespaceMap();
		Iterator<String> iterator = envMap.keySet().iterator();
		while (iterator.hasNext()) {
			String prefix = iterator.next();
			envelope.addNamespaceDeclaration(prefix, messageEnvelope
					.getNamespaceValue(prefix));
		}
	}

	@Override
	protected void buildSoapHeader(SOAPHeader header,
			MessageHeader messageHeader, SOAPFactory factory)
			throws SOAPException {
		Iterator<MessageArgument> iterator = messageHeader.childIterator();
		while (iterator.hasNext()) {
			Argument arg = iterator.next();
			SOAPHeaderElement headerElement = header.addHeaderElement(factory
					.createName(arg.getName(), arg.getPrefix(), arg.getUri()));
			String argAttr = arg.getAttributeName();
			if (argAttr != null) {
				headerElement.setAttribute(argAttr, arg.getAttribute(argAttr));
			}
			String value = arg.getValue();
			if (value != null)
				headerElement.setValue(value);
		}
	}

	@Override
	protected void buildSoapBody(SOAPBody soapBody, MessageBody messageBody,
			SOAPFactory factory) throws SOAPException {
		SOAPBodyElement bodyElement = soapBody.addBodyElement(factory
				.createName(messageBody.getName(), Message.CWMP_PREFIX,
						messageBody.getUri()));
		Iterator<MessageArgument> iterator = messageBody.childIterator();
		while (iterator.hasNext()) {
			MessageArgument arg = iterator.next();
			buildSoapElement(bodyElement, arg, factory);
		}
	}

}
