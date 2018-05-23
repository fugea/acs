package com.suma.acs.messageDo;

import javax.servlet.http.HttpSession;

import com.suma.acs.utils.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.swg.acs.Message;
import com.swg.acs.message.GetParameterAttributes;
import com.swg.acs.message.Inform;

@Component("GetParameterAttributes")
public class GetParameterAttributesDo  extends MessageBaseDo {
    private Logger logger = LoggerFactory.getLogger(GetParameterAttributesDo.class);
    private HttpSession session;
    private static final String TAG = "GetParameterAttributesDo-";
	public Message getResult() {
		return null;
	}
	public Message process(Message request) {
		logger.info(TAG + "-process-");
		GetParameterAttributes responseMessage = new GetParameterAttributes();
		Inform lastInform = (Inform) session.getAttribute(Context.LASTINFORM);
		responseMessage.setUri(lastInform.getUri());
		responseMessage.addParameterName("STBService.");
		session.setAttribute(Context.LASTACTION, responseMessage.getName());
		return responseMessage;
	}
	public void setOrder(Message order) {
		
	}
	public void setSession(HttpSession session) {
		  this.session = session;
	}

}
