package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.AdvertiserDao;
import com.gochinatv.accelarator.dao.entity.Advertiser;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.AdvertiserService;


/**
 * 
 * @作者 zhuhh
 * @描述    广告商账号业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class AdvertiserServiceImpl extends BaseServiceImpl<Advertiser> implements AdvertiserService {

	@Autowired
	private AdvertiserDao advertiserDao;
	
	@Override
	protected BaseDao<Advertiser> getDao() {
		return advertiserDao;
	}
	
	
}
