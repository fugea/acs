/**
 *
 */
package com.swg.acs.message.soap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.soap.Detail;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import com.swg.acs.Message;
import com.swg.acs.MessageArgument;
import com.swg.acs.MessageBody;
import com.swg.acs.MessageEnvelope;
import com.swg.acs.MessageHeader;
import com.swg.acs.message.Fault;

/**
 * @author satriaprayoga
 */
public abstract class CwmpMessageBuilder implements MessageBuilder {

    private static MessageBuilder messageBuilder;

    public static MessageBuilder getSoapMessageBuilderInstance() {
        if (messageBuilder == null)
            messageBuilder = new CwmpSoapMessageBuidler();
        return messageBuilder;
    }

    @Override
    public SOAPMessage build(Message message,String cwmpId) throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
//        SOAPFactory soapFactory=SOAPFactory.newInstance();
//        SOAPMessage soapMessage = messageFactory.createMessage();

//        SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
//        MessageEnvelope messageEnvelope = message.getMessageEnvelope();
//        buildSoapEnvelope(soapEnvelope, messageEnvelope);
        
        SOAPFactory soapFactory = SOAPFactory.newInstance();

        String s = "<SOAP-ENV:Envelope";
        s += " xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"";
        s += " xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\"";
        s += " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"";
        s += " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
        s += " xmlns:cwmp=\"" + message.getUri()
                + "\"><SOAP-ENV:Header></SOAP-ENV:Header><SOAP-ENV:Body></SOAP-ENV:Body></SOAP-ENV:Envelope>";
        ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
        SOAPMessage soapMessage = null;
        try {
            soapMessage = messageFactory.createMessage(null, in);
        } catch (IOException e) {
            throw new SOAPException("Create message error", e);
        }
        SOAPHeader soapHeader = soapMessage.getSOAPHeader();
        SOAPBody soapBody = soapMessage.getSOAPBody();
        message.config(cwmpId);
        MessageHeader messageHeader = message.getMessageHeader();
        buildSoapHeader(soapHeader, messageHeader, soapFactory);
        MessageBody messageBody = message.getMessageBody();
        if (messageBody.hasFault() && (message instanceof Fault)) {
            SOAPFault fault = soapBody.addFault();
            buildSoapFault(fault, messageBody, soapFactory);
        } else {
            buildSoapBody(soapBody, messageBody, soapFactory);
        }
        return soapMessage;
    }

    protected abstract void buildSoapEnvelope(SOAPEnvelope envelope, MessageEnvelope messageEnvelope) throws SOAPException;

    protected abstract void buildSoapHeader(SOAPHeader header, MessageHeader messageHeader, SOAPFactory factory) throws SOAPException;

    protected abstract void buildSoapBody(SOAPBody soapBody, MessageBody messageBody, SOAPFactory factory) throws SOAPException;

    protected void buildSoapElement(SOAPElement element, MessageArgument argument, SOAPFactory factory) throws SOAPException {
        SOAPElement el = null;
        if (argument.getPrefix() != null)
            el = element.addChildElement(factory.createName(argument.getName(), argument.getPrefix(), argument.getUri()));
        else
            el = element.addChildElement(argument.getName());
        String attrName = argument.getAttributeName();
        if (attrName != null)
            el.setAttribute(attrName, argument.getAttribute(attrName));
        if (argument.getValue() != null) el.setValue(argument.getValue());
        if (argument.childIterator().hasNext()) {
            Iterator<MessageArgument> iterator = argument.childIterator();
            while (iterator.hasNext()) {
                MessageArgument arg = iterator.next();
                buildSoapElement(el, arg, factory);
            }
        }
    }

    protected void buildSoapFault(SOAPFault fault, MessageArgument argument, SOAPFactory factory) throws SOAPException {
        fault.setFaultCode("ACS Server");
        fault.setFaultString("CWMP Fault");
        Detail detail = fault.addDetail();
        buildSoapElement(detail, argument, factory);
    }
}
