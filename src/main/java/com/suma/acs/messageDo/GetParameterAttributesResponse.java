package com.suma.acs.messageDo;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.swg.acs.Message;
@Component("GetParameterAttributesResponse")
public class GetParameterAttributesResponse extends MessageBaseDo {
	private Logger logger = LoggerFactory
			.getLogger(GetParameterAttributesDo.class);

	private static final String TAG = "銆怗etParameterAttributesDo锟�";

	public Message getResult() {
		return null;
	}

	public Message process(Message request) {
		logger.info(TAG + "-process-");
		return null;
	}

	public void setOrder(Message order) {

	}

	public void setSession(HttpSession session) {
	}

}
