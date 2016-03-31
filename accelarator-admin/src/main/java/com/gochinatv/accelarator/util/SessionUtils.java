package com.gochinatv.accelarator.util;

import com.gochinatv.accelarator.dao.entity.User;

public class SessionUtils {
    
	
	public static User getLoginUser(){
		User user = new User();
		user.setId(1);
		user.setUserName("vrs");
		return user;
	}
}
