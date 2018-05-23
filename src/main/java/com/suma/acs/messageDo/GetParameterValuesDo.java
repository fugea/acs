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
import com.swg.acs.message.GetParameterValues;
import com.swg.acs.message.Inform;

/**
 * @author loyal
 * @created
 * @2012-8-30-濞戞挸锕ゅ畷锟�:38:13
 * 
 */
@Component("GetParameterValues")
public class GetParameterValuesDo extends MessageBaseDo {
	private Logger logger = LoggerFactory.getLogger(GetParameterValuesDo.class);

	private HttpSession session;

	private static final String TAG = "GetParameterValuesDo-";

	public Message process(Message request) {
		Inform lastInform = (Inform) session.getAttribute(Context.LASTINFORM);
		GetParameterValues responseMessage = new GetParameterValues();
		responseMessage.setUri(lastInform.getUri());

		session.setAttribute(Context.LASTACTION, responseMessage.getName());
		return responseMessage;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public Message getResult() {
		return null;
	}
}
