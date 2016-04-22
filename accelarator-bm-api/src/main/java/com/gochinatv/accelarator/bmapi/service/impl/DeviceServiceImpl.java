package com.gochinatv.accelarator.bmapi.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.bmapi.bean.Device;
import com.gochinatv.accelarator.bmapi.dao.DeviceDao;
import com.gochinatv.accelarator.bmapi.service.DeviceService;


/**
 * 
 * @描述   设备信息业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceDao deviceDao;

	@Override
	public Device getEntityById(int deviceId) {
		return deviceDao.getEntityById(deviceId);
	}

	@Override
	public List<Device> getListByPlaceId(long placeId) {
		return deviceDao.getListByPlaceId(placeId);
	}

	@Override
	public void update(int deviceId, int refreshTime) {
		deviceDao.update(deviceId,refreshTime);
	}

}
