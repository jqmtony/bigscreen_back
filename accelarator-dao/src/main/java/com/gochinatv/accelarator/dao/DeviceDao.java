package com.gochinatv.accelarator.dao;

import com.gochinatv.accelarator.dao.entity.Device;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;


/**
 * 
 * @作者 zhuhh
 * @描述   设备信息数据库接口层
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface DeviceDao extends BaseDao<Device>{

	/**
	 * 校验编码唯一性
	 * @author limr
	 * @param device
	 * @return
	 */
	Device getDeviceByCode(Device device);
	   

}
