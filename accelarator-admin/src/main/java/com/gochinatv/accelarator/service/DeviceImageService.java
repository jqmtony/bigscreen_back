package com.gochinatv.accelarator.service;

import java.util.List;

import com.gochinatv.accelarator.dao.entity.DeviceImage;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;

public interface DeviceImageService extends BaseService<DeviceImage>{

	//统计查询
	List<DeviceImage> getListByStatEntity(DeviceImage deviceImage);
	   

}
