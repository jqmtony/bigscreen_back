package com.gochinatv.accelarator.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.api.bean.Device;
import com.gochinatv.accelarator.api.dao.DeviceDao;
import com.gochinatv.accelarator.api.service.DeviceService;

@Service
public class DeviceServiceImpl  implements DeviceService{


	private static Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Override
	public void saveDeviceImage(Device device) {
		deviceDao.saveDeviceImage(device);		
	}
}
