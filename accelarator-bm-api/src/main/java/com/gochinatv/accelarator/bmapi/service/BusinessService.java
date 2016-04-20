package com.gochinatv.accelarator.bmapi.service;

import com.gochinatv.accelarator.bmapi.bean.Business;


/**
 * 
 * @描述   商家账号业务层接口
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface BusinessService{

	/**
	 * 根据用户名获取当前登录用户
	 * @param userName
	 * @return
	 */
	Business getLoginBusiness(String userName);
	   

}
