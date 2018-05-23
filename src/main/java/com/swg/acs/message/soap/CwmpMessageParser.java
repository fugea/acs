/**
 * 
 */
package com.swg.acs.message.soap;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.xml.soap.Detail;
import javax.xml.soap.DetailEntry;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;

import com.swg.acs.Message;
import com.swg.acs.MessageBody;
import com.swg.acs.MessageEnvelope;
import com.swg.acs.MessageHeader;
import com.swg.acs.message.Fault;

/**
 * @author satriaprayoga
 *
 */
public abstract class CwmpMessageParser implements MessageParser {

	public final static String TAG = "CwmpMessageParser";

	private static MessageParser messageParser;

	public static MessageParser getMessageParserInstance() {
		if (messageParser == null)
			messageParser = new CwmpSoapMessageParser();
		return messageParser;
	}

	@Override
	public Message parse(SOAPMessage soapMessage) throws SOAPException {
		if (soapMessage.getSOAPBody().hasFault()) {
			return parseFault(soapMessage);
		}
		SoapUtil soapUtil = SoapUtil.getInstance();
		Message message = null;
		String name = soapUtil.getRequestName(soapMessage);
		if (name == null) {
			throw new NoSuchElementException("name not found");
		}
		try {
			message = (Message) Class.forName("com.swg.acs.message." + name).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		parseMessageEnvelope(soapMessage, message);
		parseMessageHeader(soapMessage, message);
		parseMessageBody(soapMessage, message);
		message.configParse();
		return message;
	}

	@SuppressWarnings("unchecked")
	protected Fault parseFault(SOAPMessage soapMessage) throws SOAPException {
		Fault fault = null;
		String faultCode;
		String faultString;
		Detail detail;
		SoapUtil soapUtil = SoapUtil.getInstance();
		try {
			SOAPFault soapFault = soapMessage.getSOAPBody().getFault();
			detail = soapFault.getDetail();
			faultCode = soapUtil.getRequestElementValue(soapFault, "faultcode");
			faultString = soapUtil.getRequestElementValue(soapFault,
					"faultstring");
			fault = new Fault(faultCode, faultString);
			if (detail != null) {
				Iterator<DetailEntry> iterator = detail.getDetailEntries();
				if (iterator.hasNext()) {
					SOAPElement element = soapUtil.getRequestChildElement(
							detail, "Fault");
					SOAPElement fc = soapUtil.getRequestChildElement(element,
							"FaultCode");
					faultCode = fc.getValue();
					SOAPElement fs = soapUtil.getRequestChildElement(element,
							"FaultString");
					faultString = fs.getValue();
					fault.addParameterFault("", faultCode, faultString);

				}

			}

		} catch (Exception e) {

		}
		return fault;
	}

	protected abstract MessageEnvelope parseMessageEnvelope(
			SOAPMessage soapMessage, Message message) throws SOAPException;

	protected abstract MessageHeader parseMessageHeader(
			SOAPMessage soapMessage, Message message) throws SOAPException;

	protected abstract MessageBody parseMessageBody(SOAPMessage soapMessage,
			Message message) throws SOAPException;
}
