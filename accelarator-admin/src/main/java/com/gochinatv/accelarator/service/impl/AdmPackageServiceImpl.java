package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.AdmPackageDao;
import com.gochinatv.accelarator.dao.entity.AdmPackage;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.AdmPackageService;


/**
 * 
 * @作者 zhuhh
 * @描述    广告套餐关联业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class AdmPackageServiceImpl extends BaseServiceImpl<AdmPackage> implements AdmPackageService {

	@Autowired
	private AdmPackageDao admPackageDao;
	
	@Override
	protected BaseDao<AdmPackage> getDao() {
		return admPackageDao;
	}
	
	
}
