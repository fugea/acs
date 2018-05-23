package com.suma.acs.service.impl;

import com.suma.acs.dao.BaseDaoImpl;
import com.suma.acs.entity.Device;
import com.suma.acs.service.IDeviceService;
import org.springframework.stereotype.Service;

@Service(value = "deviceService")
public class DeviceServiceImpl extends BaseDaoImpl<Device, String> implements IDeviceService {
}
