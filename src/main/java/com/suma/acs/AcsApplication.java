package com.suma.acs;

import com.suma.acs.messageDo.GetRPCMethodsDo;
import com.suma.acs.messageDo.MessageDo;
import com.suma.acs.utils.Context;
import com.sun.xml.internal.ws.resources.SoapMessages;
import com.swg.acs.Message;
import com.swg.acs.message.GetRPCMethods;
import com.swg.acs.message.Inform;
import com.swg.acs.message.soap.CwmpMessageBuilder;
import com.swg.acs.message.soap.CwmpMessageParser;
import com.swg.acs.message.soap.MessageBuilder;
import com.swg.acs.message.soap.MessageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@SpringBootApplication
@Controller
public class AcsApplication {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private BeanFactory beanFactory;

	public static void main(String[] args) {
		SpringApplication.run(AcsApplication.class, args);
	}

    @RequestMapping("/test")
    @ResponseBody
    public String test(){return "sumavision ACS V3.0, current running";}

    @RequestMapping(value = "/acs", method = RequestMethod.POST)
    public void process(HttpServletRequest req, HttpServletResponse res) {
        ServletInputStream in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        SOAPMessage resMsg = null;

        try {
            MessageFactory mf = MessageFactory.newInstance();
            MimeHeaders headers = new MimeHeaders();
            headers.setHeader("Content-Type", "text/xml; charset=UTF-8");
            in = req.getInputStream();
            SOAPMessage reqMsg = mf.createMessage(headers, in);

            //TODO
            resMsg = this.distributeMessage(reqMsg, req, res);

            if (resMsg != null) {
                if (resMsg.saveRequired()) {
                    resMsg.saveChanges();
                }

                resMsg.writeTo(out);
                res.setContentLength(out.size());
                res.setStatus(HttpServletResponse.SC_OK);
                String sout = out.toString().replace('\'', '"');
                res.getOutputStream().print(sout);
                out.flush();
            } else {
                res.setContentLength(0);
                res.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
            res.setContentType("text/xml; charset=UTF-8");
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private SOAPMessage distributeMessage(SOAPMessage soapMessage, HttpServletRequest request,
                                         HttpServletResponse response) {
	    logger.info("start distribute msg: ");
        int reqLen = request.getContentLength();
        HttpSession session = request.getSession();
        String sessionID = session.getId();
        String lastAction = (String) session.getAttribute(Context.LASTACTION);
        SOAPMessage resSOAPMsg = null;
        Message resMsg = null;
        Message reqMsg = null;
        String msgName = null;
        MessageParser msgParser = CwmpMessageParser.getMessageParserInstance();
        MessageBuilder soapMsgBuilder = CwmpMessageBuilder.getSoapMessageBuilderInstance();
        Inform lastInform = (Inform) session.getAttribute(Context.LASTINFORM);

        boolean isException = false;
        boolean cpeSentEmptyReq = true;
        if (reqLen > 0) {
            cpeSentEmptyReq = false;
            try {
                reqMsg = msgParser.parse(soapMessage);
            } catch (SOAPException e) {
                e.printStackTrace();
                isException = true;
                reqMsg = null;
            }

        }

        logger.info("distributeMsg: isException = " + isException);

        if (!isException) {
            if (reqMsg != null) {
                msgName = reqMsg.getName();
                if (msgName != null && msgName.equalsIgnoreCase("Inform")){
                    lastInform = (Inform) reqMsg;
                }
            } else {
                msgName = Context.INFORM_RESPONSE_POST;
            }
            MessageDo msgDo = (MessageDo) beanFactory.getBean(msgName);
            logger.debug("msgName: " + msgName);
            msgDo.setSession(session);
            msgDo.setRequest(request);
            resMsg = msgDo.process(reqMsg);
        }

        if (resMsg != null) {
            resMsg.setUri(lastInform.getUri());
            long curTime = System.currentTimeMillis();
            String cwmpID = curTime + lastInform.getDeviceIdStruct().getSerialNumber();
            try {
                resSOAPMsg = soapMsgBuilder.build(resMsg, cwmpID);
            } catch (SOAPException e) {
                resSOAPMsg = null;
            }
        } else {
            resSOAPMsg = null;
        }

        return resSOAPMsg;

    }
}
