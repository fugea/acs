/***********************************************************************
 * $ GetParameterNamesDo.java,v1.0 2012-8-29 9:52:07 $
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
import com.swg.acs.message.Inform;
import com.swg.acs.message.SetParameterValues;

/**
 * @author jay
 * @created @2012-8-29-9:52:07
 */
@Component("SetParameterValues")
public class SetParameterValuesDo  extends MessageBaseDo{
    private Logger logger = LoggerFactory.getLogger(SetParameterValuesDo.class);
    private HttpSession session;
    private static final String TAG = "[SetParameterValuesDo]-";

    public Message process(Message request) {
		logger.info(TAG + "-process-");
		Inform lastInform = (Inform) session.getAttribute(Context.LASTINFORM);
		SetParameterValues spv = new SetParameterValues();
        //TODO
		spv.setUri(lastInform.getUri());
		session.setAttribute(Context.LASTACTION, spv.getName());
		return spv;
	}

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public void setOrder(Message order) {
    }

    public Message getResult() {
        return null; // To change body of implemented methods use File | Settings | File Templates.
    }

}