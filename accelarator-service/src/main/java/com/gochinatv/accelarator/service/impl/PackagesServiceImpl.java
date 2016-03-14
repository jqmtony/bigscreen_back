package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.PackagesDao;
import com.gochinatv.accelarator.dao.entity.Packages;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.PackagesService;

/**
 * 
 * @作者 zhuhh
 * @描述     广告套餐业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class PackagesServiceImpl extends BaseServiceImpl<Packages> implements PackagesService {

	@Autowired
	private PackagesDao packagesDao;
	
	@Override
	protected BaseDao<Packages> getDao() {
		return packagesDao;
	}
	
	
}
