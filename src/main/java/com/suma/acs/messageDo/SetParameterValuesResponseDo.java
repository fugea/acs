/***********************************************************************
 * $ GetParameterNamesResponseDo.java,v1.0 2012-8-29 9:48:42 $
 *
 * @author: jay
 *
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.suma.acs.messageDo;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.swg.acs.Message;
import com.swg.acs.message.SetParameterValuesResponse;

/**
 * @author jay
 * @created @2012-8-29-9:48:42
 */
@Component("SetParameterValuesResponse")
public class SetParameterValuesResponseDo extends MessageBaseDo {
	private static final long serialVersionUID = 7342180206202447869L;
	private Logger logger = LoggerFactory.getLogger(SetParameterValuesResponseDo.class);
	private static final String TAG = "SetParameterValuesResponseDo-";
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private HttpSession session;
	public Message process(Message request) {
		logger.info(TAG + "-process-");
		SetParameterValuesResponse setParameterValuesResponse = (SetParameterValuesResponse) request;
		String cwmpId = setParameterValuesResponse.getCwmpId();
		int status = setParameterValuesResponse.getStatus();
		// TODO

		return null;
	}
	@Override
	public void setSession(HttpSession session) {
		this.session = session;
	}

	@Override
	public Message getResult() {
		return null;
	}

}