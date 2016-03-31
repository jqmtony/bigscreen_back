package com.gochinatv.accelarator.dao;

import com.gochinatv.accelarator.dao.entity.Business;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;


/**
 * 
 * @作者 zhuhh
 * @描述   商家账号数据库接口层
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface BusinessDao extends BaseDao<Business>{

	/**
	 * 校验用户名唯一性
	 * @author limr
	 * @param business
	 * @return
	 */
	Business getBusinessByUserName(Business business);
	   

}
