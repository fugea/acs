/***********************************************************************
 * $ GetParameterNamesResponseDo.java,v1.0 2012-8-29 9:48:42 $
 *
 * @author: jay
 *
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.suma.acs.messageDo;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.suma.acs.utils.Context;
import com.swg.acs.Message;
import com.swg.acs.message.GetParameterNamesResponse;
import com.swg.acs.message.Inform;
import com.swg.acs.message.struct.DeviceIdStruct;
import com.swg.acs.message.struct.ParameterInfoStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jay
 * @created @2012-8-29-9:48:42
 */
public class GetParameterNamesResponseDo  extends MessageBaseDo  {
    private static final long serialVersionUID = 7342180206202447869L;
    private Logger logger = LoggerFactory.getLogger(GetParameterNamesResponseDo.class);
    private HttpSession session;
    private static final String TAG = "GetParameterNamesResponseDo-";

    public Message process(Message request) {
		logger.info(TAG + "-process-");
		Inform lastInform = (Inform) session.getAttribute(Context.LASTINFORM);
		GetParameterNamesResponse getParameterNamesResponse = (GetParameterNamesResponse) request;
		List<ParameterInfoStruct> list = getParameterNamesResponse.getParameterInfoStructs();
		DeviceIdStruct device = lastInform.getDeviceIdStruct();
		for (ParameterInfoStruct parameterInfoStruct : list) {
			parameterInfoStruct.setDeviceId(device.getDeviceId());
		}
		//acsServerDao.insertParameterInfoStruct(list, device.getSerialNumber());
		//TODO insert parameter info for cpe
		return null;
	}

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public void setOrder(Message order) {
        // To change body of implemented methods use File | Settings | File Templates.
    }

    public Message getResult() {
        return null; // To change body of implemented methods use File | Settings | File Templates.
    }

}
