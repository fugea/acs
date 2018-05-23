/***********************************************************************
 * $ GetParameterNamesResponseDo.java,v1.0 2012-8-29 9:48:42 $
 *
 * @author: jay
 *
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.suma.acs.messageDo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.suma.acs.utils.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.swg.acs.Message;
import com.swg.acs.message.GetRPCMethodsResponse;
import com.swg.acs.message.Inform;

/**
 * @author jay
 * @created @2012-8-29-9:48:42
 */
@Component("GetRPCMethodsResponse")
public class GetRPCMethodsResponseDo  extends MessageBaseDo {
    private static final long serialVersionUID = 7342180206202447869L;
    private Logger logger = LoggerFactory.getLogger(GetRPCMethodsResponseDo.class);
    private HttpSession session;
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final String TAG = "getRPCMethodsResponseDo";
    public Message process(Message request) {
		logger.info(TAG + "process-");
		Inform lastInform = (Inform) session.getAttribute(Context.LASTINFORM);
		logger.info(TAG + "lastInform-" + lastInform);
		GetRPCMethodsResponse getRPCMethodsResponse = (GetRPCMethodsResponse) request;
		String cwmpId = getRPCMethodsResponse.getCwmpId();

//		List<RpcMethodRecord> list = getRPCMethodsResponse.getMethods();
//		DeviceIdStruct device = lastInform.getDeviceIdStruct();
//		if (list != null) {
//			for (RpcMethodRecord rpcMethod : list) {
//				rpcMethod.setSerialNumber(device.getSerialNumber());
//				rpcMethod.setExcuteStatus(1);
//			}
//		}
//		logger.debug(TAG + "list-" + list);
//		acsServerDao.insertRPCMethod(list,device.getSerialNumber());
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