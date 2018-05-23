/**
 *
 */
package com.swg.acs.message.cwmp;

import java.util.Map;

import com.swg.acs.Argument;
import com.swg.acs.Message;
import com.swg.acs.MessageArgument;
import com.swg.acs.MessageEnvelope;
import com.swg.acs.MessageHeader;


/**
 * @author satriaprayoga
 */
public abstract class CwmpMessage extends Message {
    private static final long serialVersionUID = -2993378427688180969L;

    public CwmpMessage() {
        super();
    }

    public CwmpMessage(String name) {
        super(name);
        messageBody = new CwmpMessageBody(name);
        messageEnvelope = new CwmpMessageEnvelope();
        messageHeader = new CwmpMessageHeader();
    }

    @Override
    protected void configureEnvelope(MessageEnvelope envelope) {
//		envelope.addNamespace(SOAP_ENC_PREFIX, SOAP_ENC_URN);
//		envelope.addNamespace(XSD_PREFIX, XSD_URN);
//		envelope.addNamespace(XSI_PREFIX, XSI_URN);
//		envelope.addNamespace(CWMP_PREFIX, CWMP_URN);       .
        Map<String, String> namespaceMap = envelope.getNamespaceMap();
        for (String name : namespaceMap.keySet()) {
            String value = namespaceMap.get(name);
            envelope.addNamespace(name, value);
        }
    }

    @Override
    protected void configureHeader(MessageHeader header, ArgumentFactory argumentFactory,String cwmpId) {
        MessageArgument idarg = header.addMessageArgument(argumentFactory.createMessageArgument("ID", CWMP_PREFIX, getUri()));
        idarg.setAttribute("SOAP-ENV:mustUnderstand", header.isMustUnderstand() ? "1" : "0");
        idarg.setValue(cwmpId);//getId()
        
        MessageArgument noMoreArg = header.addMessageArgument(argumentFactory.createMessageArgument("NoMoreRequests", CWMP_PREFIX, getUri()));
        noMoreArg.setValue(isNoMore() ? "1" : "0");

//        Argument holdarg = header.addMessageArgument(argumentFactory.createMessageArgument("HoldRequest", CWMP_PREFIX, getUri()));
//        holdarg.setAttribute("SOAP-ENV:mustUnderstand", header.isMustUnderstand() ? "1" : "0");
//        holdarg.setValue(header.isHoldRequest() ? "1" : "0");

        if (header.getSessionTimeOut() != -1) {
            Argument stoarg = header.addMessageArgument(argumentFactory.createMessageArgument("SessionTimeout", CWMP_PREFIX, getUri()));
            stoarg.setAttribute("SOAP-ENV:mustUnderstand", header.isMustUnderstand() ? "1" : "0");
            stoarg.setValue(String.valueOf(header.getSessionTimeOut()));
        }
    }

}
