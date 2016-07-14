package com.gochinatv.accelarator.service;


import java.util.List;

import com.gochinatv.accelarator.dao.entity.DeviceBootLog;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;
import com.gochinatv.accelarator.response.DeviceBootLogStatics;


public interface DeviceBootLogService extends BaseService<DeviceBootLog>{

	public List<DeviceBootLogStatics> queryStatPic(DeviceBootLog deviceBootLog);
	
}
