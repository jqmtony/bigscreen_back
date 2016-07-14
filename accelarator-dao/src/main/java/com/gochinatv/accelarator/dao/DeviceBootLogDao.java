package com.gochinatv.accelarator.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	public List<DeviceBootLog> queryStatPic(DeviceBootLog deviceBootLog);
}
