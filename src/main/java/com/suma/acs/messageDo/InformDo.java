/***********************************************************************
 * $ com.topvision.acsserver.message.Inform,v1.0 2012-8-22 14:27:22 $
 *
 * @author: jay
 *
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.suma.acs.messageDo;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.suma.acs.entity.Device;
import com.suma.acs.service.IDeviceService;
import com.suma.acs.utils.Context;
import com.suma.acs.utils.DateUtils;
import com.suma.acs.utils.IDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.swg.acs.Message;
import com.swg.acs.message.Inform;
import com.swg.acs.message.InformResponse;
import com.swg.acs.message.struct.DeviceIdStruct;
import com.swg.acs.message.struct.EventStruct;
import com.swg.acs.message.struct.ParameterValueStruct;

/**
 * @author jay
 * @created
 * @2012-8-22-14:27:22
 */
@Component("Inform")
public class InformDo extends MessageBaseDo {

	private Logger logger = LoggerFactory.getLogger(InformDo.class);
	private HttpSession session;

	private static final String TAG = "[InformDo]-";
	
	@Resource
	private IDeviceService deviceService;
	public Message process(Message request) {
		logger.info(TAG + "-process-");
 		Inform lastInform = (Inform) request;
 		
 		List<EventStruct> eventStructs = lastInform.getEventStructs();
		logger.info(TAG + "eventStructs.size = " + eventStructs.size());
		DeviceIdStruct deviceIDStruct = lastInform.getDeviceIdStruct();	
		List<ParameterValueStruct> list = lastInform.getParameterValueStructs();
		String happenCode = null;
//		boolean flag = false;
		Long devID = IDUtil.generateID(deviceIDStruct.getOui() + deviceIDStruct.getSerialNumber());


		if (eventStructs != null && eventStructs.size() > 0) {
			for (EventStruct es : eventStructs) {
				happenCode = es.getEventCode();
                logger.info(TAG + "happenCode = " + happenCode);
                switch (happenCode) {
                    case EventStruct.BOOTSTRAP:
                        doAfterBootstrap(deviceIDStruct, list);
                        break;
                    case EventStruct.BOOT:
                        doAfterBoot(deviceIDStruct);
                        break;
                    case EventStruct.PERIODIC:
                        doAfterPeriodic(deviceIDStruct);
                        break;
                    case EventStruct.SCHEDULED:
                    case EventStruct.VALUE_CHANGE:
                    case EventStruct.KICKED:
                        break;
                    case EventStruct.CONNECTION_REQUEST:
                        //doAfterConnectRequest();
                        break;
                    case EventStruct.TRANSFER_COMPLETE:
                        //doAfterTransferComplete();
                        break;
                    case EventStruct.DIAGNOSITICS_COMPLETE:
                        //doAfterDiagnositicsComplete();
                        break;
                }
			}
		}

		lastInform.clearParameterValueStructs();
		session.setAttribute(Context.LASTINFORM, lastInform);
		
		InformResponse informResponse = new InformResponse();
		informResponse.setId(lastInform.getId());
		informResponse.setUri(lastInform.getUri());
		informResponse.setMaxEnvelopes(Context.MAX_ENVELOPES);
		session.setAttribute(Context.LASTACTION, informResponse.getName());
		return informResponse;
	}

	private void doAfterBootstrap(DeviceIdStruct deviceIDStruct, List<ParameterValueStruct> params) {
        Device device = deviceService.findByPK(deviceIDStruct.getSerialNumber());
        if (device == null) {
            device = new Device();
        }
        device.setId(deviceIDStruct.getSerialNumber());
        device.setProductClass(deviceIDStruct.getProductClass());
        device.setOui(deviceIDStruct.getOui());
        device.setManufacturer(deviceIDStruct.getManufacturer());
        device.setRegisterDate(DateUtils.format(new Date()));
        device.setUpTime(DateUtils.format(new Date()));

        if (params != null && params.size() > 0) {
            for(ParameterValueStruct pv : params) {
                String name = pv.getName();
                String value = pv.getValue();
                if ("Device.DeviceInfo.HardwareVersion".equals(name)) {
                    device.setHversion(value);
                }
                if ("Device.DeviceInfo.SoftwareVersion".equals(name)) {
                    device.setSversion(value);
                }
                if ("Device.DeviceInfo.CAID".equals(name)) {
                    device.setCaID(value);
                }
                if ("Device.DeviceInfo.RegionCode".equals(name)) {
                    device.setRegionCode(value);
                }

            }
        }

        deviceService.save(device);

    }

    private void doAfterBoot(DeviceIdStruct deviceIDStruct) {
        Device device = deviceService.findByPK(deviceIDStruct.getSerialNumber());
        if (device == null) {
            logger.warn("Headend can not create device record if cpe use boot event instead of bootstrap event");
            return;
        }
        device.setUpTime(DateUtils.format(new Date()));
        deviceService.save(device);
    }

    private void doAfterPeriodic(DeviceIdStruct deviceIDStruct) {
        doAfterBoot(deviceIDStruct);
    }

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setOrder(Message order) {

	}

	public Message getResult() {
		return null;
	}

}