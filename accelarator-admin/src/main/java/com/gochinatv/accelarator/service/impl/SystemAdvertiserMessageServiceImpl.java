package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.SystemAdvertiserMessageDao;
import com.gochinatv.accelarator.dao.entity.SystemAdvertiserMessage;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.SystemAdvertiserMessageService;

/**
 * 
 * @作者 zhuhh
 * @描述   系统广告业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class SystemAdvertiserMessageServiceImpl extends BaseServiceImpl<SystemAdvertiserMessage> implements SystemAdvertiserMessageService {

	@Autowired
	private SystemAdvertiserMessageDao systemAdvertiserMessageDao;
	
	@Override
	protected BaseDao<SystemAdvertiserMessage> getDao() {
		return systemAdvertiserMessageDao;
	}
	
	
}
