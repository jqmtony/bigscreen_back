package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.AppAdSettingsDao;
import com.gochinatv.accelarator.dao.entity.AppAdSettings;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.AppAdSettingsService;


/**
 * 
 * @作者 zhuhh
 * @描述    APP广告设置业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class AppAdSettingsServiceImpl extends BaseServiceImpl<AppAdSettings> implements AppAdSettingsService {

	@Autowired
	private AppAdSettingsDao appAdSettingsDao;
	
	@Override
	protected BaseDao<AppAdSettings> getDao() {
		return appAdSettingsDao;
	}
	
	
}
