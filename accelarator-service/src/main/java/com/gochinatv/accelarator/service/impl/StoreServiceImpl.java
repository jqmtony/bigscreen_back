package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.StoreDao;
import com.gochinatv.accelarator.dao.entity.Store;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.StoreService;

/**
 * 
 * @作者 zhuhh
 * @描述   店铺管理业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService {

	@Autowired
	private StoreDao storeDao;
	
	@Override
	protected BaseDao<Store> getDao() {
		return storeDao;
	}
	
	
}
