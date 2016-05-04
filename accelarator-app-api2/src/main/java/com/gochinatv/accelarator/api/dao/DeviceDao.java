package com.gochinatv.accelarator.api.dao;

import com.gochinatv.accelarator.api.bean.Device;
import com.gochinatv.accelarator.api.bean.UploadLog;

public interface DeviceDao {

	public void saveDeviceImage(Device device);
	
	public void uploadLog(UploadLog uploadLog);

	/**
	 * 根据mac地址得到设备
	 * @param mac
	 * @return
	 */
	Device getEntityByMac(String mac);

	/**
	 * 更新device
	 * @param device
	 */
	public void update(Device device);
	
	/**
	 * 保存device
	 * @param device
	 */
	public void save(Device device);
}
