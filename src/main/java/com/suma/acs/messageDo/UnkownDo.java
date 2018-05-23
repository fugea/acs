/***********************************************************************
 * $ com.topvision.acsserver.message.Inform,v1.0 2012-8-22 14:27:22 $
 *
 * @author: jay
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

/**
 * @author jay
 * @created @2012-8-22-14:27:22
 */
@Component("Unkown")
public class UnkownDo  extends MessageBaseDo{
    private Logger logger = LoggerFactory.getLogger(UnkownDo.class);
    private HttpSession session;
    private static final String TAG = "UnkownDo-";
    
    public Message process(Message request) {
    	logger.info(TAG+"-process-");
        String lastAction = (String) session.getAttribute(Context.LASTACTION);
        if (logger.isTraceEnabled()) {
            logger.trace("unkown message : " + request + ";lastAction is : " + lastAction);
        }
        session.setAttribute(Context.LASTACTION, Context.UNKOWN);
        return request;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public void setOrder(Message order) {
        // To change body of implemented methods use File | Settings | File Templates.
    }

    public Message getResult() {
        return null; // To change body of implemented methods use File | Settings | File Templates.
    }

}