package com.suma.acs.messageDo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.suma.acs.utils.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.swg.acs.Message;
import com.swg.acs.message.GetParameterValuesResponse;
import com.swg.acs.message.Inform;
import com.swg.acs.message.struct.DeviceIdStruct;
import com.swg.acs.message.struct.ParameterValueStruct;

/**
 * @author loyal
 * @created
 * @2012-8-30-:38:13
 */
@Component("GetParameterValuesResponse")
public class GetParameterValuesResponseDo extends MessageBaseDo {
	private Logger logger = LoggerFactory
			.getLogger(GetParameterValuesResponseDo.class);

	private HttpSession session;

	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	private static final String TAG = "GetParameterValuesResponseDo-";
	
	@Override
	public Message process(Message request) {
		logger.info(TAG + "-process-");
		// 1.0 get paramterValueStructs from Inform.
		Inform lastInform = (Inform) session.getAttribute(Context.LASTINFORM);
		DeviceIdStruct deviceIDStruct = lastInform.getDeviceIdStruct();
		GetParameterValuesResponse getParameterValuesResponse = (GetParameterValuesResponse) request;
		List<ParameterValueStruct> baseInfoList = getParameterValuesResponse.getParameterValueStructs();
		String cwmpId = getParameterValuesResponse.getCwmpId();

		
		if (baseInfoList != null && baseInfoList.size() > 0) {
			Map<String, String> map = new HashMap<>();
			for (ParameterValueStruct st : baseInfoList) {
				map.put(st.getName(), st.getValue());
			}
			//TODO
			//redisService.mset(deviceIDStruct.getSerialNumber(), map);

		}
		
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
