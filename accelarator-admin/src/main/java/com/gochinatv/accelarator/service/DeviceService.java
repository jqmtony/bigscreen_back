package com.gochinatv.accelarator.service;

import com.gochinatv.accelarator.dao.entity.Device;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;


/**
 * 
 * @作者 zhuhh
 * @描述   设备信息业务层接口
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface DeviceService extends BaseService<Device>{

	/**
	 * 校验设备编码唯一性
	 * @author limr
	 * @param id
	 * @param code
	 * @return
	 */
	Device getDeviceByCode(int id, String code);

	/**
	 * 检验mac唯一性
	 * @author limr
	 * @param id
	 * @param mac
	 * @return
	 */
	Device getDeviceByMac(int id, String mac);
	   

}
