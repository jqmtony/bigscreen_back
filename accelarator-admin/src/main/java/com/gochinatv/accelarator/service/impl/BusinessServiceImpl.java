package com.gochinatv.accelarator.service.impl;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.dao.BusinessDao;
import com.gochinatv.accelarator.dao.entity.Business;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.BusinessService;


/**
 * 
 * @作者 zhuhh
 * @描述   商家账号业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class BusinessServiceImpl extends BaseServiceImpl<Business> implements BusinessService {

	@Autowired
	private BusinessDao businessDao;
	
	@Override
	protected BaseDao<Business> getDao() {
		return businessDao;
	}

	public void save(Business entity) throws Exception {
		if(StringUtils.isNotBlank(entity.getCityCode()) && entity.getCityCode().length() == 8){
			entity.setAreaCode(entity.getCityCode().substring(0, 6));
			entity.setCountryCode(entity.getCityCode().substring(0, 4));
			businessDao.save(entity);
		}else{
			throw new Exception("地区输入错误，添加商家失败");
		}
	
	}

	public Business getBusinessByUserName(int id, String userName) {
		Business business = new Business();
		business.setId(id);
		business.setUserName(userName);
		return businessDao.getBusinessByUserName(business);

	}
}
