package com.gochinatv.accelarator.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.dao.DeviceImageDao;
import com.gochinatv.accelarator.dao.entity.DeviceImage;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.DeviceImageService;


/**
 * 
 * @作者 zhuhh
 * @描述   设备信息业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class DeviceImageServiceImpl extends BaseServiceImpl<DeviceImage> implements DeviceImageService {

	@Autowired
	private DeviceImageDao deviceImageDao;
	
	@Override
	protected BaseDao<DeviceImage> getDao() {
		return deviceImageDao;
	}

	@Override
	public List<DeviceImage> getListByStatEntity(DeviceImage deviceImage) {
		return deviceImageDao.getListByStatEntity(deviceImage);
	}
	
	
}
