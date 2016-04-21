package com.gochinatv.accelarator.bmapi.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.bmapi.bean.Business;
import com.gochinatv.accelarator.bmapi.dao.BusinessDao;
import com.gochinatv.accelarator.bmapi.service.BusinessService;


/**
 * 
 * @描述   商家账号业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private BusinessDao businessDao;
	
	/**
	 * 根据用户名获取当前登录用户
	 * @param userName
	 * @return
	 */
	@Override
	public Business getLoginBusiness(String userName) {
		return businessDao.getLoginBusiness(userName);
	}
	
}
