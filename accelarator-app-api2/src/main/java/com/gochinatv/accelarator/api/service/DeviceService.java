package com.gochinatv.accelarator.api.service;

import com.gochinatv.accelarator.api.bean.Device;
import com.gochinatv.accelarator.api.bean.UploadLog;

public interface DeviceService {

	public void  saveDeviceImage(Device device);
	

	public void  uploadLog(UploadLog uploadLog);
}
