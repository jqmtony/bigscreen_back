package com.gochinatv.accelarator.api.service;

import com.gochinatv.accelarator.api.bean.Device;
import com.gochinatv.accelarator.api.bean.UploadLog;

public interface DeviceService {

	public void  saveDeviceImage(Device device);
	

	public void  uploadLog(UploadLog uploadLog);
	
	/**
	 * 根据mac地址得到设备
	 * @param mac
	 * @return
	 */
	Device getEntityByMac(String mac);

	/**
	 * 新增或更新设备信息
	 * @param device
	 */
	public void saveOrUpdateDevice(String mac, String versionNum, String versionName);
}
