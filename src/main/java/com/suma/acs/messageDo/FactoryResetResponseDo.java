package com.suma.acs.messageDo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.swg.acs.Message;
import com.swg.acs.message.FactoryResetResponse;

/**
 * 鎭㈠京鍑哄巶璁剧疆鐩稿簲
 * 
 * @author smsx
 * 
 */
@Component("FactoryResetResponse")
public class FactoryResetResponseDo extends MessageBaseDo {

	private Logger logger = LoggerFactory.getLogger(FactoryResetDo.class);

	private static final String TAG = "FactoryResetResponseDo-";
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	public Message getResult() {
		return null;
	}

	public Message process(Message request) {
		logger.info(TAG + "-process-");
		FactoryResetResponse rebootResponse = (FactoryResetResponse) request;
		logger.debug("reboot success!" + rebootResponse);
		String cwmpId = rebootResponse.getCwmpId();

		return null;
	}

	public void setOrder(Message order) {

	}

	public void setSession(HttpSession session) {

	}

}
