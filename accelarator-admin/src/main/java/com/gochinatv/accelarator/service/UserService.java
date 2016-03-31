package com.gochinatv.accelarator.service;

import com.gochinatv.accelarator.dao.entity.User;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;


/**
 * 
 * @作者 zhuhh
 * @描述   运维账号实体业务层接口
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface UserService  extends BaseService<User>{

	/**
	 * 校验用户名唯一性
	 * @author limr
	 * @param id
	 * @param userName
	 * @return
	 */
	User getUserByUserName(int id, String userName);

}
