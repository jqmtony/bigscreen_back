package com.gochinatv.accelarator.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.gochinatv.accelarator.dao.entity.User;

public class SessionUtils {
    
	
	public static final String LOGIN_KEY = "loginUser";
	
	
	
	public static User getLoginUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(LOGIN_KEY);
		return user;
	}
}
