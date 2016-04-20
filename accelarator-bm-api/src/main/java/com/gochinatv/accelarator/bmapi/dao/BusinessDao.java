package com.gochinatv.accelarator.bmapi.dao;

import java.util.List;

import com.gochinatv.accelarator.bmapi.bean.Business;


/**
 * 
 * @描述   商家账号数据库接口层
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface BusinessDao{

public List<Business> queryList(Business business);
	
	/**
	 * 根据用户名获取当前登录用户
	 * @param userName
	 * @return
	 */
	public Business getLoginBusiness(String userName);

}
