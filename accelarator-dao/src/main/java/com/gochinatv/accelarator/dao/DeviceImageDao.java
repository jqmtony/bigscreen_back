package com.gochinatv.accelarator.dao;

import java.util.List;

import com.gochinatv.accelarator.dao.entity.DeviceImage;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;


/**
 * 
 * @作者 zhuhh
 * @描述   设备信息数据库接口层
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface DeviceImageDao extends BaseDao<DeviceImage>{

	//统计查询
	List<DeviceImage> getListByStatEntity(DeviceImage deviceImage);
	   

}
