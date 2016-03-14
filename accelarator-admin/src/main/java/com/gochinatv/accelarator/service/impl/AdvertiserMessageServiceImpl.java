package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.AdvertiserMessageDao;
import com.gochinatv.accelarator.dao.entity.AdvertiserMessage;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.AdvertiserMessageService;


/**
 * 
 * @作者 zhuhh
 * @描述     广告内容业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class AdvertiserMessageServiceImpl extends BaseServiceImpl<AdvertiserMessage> implements AdvertiserMessageService {

	@Autowired
	private AdvertiserMessageDao advertiserMessageDao;
	
	@Override
	protected BaseDao<AdvertiserMessage> getDao() {
		return advertiserMessageDao;
	}
	
	
}
