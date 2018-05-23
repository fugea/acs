package com.suma.acs.messageDo;

import javax.servlet.http.HttpSession;

import com.suma.acs.utils.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.swg.acs.Message;
import com.swg.acs.message.FactoryReset;
import com.swg.acs.message.Inform;

/**
 * 鎭㈠鍑哄巶璁剧疆璁剧疆锟�
 * 
 * @author smsx
 * 
 */
@Component("FactoryReset")
public class FactoryResetDo extends MessageBaseDo {
	private Logger logger = LoggerFactory.getLogger(FactoryResetDo.class);

	private static final String TAG = "FactoryRestDo";

	private HttpSession session;

	public Message getResult() {
		return null;
	}

	public Message process(Message request) {
		logger.info(TAG + "-process-");
		Inform lastInform = (Inform) session.getAttribute(Context.LASTINFORM);
		Message responseMessage = new FactoryReset();
		responseMessage.setUri(lastInform.getUri());
		session.setAttribute(Context.LASTACTION, responseMessage.getName());
		return responseMessage;
	}

	public void setOrder(Message order) {

	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

}
