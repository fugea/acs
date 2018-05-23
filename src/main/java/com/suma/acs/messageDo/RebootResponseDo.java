/***********************************************************************
 * $Id: RebootResponseDo.java,v1.0 2012-8-30 涓婂崍8:45:15 $
 * 
 * @author: haojie
 * 
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.suma.acs.messageDo;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.suma.acs.utils.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.swg.acs.Message;
import com.swg.acs.message.RebootResponse;

/**
 * @author haojie
 * @created @2012-8-30-涓婂崍8:45:15
 * 
 */
@Component("RebootResponse")
public class RebootResponseDo extends MessageBaseDo {
	private Logger logger = LoggerFactory.getLogger(RebootResponseDo.class);
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private HttpSession session;
	public HttpSession getSession() {
		return session;
	}

	public Message process(Message request) {
		logger.info("[RebootResponseDo]" + "-process-");
		RebootResponse rebootResponse = (RebootResponse) request;
		session.setAttribute(Context.LASTACTION, request.getName());
		logger.debug("reboot success!" + rebootResponse);
		String cwmpId = rebootResponse.getCwmpId();

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.topvision.acsserver.messageDo.MessageDo#setSession(javax.servlet.http.HttpSession)
	 */
	@Override
	public void setSession(HttpSession session) {
		this.session= session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.topvision.acsserver.messageDo.MessageDo#getResult()
	 */
	@Override
	public Message getResult() {
		return null;
	}

}
