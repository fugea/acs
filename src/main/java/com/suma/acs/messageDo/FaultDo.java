/***********************************************************************
 * $Id: RebootDo.java,v1.0 2012-8-30 涓婂崍8:44:01 $
 *
 * @author: haojie
 *
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.suma.acs.messageDo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.swg.acs.Message;

/**
 * @author haojie
 * @created @2012-8-30-涓婂崍8:44:01
 */
@Component("Fault")
public class FaultDo extends MessageBaseDo {
	private Logger logger = LoggerFactory.getLogger(FaultDo.class);

	private static final String TAG = "FaultDo-";

	public Message process(Message request) {
		logger.info(TAG + "-process-");

		return null;
	}

	public void setOrder(Message order) {

	}

	public Message getResult() {
		return null;
	}

}