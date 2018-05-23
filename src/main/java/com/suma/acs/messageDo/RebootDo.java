/***********************************************************************
 * $Id: RebootDo.java,v1.0 2012-8-30 涓婂崍8:44:01 $
 * 
 * @author: haojie
 * 
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.suma.acs.messageDo;

import javax.servlet.http.HttpSession;

import com.suma.acs.utils.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.swg.acs.Message;
import com.swg.acs.message.Inform;
import com.swg.acs.message.Reboot;

/**
 * @author haojie
 * @created @2012-8-30-涓婂崍8:44:01
 * 
 */
@Component("Reboot")
public class RebootDo  extends MessageBaseDo {
    private Logger logger = LoggerFactory.getLogger(RebootDo.class);
    private HttpSession session;
    private static final String TAG = "銆怰ebootDo锟�";
    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.acsserver.messageDo.MessageDo#process(com.swg.acs.Message)
     */
    @Override
    public Message process(Message request) {
		logger.info(TAG + "-process-");
		Inform lastInform = (Inform) session.getAttribute(Context.LASTINFORM);
		Message responseMessage = new Reboot();
		responseMessage.setUri(lastInform.getUri());
		session.setAttribute(Context.LASTACTION, responseMessage.getName());
		return responseMessage;
	}

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.topvision.acsserver.messageDo.MessageDo#setSession(javax.servlet.http.HttpSession)
	 */
    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.acsserver.messageDo.MessageDo#getResult()
     */
    @Override
    public Message getResult() {
        return null; // To change body of implemented methods use File | Settings | File Templates.
    }


}
