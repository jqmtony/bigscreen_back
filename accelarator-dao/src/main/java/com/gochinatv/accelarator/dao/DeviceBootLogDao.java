package com.gochinatv.accelarator.dao;

import com.gochinatv.accelarator.dao.entity.DeviceBootLog;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;

public interface DeviceBootLogDao extends BaseDao<DeviceBootLog> {

	/**
	 * 保存DeviceBootLog
	 * @param DeviceBootLog
	 */
	public void save(DeviceBootLog deviceBootLog);
	/**
	 * 更新DeviceBootLog
	 * @param DeviceBootLog
	 */
	public void update(DeviceBootLog deviceBootLog);
}
