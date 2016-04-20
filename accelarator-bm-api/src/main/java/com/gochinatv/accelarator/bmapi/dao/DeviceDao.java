package com.gochinatv.accelarator.bmapi.dao;

import java.util.List;

import com.gochinatv.accelarator.bmapi.bean.Device;


/**
 * 
 * @描述   设备信息数据库接口层
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface DeviceDao{

	/**
	 * 得到设备详情
	 * @param deviceId
	 * @return
	 */
	Device getEntityById(int deviceId);

	/**
	 * 得到设备列表
	 * @param placeId
	 * @return
	 */
	List<Device> getListByPlaceId(long placeId);
	   

}
