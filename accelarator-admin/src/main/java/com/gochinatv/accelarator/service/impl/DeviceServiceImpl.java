package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.dao.DeviceDao;
import com.gochinatv.accelarator.dao.entity.Device;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.DeviceService;


/**
 * 
 * @作者 zhuhh
 * @描述   设备信息业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class DeviceServiceImpl extends BaseServiceImpl<Device> implements DeviceService {

	@Autowired
	private DeviceDao deviceDao;
	
	@Override
	protected BaseDao<Device> getDao() {
		return deviceDao;
	}

	public Device getDeviceByCode(int id, String code) {
		Device device = new Device();
		device.setId(id);
		device.setCode(code);
		return deviceDao.getDeviceByCode(device);
	}
	
	
}
