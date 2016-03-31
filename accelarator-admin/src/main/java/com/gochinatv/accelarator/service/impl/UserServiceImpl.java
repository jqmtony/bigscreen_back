package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.dao.UserDao;
import com.gochinatv.accelarator.dao.entity.User;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.UserService;

/**
 * 
 * @作者 zhuhh
 * @描述   运维账号实体业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	protected BaseDao<User> getDao() {
		return userDao;
	}

	public User getUserByUserName(int id, String userName) {
		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		return userDao.getUserByUserName(user);
	}
	
	
}
