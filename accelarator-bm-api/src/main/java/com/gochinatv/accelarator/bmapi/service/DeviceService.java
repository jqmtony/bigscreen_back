package com.gochinatv.accelarator.bmapi.service;

import java.util.List;

import com.gochinatv.accelarator.bmapi.bean.Device;


/**
 * 
 * @描述   设备信息业务层接口
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface DeviceService{

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
	   
	/**
	 * 更新设备轮询时长
	 * @param placeId
	 * @return
	 */
	public void update(int deviceId,int refreshTime);
}
