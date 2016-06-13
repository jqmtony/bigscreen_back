package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.dao.DeviceBootLogDao;
import com.gochinatv.accelarator.dao.entity.DeviceBootLog;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.DeviceBootLogService;

@Service
public class DeviceBootLogServiceImpl extends BaseServiceImpl<DeviceBootLog> implements DeviceBootLogService {

	@Autowired
	private DeviceBootLogDao deviceBootLogDao;
	
	@Override
	protected BaseDao<DeviceBootLog> getDao() {
		return deviceBootLogDao;
	}

}
