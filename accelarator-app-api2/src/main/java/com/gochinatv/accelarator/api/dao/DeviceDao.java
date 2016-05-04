package com.gochinatv.accelarator.api.dao;

import com.gochinatv.accelarator.api.bean.Device;
import com.gochinatv.accelarator.api.bean.UploadLog;

public interface DeviceDao {

	public void saveDeviceImage(Device device);
	
	public void uploadLog(UploadLog uploadLog);
}
