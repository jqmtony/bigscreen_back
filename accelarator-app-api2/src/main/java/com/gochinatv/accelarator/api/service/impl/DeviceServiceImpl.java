package com.gochinatv.accelarator.api.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.api.bean.Device;
import com.gochinatv.accelarator.api.bean.UploadLog;
import com.gochinatv.accelarator.api.dao.DeviceDao;
import com.gochinatv.accelarator.api.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService{


	private static Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);
	
	@Autowired
	private DeviceDao deviceDao;
	@Override
	public void saveDeviceImage(Device device) {
		deviceDao.saveDeviceImage(device);		
	}

	@Override
	public void  uploadLog(UploadLog uploadLog){
		deviceDao.uploadLog(uploadLog);		
	}
	@Override
	public Device getEntityByMac(String mac) {
		return deviceDao.getEntityByMac(mac);
	}

	public void saveOrUpdateDevice(String mac, String versionNum, String versionName){
		Device device = deviceDao.getEntityByMac(mac);
		if(device != null){
			device.setVersionNum(versionNum);
			device.setVersionName(versionName);
			device.setBootTime(new Date());
			deviceDao.update(device);
		}else{
			device = new Device();
			device.setMac(mac);
			device.setVersionNum(versionNum);
			device.setVersionName(versionName);
			device.setBootTime(new Date());
			device.setCreateTime(new Date());
			deviceDao.save(device);
		}
	}
}
